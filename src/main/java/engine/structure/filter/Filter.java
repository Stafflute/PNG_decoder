package engine.structure.filter;

import engine.structure.pixel.Pixel;

public interface Filter {
    public byte[] filter(Pixel... pixels);

    public byte[] unfilter(byte[] filteredPixel, Pixel... pixels);

    public byte getFilterType();

    public static final int X = 0;
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;

    public final static int MAX_NUMBER_OF_FILTER_PARAMETERS = 4;

    public static final int FIRST = 0;
    public static final int BYTE_BASE = 256;
}
