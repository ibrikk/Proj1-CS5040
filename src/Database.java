import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation. Also note that the Database class
 * will have a clearer role in Project2, where we will have two data structures.
 * The Database class will then determine which command should be directed to
 * which data structure.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 * @version 2024-01-27
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    // This is an Iterator object over the SkipList to loop through it from
    // outside
    // the class.
    // You will need to define an extra Iterator for the intersections method.
    private Iterator<KVPair<String, Rectangle>> itr1; // only to traverse by
                                                      // value

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will add
     * the KVPair specified into the sorted SkipList appropriately
     *
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that
        if (!(isValidAscii(pair.getKey()))) {
            System.out.println("The name must begin with a letter, "
                + "and may contain letters, digits,"
                + " and underscore characters.");
            return;
        }
        if (pair.getValue().isInvalid()) {
            System.out.println("Rectangle rejected: (" + pair.getKey() + ", "
                + pair.getValue().toString() + ")");
            return;
        }
        list.insert(pair);
        System.out.println("Rectangle inserted: (" + pair.getKey() + ", " + pair
            .getValue().toString() + ")");
    }


    /**
     * Validates if a given string adheres to a specific ASCII format. The name
     * (key) must begin with a letter, and may contain letters, digits, and
     * underscore characters. The method checks each character of the string to
     * ensure it conforms to these rules.
     *
     * @param key
     *            The string to be validated.
     * @return true if the string is valid according to the specified ASCII
     *         format, false otherwise.
     */
    public boolean isValidAscii(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }

        // Check first character is a letter
        char firstChar = key.charAt(0);
        if (!((firstChar >= 'A' && firstChar <= 'Z') || (firstChar >= 'a'
            && firstChar <= 'z'))) {
            return false;
        }

        // Check remaining characters
        for (int i = 1; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
                || (ch >= '0' && ch <= '9') || (ch == '_'))) {
                return false;
            }
        }

        return true;
    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        list.remove(name);
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        Rectangle rect = new Rectangle(x, y, w, h);
        if (rect.isInvalid()) {
            System.out.println("Rectangle rejected: (" + rect.toString() + ")");
            return;
        }
        list.removeByValue(rect);
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
     *
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        Rectangle regionSearch = new Rectangle(x, y, w, h);
        // if any numbers are negative, then we have an invalid search
        // we can have w and height that fall outside of 1024x1024
        if (regionSearch.isInvalidRegionSearch()) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
            return;
        }

        // Initialize iterator
        Iterator<KVPair<String, Rectangle>> itr = list.iterator();

        // Iterate through the SkipList using the iterator
        System.out.println("Rectangles intersecting region (" + x + ", " + y
            + ", " + w + ", " + h + "):");
        while (itr.hasNext()) {
            KVPair<String, Rectangle> pair = itr.next();
            Rectangle thisRectangle = pair.getValue();
            if (regionSearch.intersect(thisRectangle)) {
                System.out.println("(" + pair.getKey() + ", " + thisRectangle
                    + ")");
            }
        }
    }


    /**
     * Prints out all the rectangles that intersect each other. Note that it is
     * better not to implement an intersections method in the SkipList class as
     * the SkipList needs to be agnostic about the fact that it is storing
     * Rectangles.
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        itr1 = list.iterator();
        while (itr1.hasNext()) {
            KVPair<String, Rectangle> outerPair = itr1.next();
            Rectangle outerRectangle = outerPair.getValue();
            Iterator<KVPair<String, Rectangle>> itrInner = list.iterator();
            while (itrInner.hasNext()) {
                KVPair<String, Rectangle> innerPair = itrInner.next();
                Rectangle innerRectangle = innerPair.getValue();
                if (outerRectangle.intersect(innerRectangle)
                    && outerRectangle != innerRectangle) {
                    System.out.println("(" + outerPair.getKey() + ", "
                        + outerRectangle + " | " + innerPair.getKey() + ", "
                        + innerRectangle + ")");
                }
            }
        }
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     *
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        list.search(name);
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }


    /**
     * Returns the total size of the database ie the size of the SkipList
     *
     * @return size
     */
    public int size() {
        return list.size();
    }
}
