package com.sergiovanovi.repository;

import com.sergiovanovi.model.Meter;

import java.util.Collection;

public interface MeterRepository {

    Meter save(Meter meter);

    //ORDERED dateTime DESC
    Collection<Meter> getAll();

    //get last meter
    Meter getLast();
}
