package engine.structure.filter;

import engine.structure.pixel.Pixel;

public class AverageFilter implements Filter {
    //TODO
    private static final byte FILTER_TYPE = 3;

    @Override
    public byte[] filter(Pixel... pixels) {
        return new byte[0];
    }

    @Override
    public byte[] unfilter(byte[] filteredPixel, Pixel... pixels) {
        return new byte[0];
    }

    @Override
    public byte getFilterType() {
        return FILTER_TYPE;
    }
}
