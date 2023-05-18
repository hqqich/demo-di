package org.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.junit.Test;
import org.zeroturnaround.exec.ProcessExecutor;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException, InterruptedException, TimeoutException {
        String output = new ProcessExecutor().command("java", "-version")
                .readOutput(true).execute()
                .outputUTF8();
        System.out.println(output);
    }
}
