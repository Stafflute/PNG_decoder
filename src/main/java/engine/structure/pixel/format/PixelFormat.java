package engine.structure.pixel.format;

import static engine.structure.pixel.format.ColorType.*;

public class PixelFormat {
    public final ColorType colorType;
    public final int depth;

    public final int depthByteSize;

    private final static int BIT16 = 16;
    private final static int BIT16_SIZE_MULTIPLIER = 2;

    private final static int BIT8_SIZE_MULTIPLIER = 1;

    public PixelFormat(int colorTypeByte, int depth) {
        this.colorType = getColorType(colorTypeByte);
        this.depth = depth;

        if (depth == BIT16) {
            depthByteSize = BIT16_SIZE_MULTIPLIER;
        } else {
            depthByteSize = BIT8_SIZE_MULTIPLIER;
        }
    }

    public int getPixelByteSize() {
        return colorType.byteSize * depthByteSize;
    }

    public boolean is16Bit() {
        return depth == BIT16;
    }
}
