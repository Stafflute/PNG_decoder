package engine.chunk.required;

import engine.chunk.Chunk;

import java.io.IOException;
import java.io.InputStream;

public class IHDRChunk extends Chunk{

    private long length;
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
        //TODO
    }

    public int getInterlaceMethod() {
        return interlaceMethod;
    }

    public void setInterlaceMethod(int interlaceMethod) {
        this.interlaceMethod = interlaceMethod;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
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
