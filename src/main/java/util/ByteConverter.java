package util;

import java.math.BigInteger;

public class ByteConverter {
    private static final int UNSIGNED_FACTOR = 0xFF;

    private static final int BYTE_MOVER = 8;

    public static BigInteger toNumber(byte[] bytes) {
        return new BigInteger(bytes);
    }

    public static long toLong(byte[] bytes) {
        return toNumber(bytes).longValue();
    }

    public static int toInt(byte aByte) {
        return aByte & UNSIGNED_FACTOR;
    }
}
