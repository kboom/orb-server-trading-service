package com.kbhit.orangebox.trading.domain;

public class IllegalBidderException extends RuntimeException {

    public IllegalBidderException(Trade trade, Bidder bidder) {
    }

}
