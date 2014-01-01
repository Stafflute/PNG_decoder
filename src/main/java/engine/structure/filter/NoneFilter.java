package engine.structure.filter;

import engine.structure.pixel.Pixel;

public class NoneFilter implements Filter {
    public static final byte FILTER_TYPE = 0;

    @Override
    public byte[] filter(Pixel... pixels) {
        int byteSize = pixels[FIRST].getByteSize();
        byte[] result = new byte[byteSize];

        for(int i = 0; i < byteSize; i++) {
            result[i] = (byte) pixels[X].getByte(i);
        }

        return result;
    }

    @Override
    public byte[] unfilter(byte[] filteredPixel, Pixel... pixels) {
        return filteredPixel;
    }

    @Override
    public byte getFilterType() {
        return FILTER_TYPE;
    }
}
