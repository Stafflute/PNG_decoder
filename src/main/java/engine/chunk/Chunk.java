package engine.chunk;

import engine.checker.crc.CRC;
import engine.checker.crc.CRC32;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Chunk {

    protected static final int MSB_FACTOR = 256;
    protected static final int BYTE_MAX_NUM = 256;

    protected static final int BYTE_LONG_LENGTH = 2;

    protected Byte[] content;

    public abstract void read(InputStream inputStream);

    public void write(OutputStream outputStream) throws IOException {
        setContent();

        writeLength(outputStream);
        writeType(outputStream);

        writeContent(outputStream);

    }

    protected abstract void writeType(OutputStream outputStream) throws IOException;

    protected abstract void writeContent(OutputStream outputStream) throws IOException;

    private void writeLength(OutputStream outputStream) throws IOException {
        byte[] sizeBytes = new byte[BYTE_LONG_LENGTH];

        long size = content.length;

        sizeBytes[0] = (byte) (size % BYTE_MAX_NUM);
        sizeBytes[1] = (byte) (size / MSB_FACTOR);

        outputStream.write(sizeBytes);
    }

    private void writeCRC32(OutputStream outputStream) throws IOException {
        byte[] crcBytes = new byte[BYTE_LONG_LENGTH];

        CRC<Long, Byte> crc = new CRC32();

        long crcNumber = crc.encode(content);

        crcBytes[0] = (byte) (crcNumber % BYTE_MAX_NUM);
        crcBytes[1] = (byte) (crcNumber / MSB_FACTOR);

        outputStream.write(crcBytes);
    }

    protected abstract void setContent();
}
