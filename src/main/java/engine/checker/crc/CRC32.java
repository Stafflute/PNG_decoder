package engine.checker.crc;

import java.util.List;

public final class CRC32 extends CRC<Long, Byte> {

    private static final int BASE = 2;
    private static final int CRC_BIT_LENGTH = 8;
    private static final int CRC_LENGTH = (int) Math.pow(BASE, CRC_BIT_LENGTH);
    private static final Long KEY = 0xedb88320L;

    private static final long HEADER = 0xffffffffL;
    private static final long WINDOW = 0xffL;

    private static final Long[] crcTable = new Long[CRC_LENGTH];

    static {
        updateTable();
    }

    @Override
    public Long encode(Byte[] bytes) {
        Long crc = HEADER;

        for (int i = 0; i < bytes.length; i++) {
            int calculatedIndex = (int) ((crc ^ bytes[i]) & WINDOW);
            crc = crcTable[calculatedIndex] ^ (crc >> CRC_BIT_LENGTH);
        }

        crc ^= HEADER;

        return crc;
    }

    private static void updateTable() {
        Long temp;

        for (int i = 0; i < CRC_LENGTH; i++) {
            temp = Long.valueOf(i);

            for (int j = 0; j < CRC_BIT_LENGTH; j++) {
                if ((temp & 1) == 1) {
                    temp = KEY ^ (temp >> 1);
                } else {
                    temp >>= 1;
                }
            }

            crcTable[i] = temp;
        }
    }
}
