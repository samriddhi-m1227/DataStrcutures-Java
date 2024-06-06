/**
 * Represents an efficient algorithm for finding the closest pair of points using a divide-and-conquer approach.
 * Provides methods to calculate the closest pair of points, recursively divide the points, and find the closest pair in a strip.
 */
public class EfficientAlgo {
	 /**
     * Finds the closest pair of points from a 2D array of points.
     * @param points The 2D array of points where each row represents a point with x and y coordinates.
     * @return The closest pair of points.
     */
	public static Pair getClosestPair(double[][] points) {
        int p1 = 0, p2 = 1;
        double shortestDistance = new Pair(new Point(points[p1][0], points[p1][1]), new Point(points[p2][0], points[p2][1])).getDistance();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = new Pair(new Point(points[i][0], points[i][1]), new Point (points[j][0], points[j][1])).getDistance();
                if (shortestDistance > distance) {
                    p1 = i;
                    p2 = j;
                    shortestDistance = distance;
                }
            }
        }
        return new Pair(new Point(points[p1][0], points[p1][1]), new Point(points[p2][0], points[p2][1]));
    }
	/**
	 * Finds the closest pair of points from an array of Point objects.
	 * Sorts the points based on x-coordinate, then calls the recursive method to find the closest pair.
	 * @param points The array of Point objects.
	 * @return The closest pair of points.
	 */
	public static Pair getClosestPair(Point[] points) {
        int p1 = 0, p2 = 1;
        double shortestDistance = new Pair(points[p1], points[p2]).getDistance();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = new Pair(points[i], points[j]).getDistance();
                if (shortestDistance > distance) {
                    p1 = i;
                    p2 = j;
                    shortestDistance = distance;
                }
            }
        }
        return new Pair(points[p1], points[p2]);
    }

    /**
     * Recursively finds the closest pair of points within a subset of points.
     * Uses a divide-and-conquer approach to split the points and recursively find the closest pair.
     * @param pointsOrderedOnX The array of points ordered on x-coordinate.
     * @param low The lower index of the subset.
     * @param high The higher index of the subset.
     * @param pointsOrderedOnY The array of points ordered on y-coordinate.
     * @return The closest pair of points within the subset.
     */
	public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {
        if (high - low <= 3) {
            return getClosestPair(pointsOrderedOnX);
        }

        int mid = (low + high) / 2;
        Point midPoint = pointsOrderedOnX[mid];

        Pair leftPair = distance(pointsOrderedOnX, low, mid, pointsOrderedOnY);
        Pair rightPair = distance(pointsOrderedOnX, mid + 1, high, pointsOrderedOnY);

        Pair minPair = (leftPair.getDistance() <= rightPair.getDistance()) ? leftPair : rightPair;

        java.util.ArrayList<Point> stripL = new java.util.ArrayList<>();
        java.util.ArrayList<Point> stripR = new java.util.ArrayList<>();
        for (Point point : pointsOrderedOnY) {
            if (point.x <= midPoint.x && midPoint.x - point.x <= minPair.getDistance())
                stripL.add(point);
            else if (point.x > midPoint.x && point.x - midPoint.x <= minPair.getDistance())
                stripR.add(point);
        }

        for (int i = 0; i < stripL.size(); i++) {
            for (int j = 0; j < stripR.size() && (stripR.get(j).y - stripL.get(i).y) <= minPair.getDistance(); j++) {
                double dist = Math.sqrt(Math.pow(stripL.get(i).x - stripR.get(j).x, 2) +
                        Math.pow(stripL.get(i).y - stripR.get(j).y, 2));
                if (dist < minPair.getDistance()) {
                    minPair = new Pair(stripL.get(i), stripR.get(j));
                }
            }
        }

        return minPair;
    }

    /**
     * The main method to demonstrate the efficient algorithm by generating random points and finding the closest pair.
     * Generates 100 random points, finds the closest pair, and prints the result along with the time spent.
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
    	 // Generate 100 random points
        Point[] points = new Point[100];
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < 100; i++) {
            double x = rand.nextDouble() * 100;
            double y = rand.nextDouble() * 100;
            points[i] = new Point(x, y);
        }    

        long startTime = System.currentTimeMillis();
        Pair closestPair = getClosestPair(points);
        long endTime = System.currentTimeMillis();

        System.out.println("The shortest distance is " + closestPair.getDistance() + " between the points");
        System.out.println(closestPair.toString());
        System.out.println("Time spent on the divide-and-conquer algorithm is " + (endTime - startTime) + " milliseconds");
        
        
    }
}