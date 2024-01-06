package poker.hand.approvals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import poker.hand.GameSimulation;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class GoldenMasterTest {
    @Disabled
    @Test
    public void test() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        GameSimulation.runGame();
        Approvals.verify(baos.toString());

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}
