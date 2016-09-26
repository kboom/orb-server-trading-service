package com.kbhit.orangebox.trading;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = TradingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class IntegrationTest {

}
