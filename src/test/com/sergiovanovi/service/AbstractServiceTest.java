package com.sergiovanovi.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract public class AbstractServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractServiceTest.class);

    private static StringBuilder results = new StringBuilder();

    @Autowired
    public Environment env;

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-95s %7d", description.getDisplayName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            LOG.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResult() {
        LOG.info("\n--------------------------------------------------------------------------------------------------------------" +
                "\nTest                                                                                               Duration, ms" +
                "\n---------------------------------------------------------------------------------------------------------------\n" +
                results +
                "---------------------------------------------------------------------------------------------------------------\n");
        results.setLength(0);
    }
}
