package com.kbhit.orangebox.trading.controllers;

import com.kbhit.orangebox.trading.controllers.dto.TradeDto;
import com.kbhit.orangebox.trading.domain.repository.TradeRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradingController {

    @Autowired
    private TradeRepository tradeRepository;

    @ApiOperation(value = "getSingleTrade")
    @RequestMapping("/trades/{tradeId}")
    @ResponseBody
    public ResponseEntity<TradeDto> getTrade(@PathVariable String tradeId) {
//        Trade trade = tradeRepository.findTradeById(referenceTrade(tradeId));
        return new ResponseEntity<>(new TradeDto(), HttpStatus.OK);
    }

}
