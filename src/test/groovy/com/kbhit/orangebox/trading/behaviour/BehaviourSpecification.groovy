package com.kbhit.orangebox.trading.behaviour;

import com.kbhit.orangebox.trading.TradingApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import spock.lang.Specification;

@ActiveProfiles("dev")
@SpringBootTest(classes = TradingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class BehaviourSpecification extends Specification {

}
