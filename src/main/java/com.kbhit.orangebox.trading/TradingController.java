package com.kbhit.orangebox.trading;

import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.repository.TradeRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;

@RestController
public class TradingController {

    @Autowired
    private TradeRepository tradeRepository;

    @ApiOperation(value = "getSingleTrade")
    @RequestMapping("/trades/{tradeId}")
    @ResponseBody
    public Trade getTrade(@PathVariable String tradeId) {
        return tradeRepository.findTradeById(referenceTrade(tradeId));
    }

}
