package zip.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ZlibInputStreamTest {
    InputStream inputStream;
    ZlibInputStream zlibInputStream;

    @Before
    public void setUp() throws Exception {
         inputStream = new FileInputStream("src/test/testfiles/blend_sample_1");
    }

    @After
    public void tearDown() throws Exception {
        if(zlibInputStream != null) {
            zlibInputStream.close();
            zlibInputStream = null;
        } else {
            inputStream.close();
        }
    }

    @Test
    public void testIstantiation() throws Exception {
        zlibInputStream = new ZlibInputStream(inputStream);
    }

    @Test
    public void testReadAll() throws Exception {
        testIstantiation();
        byte[] result = zlibInputStream.readAll();

        InputStream expectedFileStream =
                new FileInputStream("src/test/testfiles/blend_sample_1_expected");

        assertThat(result.length, equalTo(expectedFileStream.available()));

        for(byte b : result) {
            byte elem = (byte) expectedFileStream.read();

            assertThat(b, equalTo(elem));
        }

        expectedFileStream.close();
    }

    @Test
    public void testGetAdler32() throws Exception {
        testIstantiation();
        zlibInputStream.readAll();
        int[] resultAdler32 = zlibInputStream.getAdler32();

        int[] expected = new int[] {0x02, 0x63, 0xF7, 0xC3};

        for(int i = 0; i < expected.length; i++) {
            assertThat(resultAdler32[i], equalTo(expected[i]));
        }
    }
}
