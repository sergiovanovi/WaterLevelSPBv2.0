package com.sergiovanovi.util;

import com.sergiovanovi.model.Meter;
import com.sergiovanovi.model.User;
import com.sergiovanovi.service.MeterService;
import com.sergiovanovi.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;


import static org.slf4j.LoggerFactory.getLogger;

public class MeterParserAndMailSender {

    @Autowired
    private MeterService meterService;

    @Autowired
    private UserService userService;

    private static final Logger LOG = getLogger("application");

    @Scheduled(fixedDelay = 7200000) //2hour
    public void parsMeter() {

        try {
            URL url = new URL("http://spun.fkpkzs.ru/Level/Gorny");

            try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()))){
                String stringMeter;

                //TODO Make it easier and ask the measurement provider for a direct link
                do {
                    stringMeter = reader.readLine();
                    if (reader.getLineNumber() == 265) {
                        stringMeter = stringMeter.trim();
                        stringMeter = stringMeter.substring(stringMeter.indexOf("<span>") + 6, stringMeter.indexOf("</span>"));
                        double waterLevel = Double.parseDouble(stringMeter);

                        Meter meter = new Meter();
                        meter.setDateTime(LocalDateTime.now());
                        meter.setLevel(waterLevel);
                        meterService.save(meter);

                        //checkMeter(waterLevel);
                        LOG.info(LocalDateTime.now() + " Measurement is read and verified");
                        break;
                    }
                } while (stringMeter != null);

            } catch (IOException e) {
                LOG.error(LocalDateTime.now() + " Can not open a stream");
            }

        } catch (MalformedURLException e) {
            LOG.error(LocalDateTime.now() + " Can not open a URL");
        }
    }

    public void checkMeter(double level) {
        String message = "Current level " + level + " cm." + "\n" + "More info here http://www.pasp.ru/op-info-weather?mode=current";

        List<User> listUsers = (List<User>) userService.getAll();
        for (User user : listUsers) {
            if (user.isEnabled()) {
                int util = user.getUtil();
                double min = user.getMin();
                double max = user.getMax();
                String email = user.getEmail();

                if (level > max && util != 1 && sendEmail(email, "The water level in the port of St. Petersburg is higher than " + user.getMax(), message)) {
                    user.setUtil(1);
                    userService.save(user);
                } else if (level < min && util != -1 && sendEmail(email, "The water level in the port of St. Petersburg is below " + user.getMin(), message)) {
                    user.setUtil(-1);
                    userService.save(user);
                } else if (level <= max && level >= min && util != 0 && sendEmail(email, "The water level in the port of St. Petersburg ranges from " + user.getMin() + " to " + user.getMax(), message)) {
                    user.setUtil(0);
                    userService.save(user);
                }
            }
        }
    }

    public static boolean sendEmail(String email, String title, String message) {
        String from = "waterlevelinfospb@mail.ru";
        String username = "waterlevelinfospb@mail.ru";
        String password = "Assword11";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.mail.ru");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipients(Message.RecipientType.TO, email);
            mimeMessage.setSubject(title);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
            LOG.info(LocalDateTime.now() + "  Send email to " + email +" successfully");
            return true;
        } catch (MessagingException e) {
            LOG.info(LocalDateTime.now() + " Sending email failed");
            return false;
        }
    }
}
