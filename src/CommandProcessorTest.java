import org.junit.Before;
import org.junit.Test;
import student.TestCase;

public class CommandProcessorTest extends TestCase {

    private CommandProcessor cmdp;

    @Before
    public void setUp() {
        cmdp = new CommandProcessor();
    }


    @Test
    public void testProcessor1() {
        String unrecognizedCommand = "delete r1 0 0 1024 1024";
        systemOut().clearHistory();
        cmdp.processor(unrecognizedCommand);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Unrecognized command.");
    }

}
