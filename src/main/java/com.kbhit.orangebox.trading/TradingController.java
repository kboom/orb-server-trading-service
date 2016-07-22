package com.kbhit.orangebox.trading;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradingController {

    @ApiOperation(value = "test", nickname = "testNickname")
    @RequestMapping("/greet")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
