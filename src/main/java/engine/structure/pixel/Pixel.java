package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

public abstract class Pixel {
    protected byte[] source;
    protected PixelFormat format;

    public byte[] getCode() {
        return source;
    }

    public int getByteSize() {
        return format.getByteSize();
    }

    public int getByte(int position) {
        return source[position];
    }
}
