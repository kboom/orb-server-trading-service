package com.kbhit.orangebox.trading.controllers.dto;

public class ItemDto {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDto itemDto = (ItemDto) o;

        return id != null ? id.equals(itemDto.id) : itemDto.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public ItemDto setName(String name) {
        this.name = name;
        return this;
    }
}
