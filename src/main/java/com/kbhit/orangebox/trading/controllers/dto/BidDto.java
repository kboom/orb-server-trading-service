package com.kbhit.orangebox.trading.controllers.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class BidDto {

    private BidderDto placingBidder;

    private BidderDto respondingBidder;

    @Valid
    @NotNull
    @Size(min = 1)
    private Set<ItemDto> requestedItems;

    @Valid
    @NotNull
    @Size(min = 1)
    private Set<ItemDto> offeredItems;

    public BidderDto getPlacingBidder() {
        return placingBidder;
    }

    public void setPlacingBidder(BidderDto placingBidder) {
        this.placingBidder = placingBidder;
    }

    public Set<ItemDto> getRequestedItems() {
        return requestedItems;
    }

    public BidDto setRequestedItems(Set<ItemDto> requestedItems) {
        this.requestedItems = requestedItems;
        return this;
    }

    public Set<ItemDto> getOfferedItems() {
        return offeredItems;
    }

    public BidDto setOfferedItems(Set<ItemDto> offeredItems) {
        this.offeredItems = offeredItems;
        return this;
    }

    public BidderDto getRespondingBidder() {
        return respondingBidder;
    }

    public void setRespondingBidder(BidderDto respondingBidder) {
        this.respondingBidder = respondingBidder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidDto bidDto = (BidDto) o;

        if (requestedItems != null ? !requestedItems.equals(bidDto.requestedItems) : bidDto.requestedItems != null)
            return false;
        return offeredItems != null ? offeredItems.equals(bidDto.offeredItems) : bidDto.offeredItems == null;

    }

    @Override
    public int hashCode() {
        int result = requestedItems != null ? requestedItems.hashCode() : 0;
        result = 31 * result + (offeredItems != null ? offeredItems.hashCode() : 0);
        return result;
    }
}
