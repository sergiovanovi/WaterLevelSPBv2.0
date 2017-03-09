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

    @Scheduled(fixedDelay = 18000000) //4hour
    public void parsMeter() {

        LineNumberReader reader = null;
        try {
            URL url = new URL("http://www.pasp.ru/op-info-weather?mode=current");
            try {
                reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String stringMeter = reader.readLine();

                while (stringMeter != null) {
                    if (reader.getLineNumber() == 184) {
                        stringMeter = stringMeter.trim();
                        stringMeter = stringMeter.substring(stringMeter.indexOf(":") + 2, stringMeter.lastIndexOf(" "));
                        double waterLevel = Double.parseDouble(stringMeter);
                        Meter meter = new Meter();
                        meter.setDateTime(LocalDateTime.now());
                        meter.setLevel(waterLevel);
                        meterService.save(meter);
                        checkMeter(waterLevel);
                        break;
                    }
                    stringMeter = reader.readLine();
                }
            } catch (IOException e) {
                LOG.error("can not open a stream");
            } finally {
                if (reader != null) try {
                    reader.close();
                } catch (IOException e) {
                    LOG.error("can not close a stream");
                }
            }
        } catch (MalformedURLException e) {
            LOG.error("web-site does not respond");
        }
    }

    public void checkMeter(double level) {
        double meter = level;
        List<User> listUsers = (List<User>) userService.getAll();

        for (User user : listUsers) {
            double util = user.getUtil();
            double min = user.getMin();
            double max = user.getMax();
            String email = user.getEmail();

            if (meter > max && util != 1) {
                try {
                    sendEmail(email, "The water level in the port of St. Petersburg is higher than " + user.getMax(), meter);
                    user.setUtil(3);
                    userService.save(user);
                    LOG.info("Send email successfully");
                } catch (javax.mail.MessagingException e) {
                    LOG.error("Sending email fail");
                }
            } else if (meter < min && util != -1) {
                try {
                    sendEmail(email, "The water level in the port of St. Petersburg is below " + user.getMin(), meter);
                    user.setUtil(1);
                    userService.save(user);
                    LOG.info("Send email successfully");
                } catch (javax.mail.MessagingException e) {
                    LOG.error("Sending email fail");
                }
            } else if (meter <= max && meter >= min && util != 0) {
                try {
                    sendEmail(email, "The water level in the port of St. Petersburg ranges from " + user.getMin() + " to " + user.getMax(), meter);
                    user.setUtil(2);
                    userService.save(user);
                    LOG.info("Send email successfully");
                } catch (javax.mail.MessagingException e) {
                    LOG.error("Sending email fail");
                }
            }
        }
    }

    public void sendEmail(String email, String message, double meter) throws javax.mail.MessagingException {
        String from = "waterlevelinfospb@mail.ru";
        String to = email;
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
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipients(Message.RecipientType.TO, to);
        mimeMessage.setSubject(message);
        mimeMessage.setText(message + ".\n" + "Current level " + meter + " cm." + "\n" + "More info here http://www.pasp.ru/op-info-weather?mode=current");
        Transport.send(mimeMessage);
    }
}
