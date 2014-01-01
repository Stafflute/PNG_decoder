package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

public class GreyscalePixel extends Pixel {

    protected final static int GREY_POSITION = 0;

    protected GreyscalePixel(PixelFormat format) {
        super(format);
    }

    public void setBrightness(long intensity) {
        setSourceContent(intensity, GREY_POSITION);
    }

    public long getBrightness() {
        return getSourceContent(GREY_POSITION);
    }
}
