package com.kbhit.orangebox.trading.controllers;

import com.google.common.collect.Lists;
import com.kbhit.orangebox.trading.controllers.dto.BidDto;
import com.kbhit.orangebox.trading.controllers.dto.ItemDto;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.BidderService;
import com.kbhit.orangebox.trading.domain.CounterParties;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.repository.TradeRepository;
import com.kbhit.orangebox.trading.domain.service.*;
import com.kbhit.orangebox.trading.security.AuthoritiesConstants;
import io.swagger.annotations.ApiOperation;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.kbhit.orangebox.trading.domain.Bid.buildBidFor;
import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class BiddingController {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private BiddingService biddingService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private BiddingContextService userContextService;

    @Autowired
    private BidderService bidderService;

    @Autowired
    private Mapper mapper;

    @ApiOperation(value = "postInitialBid")
    @RequestMapping(value = "/bids", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.USER)
    @ResponseBody
    public ResponseEntity<BidDto> postInitialBid(@RequestBody BidDto bidDto) {
        Trade trade = biddingService.createTradeBetween(getCounterParties(bidDto));
        trade.makeBid(createBidFor(trade, bidDto));
        return new ResponseEntity<>(mapper.map(trade.getInitialBid(), BidDto.class), HttpStatus.OK);
    }

    @ApiOperation(value = "postResponseBid")
    @RequestMapping(value = "/trades/{tradeId}/bids", method = POST)
    @Secured(AuthoritiesConstants.USER)
    @ResponseBody
    public ResponseEntity<BidDto> postBid(@PathVariable("tradeId") String tradeId, @RequestBody BidDto bidDto) {
        Trade trade = tradeRepository.findTradeById(referenceTrade(tradeId));
        trade.makeBid(createBidFor(trade, bidDto));
        return new ResponseEntity<>(mapper.map(trade.getLatestBid(), BidDto.class), HttpStatus.OK);
    }

    private Bid createBidFor(Trade trade, BidDto bidDto) {
        return buildBidFor(trade)
                .withBidder(userContextService.getBiddingUser())
                .withPlaceDate(timeService.getCurrentTime())
                .withOfferedItems(collectStoredItems(bidDto.getOfferedItems()))
                .withRequestedItems(collectStoredItems(bidDto.getRequestedItems()))
                .build();
    }

    private CounterParties getCounterParties(@RequestBody BidDto bidDto) {
        return bidderService.resolveCounterParties(
                bidDto.getPlacingBidder().getLogin(),
                bidDto.getRespondingBidder().getLogin());
    }

    private Collection<Item> collectStoredItems(Collection<ItemDto> items) {
        return storageService.getItemsById(Lists.transform(Lists.newArrayList(items), ItemDto::getItemId));
    }

}
