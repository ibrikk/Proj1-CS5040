import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

public class DatabaseTest extends TestCase {

    Database db;
    String errorMessage1 =
        "The name must begin with a letter, and may contain letters, digits, and underscore characters.";

    @Before
    public void setUp() {
        db = new Database();
    }


    @After
    public void cleanUp() {
        systemOut().clearHistory();
    }


    @Test
    public void testInsert1() {
        KVPair<String, Rectangle> pair = new KVPair<>("5a_", new Rectangle(0, 0,
            1024, 1024));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    @Test
    public void testInsert2() {
        KVPair<String, Rectangle> pair = new KVPair<>("a_:", new Rectangle(0, 0,
            1024, 1024));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    @Test
    public void testInsert() {
        KVPair<String, Rectangle> pair = new KVPair<>("A", new Rectangle(-5, 0,
            1024, 1024));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Rectangle rejected: (A, -5, 0, 1024, 1024)");
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


    @Test
    public void testValidNameWithLettersAndDigits() {
        KVPair<String, Rectangle> pair = new KVPair<>("Name123", new Rectangle(
            10, 10, 100, 100));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output,
            "Rectangle inserted: (Name123, 10, 10, 100, 100)");
    }


    @Test
    public void testValidNameWithUnderscore() {
        KVPair<String, Rectangle> pair = new KVPair<>("Name_123", new Rectangle(
            10, 10, 100, 100));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output,
            "Rectangle inserted: (Name_123, 10, 10, 100, 100)");
    }


    @Test
    public void testNameStartingWithDigit() {
        KVPair<String, Rectangle> pair = new KVPair<>("1Name", new Rectangle(10,
            10, 100, 100));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    @Test
    public void testNameWithSpecialCharacters() {
        KVPair<String, Rectangle> pair = new KVPair<>("Name#123", new Rectangle(
            10, 10, 100, 100));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    @Test
    public void testEmptyName() {
        KVPair<String, Rectangle> pair = new KVPair<>("", new Rectangle(10, 10,
            100, 100));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, errorMessage1);
    }


    @Test
    public void testNullName() {
        KVPair<String, Rectangle> pair = new KVPair<>(null, new Rectangle(10,
            10, 100, 100));
        systemOut().clearHistory();
        db.insert(pair);
        String output = systemOut().getHistory();
        assertTrue(output.contains(errorMessage1));
    }
}
