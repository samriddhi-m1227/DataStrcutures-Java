import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Driver extends DriverBase {
   @SafeVarargs
   public static <E extends Comparable<E>> void check(Map<String, String> testOutput, String testCase, ArrayList<E> sortedList, E... expectedOrder) {
      int i = 0;
      for (E entry : expectedOrder) {
         if (!sortedList.get(i).equals(entry)) {
            testOutput.put(testCase, "Sorting failed. Expected " + expectedOrder + ", Got " + sortedList);
	    return;
         }
	 i++;
      }
      testOutput.put(testCase, null);
   }

   public static void main(String[] args) {
      ArrayList<Integer> intList = new ArrayList<>();
      intList.add(200);
      intList.add(100);
      intList.add(400);
      intList.add(-250);

      checkDoesNotThrowException("Sorting integers", () -> {
        Generics.sort(intList);
        check(testOutput, "Sorting integers", intList, -250, 100, 200, 400);
      });

      ArrayList<String> strList = new ArrayList<>();
      strList.add("alpha");
      strList.add("gamma");
      strList.add("beta");
      strList.add("epsilon");
      checkDoesNotThrowException("Sorting integers", () -> {
        Generics.sort(strList);
        check(testOutput, "Sorting strings", strList, "alpha", "beta", "epsilon", "gamma");
      });

      ArrayList<Double> dblList = new ArrayList<>();
      dblList.add(5.123);
      dblList.add(-5.021);
      dblList.add(1.301);
      dblList.add(3.4);
      checkDoesNotThrowException("Sorting integers", () -> {
        Generics.sort(dblList);
        check(testOutput, "Sorting doubles", dblList, -5.021, 1.301, 3.4, 5.123);
      });

      ArrayList<Byte> byteList = new ArrayList<>();
      byteList.add((byte)100);
      byteList.add((byte)-101);
      byteList.add((byte)50);
      byteList.add((byte)-25);
      checkDoesNotThrowException("Sorting integers", () -> {
        Generics.sort(byteList);
        check(testOutput, "Sorting bytes", byteList, (byte)-101, (byte)-25, (byte)50, (byte)100);
      });

      printJsonOutput();

   }
}
