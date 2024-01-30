public class FranTests
{

    public FranTests()
    {
        // TODO Auto-generated constructor stub
    }
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    public static void main(String args[]) {
        System.out.println("ok...");
        SkipList<String, Rectangle> skl = new SkipList<>();
        Rectangle rec1 = new Rectangle(0, 0, 100, 100);
        Rectangle rec2 = new Rectangle(99, 99, 924, 924);
        Rectangle recNotValid = new Rectangle(-99, -99, 924, 924);
        KVPair<String, Rectangle> it1 = new KVPair<>("A", rec1);
        KVPair<String, Rectangle> it2 = new KVPair<>("B", rec2);
        KVPair<String, Rectangle> it3 = new KVPair<>("C",recNotValid);
        skl.insert(it3);
        skl.dump();
    }
}
