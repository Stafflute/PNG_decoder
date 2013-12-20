package zip.input;

import util.ByteBitInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static zip.structure.ZlibHeaderStructure.*;

/**
 * Input stream utilizzato per la decompressione
 */
public class ZlibInputStream extends ByteBitInputStream {

    public ZlibInputStream(InputStream inputStream) {
        super(inputStream);
    }

    private int index = 0;

    @Override
    public int read() throws IOException {
        int bitToGet = getFieldBitSize(index);
        index++;

        return read(bitToGet);  //TODO
    }
}
