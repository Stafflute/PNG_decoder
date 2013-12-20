package zip.structure;

public class ZlibHeaderStructure {
    public static final int CINFO = 0;
    public static final int CM = 1;
    public static final int FLEVEL = 2;
    public static final int FDICT = 3; //TODO Not implemented yet
    public static final int FCHECK = 4; //TODO Not implemented yet
    public static final int DEFLATE = 5;
    public static final int ADLER32 = 6;

    public static final int VARIABLE_LENGTH = -1;

    private static final int[] ZLIB_SIZES = new int[]{
        4, 4, 2, 1, 5, -1, 32
    };

    public static int getFieldBitSize(int fieldType) {
        return ZLIB_SIZES[fieldType];
    }
}
