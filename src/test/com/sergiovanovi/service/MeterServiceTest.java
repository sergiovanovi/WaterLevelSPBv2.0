package com.sergiovanovi.service;

import com.sergiovanovi.model.Meter;
import mock.MockMeterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.Assert.*;

public class MeterServiceTest extends AbstractServiceTest{

    @Autowired
    private MeterService meterService;

    @Test
    public void testSaveAndGetLast() throws Exception {
        Meter expected = meterService.save(new Meter(99, LocalDateTime.of(2018, 3, 5, 10, 0)));
        meterService.save(expected);
        meterService.save(new Meter(60));//default current timestamp
        Meter actual = meterService.getLast();
        assertTrue(expected.toString().equals(actual.toString()));
        assertFalse(expected.toString().equals(MockMeterService.meters.get(0).toString()));
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meter> actual =  meterService.getAll();
        actual.forEach(meter -> meter.setId(0));
        assertEquals(MockMeterService.meters.toString(), actual.toString());
    }
}