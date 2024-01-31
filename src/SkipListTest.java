import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import student.TestCase;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * Test class for SkipList class.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-01-27
 */
public class SkipListTest extends TestCase {
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        // Nothing here
    }


    /**
     * Tests the insert method of the SkipList class.
     * Verifies that the size of the list is incremented correctly upon
     * insertion
     * and that the head element is as expected after insertions.
     */
    @Test
    public void testInsert() {
        SkipList<String, Rectangle> skl = new SkipList<>();
        Rectangle rec1 = new Rectangle(0, 0, 100, 100);
        Rectangle rec2 = new Rectangle(99, 99, 924, 924);
        KVPair<String, Rectangle> it1 = new KVPair<>("A", rec1);
        KVPair<String, Rectangle> it2 = new KVPair<>("B", rec2);
        skl.insert(it1);
        assertEquals(skl.size(), 1);
        assertNull(skl.getHeadElement());

        skl.insert(it2);
        assertEquals(skl.size(), 2);

        skl.insert(it1);
        assertEquals(skl.size(), 3);
    }


    /**
     * Tests a big insert
     */
    @Test
    public void testBigInsert() {
        SkipList<String, Rectangle> skl = new SkipList<>();
        Rectangle rec1 = new Rectangle(0, 0, 100, 100);
        Rectangle rec2 = new Rectangle(99, 99, 924, 924);
        Rectangle recNotValid = new Rectangle(-99, -99, 924, 924);
        KVPair<String, Rectangle> it1 = new KVPair<>("A", rec1);
        KVPair<String, Rectangle> it2 = new KVPair<>("B", rec2);
        KVPair<String, Rectangle> it3 = new KVPair<>("C", recNotValid);
        skl.insert(it3);
        skl.insert(it3);
        skl.insert(it3);
        skl.insert(it3);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                skl.insert(it1);
            }
            else {
                skl.insert(it2);
            }
        }
        assertEquals(skl.size(), 104);
        assertNull(skl.getHeadElement());
    }


    /**
     * Tests the adjustHead method of the SkipList class.
     * Verifies that the level of the head node is adjusted properly and
     * that the size remains unchanged after adjusting the head.
     */
    @Test
    public void testAdjustHead() {
        SkipList<String, Rectangle> skl = new SkipList<>();
        int newLevel = 3;
        skl.adjustHead(newLevel);
        assertEquals(skl.size(), 0);
        assertEquals(skl.getHeadLevel(), newLevel);
        assertNull(skl.getHeadElement());
    }


    /**
     * Tests the dump method on an empty SkipList.
     * Verifies the correct output is generated when the SkipList is empty.
     */
    @Test
    public void testDumpEmptySkipList() {
        Database db = new Database();
        systemOut().clearHistory();
        db.dump();
        String output = systemOut().getHistory();
        String expectedOutput = "SkipList dump:\nNode with depth 1, "
            + "value null\nSkipList size is: 0\n";
        assertEquals(expectedOutput, output);
    }


    /**
     * Tests the dump method on a SkipList with a single element.
     * Verifies the correct output is generated for a list containing one
     * element.
     */
    @Test
    public void testDumpSingleElementSkipList() {
        Database db = new Database();
        db.insert(new KVPair<>("key1", new Rectangle(1, 2, 3, 4)));
        systemOut().clearHistory();
        db.dump();

        String output = systemOut().getHistory();
        assertTrue(output.startsWith("SkipList dump:\n"));
        assertTrue(output.contains("value (key1, 1, 2, 3, 4)"));
        assertTrue(output.endsWith("SkipList size is: 1\n"));
    }


    /**
     * Tests the dump method on a SkipList with multiple elements.
     * Verifies the correct output is generated for a list containing several
     * elements.
     */
    @Test
    public void testDumpMultipleElementsSkipList() {
        Database db = new Database();
        db.insert(new KVPair<>("key1", new Rectangle(1, 2, 3, 4)));
        db.insert(new KVPair<>("key2", new Rectangle(5, 6, 7, 8)));
        systemOut().clearHistory();
        db.dump();

        String output = systemOut().getHistory();
        assertTrue(output.startsWith("SkipList dump:\n"));
        assertTrue(output.contains("value (key1, 1, 2, 3, 4)"));
        assertTrue(output.contains("value (key2, 5, 6, 7, 8)"));
        assertTrue(output.endsWith("SkipList size is: 2\n"));
    }


    /**
     * Tests the search method of the SkipList class.
     */
    @Test
    public void testSearch() {
        SkipList<String, Rectangle> skl = new SkipList<>();
        Rectangle rec1 = new Rectangle(0, 0, 100, 100);
        Rectangle rec2 = new Rectangle(99, 99, 924, 924);
        KVPair<String, Rectangle> it1 = new KVPair<>("A", rec1);
        KVPair<String, Rectangle> it2 = new KVPair<>("B", rec2);
        skl.insert(it1);
        skl.insert(it2);
        ArrayList<KVPair<String, Rectangle>> output1 = skl.search("B");
        assertEquals(output1.get(0).getKey(), "B");
        ArrayList<KVPair<String, Rectangle>> output2 = skl.search("B");
        assertEquals(output2.get(0).getKey(), "B");
    }

}
