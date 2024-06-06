import java.util.Map;
import java.util.HashMap;

public class Driver extends DriverBase {
   public static void check(String testCase, Pair actual, Pair expected) {
      if (!pairEquals(actual, expected)) {
         testOutput.put(testCase, "Expected " + pairToString(expected) + ", Got " + pairToString(actual));
      } else {
         testOutput.put(testCase, null);
      }
   }

   public static boolean pairEquals(Pair one, Pair two) {
      // Compare the two points.
      return ((one.p1.equals(two.p1) && one.p2.equals(two.p2)) ||
            (one.p1.equals(two.p2) && one.p2.equals(two.p1)));
   }

   public static String pairToString(Pair p) {
      return "{" + p.p1.toString() + ", " + p.p2.toString() + "}";
   }

   public static void main(String[] args) {
      testOutput.put("Code compiles", null);

      checkDoesNotThrowException("Same Y coordinate", throwingConsumerWrapper(() -> {
         // Create data points from with same y coordinate but different x coordinates
         // and find closest point
         double[][] p1 = { { 10, 0 }, { 20, 0 }, { 30, 0 }, { 31, 0 }, { 40, 0 }, { 50, 0 } };
         Pair actual = EfficientAlgo.getClosestPair(p1);
         Pair expected = new Pair(new Point(30, 0), new Point(31, 0));
         check("Same Y coordinate", actual, expected);
      }));

      // Create data points with same x coordinate but different y coordinates and
      // find closest pair
      checkDoesNotThrowException("Same X coordinate", throwingConsumerWrapper(() -> {
         double[][] p2 = { { 0, 10 }, { 0, 20 }, { 0, 30 }, { 0, 31 }, { 0, 40 }, { 0, 50 } };
         Pair actual = EfficientAlgo.getClosestPair(p2);
         Pair expected = new Pair(new Point(0, 30), new Point(0, 31));
         check("Same X coordinate", actual, expected);
      }));

      // Create data points with on a diagonal with closest point being on the first
      // half.
      checkDoesNotThrowException("Diagonal with closest pair in first half", throwingConsumerWrapper(() -> {
         double[][] p3 = { { 10, 10 }, { 11, 11 }, { 20, 20 }, { 30, 30 }, { 40, 40 }, { 50, 50 } };
         Pair actual = EfficientAlgo.getClosestPair(p3);
         Pair expected = new Pair(new Point(10, 10), new Point(11, 11));
         check("Diagonal with closest pair in first half", actual, expected);
      }));

      // Create data points with on a diagonal with closest point being on the second
      // half.
      checkDoesNotThrowException("Diagonal with closest pair in second half", throwingConsumerWrapper(() -> {
         double[][] p4 = { { 10, 10 }, { 20, 20 }, { 30, 30 }, { 40, 40 }, { 41, 41 }, { 50, 50 } };
         Pair actual = EfficientAlgo.getClosestPair(p4);
         Pair expected = new Pair(new Point(40, 40), new Point(41, 41));
         check("Diagonal with closest pair in second half", actual, expected);
      }));

      // Create data points on a diagonal with closest point being in the center.
      checkDoesNotThrowException("Diagonal with closest pair in middle", throwingConsumerWrapper(() -> {
         double[][] p5 = { { 10, 10 }, { 20, 20 }, { 30, 30 }, { 31, 31 }, { 40, 40 }, { 50, 50 } };
         Pair actual = EfficientAlgo.getClosestPair(p5);
         Pair expected = new Pair(new Point(30, 30), new Point(31, 31));
         check("Diagonal with closest pair in middle", actual, expected);
      }));

      // Print the output
      printJsonOutput();
   }
}
