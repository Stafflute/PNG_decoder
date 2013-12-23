package engine.chunk;

import engine.checker.crc.CRC;
import engine.checker.crc.CRC32;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static util.ByteConverter.*;

public abstract class Chunk {

    protected static final int MSB_FACTOR = 256;
    protected static final int BYTE_MAX_NUM = 256;

    protected static final int BYTE_LONG_LENGTH = 2;

    protected Byte[] content;

    protected String type;

    public void read(InputStream inputStream) throws IOException {
        long length = readLength(inputStream);

        content = new Byte[(int) length];

        readType(inputStream);

        //TODO
    }

    protected long readLength(InputStream inputStream) throws IOException {
        byte[] sizeBytes = new byte[BYTE_LONG_LENGTH];
        inputStream.read(sizeBytes);

        long size = toLong(sizeBytes);

        return size;
    }

    public void write(OutputStream outputStream) throws IOException {
        setContent();

        writeLength(outputStream);
        writeType(outputStream);

        writeContent(outputStream);

        writeCRC32(outputStream);
    }

    protected abstract void writeType(OutputStream outputStream) throws IOException; //TODO

    protected abstract void writeContent(OutputStream outputStream) throws IOException; //TODO

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

    protected void readType(InputStream inputStream) throws IOException {
        //TODO
    }
}
