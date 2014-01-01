package engine.structure.pixel;

import engine.structure.pixel.format.PixelFormat;

import java.util.Iterator;

import static util.ByteConverter.*;

public abstract class Pixel {
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
        return toInt(source[physicalPosition]);
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
}
