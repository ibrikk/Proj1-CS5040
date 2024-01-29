import org.junit.Before;
import org.junit.Test;
import student.TestCase;

public class RectangleTest extends TestCase {

    @Before
    public void setUp() {
        // Nothing Here
    }


    @Test
    public void testRectangleCreation() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertEquals(10, rect.getxCoordinate());
        assertEquals(20, rect.getyCoordinate());
        assertEquals(30, rect.getWidth());
        assertEquals(40, rect.getHeight());
    }


    @Test
    public void testInvalidRectangle() {
        Rectangle rect = new Rectangle(-1, -1, -1, -1);
        assertTrue(rect.isInvalid());
    }


    @Test
    public void testValidRectangle() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        assertFalse(rect.isInvalid());
    }


    @Test
    public void testIntersection() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(15, 15, 25, 25); // Overlaps with rect1
        assertTrue(rect1.intersect(rect2));
    }


    @Test
    public void testNoIntersection() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(50, 50, 10, 10); // Does not overlap
                                                         // with rect1
        assertFalse(rect1.intersect(rect2));
    }


    @Test
    public void testEquals() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 20);
        assertTrue(rect1.equals(rect2));
    }


    @Test
    public void testNotEquals() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(20, 20, 30, 30);
        assertFalse(rect1.equals(rect2));
    }


    @Test
    public void testToString() {
        Rectangle rect = new Rectangle(10, 20, 30, 40);
        assertEquals("10, 20, 30, 40", rect.toString());
    }
}
