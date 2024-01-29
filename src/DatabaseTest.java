import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

public class DatabaseTest extends TestCase {

    private Database db;
    private String errorMessage1 =
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


    // Test for null string
    @Test
    public void testIsValidAsciiWithNull() {
        assertFalse(db.isValidAscii(null));
    }


    // Test for empty string
    @Test
    public void testIsValidAsciiWithEmptyString() {
        assertFalse(db.isValidAscii(""));
    }


    // Test for string starting with a digit
    @Test
    public void testIsValidAsciiWithDigitStart() {
        assertFalse(db.isValidAscii("1abc"));
    }


    // Test for string starting with an underscore
    @Test
    public void testIsValidAsciiWithUnderscoreStart() {
        assertFalse(db.isValidAscii("_abc"));
    }


    // Test for string with invalid characters
    @Test
    public void testIsValidAsciiWithSpecialChars() {
        assertFalse(db.isValidAscii("abc#123"));
    }


    // Test for string with spaces
    @Test
    public void testIsValidAsciiWithSpaces() {
        assertFalse(db.isValidAscii("abc 123"));
    }


    // Test for valid string
    @Test
    public void testIsValidAsciiWithValidString() {
        assertTrue(db.isValidAscii("abc_123"));
    }


    // Tests for each individual condition in the logical expression
    @Test
    public void testIsValidAsciiWithUpperBoundChars() {
        assertTrue(db.isValidAscii("Z"));
        assertTrue(db.isValidAscii("A"));
        assertTrue(db.isValidAscii("z"));
        assertTrue(db.isValidAscii("a"));
        assertFalse(db.isValidAscii("0"));
        assertFalse(db.isValidAscii("9"));
        assertFalse(db.isValidAscii("_"));
    }


    @Test
    public void testIsValidAsciiWithLowerBoundChars() {
        assertFalse(db.isValidAscii("["));
        assertFalse(db.isValidAscii("@"));
        assertFalse(db.isValidAscii("`"));
        assertFalse(db.isValidAscii("{"));
        assertFalse(db.isValidAscii("/"));
        assertFalse(db.isValidAscii(":"));
    }
}
