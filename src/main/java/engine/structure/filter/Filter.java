package engine.structure.filter;

import engine.structure.pixel.Pixel;

public interface Filter {
    public byte[] filter(Pixel... pixels);

    public Pixel unfilter(byte[] filteredPixel, Pixel... pixels);

    public byte getFilterType();

    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
}
