package engine.structure.pixel.format;

import static engine.structure.pixel.format.ColorType.*;

public class PixelFormat {
    public final ColorType colorType;
    public final int depth;

    public PixelFormat(int colorTypeByte, int depth) {
        this.colorType = getColorType(colorTypeByte);
        this.depth = depth;
    }

    public int getByteSize() {
        //TODO
        return 0;
    }
}
