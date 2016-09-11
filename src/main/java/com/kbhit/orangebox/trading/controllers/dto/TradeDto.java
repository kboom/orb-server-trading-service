package com.kbhit.orangebox.trading.controllers.dto;

import java.util.List;

public class TradeDto {

    private String id;

    private BidDto initialBid;

    private BidDto latestBid;

    private List<BidDto> historicBids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BidDto getInitialBid() {
        return initialBid;
    }

    public void setInitialBid(BidDto initialBid) {
        this.initialBid = initialBid;
    }

    public BidDto getLatestBid() {
        return latestBid;
    }

    public void setLatestBid(BidDto latestBid) {
        this.latestBid = latestBid;
    }

    public List<BidDto> getHistoricBids() {
        return historicBids;
    }

    public void setHistoricBids(List<BidDto> historicBids) {
        this.historicBids = historicBids;
    }
}
