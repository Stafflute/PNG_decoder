package zip.input;

import util.BitInputStream;
import util.ByteBitInputStream;
import zip.deflate.Decompressor;

import java.io.IOException;
import java.io.InputStream;
import static zip.structure.ZlibHeaderStructure.*;

/**
 * Input stream utilizzato per la decompressione
 */
public class ZlibInputStream {

    private final static int BYTE_LENGTH = 8;
    private final static int ADLER32_BYTE_LENGTH =
            getFieldBitSize(ADLER32) / BYTE_LENGTH;

    private final BitInputStream bitInputStream;

    public final int cinfo;
    public final int cm;

    public final int flevel;
    public final int fdict;
    public final int fcheck;

    private int[] adler32;

    public ZlibInputStream(InputStream inputStream) throws IOException {
         bitInputStream = new ByteBitInputStream(inputStream);

        int size;

        size = getFieldBitSize(CINFO);
        cinfo =  bitInputStream.read(size);

        size = getFieldBitSize(CM);
        cm =  bitInputStream.read(size);

        size = getFieldBitSize(FLEVEL);
        flevel =  bitInputStream.read(size);

        size = getFieldBitSize(FDICT);
        fdict =  bitInputStream.read(size);

        size = getFieldBitSize(FCHECK);
        fcheck =  bitInputStream.read(size);

    }

    public byte[] readAll() throws IOException {
        byte[] result = Decompressor.decompress(bitInputStream);

        adler32 = new int[ADLER32_BYTE_LENGTH];

        for(int i = 0; i < adler32.length; i++) {
            adler32[i] = bitInputStream.readByte();
        }

        return result;
    }

    public int[] getAdler32() {
        return adler32;
    }

    public void close() throws IOException {
        bitInputStream.close();
    }
}
