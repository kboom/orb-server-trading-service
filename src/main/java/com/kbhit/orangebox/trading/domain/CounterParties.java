package com.kbhit.orangebox.trading.domain;

public class CounterParties {

    private Bidder requester;

    private Bidder responder;

    public CounterParties(Bidder requester, Bidder responder) {
        this.requester = requester;
        this.responder = responder;
    }

    public Bidder getRequester() {
        return requester;
    }

    public Bidder getResponder() {
        return responder;
    }

}
