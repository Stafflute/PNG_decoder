package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

public class TruecolorAlphaPixel extends TruecolorPixel {

    protected final static int ALPHA_POSITION = 4;

    public TruecolorAlphaPixel(PixelFormat format) {
        super(format);
    }

    public TruecolorAlphaPixel(PixelFormat format, byte[] source) {
        super(format, source);
    }

    public void setAlpha(long intensity) {
        setSourceContent(intensity, ALPHA_POSITION);
    }

    public long getAlpha() {
        return getSourceContent(ALPHA_POSITION);
    }
}
