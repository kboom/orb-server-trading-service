package com.kbhit.orangebox.trading.controllers;

import com.kbhit.orangebox.trading.controllers.dto.TradeDto;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.repository.TradeRepository;
import com.kbhit.orangebox.trading.security.AuthoritiesConstants;
import io.swagger.annotations.ApiOperation;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.kbhit.orangebox.trading.controllers.utils.ResourceResponseBuilder.aResourceResponse;
import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TradingController {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private Mapper mapper;

    @ApiOperation(value = "getSingleTrade")
    @RequestMapping(value = "/trades/{tradeId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<TradeDto> getTrade(@PathVariable String tradeId) {
        Trade trade = tradeRepository.findTradeById(referenceTrade(tradeId));
        return aResourceResponse(TradeDto.class)
                .withResource(mapper.map(trade, TradeDto.class))
                .build();
    }

}
