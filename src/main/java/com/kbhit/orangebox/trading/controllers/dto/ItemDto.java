package com.kbhit.orangebox.trading.controllers.dto;

public class ItemDto {

    private String itemId;
    private String name;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDto itemDto = (ItemDto) o;

        return itemId != null ? itemId.equals(itemDto.itemId) : itemDto.itemId == null;

    }

    @Override
    public int hashCode() {
        return itemId != null ? itemId.hashCode() : 0;
    }

    public ItemDto setName(String name) {
        this.name = name;
        return this;
    }
}
