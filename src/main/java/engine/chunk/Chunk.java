package engine.chunk;

import engine.checker.crc.CRC;
import engine.checker.crc.CRC32;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static util.ByteConverter.*;

public abstract class Chunk {

    protected static final int BYTE_LONG_LENGTH = 4;

    protected Byte[] content;

    protected String type;
    protected byte[] typeBytes;

    public void read(InputStream inputStream) throws IOException {
        long length = readLength(inputStream);

        content = new Byte[(int) length];

        readType(inputStream);

        readContent(inputStream);

        readCRC32(inputStream);
    }

    public void write(OutputStream outputStream) throws IOException {
        setContent();

        writeLength(outputStream);
        writeType(outputStream);

        writeContent(outputStream);

        writeCRC32(outputStream);
    }

    protected  void writeType(OutputStream outputStream) throws IOException {
        outputStream.write(typeBytes);
    }

    protected void writeContent(OutputStream outputStream) throws IOException {
        outputStream.write(unboxByte(content));
    }

    private void writeLength(OutputStream outputStream) throws IOException {
        long size = content.length;

        byte[] sizeBytes = to4Byte(size);

        outputStream.write(sizeBytes);
    }

    private void writeCRC32(OutputStream outputStream) throws IOException {
        CRC<Long, Byte> crc = new CRC32();

        long crcNumber = crc.encode(content);

        byte[] crcBytes = to4Byte(crcNumber);

        outputStream.write(crcBytes);
    }

    protected abstract void setContent();

    protected long readLength(InputStream inputStream) throws IOException {
        byte[] sizeBytes = new byte[BYTE_LONG_LENGTH];
        inputStream.read(sizeBytes);

        long size = toLong(sizeBytes);

        return size;
    }

    protected void readType(InputStream inputStream) throws IOException {
        byte[] typeBytes = new byte[BYTE_LONG_LENGTH];
        inputStream.read(typeBytes);

        this.typeBytes = typeBytes;
        type = new String(typeBytes);
    }

    protected abstract void readContent(InputStream inputStream) throws IOException;

    protected void readCRC32(InputStream inputStream) throws IOException {
        byte[] crc32Bytes = new byte[BYTE_LONG_LENGTH];
        inputStream.read(crc32Bytes);

        CRC<Long, Byte> crc = new CRC32();

        long crc32 = toLong(crc32Bytes);

        if(crc.check(content, crc32)) {
            throw new notValidCRCException();
        }
    }
}
