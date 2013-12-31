package engine.structure.pixel.format;

import java.util.HashMap;
import java.util.Map;

public enum ColorType {
    GREYSCALE (0),
    TRUECOLOR (2),
    PALETTE (3),
    GREYSCALE_ALPHA (4),
    TRUECOLOR_ALPHA (6),
    UNKNOWN (-1);

    private final int byteValue;

    private ColorType(int byteValue) {
        this.byteValue = byteValue;
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