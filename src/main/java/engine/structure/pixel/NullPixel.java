package engine.structure.pixel;

public class NullPixel extends Pixel{

    public final static Pixel NULL_PIXEL = new NullPixel();

    private NullPixel() {}

    @Override
    public byte[] getCode() {
        return null;
    }

    @Override
    public int getByteSize() {
        return -1;
    }

    @Override
    public int getByte(int channel) {
        return 0;
    }
}
