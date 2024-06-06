public class Pair {
    Point p1, p2;

    /**
     * Constructs a Pair object with given points.
     * @param p1 The first point of the pair.
     * @param p2 The second point of the pair.
     */
    public Pair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Calculates the distance between the two points in the pair.
     * @return The distance between the two points.
     */
    public double getDistance() {
    	 return Math.sqrt(Math.pow(Math.abs(p2.x - p1.x), 2.0) + Math.pow(Math.abs(p2.y - p1.y), 2.0));
    }

    /**
     * Checks if two pairs are equal based on their points.
     * @param o The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return this.p1.compareTo(pair.p1) == 0 && this.p2.compareTo(pair.p2) == 0;
    }

    /**
     * Generates a hash code for the pair.
     * @return The hash code of the pair.
     */
    @Override
    public int hashCode() {
        return p1.hashCode() ^ p2.hashCode();
    }

    /**
     * Returns a string representation of the pair.
     * @return A string representing the pair in the format "{(x1, y1), (x2, y2)}".
     */
    @Override
    public String toString() {
        return "{" + p1.toString() + ", " + p2.toString() + "}";
    }
}