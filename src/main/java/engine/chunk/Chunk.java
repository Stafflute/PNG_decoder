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

    protected static long readLength(InputStream inputStream) throws IOException {
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

    private static final String SPECIFIC_PACKAGE = Chunk.class.getPackage().toString() + ".specific.";
    private static final String CLASS_CHUNK_NAME = Chunk.class.getSimpleName();

    /**
     * Legge una chunk in base all'input stream.
     * In base al tipo di chunk letto istanzia in base al tipo letto.
     * Se il tipo non è riconosciuto restituisce un chunk di tipo UnknownChunk.
     *
     * @param inputStream sorgente in input desiderata
     * @return chunk letto
     * @throws IOException
     */
    public static Chunk readChunk(InputStream inputStream) throws IOException {
        Chunk chunk = null;

        long length = readLength(inputStream);

        byte[] typeBytes = new byte[BYTE_LONG_LENGTH];
        inputStream.read(typeBytes);

        String type = new String(typeBytes);

        try {

            Class classType = Class.forName(SPECIFIC_PACKAGE + type + CLASS_CHUNK_NAME);
            chunk = (Chunk) classType.newInstance();
            chunk.read(inputStream, length, typeBytes);

        } catch (ClassNotFoundException e) {

            System.err.println("Chunk not found");
            chunk = new UnknownChunk();
            inputStream.skip(length + BYTE_LONG_LENGTH);

        } catch (InstantiationException e) {
            //ERROR
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //ERROR
            e.printStackTrace();
        }

        return chunk;
    }

    private void read(InputStream inputStream, long length, byte[] typeBytes) throws IOException {
        content = new Byte[(int) length];

        this.typeBytes = typeBytes;

        type = new String(typeBytes);

        readContent(inputStream);

        readCRC32(inputStream);
    }

    protected int setLong(long aLong, int pos) {
        byte[] bytes = to4Byte(aLong);

        for(int i = 0; i < BYTE_LONG_LENGTH; i++) {
            content[pos + i] = bytes[i];
        }

        return pos + BYTE_LONG_LENGTH;
    }

    protected int setByte(int anInt, int pos) {
        content[pos] = (byte) anInt;

        return ++pos;
    }
}
