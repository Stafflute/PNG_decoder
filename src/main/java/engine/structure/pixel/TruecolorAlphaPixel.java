package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

public class TruecolorAlphaPixel extends TruecolorPixel {

    protected final static int ALPHA_POSITION = 4;

    protected TruecolorAlphaPixel(PixelFormat format) {
        super(format);
    }

    public void setAlpha(long intensity) {
        setSourceContent(intensity, ALPHA_POSITION);
    }

    public long getAlpha() {
        return getSourceContent(ALPHA_POSITION);
    }
}
