import java.util.Comparator;

/**
 * Assignment for Java Collections Framework.
 * @author Samriddhi Matharu
 * 
 * 
 * * Implements Comparable interface to allow sorting of points based on their coordinates.
 */
public class Point implements Comparable<Point> {
	
	// Data fields to store x and y coordinates
    private double x;
    private double y;

    /**
     * Constructs a point with specified x and y coordinates.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Compares this point with another point based on their x-coordinates.
     * If x-coordinates are equal, compares based on y-coordinates.
     * @param otherPoint   The point to be compared with.
     * @return integer as this point can be less than, equal to, or greater than the specified point.
     */
    @Override
    public int compareTo(Point otherPoint) {
        // Compare x-coordinates first
        if (this.x == otherPoint.x) {
            // If x-coordinates are equal, compare y-coordinates
            return Double.compare(this.y, otherPoint.y);
        }
        // If x-coordinates are different, return their comparison
        return Double.compare(this.x, otherPoint.x);
    }

    /**
     * Indicates whether some other object is "equal to" this point.
     * Two points are considered equal if they have the same x and y coordinates.
     * @param o  The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        // Check if the object is the same instance
        if (this == o) return true;
        // Check if the object is an instance of Point
        if (o instanceof Point) {
            // Cast the object to a Point
            Point point = (Point) o;
            // Compare x and y coordinates
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }
        // If the object is not an instance of Point, return false
        return false;
    }

    /**
     * Returns a string representation of the point.
     * @return A string representation of the point.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Comparator to compare points based on their y-coordinates.
     */
    public static class CompareY implements Comparator<Point> {
        /**
         * Compares two points based on their y-coordinates.
         * If y-coordinates are equal, compares based on x-coordinates.
         * @param p1 The first point to compare.
         * @param p2 The second point to compare.
         * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
         */
        @Override
        public int compare(Point p1, Point p2) {
            // Compare y-coordinates first
            if (p1.y == p2.y) {
                // If y-coordinates are equal, compare x-coordinates
                return Double.compare(p1.x, p2.x);
            }
            // If y-coordinates are different, return their comparison
            return Double.compare(p1.y, p2.y);
        }
    }
}