import student.TestCase;

public class SkipListTest extends TestCase {
    public void setUp() {
        // Nothing Here
    }


    public void testInsert() {
        SkipList skl = new SkipList();
        Rectangle rec1 = new Rectangle(0, 0, 100, 100);
        Rectangle rec2 = new Rectangle(99, 99, 924, 924);
        KVPair<String, Rectangle> it1 = new KVPair<>("A", rec1);
        KVPair<String, Rectangle> it2 = new KVPair<>("B", rec2);
        skl.insert(it1);
        assertEquals(skl.size(), 1);
        
        skl.insert(it2);
        assertEquals(skl.size(), 2);
        
        skl.insert(it1);
        assertEquals(skl.size(), 3);
    }
}
