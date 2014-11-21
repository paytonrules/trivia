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
    private ByteArrayOutputStream standardOutputStream;

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
        redirectStandardOutput();

        GameRunner.main(new String[] {});

        String generatedOutput = readStandardOutToString();

        final Path goldenMasterPath =
                Paths.get(getClass().getResource("/golden-master.txt").getFile());

        String masterOutput = new String(Files.readAllBytes(goldenMasterPath),
                Charset.defaultCharset());

        assertEquals(generatedOutput, masterOutput);
    }

    private String readStandardOutToString() {
        return new String( standardOutputStream.toByteArray(), Charset.defaultCharset());
    }

    private void redirectStandardOutput() {
        standardOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(standardOutputStream);
        System.setOut(printStream);
    }
}
