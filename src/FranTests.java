/**
 * Frantests
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 * @version 2024-01-27
 */
public class FranTests {

    /**
     * Constructor for the FranTests class.
     */
    public FranTests() {
        // Constructor stub
    }


    /**
     * The main method for executing the test cases.
     * This method creates a SkipList and inserts various rectangles into it.
     * It then calls the dump method of the SkipList to display its contents.
     * This serves as a demonstration of how the SkipList handles different
     * types of rectangle insertions.
     *
     * @param args
     *            Command line arguments (not used).
     */
    public static void main(String args[]) {
        System.out.println("ok...");

        // Create a new SkipList for testing
        SkipList<String, Rectangle> skl = new SkipList<>();

        // Create some rectangle instances
        Rectangle rec1 = new Rectangle(0, 0, 100, 100); // A valid rectangle
        Rectangle rec2 = new Rectangle(99, 99, 924, 924); // Another valid
                                                          // rectangle
        Rectangle recNotValid = new Rectangle(-99, -99, 924, 924); // An invalid
                                                                   // rectangle

        // Create key-value pairs (KVPair) for the rectangles
        KVPair<String, Rectangle> it1 = new KVPair<>("A", rec1);
        KVPair<String, Rectangle> it2 = new KVPair<>("B", rec2);
        KVPair<String, Rectangle> it3 = new KVPair<>("C", recNotValid);

        // Insert rectangles into the SkipList
        skl.insert(it3); // Note: This rectangle is invalid and may be rejected

        // Perform a dump operation to display the contents of the SkipList
        skl.dump();
    }
}
