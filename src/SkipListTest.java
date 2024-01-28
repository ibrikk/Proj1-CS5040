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
    @Before
    public void setUp() {
        // Nothing Here
    }


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


    @Test
    public void testAdjustHead() {
        SkipList<String, Rectangle> skl = new SkipList<>();
        int newLevel = 3;
        skl.adjustHead(newLevel);
        assertEquals(skl.size(), 0);
        assertEquals(skl.getHeadLevel(), newLevel);
        assertNull(skl.getHeadElement());

    }
}
