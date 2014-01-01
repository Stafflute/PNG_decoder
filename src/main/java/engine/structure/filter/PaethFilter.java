package engine.structure.filter;

import engine.structure.pixel.Pixel;

public class PaethFilter implements Filter {
    private static final byte FILTER_TYPE = 4;

    @Override
    public byte[] filter(Pixel... pixels) {
        int byteSize = pixels[FIRST].getByteSize();
        byte[] result = new byte[byteSize];

        for(int i = 0; i < byteSize; i++) {
            int a = pixels[A].getByte(i);
            int b = pixels[B].getByte(i);
            int c = pixels[C].getByte(i);

            int partialResult = pixels[X].getByte(i) - paeth(a, b, c);
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
            int c = pixels[C].getByte(i);

            int partialResult = filteredPixel[i] + paeth(a, b, c);
            result[i] = (byte) (partialResult % BYTE_BASE);
        }

        return result;
    }

    private static int paeth(int a, int b, int c) {
        int pr = c;

        int p = a + b - c;

        int pa = Math.abs(p - a);
        int pb = Math.abs(p - b);
        int pc = Math.abs(p - c);

        if (pa <= pb && pa <= pc) {
            pr = a;
        } else if (pb <= pc) {
            pr = b;
        }

        return  pr;
    }

    @Override
    public byte getFilterType() {
        return FILTER_TYPE;
    }
}
