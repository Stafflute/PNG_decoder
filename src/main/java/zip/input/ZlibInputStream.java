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

    private final BitInputStream bitInputStream;

    public final int cinfo;
    public final int cm;

    public final int flevel;
    public final int fdict;
    public final int fcheck;

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
        return Decompressor.decompress(bitInputStream);
    }
}
