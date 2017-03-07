package com.sergiovanovi.util;

import com.sergiovanovi.model.Meter;
import com.sergiovanovi.service.MeterService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;

public class MeterParser {

    @Autowired
    private MeterService meterService;
    private static final Logger LOG = getLogger(MeterParser.class);

    @Scheduled(fixedDelay = 3600000) //1hour
    public void parsMeter() {

        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("util.properties");
            prop.load(input);
            LOG.info(prop.getProperty("url"));
            LOG.info(prop.getProperty("line"));
            LOG.info(prop.getProperty("startIndex"));
        } catch (IOException ex) {
            LOG.error("can not open a util.properties");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("can not open a util.properties");
                }
            }
        }

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
}
