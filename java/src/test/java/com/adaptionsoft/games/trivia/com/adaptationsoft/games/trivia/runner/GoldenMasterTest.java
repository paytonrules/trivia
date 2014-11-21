package com.adaptionsoft.games.trivia.com.adaptationsoft.games.trivia.runner;
import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class GoldenMasterTest {
    private PrintStream originalOutputStream;

    @Before
    public void setUp() {
        originalOutputStream = System.out;
    }

    @After
    public void resetStandardOut() throws IOException {
        System.setOut(originalOutputStream);
    }

    @Test
    public void ItPassesTheGoldenMaster() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        GameRunner.main(new String[] {});

        String generatedOutput = new String( outputStream.toByteArray(), Charset.defaultCharset());

        final Path goldenMasterPath =
                Paths.get(getClass().getResource("/golden-master.txt").getFile());
        String masterOutput = new String(Files.readAllBytes(goldenMasterPath),
                Charset.defaultCharset());

        assertEquals(generatedOutput, masterOutput);
    }
}
