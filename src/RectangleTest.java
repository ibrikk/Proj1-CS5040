import org.junit.Before;
import org.junit.Test;
import student.TestCase;

/**
 * This class tests rectangle methods
 * 
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-01-27
 */
public class RectangleTest extends TestCase {

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        // Nothing Here
    }


    /**
     * Tests if two intersecting rectangles are correctly identified as
     * intersecting.
     */
    @Test
    public void testIntersectingRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 30, 30);
        Rectangle rect2 = new Rectangle(20, 20, 40, 40); // Overlaps with rect1
        assertTrue(rect1.intersect(rect2));
        assertTrue(rect2.intersect(rect1)); // Test symmetry
    }


    /**
     * Tests if two non-intersecting rectangles are correctly identified as not
     * intersecting.
     */
    @Test
    public void testNonIntersectingRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(40, 40, 20, 20); // Does not overlap
                                                         // with rect1
        assertFalse(rect1.intersect(rect2));
        assertFalse(rect2.intersect(rect1)); // Test symmetry
    }


    /**
     * Tests if rectangles touching edges are correctly identified as
     * non-intersecting.
     */
    @Test
    public void testNotIntersectingEdgeRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(30, 10, 20, 20); // Touches the edge of
                                                         // rect1
        assertFalse(rect1.intersect(rect2));

        assertFalse(rect2.intersect(rect1));
    }


    /**
     * Tests if two identical rectangles are correctly identified as equal.
     */
    @Test
    public void testEquals() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 20);
        assertTrue(rect1.equals(rect2));
        assertTrue(rect2.equals(rect1)); // Test symmetry
    }


    /**
     * Tests if different rectangles are correctly identified as not equal.
     */
    @Test
    public void testNotEquals() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(20, 20, 30, 30);
        assertFalse(rect1.equals(rect2));
        assertFalse(rect2.equals(rect1));
    }


    /**
     * Tests equality comparison between a Rectangle object and a non-Rectangle
     * object.
     */
    @Test
    public void testEqualsWithNonRectangle() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        assertFalse(rect.equals(new Object()));
    }


    /**
     * Tests equality comparison of a Rectangle object with a null reference.
     */
    @Test
    public void testEqualsWithNull() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        assertFalse(rect.equals(null));
    }


    /**
     * Tests equality of a Rectangle object with itself.
     */
    @Test
    public void testEqualityWithItself() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        assertTrue(rect.equals(rect));
    }


    /**
     * Tests inequality of two Rectangle objects with slightly different
     * dimensions.
     */
    @Test
    public void testInequalityWithSlightlyDifferentRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 21);
        assertFalse(rect1.equals(rect2));
    }


    /**
     * Tests inequality of two Rectangle objects with a slight mutation.
     */
    @Test
    public void testInequalityAfterMutation() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 11, 20, 20);
        assertFalse(rect1.equals(rect2));
    }


    /**
     * Tests intersection of two rectangles at their boundary.
     */
    @Test
    public void testIntersectionAtBoundary() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(30, 30, 1, 1);
        assertFalse(rect1.intersect(rect2));
    }


    /**
     * Tests lack of intersection for rectangles just outside each other's
     * boundary.
     */
    @Test
    public void testNoIntersectionJustOutsideBoundary() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(31, 31, 10, 10);
        assertFalse(rect1.intersect(rect2));
    }


    /**
     * Tests intersection of rectangles touching each other at the edges.
     */
    @Test
    public void testRectanglesTouchingEdges() {
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(9, 0, 10, 10);
        Rectangle rect3 = new Rectangle(0, 9, 10, 10);
        assertTrue(rect1.intersect(rect2));
        assertTrue(rect1.intersect(rect3));
    }


    /**
     * Tests intersection of adjacent rectangles.
     */
    @Test
    public void testAdjacentRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 10, 10);
        Rectangle rect2 = new Rectangle(19, 10, 10, 10);
        assertTrue(rect1.intersect(rect2));
    }


    /**
     * Tests inequality of different rectangles.
     */
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


    /**
     * Tests inequality of a rectangle contained within another rectangle.
     */
    @Test
    public void testContainedRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 30, 30);
        Rectangle rect2 = new Rectangle(15, 15, 5, 5);
        assertFalse(rect1.equals(rect2));
    }


    /**
     * Tests equality of two identical rectangles.
     */
    @Test
    public void testEqualRectangles() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 20);
        assertTrue(rect1.equals(rect2));
    }


    /**
     * Tests invalidity of a rectangle with a negative x-coordinate.
     */
    @Test
    public void testInvalidXCoordinate() {
        Rectangle rect = new Rectangle(-1, 0, 10, 10);
        assertTrue(rect.isInvalid());
    }


    /**
     * Tests invalidity of a rectangle with a negative y-coordinate.
     */
    @Test
    public void testInvalidYCoordinate() {
        Rectangle rect = new Rectangle(0, -1, 10, 10);
        assertTrue(rect.isInvalid());
    }


    /**
     * Tests invalidity of a rectangle with zero width.
     */
    @Test
    public void testInvalidWidthZero() {
        Rectangle rect = new Rectangle(0, 0, 0, 10);
        assertTrue(rect.isInvalid());
    }


    /**
     * Tests invalidity of a rectangle with negative width.
     */
    @Test
    public void testInvalidWidthNegative() {
        Rectangle rect = new Rectangle(0, 0, -10, 10);
        assertTrue(rect.isInvalid());
    }


    /**
     * Tests invalidity of a rectangle with zero height.
     */
    @Test
    public void testInvalidHeightZero() {
        Rectangle rect = new Rectangle(0, 0, 10, 0);
        assertTrue(rect.isInvalid());
    }


    /**
     * Tests invalidity of a rectangle with negative height.
     */
    @Test
    public void testInvalidHeightNegative() {
        Rectangle rect = new Rectangle(0, 0, 10, -10);
        assertTrue(rect.isInvalid());
    }


    /**
     * Tests the validity of a properly defined rectangle.
     */
    @Test
    public void testValidRectangle() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        assertFalse(rect.isInvalid());
    }

}
