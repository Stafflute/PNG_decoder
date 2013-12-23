package engine.checker.crc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class CRC32Test {
    private CRC crc;
    private static final Byte[] BYTES = new Byte[]
            //First 4B is the chunk type, the other 13B is the data
            {0x49, 0x48, 0x44, 0x52, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x02, 0x08, 0x06, 0x00, 0x00, 0x01};

    @org.junit.Before
    public void setUp() throws Exception {
        crc = new CRC32();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        crc = null;
    }

    @org.junit.Test
    public void testEncodeNormalCase() throws Exception {
        final Object EXCEPTED = 0x05B13DB2L;

        Object result = crc.encode(BYTES);
        assertThat(result, equalTo(EXCEPTED));
    }

    //TODO testEncodeEmptyList method

    @org.junit.Test
    public void testCheckTrue() throws Exception {

        final Number EXCEPTED = 0x05B13DB2L;


        boolean checkedResult = crc.check(BYTES, EXCEPTED);
        assertThat(checkedResult, equalTo(true));
    }

    @org.junit.Test
    public void testCheckFalse() throws Exception {

        final Number NOT_EXCEPTED = 0xffffffffL;

        boolean checkedResult = crc.check(BYTES, NOT_EXCEPTED);
        assertThat(checkedResult, equalTo(false));
    }
}
