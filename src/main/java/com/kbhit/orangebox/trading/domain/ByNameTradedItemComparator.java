package com.kbhit.orangebox.trading.domain;

import java.util.Comparator;

class ByNameTradedItemComparator implements Comparator<TradedItem> {

    @Override
    public int compare(TradedItem firstItem, TradedItem secondItem) {
        String firstName = firstItem.getName();
        String secondName = secondItem.getName();
        if (firstName != null && secondName != null) {
            return firstName.compareTo(secondName);
        } else {
            return firstItem.getItemId().rawValue()
                    .compareTo(secondItem.getItemId().rawValue());
        }
    }

}
