package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.TestDataLoader;
import com.kbhit.orangebox.trading.TradingApplication;
import com.kbhit.orangebox.trading.config.StandaloneConfig;
import com.kbhit.orangebox.trading.config.TestServicesConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = {TradingApplication.class, StandaloneConfig.class, TestServicesConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class DomainTest {

    private static boolean testDataLoaded = false;

    @Autowired
    private TestDataLoader testDataLoader;

    @Before
    public void before() {
        if (!testDataLoaded) {
            loadTestData(testDataLoader);
            testDataLoaded = true;
        }
    }

    protected void loadTestData(TestDataLoader testDataLoader) {

    }

}
