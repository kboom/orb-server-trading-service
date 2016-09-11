package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ItemId implements Serializable {

    private String itemId;

    private ItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemId itemId = (ItemId) o;

        return this.itemId != null ? this.itemId.equals(itemId.itemId) : itemId.itemId == null;

    }

    @Override
    public int hashCode() {
        return itemId != null ? itemId.hashCode() : 0;
    }

    public static ItemId itemId(String id) {
        return new ItemId(id);
    }

    @SuppressWarnings("unused")
    public ItemId() {
    }

    public String rawValue() {
        return itemId;
    }

}
