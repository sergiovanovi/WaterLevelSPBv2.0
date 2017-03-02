package com.sergiovanovi.repository;

import com.sergiovanovi.model.Meter;

import java.util.Collection;

public interface MeterRepository {

    void save(Meter meter);

    //ORDERED dateTime
    Collection<Meter> getAll();

    //get last meter
    Meter getLast();
}
