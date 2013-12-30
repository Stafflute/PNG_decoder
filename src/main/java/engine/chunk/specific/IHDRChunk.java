package engine.chunk.specific;

import engine.chunk.Chunk;

import java.io.IOException;
import java.io.InputStream;

import static util.ByteConverter.*;

public class IHDRChunk extends Chunk{

    private long weight;
    private long height;
    private int depth;
    private int colourType;
    private int filterMethod;
    private int interlaceMethod;

    @Override
    protected void setContent() {
        //TODO
    }

    @Override
    protected void readContent(InputStream inputStream) throws IOException {
        byte[] byteLong;

        byteLong = new byte[BYTE_LONG_LENGTH];
        inputStream.read(byteLong);
        weight = toLong(byteLong);

        byteLong = new byte[BYTE_LONG_LENGTH];
        inputStream.read(byteLong);
        height = toLong(byteLong);

        depth = inputStream.read();
        colourType = inputStream.read();
        filterMethod = inputStream.read();
        interlaceMethod = inputStream.read();
    }

    public int getInterlaceMethod() {
        return interlaceMethod;
    }

    public void setInterlaceMethod(int interlaceMethod) {
        this.interlaceMethod = interlaceMethod;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getColourType() {
        return colourType;
    }

    public void setColourType(int colourType) {
        this.colourType = colourType;
    }

    public int getFilterMethod() {
        return filterMethod;
    }

    public void setFilterMethod(int filterMethod) {
        this.filterMethod = filterMethod;
    }
}
