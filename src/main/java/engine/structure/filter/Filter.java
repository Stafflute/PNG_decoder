package engine.structure.filter;

import engine.structure.pixel.Pixel;

public interface Filter {
    public byte filter(Pixel... pixels);

    public Pixel unfilter(byte[] filteredPixel, Pixel... pixels);

    public byte getFilterType();
}
