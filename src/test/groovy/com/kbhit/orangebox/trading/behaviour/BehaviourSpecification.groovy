package com.kbhit.orangebox.trading.behaviour

import com.kbhit.orangebox.trading.TestDataLoader
import com.kbhit.orangebox.trading.TradingApplication
import com.kbhit.orangebox.trading.config.DummiesConfig
import com.kbhit.orangebox.trading.config.StandaloneConfig
import com.kbhit.orangebox.trading.config.TestServicesConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("dev")
@SpringBootTest(classes = [TradingApplication.class, DummiesConfig.class,
        StandaloneConfig.class, TestServicesConfig.class],
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class BehaviourSpecification extends Specification {

    private static boolean testDataLoaded = false;

    @Autowired
    private TestDataLoader testDataLoader

    def setup() {
        if (!testDataLoaded) {
            loadTestData(testDataLoader)
            testDataLoaded = true;
        }
    }

    protected void loadTestData(TestDataLoader testDataLoader) {

    }

}
