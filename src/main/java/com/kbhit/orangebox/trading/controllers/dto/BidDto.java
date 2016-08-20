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

    public void setRequestedItems(Set<ItemDto> requestedItems) {
        this.requestedItems = requestedItems;
    }

    public Set<ItemDto> getOfferedItems() {
        return offeredItems;
    }

    public void setOfferedItems(Set<ItemDto> offeredItems) {
        this.offeredItems = offeredItems;
    }

}
