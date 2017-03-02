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
    public void save(Meter meter) {
        crudMeterRepository.save(meter);
    }

    @Override
    public Collection<Meter> getAll() {
        return crudMeterRepository.findAll();
    }

    @Override
    public Meter getLast() {
        return crudMeterRepository.findLastByDateTime();
    }
}
