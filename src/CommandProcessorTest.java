import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * A JUnit test class for the CommandProcessor class. This class tests the
 * processing of commands by the CommandProcessor, ensuring that it correctly
 * handles various inputs.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 * @version 2024-01-27
 */
public class CommandProcessorTest extends TestCase {

    private CommandProcessor cmdp;

    /**
     * Sets up the test fixture. Called before every test case method.
     */
    @Before
    public void setUp() {
        cmdp = new CommandProcessor();
    }


    /**
     * Tests the processor method in the CommandProcessor class with an
     * unrecognized command. Ensures that the processor method correctly
     * identifies and responds to an unrecognized command.
     */
    @Test
    public void testProcessor1() {
        String unrecognizedCommand = "delete r1 0 0 1024 1024";
        systemOut().clearHistory();
        cmdp.processor(unrecognizedCommand);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Unrecognized command.");
    }


    /**
     * Test many inserts
     */
    @Test
    public void testProcessorInsert() {
        String unrecognizedCommand = "delete r1 0 0 1024 1024";
        String cmd1 = "insert r1 0 0 217 1474647";
        String cmd2 = "insert r2 10 10 15 15";
        String cmd3 = "insert R2 11 11 5 5 ";
        String cmd4 = "insert r3 0 0 1000 10";
        String cmd5 = "insert r4 0 0 10 1000";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        cmdp.processor(cmd5);
        systemOut().clearHistory();

        String cmd6 = "regionsearch 900 5 0 0 ";
        systemOut().clearHistory();
        cmdp.processor(cmd6);
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle rejected: (900, 5, 0, 0)");

        String cmd7 = "regionsearch 900 5 1 1";
        systemOut().clearHistory();
        cmdp.processor(cmd7);
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangles intersecting region (900, 5, 1, 1):\r\n"
                + "(r3, 0, 0, 1000, 10)");

        String cmd8 = "regionsearch 5 900 0 1 ";
        systemOut().clearHistory();
        cmdp.processor(cmd8);
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle rejected: (5, 900, 0, 1)");
    }


    /**
     * Test many inserts
     */
    @Test
    public void testIntersects() {
        String cmd1 = "insert a 10 10 15 15";
        String cmd2 = "insert b 11 11 5 5";
        String cmd3 = "insert c 0 0 1000 10";
        String cmd4 = "insert d 0 0 10 1000";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        systemOut().clearHistory();
        cmdp.processor("intersections");
        assertFuzzyEquals(systemOut().getHistory(), "Intersection pairs:\r\n"
            + "(a, 10, 10, 15, 15 | b, 11, 11, 5, 5)\r\n"
            + "(b, 11, 11, 5, 5 | a, 10, 10, 15, 15)\r\n"
            + "(c, 0, 0, 1000, 10 | d, 0, 0, 10, 1000)\r\n"
            + "(d, 0, 0, 10, 1000 | c, 0, 0, 1000, 10)");
    }


    /**
     * Test remove by name
     */
    @Test
    public void testRemoveByName() {
        String cmd1 = "insert a 10 10 15 15";
        String cmd2 = "insert b 11 11 5 5";
        String cmd3 = "insert c 0 0 1000 10";
        String cmd4 = "insert d 0 0 10 1000";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        systemOut().clearHistory();
        cmdp.processor("remove d");
        assertFuzzyEquals(systemOut().getHistory(),
            "rectangle removed: (d 0 0 10 1000)");
    }


    /**
     * Test remove by name
     */
    @Test
    public void testRemoveByValues() {
        String cmd1 = "insert a 10 10 15 15";
        String cmd2 = "insert b 11 11 5 5";
        String cmd3 = "insert c 0 0 1000 10";
        String cmd4 = "insert d 0 0 10 1000";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        systemOut().clearHistory();
        cmdp.processor("remove 10 10 15 15");
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle removed: (a 10 10 15 15)");
    }


    /**
     * Test remove by name
     */
    @Test
    public void testRemoveByValuesNotValidRectangle() {
        String cmd1 = "insert a 10 10 15 15";
        String cmd2 = "insert b 11 11 5 5";
        String cmd3 = "insert c 0 0 1000 10";
        String cmd4 = "insert d 0 0 10 1000";

        cmdp.processor(cmd1);
        cmdp.processor(cmd2);
        cmdp.processor(cmd3);
        cmdp.processor(cmd4);
        systemOut().clearHistory();
        cmdp.processor("remove 1 1 1 1");
        assertFuzzyEquals(systemOut().getHistory(),
            "Rectangle not found: (1 1 1 1)");
    }
}
