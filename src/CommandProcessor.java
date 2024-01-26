/**
 * The purpose of this class is to parse a single line from the command text
 * file according to the format specified in the project specs.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2021-08-23
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands
     * to.
     *
     * @param dataIn
     *            the database object to manipulate
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method parses keywords in the line and calls methods in the database
     * as
     * required. Each line command will be specified by one of the keywords to
     * perform the actions. These actions are performed on specified objects and
     * include insert, remove, regionsearch, search, and dump. If the command in
     * the
     * file line is not one of these, an appropriate message will be written in
     * the
     * console. This processor method is called for each line in the file. Note
     * that
     * the methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     *
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // converts the string of the line into an
        // array of its space (" ") delimited elements
        String[] arr = line.split("\\s{1,}");
        String command = arr[0]; // the command will be the first of these
                                 // elements
        // calls the insert function and passes the correct
        // parameters by converting the string integers into
        // their Integer equivalent, trimming the whitespace
        if (command.equals("insert")) {
            // Calls insert
            if (isEligible(arr[2], arr[3], arr[4], arr[5]) && arr[1] != null
                && !arr[1].isEmpty()) {
                Rectangle rectangle = new Rectangle(Integer.parseInt(arr[2]),
                    Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer
                        .parseInt(arr[5]));
                KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>(
                    arr[1], rectangle);
                data.insert(pair);
            }
        }
        // calls the appropriate remove method based on the
        // number of white space delimited strings in the line
        else if (command.equals("remove")) {
            // checks the number of white space delimited strings in the line
            int numParam = arr.length - 1;
            if (numParam == 1) {
                // Calls remove by name
                if (arr[0] != null && !arr[0].isEmpty()) {
                    data.remove(arr[0]);
                }

            }
            else if (numParam == 4) {
                // Calls remove by coordinate, converting string
                // integers into their Integer equivalent minus whitespace
                if (isEligible(arr[1], arr[2], arr[3], arr[4])) {
                    data.remove(Integer.parseInt(arr[1]), Integer.parseInt(
                        arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(
                            arr[4]));
                }
            }

        }
        else if (command.equals("regionsearch")) {
            // calls the regionsearch method for a set of coordinates
            // the string integers in the line will be trimmed of whitespace

        }
        else if (command.equals("intersections")) {
            // calls the intersections method, no parameters to be passed
            // (see the intersections JavaDoc in the Database class for more
            // information)

        }
        else if (command.equals("search")) {
            // calls the search method for a name of object

        }
        else if (command.equals("dump")) {
            // calls the dump method for the database, takes no parameters
            // (see the dump() JavaDoc in the Database class for more
            // information)

        }
        else {
            // the first white space delimited string in the line is not
            // one of the commands which can manipulate the database,
            // a message will be written to the console
            System.out.println("Unrecognized command.");
        }
    }


    private boolean isEligible(
        String x,
        String y,
        String width,
        String length) {
        return !(Integer.parseInt(x) < 0 || Integer.parseInt(y) < 0 || Integer
            .parseInt(width) <= 0 || Integer.parseInt(length) <= 0);
         
    }

}
