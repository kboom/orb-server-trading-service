package com.kbhit.orangebox.trading.behaviour

import com.kbhit.orangebox.trading.TradingApplication
import com.kbhit.orangebox.trading.config.StandaloneConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("dev")
@SpringBootTest(classes = [TradingApplication.class, StandaloneConfig.class],
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class BehaviourSpecification extends Specification {

}
