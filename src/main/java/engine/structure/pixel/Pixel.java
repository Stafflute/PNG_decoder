package engine.structure.pixel;

public interface Pixel {
    public byte[] getCode();
    public int getByteSize();
    public int getByte(int channel);
}
