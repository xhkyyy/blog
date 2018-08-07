package time_2038_problem;

import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

/**
 * Year 2038 problem
 * https://en.wikipedia.org/wiki/Year_2038_problem
 */
public class Time2038ProblemTest {

    private static final TimeZone utc = TimeZone.getTimeZone("UTC");

    public static final FastDateFormat UTC_TIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss", utc);

    @Test
    public void test() {

        System.out.println("\n\n-----------------");

        System.out.println(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date(0)));

        // 32 bit 标示的最大正整数
        // 0111 1111 1111 1111 1111 1111 1111 1111
        double max = Math.pow(2, 31) - 1;
        System.out.println("max: " + max);

        long time = (new Date(0).getTime() + (long) max) * 1000;

        System.out.println("time: " + time);

        System.out.println("GMT:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date(time)));

        System.out.println("UTC: " + UTC_TIME_FORMAT.format(new Date(time)));

        System.out.println("-----------------\n\n");

    }

    @Test
    public void test41() {

        System.out.println("\n\n-----------------");

        double max = Math.pow(2, 41) - 1;
        System.out.println("max: " + max);

        long time = (new Date(0).getTime() + (long) max) * 1000;

        System.out.println("GMT:" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(new Date(time)));

        System.out.println("-----------------\n\n");

    }

}