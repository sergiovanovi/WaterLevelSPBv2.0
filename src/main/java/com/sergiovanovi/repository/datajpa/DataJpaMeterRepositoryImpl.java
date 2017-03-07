package com.sergiovanovi.repository.datajpa;

import com.sergiovanovi.model.Meter;
import com.sergiovanovi.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DataJpaMeterRepositoryImpl implements MeterRepository{

    @Autowired
    private CRUDMeterRepository crudMeterRepository;

    @Override
    public Meter save(Meter meter) {
        return crudMeterRepository.save(meter);
    }

    @Override
    public Collection<Meter> getAll() {
        return crudMeterRepository.getAll();
    }

    @Override
    public Collection<Meter> getAllDesc() {
        return crudMeterRepository.getAllDesc();
    }

    @Override
    public Meter getLast() {
        return crudMeterRepository.getLast();
    }
}
