package engine.structure.filter;

import engine.structure.pixel.Pixel;

public class AverageFilter implements Filter {
    public static final byte FILTER_TYPE = 3;

    @Override
    public byte[] filter(Pixel... pixels) {
        int byteSize = pixels[FIRST].getByteSize();
        byte[] result = new byte[byteSize];

        for(int i = 0; i < byteSize; i++) {
            int a = pixels[A].getByte(i);
            int b = pixels[B].getByte(i);

            int partialResult = pixels[X].getByte(i) - average(a, b);
            result[i] = (byte) (partialResult % BYTE_BASE);
        }

        return result;
    }

    @Override
    public byte[] unfilter(byte[] filteredPixel, Pixel... pixels) {
        int byteSize = filteredPixel.length;
        byte[] result = new byte[byteSize];

        for(int i = 0; i < byteSize; i++) {
            int a = pixels[A].getByte(i);
            int b = pixels[B].getByte(i);

            int partialResult = filteredPixel[i] + average(a, b);
            result[i] = (byte) (partialResult % BYTE_BASE);
        }

        return result;
    }

    private static int average(int a, int b) {
        return  (a + b) / 2;
    }

    @Override
    public byte getFilterType() {
        return FILTER_TYPE;
    }
}
