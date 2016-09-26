package com.kbhit.orangebox.trading.stubs;

import com.kbhit.orangebox.trading.domain.service.TimeService;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

public class ConfigurableTimeService implements TimeService {

    private ReadableDateTime time = DateTime.now();

    @Override
    public ReadableDateTime getCurrentTime() {
        return time;
    }

    public void setCurrentTime(ReadableDateTime time) {
        this.time = new DateTime(time);
    }

}
