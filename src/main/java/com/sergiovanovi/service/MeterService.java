package com.sergiovanovi.service;

import com.sergiovanovi.model.Meter;

import java.util.Collection;

public interface MeterService {

    Meter save(Meter meter);

    //ORDERED dateTime
    Collection<Meter> getAll();

    //ORDERED dateTime DESC
    Collection<Meter> getAllDesc();

    //get last meter
    Meter getLast();
}
