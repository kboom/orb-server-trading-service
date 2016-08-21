package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ItemId implements Serializable {

    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemId itemId = (ItemId) o;

        return id != null ? id.equals(itemId.id) : itemId.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    ItemId(String id) {
        this.id = id;
    }

    public static ItemId itemId(String id) {
        return new ItemId(id);
    }

}
