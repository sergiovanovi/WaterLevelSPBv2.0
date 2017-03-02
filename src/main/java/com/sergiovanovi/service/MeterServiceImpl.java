package com.sergiovanovi.service;

import com.sergiovanovi.model.Meter;
import com.sergiovanovi.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MeterServiceImpl implements MeterService{

    @Autowired
    private MeterRepository meterRepository;

    @Override
    public void save(Meter meter) {
        meterRepository.save(meter);
    }

    @Override
    public Collection<Meter> getAll() {
        return meterRepository.getAll();
    }

    @Override
    public Meter getLast() {
        return meterRepository.getLast();
    }
}
