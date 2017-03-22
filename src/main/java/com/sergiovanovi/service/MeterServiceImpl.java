package com.sergiovanovi.service;

import com.sergiovanovi.model.Meter;
import com.sergiovanovi.repository.MeterRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class MeterServiceImpl implements MeterService{

    private static final Logger LOG = getLogger("application");

    @Autowired
    private MeterRepository meterRepository;

    @Override
    public Meter save(Meter meter) {
        Meter savedMeter = meterRepository.save(meter);
        if (savedMeter != null) {
            LOG.info(LocalDateTime.now() + " meter successfully SAVED: " + meter);
        } else {
            LOG.error(LocalDateTime.now() + " meter NOT SAVED: " + meter);
        }
        return savedMeter;
    }

    @Override
    public Collection<Meter> getAll() {
        List<Meter> meters = (List<Meter>) meterRepository.getAll();
        Collections.reverse(meters);
        return meters;
    }

    @Override
    public Meter getLast() {
        return meterRepository.getLast();
    }
}
