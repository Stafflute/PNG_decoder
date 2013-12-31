package engine.structure.scanline;

import engine.structure.filter.Filter;
import engine.structure.pixel.Pixel;

import java.util.List;

public class DefaultScanline implements Scanline {
    public final Filter filter;
    public final List<Pixel> pixelList;

    private final byte FILTER_BYTE = 1;
    private final byte FILTER_BYTE_POSITION = 0;
    private final byte FIRST = 0;

    public DefaultScanline(Filter filter, List<Pixel> pixelList) {
        this.filter = filter;
        this.pixelList = pixelList;
    }

    @Override
    public byte[] getCode(Scanline scanline) {
        int pixelByteSize = pixelList.get(FIRST).getByteSize();
        int byteSize = pixelByteSize * pixelList.size();
        byteSize += FILTER_BYTE;

        byte[] result = new byte[byteSize];

        result[FIRST] = filter.getFilterType();

        for(int i = 1; i < result.length; i += pixelByteSize) {
            //TODO
        }

        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
