package engine.structure.scanline;

import engine.structure.filter.Filter;
import engine.structure.filter.FilterFactory;
import engine.structure.pixel.Pixel;
import engine.structure.pixel.PixelFactory;
import engine.structure.pixel.format.PixelFormat;

import static engine.structure.pixel.NullPixel.*;
import static engine.structure.filter.Filter.*;

import java.util.ArrayList;
import java.util.List;

public class Scanline {
    public final Filter filter;
    public final List<Pixel> pixelList;

    private final static byte FILTER_BYTE = 1;
    private final static byte FIRST = 0;
    private final static byte FILTER_BYTE_POSITION = FIRST;
    private final static byte FIRST_BYTE_PIXEL_POSITION = FILTER_BYTE_POSITION + 1;

    public Scanline(Filter filter, List<Pixel> pixelList) {
        this.filter = filter;
        this.pixelList = pixelList;
    }

    public byte[] getCode(Scanline previousScanline) {
        List<Pixel> previousPixelList = null;
        boolean hasPreviousScanline = (previousScanline == null);

        if (hasPreviousScanline) {
            previousPixelList = previousScanline.pixelList;
        }

        int pixelByteSize = pixelList.get(FIRST).getByteSize();
        int byteSize = pixelByteSize * pixelList.size();
        byteSize += FILTER_BYTE;

        byte[] result = new byte[byteSize];

        result[FILTER_BYTE_POSITION] = filter.getFilterType();

        int j = 0;
        for (int i = FIRST_BYTE_PIXEL_POSITION; i < result.length; i += pixelByteSize) {
            Pixel[] pixels = new Pixel[MAX_NUMBER_OF_FILTER_PARAMETERS];

            Pixel x = pixelList.get(j);
            Pixel a = null;
            Pixel b = null;
            Pixel c = null;

            boolean isFirstPixel = (j == FIRST);

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

            pixels[X] = x;
            pixels[A] = a;
            pixels[B] = b;
            pixels[C] = c;

            byte[] filteredByte = filter.filter(pixels);

            System.arraycopy(filteredByte, 0, result, i, pixelByteSize);

            j++;
        }

        return result;
    }

    public static Scanline readScanline(byte[] bytes, PixelFormat format, Scanline previousScanline) {
        List<Pixel> previousPixelList = null;
        boolean hasPreviousScanline = (previousScanline == null);

        if (hasPreviousScanline) {
            previousPixelList = previousScanline.pixelList;
        }

        byte filterByte = bytes[FILTER_BYTE_POSITION];
        Filter filter = FilterFactory.getFilter(filterByte);

        int pixelByteSize = format.getByteSize();

        List<Pixel> pixelList = new ArrayList<>();

        int j = 0;
        for (int i = FIRST_BYTE_PIXEL_POSITION; i < bytes.length; i += pixelByteSize) {
            byte[] filteredPixelBytes = new byte[pixelByteSize];
            System.arraycopy(bytes, i, filteredPixelBytes, FIRST, pixelByteSize);

            Pixel[] pixels = new Pixel[MAX_NUMBER_OF_FILTER_PARAMETERS];

            Pixel a = null;
            Pixel b = null;
            Pixel c = null;

            boolean isFirstPixel = (j == FIRST);

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

            byte[] pixelBytes = filter.unfilter(filteredPixelBytes, pixels);

            Pixel pixel = PixelFactory.getPixel(pixelBytes, format);
            pixelList.add(pixel);

            j++;
        }

        return  new Scanline(filter, pixelList);
    }
}
