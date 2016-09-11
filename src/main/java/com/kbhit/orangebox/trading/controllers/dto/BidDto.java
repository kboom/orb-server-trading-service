package com.kbhit.orangebox.trading.controllers.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class BidDto {

    @Valid
    @NotNull
    @Size(min = 1)
    private Set<ItemDto> requestedItems;

    @Valid
    @NotNull
    @Size(min = 1)
    private Set<ItemDto> offeredItems;

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

}
