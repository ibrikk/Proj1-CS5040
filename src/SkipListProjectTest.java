//import java.io.IOException;
//
//import student.TestCase;
///**
// * This class is used to test stuff as we are developing
// *
// * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
// *
// * @version 2021-08-23
// * @param <K>
// *            Key
// * @param <V>
// *            Value
// */
//
//public class SkipListProjectTest extends TestCase {
//
//    /**
//     * Setup tests
//     */
//    public void setUp() {
//        // Nothing Here
//    }
//
//
//    public void testMain() {
//// String[] args = { "duplicate_test.txt" };
//        String[] args = { "P1test1.txt" };
//        systemOut().clearHistory();
//        SkipListProject.main(args);
//        String output = systemOut().getHistory();
//        String referenceOutput = null;
//        try {
//            referenceOutput = SkipListProject.readFile("duplicate_testout.txt");
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        assertFuzzyEquals(referenceOutput, output);
//    }
//}
