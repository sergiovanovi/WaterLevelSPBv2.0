package com.sergiovanovi.service;

import com.sergiovanovi.model.Meter;
import com.sergiovanovi.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MeterServiceImpl implements MeterService{

    @Autowired
    private MeterRepository meterRepository;

    @Override
    public Meter save(Meter meter) {
        Assert.notNull(meter,"meter must be not null");
        return meterRepository.save(meter);
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
