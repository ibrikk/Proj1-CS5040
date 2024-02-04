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
    private SkipList<String, Rectangle> skipList1;
    private SkipList<String, Rectangle> skipList2;

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

        skipList1 = new SkipList<>();

        skipList2 = new SkipList<>();
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


    /**
     * Test removing an existing rectangle by name.
     * Verifies if the rectangle is correctly removed and
     * if the size of the SkipList is updated accordingly.
     */
    @Test
    public void testRemoveExisting() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        skipList1.insert(new KVPair<>("A", rect));
        assertEquals(1, skipList1.size());
        KVPair<String, Rectangle> removed = skipList1.remove("A");
        assertNotNull(removed);
        assertEquals("A", removed.getKey());
        assertEquals(rect, removed.getValue());
        assertEquals(0, skipList1.size());
    }


    /**
     * Test removing a rectangle by a non-existing name.
     * Verifies that the method returns null and that the
     * size of the SkipList remains unchanged.
     */
    @Test
    public void testRemoveNonExisting() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        skipList1.insert(new KVPair<>("A", rect));
        assertEquals(1, skipList1.size());
        KVPair<String, Rectangle> removed = skipList1.remove("B");
        assertNull(removed);
        assertEquals(1, skipList1.size());
    }


    /**
     * Test removing one of multiple rectangles with the same name.
     * Verifies that after removal, the size of the SkipList is decremented.
     */
    @Test
    public void testRemoveOneOfMultiple() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(30, 30, 40, 40);
        skipList1.insert(new KVPair<>("A", rect1));
        skipList1.insert(new KVPair<>("A", rect2));
        assertEquals(2, skipList1.size());
        skipList1.remove("A");
        assertEquals(1, skipList1.size());
    }


    /**
     * Test removing a rectangle from an empty SkipList.
     * Verifies that the method returns null and the size remains zero.
     */
    @Test
    public void testRemoveWithEmptyList() {
        KVPair<String, Rectangle> removed = skipList1.remove("A");
        assertNull(removed);
        assertEquals(0, skipList1.size());
    }


    /**
     * Tests the removal of an existing rectangle from the SkipList.
     * Ensures that a rectangle that exists within the SkipList is correctly
     * removed, and the size of the SkipList is decremented accordingly.
     */
    @Test
    public void testRemoveExistingRectangle() {
        Rectangle rect = new Rectangle(10, 10, 5, 5);
        KVPair<String, Rectangle> pair = new KVPair<>("R1", rect);
        skipList2.insert(pair);

        assertEquals(1, skipList2.size());
        KVPair<String, Rectangle> removedPair = skipList2.removeByValue(rect);
        assertNotNull(removedPair);
        assertEquals("R1", removedPair.getKey());
        assertEquals(0, skipList2.size());
    }


    /**
     * Tests the attempt to remove a rectangle that does not exist in the
     * SkipList.
     * Verifies that the method correctly identifies non-existent rectangles,
     * does not alter the size of the SkipList, and returns null.
     */
    @Test
    public void testRemoveNonExistingRectangle() {
        Rectangle rect = new Rectangle(10, 10, 5, 5);
        KVPair<String, Rectangle> removedPair = skipList2.removeByValue(rect);
        assertNull(removedPair);
        assertEquals(0, skipList2.size());
    }


    /**
     * Tests the behavior of the removal method when a null value is passed.
     * This test ensures that attempting to remove a null value correctly
     * results in no change to the SkipList and the appropriate message is
     * printed.
     */
    @Test
    public void testRemoveWithNullValue() {
        systemOut().clearHistory();
        skipList2.removeByValue(null);
        String output = systemOut().getHistory();
        assertFuzzyEquals(output, "Rectangle not found: null");
    }


    /**
     * Tests the removal of one rectangle when multiple rectangles are present
     * in the SkipList. This test verifies that the correct rectangle is removed
     * based on the value and that the SkipList size is updated correctly.
     */
    @Test
    public void testRemoveOneOfMultipleRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 5, 5);
        Rectangle rect2 = new Rectangle(15, 15, 5, 5);
        skipList2.insert(new KVPair<>("R1", rect1));
        skipList2.insert(new KVPair<>("R2", rect2));

        assertEquals(2, skipList2.size());
        KVPair<String, Rectangle> removedPair = skipList2.removeByValue(rect1);
        assertNotNull(removedPair);
        assertEquals("R1", removedPair.getKey());
        assertEquals(1, skipList2.size());
    }


    /**
     * Tests the removal of elements by key in a SkipList. Verifies that
     * elements
     * can be successfully removed using their keys, and that the size of the
     * SkipList is updated accordingly. It ensures that removing elements
     * decreases
     * the size of the SkipList and that the removed elements are correctly
     * identified.
     */
    @Test
    public void testRemoveKey() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Rectangle rect1 = new Rectangle(15, 15, 25, 25);
        Rectangle rect2 = new Rectangle(100, 100, 100, 100);
        skipList1.insert(new KVPair<>("A", rect));
        skipList1.insert(new KVPair<>("B", rect1));
        skipList1.insert(new KVPair<>("C", rect2));
        assertEquals(3, skipList1.size());
        KVPair<String, Rectangle> removed1 = skipList1.remove("A");
        KVPair<String, Rectangle> removed2 = skipList1.remove("B");
        assertNotNull(removed1);
        assertNotNull(removed2);
        assertEquals(1, skipList1.size());
        KVPair<String, Rectangle> removed3 = skipList1.remove("C");
        assertNotNull(removed3);
        assertEquals(0, skipList1.size());
    }


    /**
     * Tests the removal of elements by value in a SkipList. This method
     * verifies
     * that elements can be successfully removed using their values
     * (rectangles),
     * and ensures that the size of the SkipList is correctly updated after
     * removal.
     * It checks that each removal operation effectively decreases the size of
     * the
     * SkipList and that the specific elements intended for removal are indeed
     * removed.
     */
    @Test
    public void testRemoveValue() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Rectangle rect1 = new Rectangle(15, 15, 25, 25);
        Rectangle rect2 = new Rectangle(100, 100, 100, 100);
        skipList1.insert(new KVPair<>("A", rect));
        skipList1.insert(new KVPair<>("B", rect1));
        skipList1.insert(new KVPair<>("C", rect2));
        assertEquals(3, skipList1.size());
        KVPair<String, Rectangle> removed1 = skipList1.removeByValue(rect);
        assertNotNull(removed1);
        KVPair<String, Rectangle> removed2 = skipList1.removeByValue(rect1);
        assertNotNull(removed2);
        assertEquals(1, skipList1.size());
        KVPair<String, Rectangle> removed3 = skipList1.removeByValue(rect2);
        assertNotNull(removed3);
        assertEquals(0, skipList1.size());
    }


    /**
     * Tests the removal of elements by value in a SkipList. This method
     * verifies
     * that elements can be successfully removed using their values
     * (rectangles),
     * and ensures that the size of the SkipList is correctly updated after
     * removal.
     * It checks that each removal operation effectively decreases the size of
     * the
     * SkipList and that the specific elements intended for removal are indeed
     * removed.
     */
    @Test
    public void testRemoveValue2() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Rectangle rect1 = new Rectangle(15, 15, 25, 25);
        Rectangle rect2 = new Rectangle(100, 100, 100, 100);
        skipList1.insert(new KVPair<>("A", rect));
        skipList1.insert(new KVPair<>("B", rect1));
        skipList1.insert(new KVPair<>("C", rect2));
        assertEquals(3, skipList1.size());
        KVPair<String, Rectangle> removed1 = skipList1.removeByValue(rect1);
        assertNotNull(removed1);
        assertEquals(2, skipList1.size());
    }

}
