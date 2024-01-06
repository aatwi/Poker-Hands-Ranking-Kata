package com.murex.approvals;

import com.murex.GameSimulation;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class GoldenMasterTest {
    @Test
    public void test() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        GameSimulation.runGame();
        Approvals.verify(baos.toString());

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}
