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

        int i = 0;
        for(i = 0; expectedFileStream.available() > 0; i++) {
            byte elem = (byte) expectedFileStream.read();

            assertThat(result[i], equalTo(elem));
        }
    }
}
