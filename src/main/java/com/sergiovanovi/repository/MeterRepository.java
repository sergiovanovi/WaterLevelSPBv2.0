package com.sergiovanovi.repository;

import com.sergiovanovi.model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface MeterRepository extends JpaRepository<Meter, Integer>{

    @Override
    Meter save(Meter meter);

    Meter findLastByDateTime();

    @Override
    List<Meter> findAll();

}
