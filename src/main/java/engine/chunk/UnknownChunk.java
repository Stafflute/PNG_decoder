package engine.chunk;

import java.io.IOException;
import java.io.InputStream;

public class UnknownChunk extends Chunk {
    @Override
    protected void setContent() {
        throw new UnknownStructureException();
    }

    @Override
    protected void readContent(InputStream inputStream) throws IOException {
        throw new UnknownStructureException();
    }
}
