package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

import java.util.Iterator;

import static util.ByteConverter.*;

public abstract class Pixel implements Iterable<Integer> {
    protected final byte[] source;
    protected final PixelFormat format;

    public Pixel(PixelFormat format) {
        this.format = format;
        this.source = new byte[format.getPixelByteSize()];
    }

    public Pixel(PixelFormat format, byte[] source) {
        this.format = format;
        this.source = source;
    }

    public byte[] getCode() {
        return source;
    }

    public int getByteSize() {
        return format.getPixelByteSize();
    }

    public int getByte(int physicalPosition) {
        return source[physicalPosition];
    }

    protected void setSourceContent(long intensity, int channelPos) {
        if (format.is16Bit()) {
            byte[] channelBytes = to2Byte(intensity);
            System.arraycopy(channelBytes, 0, source, channelPos * 2, channelBytes.length);
        } else {
            source[channelPos] = (byte) intensity;
        }
    }

    protected long getSourceContent(int channelPos) {
        int depthByteSize = format.depthByteSize;
        byte[] channelBytes = new byte[depthByteSize];
        System.arraycopy(source, channelPos * depthByteSize, channelBytes, 0, channelBytes.length);

        return toLong(channelBytes);
    }

    public Iterator<Integer> iterator() {
        return new PixelIterator(source);
    }

    private class PixelIterator implements Iterator<Integer> {
        private  int i = -1;

        public PixelIterator(byte[] source) {
        }

        @Override
        public boolean hasNext() {
            return i < source.length;
        }

        @Override
        public Integer next() {
            i++;
            return toInt(source[i]);
        }

        @Override
        public void remove() {
           //not implemented yet
        }
    }
}
