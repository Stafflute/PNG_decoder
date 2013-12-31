package engine.structure.pixel.format;

import java.util.HashMap;
import java.util.Map;

public enum ColorType {
    GREYSCALE (0, 1),
    TRUECOLOR (2, 3),
    PALETTE (3, 1),
    GREYSCALE_ALPHA (4, 2),
    TRUECOLOR_ALPHA (6, 4),
    UNKNOWN (-1, 0);

    private final int byteValue;
    public final int byteSize;

    private ColorType(int byteValue, int byteSize) {
        this.byteValue = byteValue;
        this.byteSize = byteSize;
    }

    private static final Map<Integer, ColorType> COLOR_TYPE_MAP = new HashMap<>();

    static {
        ColorType[] colorTypes = ColorType.class.getEnumConstants();
        for (ColorType colorType : colorTypes) {
            COLOR_TYPE_MAP.put(colorType.byteValue, colorType);
        }
    }

    public static ColorType getColorType(int colorTypeByte) {
        ColorType result = UNKNOWN;

        if (COLOR_TYPE_MAP.containsKey(colorTypeByte)) {
            result = COLOR_TYPE_MAP.get(colorTypeByte);
        }

        return result;
    }
}