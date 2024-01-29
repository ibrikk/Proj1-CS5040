import org.junit.Before;
import org.junit.Test;
import student.TestCase;

public class RectangleTest extends TestCase {

    @Before
    public void setUp() {
        // Nothing Here
    }


    @Test
    public void testIntersectingRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 30, 30);
        Rectangle rect2 = new Rectangle(20, 20, 40, 40); // Overlaps with rect1
        assertTrue(rect1.intersect(rect2));
        assertTrue(rect2.intersect(rect1)); // Test symmetry
    }


    @Test
    public void testNonIntersectingRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(40, 40, 20, 20); // Does not overlap
                                                         // with rect1
        assertFalse(rect1.intersect(rect2));
        assertFalse(rect2.intersect(rect1)); // Test symmetry
    }


    @Test
    public void testIntersectingEdgeRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(30, 10, 20, 20); // Touches the edge of
                                                         // rect1
        assertFalse(rect1.intersect(rect2)); // Adjust based on your definition
                                             // of intersect
        assertFalse(rect2.intersect(rect1));
    }


    @Test
    public void testEquals() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 20);
        assertTrue(rect1.equals(rect2));
        assertTrue(rect2.equals(rect1)); // Test symmetry
    }


    @Test
    public void testNotEquals() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(20, 20, 30, 30);
        assertFalse(rect1.equals(rect2));
        assertFalse(rect2.equals(rect1));
    }


    @Test
    public void testEqualsWithNonRectangle() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        assertFalse(rect.equals(new Object()));
    }


    @Test
    public void testEqualsWithNull() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        assertFalse(rect.equals(null));
    }


    @Test
    public void testEqualityWithItself() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        assertTrue(rect.equals(rect)); // Should always be true, tests
                                       // self-check
    }


    @Test
    public void testInequalityWithSlightlyDifferentRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 21); // Slightly different
                                                         // height
        assertFalse(rect1.equals(rect2)); // Should be false
    }


    @Test
    public void testInequalityAfterMutation() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 11, 20, 20); // Y coordinate off by
                                                         // 1
        assertFalse(rect1.equals(rect2)); // Should catch mutation where
                                          // equality is replaced with true
    }


    @Test
    public void testIntersectionAtBoundary() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(30, 30, 1, 1); // Touches the corner of
                                                       // rect1
        assertFalse(rect1.intersect(rect2)); // Should catch mutation where
                                             // comparison is replaced with true
    }


    @Test
    public void testNoIntersectionJustOutsideBoundary() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(31, 31, 10, 10); // Just outside the
                                                         // corner of rect1
        assertFalse(rect1.intersect(rect2)); // Should be false
    }


    @Test
    public void testRectanglesTouchingEdges() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(10, 0, 10, 10); // Touches rect1 at the
                                                        // edge
        Rectangle rect3 = new Rectangle(0, 10, 10, 10); // Touches rect1 at the
                                                        // corner
        assertFalse(rect1.intersect(rect2));
        assertFalse(rect1.intersect(rect3));
    }


    @Test
    public void testAdjacentRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 10, 10);
        Rectangle rect2 = new Rectangle(20, 10, 10, 10); // Starts where rect1
                                                         // ends
        assertFalse(rect1.intersect(rect2));
    }


    @Test
    public void testDifferentRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 21);
        Rectangle rect3 = new Rectangle(10, 11, 20, 20);
        Rectangle rect4 = new Rectangle(11, 10, 20, 20);
        assertFalse(rect1.equals(rect2));
        assertFalse(rect1.equals(rect3));
        assertFalse(rect1.equals(rect4));
    }


    @Test
    public void testContainedRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 30, 30);
        Rectangle rect2 = new Rectangle(15, 15, 5, 5); // Fully inside rect1
        assertFalse(rect1.equals(rect2));
    }


    @Test
    public void testEqualRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 20);
        assertTrue(rect1.equals(rect2));
    }


    // Test for negative x-coordinate
    @Test
    public void testInvalidXCoordinate() {
        Rectangle rect = new Rectangle(-1, 0, 10, 10);
        assertTrue(rect.isInvalid());
    }


    // Test for negative y-coordinate
    @Test
    public void testInvalidYCoordinate() {
        Rectangle rect = new Rectangle(0, -1, 10, 10);
        assertTrue(rect.isInvalid());
    }


    // Test for zero width
    @Test
    public void testInvalidWidthZero() {
        Rectangle rect = new Rectangle(0, 0, 0, 10);
        assertTrue(rect.isInvalid());
    }


    // Test for negative width
    @Test
    public void testInvalidWidthNegative() {
        Rectangle rect = new Rectangle(0, 0, -10, 10);
        assertTrue(rect.isInvalid());
    }


    // Test for zero height
    @Test
    public void testInvalidHeightZero() {
        Rectangle rect = new Rectangle(0, 0, 10, 0);
        assertTrue(rect.isInvalid());
    }


    // Test for negative height
    @Test
    public void testInvalidHeightNegative() {
        Rectangle rect = new Rectangle(0, 0, 10, -10);
        assertTrue(rect.isInvalid());
    }


    // Test for valid rectangle
    @Test
    public void testValidRectangle() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        assertFalse(rect.isInvalid());
    }

}
