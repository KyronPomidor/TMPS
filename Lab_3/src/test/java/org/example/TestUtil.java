package org.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// Helper: captures System.out while an action runs
final class TestUtil {
    private TestUtil() {}

    static String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString().replace("\r\n", "\n");
    }
}
