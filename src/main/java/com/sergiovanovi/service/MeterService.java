package com.sergiovanovi.service;

import com.sergiovanovi.model.Meter;

import java.util.Collection;

public interface MeterService {

    Meter save(Meter meter);

    //ORDERED dateTime
    Collection<Meter> getAll();

    //get last meter
    Meter getLast();
}
