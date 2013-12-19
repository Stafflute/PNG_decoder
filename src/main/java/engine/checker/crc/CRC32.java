package engine.checker.crc;

import java.util.List;

public final class CRC32 extends CRC<Long, List<Character>> {

    private static final int BASE = 2;
    private static final int CRC_BIT_LENGTH = 8;
    private static final int CRC_LENGTH = (int) Math.pow(BASE, CRC_BIT_LENGTH);
    private static final Long KEY = 0xedb88320L;

    private static final long HEADER = 0xffffffffL;
    private static final long WINDOW = 0xffL;

    private final Long[] crcTable = new Long[CRC_LENGTH];

    public CRC32() {
        updateTable();
    }

    @Override
    public Long encode(List<Character> characters) {
        Long crc = HEADER;

        for (int i = 0; i < characters.size(); i++) {
            int calculatedIndex = (int) ((crc ^ characters.get(i)) & WINDOW);
            crc = crcTable[calculatedIndex] ^ (crc >> CRC_BIT_LENGTH);
        }

        crc ^= HEADER;

        return crc;
    }

    private void updateTable() {
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
