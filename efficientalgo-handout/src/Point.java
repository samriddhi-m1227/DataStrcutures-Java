import java.util.Comparator;

/**
 * Assignment for finding closest points.
 * @author Samriddhi Matharu
 */
public class Point implements Comparable<Point> {
    double x, y;

    /**
     * Constructs a Point object with given x and y coordinates.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if two points are equal based on their coordinates.
     * @param o The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    /**
     * Generates a hash code for the point.
     * @return The hash code of the point.
     */
    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    /**
     * Returns a string representation of the point.
     * @return A string representing the point in the format (x, y).
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares this point with another point based on their x-coordinates.
     * If x-coordinates are equal, compares based on y-coordinates.
     * @param that The point to compare with.
     * @return A negative integer, zero, or a positive integer if this point
     * is less than, equal to, or greater than the specified point.
     */
    @Override
    public int compareTo(Point that) {
        if (this.x != that.x) {
            return Double.compare(this.x, that.x);
        } else {
            return Double.compare(this.y, that.y);
        }
    }

    /**
     * Provides a comparator for comparing points based on their y-coordinates.
     * Used for sorting points based on y-coordinate.
     */
    public static class CompareY implements Comparator<Point> {

        /**
         * Compares two points based on their y-coordinates.
         * If y-coordinates are equal, compares based on x-coordinates.
         * @param o1 The first point to compare.
         * @param o2 The second point to compare.
         * @return A negative integer, zero, or a positive integer if the first point
         * is less than, equal to, or greater than the second point.
         */
        @Override
        public int compare(Point o1, Point o2) {
            if (o1.y != o2.y) {
                return Double.compare(o1.y, o2.y);
            } else {
                return Double.compare(o1.x, o2.x);
            }
        }
    }

	
}