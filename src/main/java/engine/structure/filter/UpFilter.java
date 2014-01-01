package engine.structure.filter;

import engine.structure.pixel.Pixel;

public class UpFilter implements Filter {
    public static final byte FILTER_TYPE = 2;

    @Override
    public byte[] filter(Pixel... pixels) {
        int byteSize = pixels[FIRST].getByteSize();
        byte[] result = new byte[byteSize];

        for(int i = 0; i < byteSize; i++) {
            int partialResult = pixels[X].getByte(i) - pixels[B].getByte(i);
            result[i] = (byte) (partialResult % BYTE_BASE);
        }

        return result;
    }

    @Override
    public byte[] unfilter(byte[] filteredPixel, Pixel... pixels) {
        int byteSize = filteredPixel.length;
        byte[] result = new byte[byteSize];

        for(int i = 0; i < byteSize; i++) {
            int partialResult = filteredPixel[i] + pixels[B].getByte(i);
            result[i] = (byte) (partialResult % BYTE_BASE);
        }

        return result;
    }

    @Override
    public byte getFilterType() {
        return FILTER_TYPE;
    }
}
