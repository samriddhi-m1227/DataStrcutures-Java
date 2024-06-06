import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Driver extends DriverBase {

   private static final void checkOrder(String testCase, List<Point> result, Point... points) {
      for (int i = 0; i < points.length; i++) {
         if (!result.get(i).equals(points[i])) {
            testOutput.put(testCase, "Element[" + i +"]: Expected " + points[i] + ", Got " + result.get(i));
            return;
         }
      }
      // All checked out.
      testOutput.put(testCase, null);
   }
   
   public static void main(String[] args) {
      // 1. Create a list of points and sort with X coordinate and check
      checkDoesNotThrowException("Comparing on X coordinate", () -> {
          ArrayList<Point> list = new ArrayList<>();
          Point p1 = new Point(1, 1);
          Point p2 = new Point(2, 2);
          Point p3 = new Point(3, 3);
          Point p4 = new Point(4, 4);
          Point p5 = new Point(5, 5);
          list.add(p3);
          list.add(p4);
          list.add(p1);      
          list.add(p5);
          list.add(p2);
          list.sort(null);
          checkOrder("Comparing on X coordinate", list, p1, p2, p3, p4, p5);
      });

      // 2. Sort with y cooridinate and check
      checkDoesNotThrowException("Comparing on Y coordinate", () -> {
          ArrayList<Point> list = new ArrayList<>();
          Point p1 = new Point(1, 1);
          Point p2 = new Point(2, 2);
          Point p3 = new Point(3, 3);
          Point p4 = new Point(4, 4);
          Point p5 = new Point(5, 5);
          list.clear();
          list.add(p3);
          list.add(p4);
          list.add(p1);      
          list.add(p5);
          list.add(p2);
          list.sort(new Point.CompareY());
          checkOrder("Comparing on Y coordinate", list, p1, p2, p3, p4, p5);
      });

      // 3. Create a set of points with same x coordinates and sort with X and check
      checkDoesNotThrowException("Comparing when X coordinate is same", () -> {
          ArrayList<Point> list = new ArrayList<>();
          Point p1 = new Point(0, 1);
          Point p2 = new Point(0, 2);
          Point p3 = new Point(0, 3);
          Point p4 = new Point(0, 4);
          Point p5 = new Point(0, 5);
          list.clear();
          list.add(p3);
          list.add(p4);
          list.add(p1);      
          list.add(p5);
          list.add(p2);
          list.sort(null);
          checkOrder("Comparing when X coordinate is same", list, p1, p2, p3, p4, p5);
      });

      // 4. Create a set of points with same y coordinates and call CompareY and check
      checkDoesNotThrowException("Comparing X when Y coordinate is same", () -> {
          ArrayList<Point> list = new ArrayList<>();
          Point p1 = new Point(1, 0);
          Point p2 = new Point(2, 0);
          Point p3 = new Point(3, 0);
          Point p4 = new Point(4, 0);
          Point p5 = new Point(5, 0);
          list.clear();
          list.add(p3);
          list.add(p4);
          list.add(p1);      
          list.add(p5);
          list.add(p2);
          list.sort(new Point.CompareY());
          checkOrder("Comparing X when Y coordinate is same", list, p1, p2, p3, p4, p5);
      });
   
      // Print the output
      printJsonOutput();

   }
}
