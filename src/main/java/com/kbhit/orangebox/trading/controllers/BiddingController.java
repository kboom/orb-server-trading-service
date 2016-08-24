package com.kbhit.orangebox.trading.controllers;

import com.google.common.collect.Lists;
import com.kbhit.orangebox.trading.controllers.dto.BidDto;
import com.kbhit.orangebox.trading.controllers.dto.ItemDto;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Item;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.UserContextService;
import com.kbhit.orangebox.trading.domain.service.BiddingService;
import com.kbhit.orangebox.trading.domain.service.StorageService;
import com.kbhit.orangebox.trading.domain.service.TimeService;
import io.swagger.annotations.ApiOperation;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

import static com.kbhit.orangebox.trading.domain.Bid.buildBid;
import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BiddingController {

    @Autowired
    private BiddingService biddingService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private UserContextService userContextService;

    @Autowired
    private Mapper mapper;

    @ApiOperation(value = "postInitialBid")
    @RequestMapping(value = "/bids", method = POST)
    @ResponseBody
    public ResponseEntity<BidDto> postInitialBid(@RequestBody BidDto bidDto) {
        Bid bid = constructBid(bidDto);
        Trade trade = biddingService.createTradeFor(bid);
        return new ResponseEntity<>(mapper.map(trade.getInitialBid(), BidDto.class), HttpStatus.OK);
    }

    @ApiOperation(value = "postBid")
    @RequestMapping(value = "/trades/{tradeId}/bids", method = POST)
    @ResponseBody
    public ResponseEntity<BidDto> postBid(@PathVariable("tradeId") String tradeId, @RequestBody BidDto bidDto) {
        Bid bid = constructBid(bidDto);
        Trade trade = biddingService.postBidFor(referenceTrade(tradeId), bid);
        return new ResponseEntity<>(mapper.map(trade.getLatestBid(), BidDto.class), HttpStatus.OK);
    }

    private Bid constructBid(BidDto bidDto) {
        return buildBid()
                .withBidder(userContextService.getBiddingUser())
                .withPlaceDate(timeService.getCurrentTime())
                .withOfferedItems(collectItems(bidDto.getOfferedItems()))
                .withRequestedItems(collectItems(bidDto.getRequestedItems()))
                .build();
    }

    private Collection<Item> collectItems(Set<ItemDto> items) {
        return storageService.getItemsById(Lists.transform(Lists.newArrayList(items), ItemDto::getId));
    }

}
