package mock;

import com.sergiovanovi.model.Meter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MockMeterService {
    public static List<Meter> meters = Arrays.asList(
                new Meter(0, LocalDateTime.of(2017, 3, 1, 10, 0)),
                new Meter(12, LocalDateTime.of(2017, 3, 1, 18, 0)),
                new Meter(35, LocalDateTime.of(2017, 3, 2, 11, 0)),
                new Meter(0, LocalDateTime.of(2017, 3, 2, 19, 0)),
                new Meter(-12, LocalDateTime.of(2017, 3, 3, 12, 0)),
                new Meter(-41, LocalDateTime.of(2017, 3, 3, 20, 0)),
                new Meter(0, LocalDateTime.of(2017, 3, 4, 13, 0))
        );
}
