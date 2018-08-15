package netty_time_server_pojo;

import netty_time_server.Constant;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date(value() * 1000L));
    }

}
