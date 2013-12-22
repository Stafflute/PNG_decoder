package zip.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

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

        for(int i = 0; i < result.length; i++) {
            int resultElab = (result[i] < 0) ? result[i] + 256 : result[i];
            String aString = String.format("%2s", Integer.toHexString(resultElab)).replace(' ', '0');
            if (i % 24 == 0) {
                System.out.print("\n" + aString + " ");
            } else {
                System.out.print(aString + " ");
            }
        }
    }
}
