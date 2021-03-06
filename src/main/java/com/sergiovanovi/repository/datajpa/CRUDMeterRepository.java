package com.sergiovanovi.repository.datajpa;

import com.sergiovanovi.model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CRUDMeterRepository extends JpaRepository<Meter, Integer>{

    @Override
    @Transactional
    Meter save(Meter meter);

    @Query(value = "SELECT * FROM meters ORDER BY date_time DESC LIMIT 1", nativeQuery = true)
    Meter getLast();

    //last 7 days 84
    @Query(value = "SELECT * FROM meters ORDER BY date_time DESC LIMIT 84", nativeQuery = true)
    List<Meter> getAll();
}
