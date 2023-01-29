import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class IntOpsTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void main() {
        IntOps.main(new String[] {"1234", "99"});
        String expected = """
                1234 * 99 = 122166
                1234 / 99 = 12
                1234 % 99 = 46
                1234 = 12 * 99 + 46
                """;
        String output = outputStreamCaptor.toString();
        output = output.replaceAll("\\r\\n?", "\n");
        assertEquals(expected, output, "Equals");
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}