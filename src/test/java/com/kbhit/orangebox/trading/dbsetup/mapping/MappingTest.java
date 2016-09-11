package com.kbhit.orangebox.trading.dbsetup.mapping;

import com.kbhit.orangebox.trading.config.MappingConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MappingConfiguration.class})
abstract class MappingTest {

}
