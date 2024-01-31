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

    private SkipList<String, Rectangle> skipList;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        skipList = new SkipList<>();
        // Inserting some rectangles with different keys and same keys
        skipList.insert(new KVPair<>("R1", new Rectangle(0, 0, 10, 10)));
        skipList.insert(new KVPair<>("R2", new Rectangle(10, 10, 20, 20)));
        skipList.insert(new KVPair<>("R2", new Rectangle(15, 15, 25, 25)));
        // Duplicate
        // key
        skipList.insert(new KVPair<>("R3", new Rectangle(20, 20, 30, 30)));
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
        skl.insert(it2);
        ArrayList<KVPair<String, Rectangle>> output2 = skl.search("B");
        assertEquals(output2.get(0).getKey(), "B");
        assertEquals(output2.get(1).getKey(), "B");
    }


    /**
     * Tests searching for a key that exists in the SkipList. This test ensures
     * that the search method can find and return all instances of a given key.
     */
    @Test
    public void testSearchForExistingKey() {
        ArrayList<KVPair<String, Rectangle>> result = skipList.search("R2");
        assertEquals(2, result.size()); // Expecting 2 results for "R2"
    }


    /**
     * Tests searching for a key that does not exist in the SkipList. This test
     * verifies that the search method correctly returns an empty list when the
     * searched key is not present.
     */
    @Test
    public void testSearchForNonExistingKey() {
        ArrayList<KVPair<String, Rectangle>> result = skipList.search("R4");
        assertTrue(result.isEmpty()); // No results expected for "R4"
    }


    /**
     * Tests searching for a key at different levels in the SkipList. This test
     * checks whether the search method can navigate through multiple levels
     * correctly and find all instances of a given key.
     */
    @Test
    public void testSearchAtDifferentLevels() {
        // Inserting more rectangles to potentially increase SkipList levels
        skipList.insert(new KVPair<>("R2", new Rectangle(30, 30, 40, 40)));
        skipList.insert(new KVPair<>("R2", new Rectangle(35, 35, 45, 45)));

        ArrayList<KVPair<String, Rectangle>> result = skipList.search("R2");
        assertEquals(4, result.size()); // 4 results expected for "R2"
    }


    /**
     * Tests searching in an empty SkipList. This test ensures that the search
     * method can handle an empty list scenario without errors and returns an
     * empty list.
     */
    @Test
    public void testSearchWithEmptySkipList() {
        SkipList<String, Rectangle> emptySkipList = new SkipList<>();
        ArrayList<KVPair<String, Rectangle>> result = emptySkipList.search(
            "R1");
        assertTrue(result.isEmpty()); // No results expected in an empty
                                      // SkipList
    }


    /**
     * Tests the order of the search results. This test ensures that the search
     * method returns the results in the correct order based on their insertion
     * order in the SkipList.
     */
    @Test
    public void testSearchResultOrder() {
        // This test ensures that the search results are in the correct order
        ArrayList<KVPair<String, Rectangle>> result = skipList.search("R2");
        // Verify the order of rectangles in the result
        assertEquals(10, result.get(1).getValue().getxCoordinate());
        assertEquals(10, result.get(1).getValue().getyCoordinate());
        assertEquals(20, result.get(1).getValue().getWidth());
        assertEquals(20, result.get(1).getValue().getHeight());
        assertEquals(15, result.get(0).getValue().getxCoordinate());
        assertEquals(15, result.get(0).getValue().getyCoordinate());
        assertEquals(25, result.get(0).getValue().getWidth());
        assertEquals(25, result.get(0).getValue().getHeight());
    }

}
