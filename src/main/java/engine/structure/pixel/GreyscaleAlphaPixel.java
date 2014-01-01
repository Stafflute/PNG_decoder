package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

public class GreyscaleAlphaPixel extends GreyscalePixel {

    protected final static int ALPHA_POSITION = 1;

    public GreyscaleAlphaPixel(PixelFormat format) {
        super(format);
    }

    public GreyscaleAlphaPixel(PixelFormat format, byte[] source) {
        super(format, source);
    }

    public void setAlpha(long intensity) {
        setSourceContent(intensity, ALPHA_POSITION);
    }

    public long getAlpha() {
        return getSourceContent(ALPHA_POSITION);
    }
}
