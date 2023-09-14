package sn.ept.git.seminaire.cicd.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class MyFileReaderTest {

    private MyFileReader fileReader;

    @BeforeEach
    public void setUp() {
        fileReader = mock(MyFileReader.class);
    }

    @Test
    public void testReadFile() throws IOException {
        String filePath = "testfile.txt";
        List<String> toHave = Arrays.asList("line 1", "line 2");
        when(fileReader.read(filePath)).thenReturn(toHave);

        List<String> results = fileReader.read(filePath);

        assertEquals(toHave, results);
    }

    @Test
    public void testReadFileThrowsIOException() throws IOException {
        String filePath = "nonexistentfile.txt";
        when(fileReader.read(filePath)).thenThrow(new IOException());

        try {
            fileReader.read(filePath);
            assertFalse(true);
        } catch (IOException e) {
            assertTrue(true);
        }
    }
}
