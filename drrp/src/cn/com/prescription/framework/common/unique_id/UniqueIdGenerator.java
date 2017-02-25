package cn.com.prescription.framework.common.unique_id;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UniqueIdGenerator {

    public UniqueIdGenerator() {
    }

    public static synchronized String getUniqueId() {
        Date now = new Date();
        String dateFormat = Long.toString(Long.parseLong(datePattern.format(now)), 36);
        String sequence = sequencer();
        return dateFormat.concat(sequence);
    }

    private static String sequencer() {
        if (seq == max)
            seq = 0;
        else
            seq++;
        if (seq < 36)
            return "0".concat(Integer.toString(seq, 36));
        else
            return Integer.toString(seq, 36);
    }

    private static final int max;
    private static volatile int seq;
    private static SimpleDateFormat datePattern = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    static {
        max = Integer.parseInt("zz", 36);
        seq = (new Random()).nextInt(max);
    }
}
