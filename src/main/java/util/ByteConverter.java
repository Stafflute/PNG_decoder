package util;

import java.math.BigInteger;

public class ByteConverter {
    private static final int MSB_FACTOR = 256;
    private static final int BYTE_MAX_NUM = 256;

    private static final int BYTE_LONG_LENGTH = 4;

    private static final int BYTE_SHORT_LENGTH = 2;

    private static final int UNSIGNED_FACTOR = 0xFF;

    private static final long MSB_PLACE_LONG = 0xFF000000;

    private static final long MSB_PLACE_SHORT = 0x0000FF00;

    private static final int BYTE_MOVER = 8;

    private static final int TO_SHIFT_RIGHT_LONG = BYTE_MOVER * 3;

    private static final int TO_SHIFT_RIGHT_SHORT = BYTE_MOVER;

    public static BigInteger toNumber(byte[] bytes) {
        return new BigInteger(bytes);
    }

    public static long toLong(byte[] bytes) {
        return toNumber(bytes).longValue();
    }

    public static int toInt(byte aByte) {
        return aByte & UNSIGNED_FACTOR;
    }

    public static byte[] to4Byte(long aLong) {
        byte[] result = new byte[BYTE_LONG_LENGTH];

        for(int i = 0; i < result.length; i++) {
            int toShiftLeft = i * BYTE_MOVER;
            long partialResult = (aLong << toShiftLeft) & MSB_PLACE_LONG;
            result[i] = (byte) (partialResult >> TO_SHIFT_RIGHT_LONG);
        }

        return  result;
    }

    public static byte[] to2Byte(long aLong) {
        byte[] result = new byte[BYTE_SHORT_LENGTH];

        for(int i = 0; i < result.length; i++) {
            int toShiftLeft = i * BYTE_MOVER;
            long partialResult = (aLong << toShiftLeft) & MSB_PLACE_SHORT;
            result[i] = (byte) (partialResult >> TO_SHIFT_RIGHT_SHORT);
        }

        return  result;
    }

    public static byte[] unboxByte(Byte[] boxedBytes) {
        byte[] result = new byte[boxedBytes.length];

        for(int i = 0; i < result.length; i++) {
            result[i] = boxedBytes[i].byteValue();
        }

        return result;
    }
}
