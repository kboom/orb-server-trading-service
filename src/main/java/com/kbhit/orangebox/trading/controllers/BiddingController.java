package com.kbhit.orangebox.trading.controllers;

import com.kbhit.orangebox.trading.controllers.dto.BidDto;
import com.kbhit.orangebox.trading.controllers.mapping.ObjectConverter;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.service.BiddingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @Autowired
    private ObjectConverter objectConverter;

    @ApiOperation(value = "postInitialBid")
    @RequestMapping(value = "/bids", method = POST)
    @ResponseBody
    public ResponseEntity<BidDto> postInitialBid(@RequestBody BidDto bidDto) {
        Bid bid = objectConverter.toDomain(bidDto, Bid.class);
        Trade trade = biddingService.createTradeFor(bid);
        return new ResponseEntity<>(objectConverter.toTransfer(trade.getInitialBid(), BidDto.class), HttpStatus.OK);
    }

    @ApiOperation(value = "postBid")
    @RequestMapping(value = "/trades/{tradeId}/bids", method = POST)
    @ResponseBody
    public ResponseEntity<BidDto> postBid(@RequestBody BidDto bidDto) {
        Bid bid = objectConverter.toDomain(bidDto, Bid.class);
        Trade trade = biddingService.postBid(bid);
        return new ResponseEntity<>(objectConverter.toTransfer(trade.getLatestBid(), BidDto.class), HttpStatus.OK);
    }


}
