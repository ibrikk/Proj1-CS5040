/**
 * This class holds the coordinates and dimensions of a rectangle and methods to
 * check if it intersects or has the same coordinates as an other rectangle.
 *
 * @author Ibrahim Khalilov {ibrahimk}, Francisca Wood {franciscawood}
 *
 * @version 2024-01-27
 */
public class Rectangle {
    // the x coordinate of the rectangle
    private int xCoordinate;
    // the y coordinate of the rectangle
    private int yCoordinate;
    // the distance from the x coordinate the rectangle spans
    private int width;
    // the distance from the y coordinate the rectangle spans
    private int height;

    /**
     * Creates an object with the values to the parameters given in the
     * xCoordinate, yCoordinate, width, height
     *
     * @param x
     *            x-coordinate of the rectangle
     * @param y
     *            y-coordinate of the rectangle
     * @param w
     *            width of the rectangle
     * @param h
     *            height of the rectangle
     */
    public Rectangle(int x, int y, int w, int h) {
        xCoordinate = x;
        yCoordinate = y;
        width = w;
        height = h;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }


    /**
     * Getter for the width
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Getter for the height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Checks if the invoking rectangle intersects with rec.
     *
     * @param r2
     *            Rectangle parameter
     * @return true if the rectangle intersects with rec, false if not
     */
    public boolean intersect(Rectangle r2) {
        // Check for null
        if (r2 == null) {
            return false;
        }

        // Return true if the rectangles intersect, false otherwise
        return !(xCoordinate + width - 1 < r2.xCoordinate || r2.xCoordinate
            + r2.width - 1 < xCoordinate || yCoordinate + height
                - 1 < r2.yCoordinate || r2.yCoordinate + r2.height
                    - 1 < yCoordinate);
    }


    /**
     * Checks, if the invoking rectangle has the same coordinates as rec.
     *
     * @param rec
     *            the rectangle parameter
     * @return true if the rectangle has the same coordinates as rec, false if
     *         not
     */
    public boolean equals(Object rec) {
        if (this == rec) {
            return true;
        }
        if (rec == null || getClass() != rec.getClass()) {
            return false;
        }
        Rectangle other = (Rectangle)rec;
        return xCoordinate == other.xCoordinate
            && yCoordinate == other.yCoordinate && width == other.width
            && height == other.height;
    }


    /**
     * Outputs a human readable string with information about the rectangle
     * which includes the x and y coordinate and its height and width
     *
     * @return a human readable string containing information about the
     *         rectangle
     */
    public String toString() {
        return String.valueOf(this.xCoordinate) + ", " + String.valueOf(
            this.yCoordinate) + ", " + String.valueOf(this.width) + ", "
            + String.valueOf(this.height);
    }


    /**
     * Checks if the rectangle has invalid parameters
     *
     * @return true if the rectangle has invalid parameters, false if not
     */
    public boolean isInvalid() {
        // Check for negative coordinates or non-positive dimensions
        if (this.xCoordinate < 0 || this.yCoordinate < 0 || this.width <= 0
            || this.height <= 0) {
            return true;
        }

        // Check if the rectangle extends beyond the right or bottom edge of the
        // 1024x1024 world box
        if (this.xCoordinate + this.width > 1024 || this.yCoordinate
            + this.height > 1024) {
            return true;
        }

        // The rectangle is valid and within bounds
        return false;
    }


    /**
     * Checks if the rectangle has invalid parameters
     *
     * @return true if the rectangle has invalid parameters, false if not
     */
    public boolean isInvalidRegionSearch() {
        // Check for negative width or height, which are invalid region searches
        if (this.width <= 0 || this.height <= 0) {
            return true;
        }

        // The rectangle region search is valid
        return false;
    }
}
