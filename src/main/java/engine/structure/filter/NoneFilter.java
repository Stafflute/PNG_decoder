package engine.structure.filter;

import engine.structure.pixel.Pixel;

public class NoneFilter implements Filter {
    private static final byte FILTER_TYPE = 0;

    //TODO

    @Override
    public byte[] filter(Pixel... pixels) {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] unfilter(byte[] filteredPixel, Pixel... pixels) {
        return new byte[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte getFilterType() {
        return FILTER_TYPE;
    }
}
