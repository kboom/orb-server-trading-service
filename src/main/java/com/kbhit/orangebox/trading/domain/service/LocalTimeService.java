package com.kbhit.orangebox.trading.domain.service;

import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

public class LocalTimeService implements TimeService {

    @Override
    public ReadableDateTime getCurrentTime() {
        return DateTime.now();
    }

}
