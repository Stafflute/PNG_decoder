package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

public class TruecolorPixel extends Pixel {

    protected final static int RED_POSITION = 0;

    protected final static int GREEN_POSITION = 1;

    protected final static int BLUE_POSITION = 2;

    protected TruecolorPixel(PixelFormat format) {
        super(format);
    }

    public void setRed(long intensity) {
        setSourceContent(intensity, RED_POSITION);
    }

    public long getRed() {
        return getSourceContent(RED_POSITION);
    }

    public void setGreen(long intensity) {
        setSourceContent(intensity, GREEN_POSITION);
    }

    public long getGreen() {
        return getSourceContent(GREEN_POSITION);
    }

    public void setBlue(long intensity) {
        setSourceContent(intensity, BLUE_POSITION);
    }

    public long getBlue() {
        return getSourceContent(BLUE_POSITION);
    }
}
