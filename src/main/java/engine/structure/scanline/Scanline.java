package engine.structure.scanline;

import engine.structure.filter.Filter;
import engine.structure.pixel.Pixel;
import engine.structure.pixel.format.PixelFormat;

import static engine.structure.pixel.NullPixel.*;
import static engine.structure.filter.Filter.*;

import java.util.List;

public class Scanline {
    public final Filter filter;
    public final List<Pixel> pixelList;

    private final byte FILTER_BYTE = 1;
    private final byte FILTER_BYTE_POSITION = 0;
    private final byte FIRST = 0;
    private final byte FIRST_BYTE_PIXEL_POSITION = 1;

    private final static int MAX_NUMBER_OF_PARAMETERS = 3;

    public Scanline(Filter filter, List<Pixel> pixelList) {
        this.filter = filter;
        this.pixelList = pixelList;
    }

    public byte[] getCode(Scanline previousScanline) {
        Scanline defaultPreviousScanline = (Scanline) previousScanline;
        List<Pixel> previousPixelList = defaultPreviousScanline.pixelList;

        int pixelByteSize = pixelList.get(FIRST).getByteSize();
        int byteSize = pixelByteSize * pixelList.size();
        byteSize += FILTER_BYTE;

        byte[] result = new byte[byteSize];

        result[FIRST] = filter.getFilterType();

        int j = 0;
        for (int i = FIRST_BYTE_PIXEL_POSITION; i < result.length; i += pixelByteSize) {
            Pixel[] pixels = new Pixel[MAX_NUMBER_OF_PARAMETERS];

            Pixel a = null;
            Pixel b = null;
            Pixel c = null;

            boolean isFirstPixel = (i == FIRST_BYTE_PIXEL_POSITION);
            boolean hasPreviousScanline = (previousScanline == null);

            if (isFirstPixel) {
                a = NULL_PIXEL;
                c = NULL_PIXEL;
            } else {
                a = pixelList.get(j - 1);
            }

            if (!hasPreviousScanline) {
                b = NULL_PIXEL;
                c = NULL_PIXEL;
            } else {
                b = previousPixelList.get(j);
            }

            if(!isFirstPixel && hasPreviousScanline) {
                c = previousPixelList.get(j - 1);
            }

            pixels[A] = a;
            pixels[B] = b;
            pixels[C] = c;

            byte[] filteredByte = filter.filter(pixels);

            System.arraycopy(filteredByte, 0, result, i, pixelByteSize);

            j++;
        }

        return result;
    }

    public static Scanline readScanline(byte[] bytes, PixelFormat format) {

        return  null;//TODO
    }
}
