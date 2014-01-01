package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

public class PixelFactory {
    public static Pixel getPixel(byte[] pixelBytes, PixelFormat format) {
        Pixel result = null;

        switch (format.colorType) {
            case GREYSCALE:
                result = new GreyscalePixel(format, pixelBytes);
                break;
            case GREYSCALE_ALPHA:
                result = new GreyscaleAlphaPixel(format, pixelBytes);
                break;
            case TRUECOLOR:
                result = new TruecolorPixel(format, pixelBytes);
                break;
            case TRUECOLOR_ALPHA:
                result = new TruecolorAlphaPixel(format, pixelBytes);
                break;
            case PALETTE:
                result = new PalettePixel(format, pixelBytes);
                break;
            default:
                result = NullPixel.NULL_PIXEL;
                break;
        }

        return result;
    }

    public static Pixel getEmptyPixel(PixelFormat format) {
        Pixel result = null;

        switch (format.colorType) {
            case GREYSCALE:
                result = new GreyscalePixel(format);
                break;
            case GREYSCALE_ALPHA:
                result = new GreyscaleAlphaPixel(format);
                break;
            case TRUECOLOR:
                result = new TruecolorPixel(format);
                break;
            case TRUECOLOR_ALPHA:
                result = new TruecolorAlphaPixel(format);
                break;
            case PALETTE:
                result = new PalettePixel(format);
                break;
            default:
                result = NullPixel.NULL_PIXEL;
                break;
        }

        return result;
    }
}
