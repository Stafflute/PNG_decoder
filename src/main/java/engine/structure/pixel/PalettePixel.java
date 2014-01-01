package engine.structure.pixel;

import engine.structure.palette.Palette;
import engine.structure.pixel.format.PixelFormat;

public class PalettePixel extends Pixel {

    protected final static int PALETTE_POSITION = 0;

    protected PalettePixel(PixelFormat format) {
        super(format);
    }

    public void setPalette(long palette) {
        setSourceContent(palette, PALETTE_POSITION);
    }

    public long getPalette() {
        return getSourceContent(PALETTE_POSITION);
    }
}
