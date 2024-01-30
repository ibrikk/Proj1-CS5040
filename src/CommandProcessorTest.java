import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * A JUnit test class for the CommandProcessor class.
 * This class tests the processing of commands by the CommandProcessor,
 * ensuring that it correctly handles various inputs.
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 * @version 2024-01-27
 */
public class CommandProcessorTest extends TestCase {

    private CommandProcessor cmdp;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        cmdp = new CommandProcessor();
    }


    /**
     * Tests the processor method in the CommandProcessor class with an
     * unrecognized command.
     * Ensures that the processor method correctly identifies and responds to an
     * unrecognized command.
     */
    @Test
    public void testProcessor1() {
        String unrecognizedCommand = "delete r1 0 0 1024 1024";
        systemOut().clearHistory();
        cmdp.processor(unrecognizedCommand);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Unrecognized command.");
    }

}
