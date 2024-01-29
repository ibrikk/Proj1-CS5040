import org.junit.Before;
import org.junit.Test;
import student.TestCase;

public class DatabaseTest extends TestCase {

    Database db;

    @Before
    public void setUp() {
        db = new Database();
    }


    @Test
    public void testInsert1() {
        KVPair<String, Rectangle> pair = new KVPair<>("5a_", new Rectangle(0, 0,
            1024, 1024));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "The name must begin with a letter, "
            + "and may contain letters, digits,"
            + " and underscore characters.");
    }
    
    @Test
    public void testInsert2() {
        KVPair<String, Rectangle> pair = new KVPair<>("a_:", new Rectangle(0, 0,
            1024, 1024));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "The name must begin with a letter, "
            + "and may contain letters, digits,"
            + " and underscore characters.");
    }
    
    @Test
    public void testInsert3() {
        KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(-5, 0,
            1024, 1024));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Rectangle rejected: (" + "A" + ", "
            + "-5, 0, 1024, 1024" + ")");
    }
    
    @Test
    public void testInsert4() {
        KVPair<String, Rectangle> pair = new KVPair<>(" ", new Rectangle(0, 0,
            1024, 1024));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "The name must begin with a letter, "
            + "and may contain letters, digits,"
            + " and underscore characters.");
    }
}
