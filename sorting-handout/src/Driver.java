import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Driver extends DriverBase {
   private static void check(String testCase, ArrayList<Integer> actual, ArrayList<Integer> expected) {
      if (actual.equals(expected)) {
         testOutput.put(testCase, null);
      } else {
         testOutput.put(testCase, testCase);
      }
   }

   static final Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
	    return o1.compareTo(o2);
        }
    };


    @SuppressWarnings("unchecked")
   public static void main(String[] args) {
      ArrayList<Integer> list = new ArrayList<>();
      for (int i = 0; i < 10000; i++) {
         list.add((new Random()).nextInt());
      }
      ArrayList<Integer> defaultSortedList = (ArrayList<Integer>)list.clone();
      defaultSortedList.sort(comparator);
      ArrayList<Integer> list2 = (ArrayList<Integer>)list.clone();
      Sorting.mergeSort(list2);
      check("Merge Sort", list2, defaultSortedList);

      list2 = (ArrayList<Integer>)list.clone();
      Sorting.quickSort(list2);
      check("Quick Sort", list2, defaultSortedList);

      list2 = (ArrayList<Integer>)list.clone();
      Sorting.selectionSort(list2);
      check("Selection Sort", list2, defaultSortedList);

      list2 = (ArrayList<Integer>)list.clone();
      Sorting.radixSort(list2);
      check("Radix Sort", list2, defaultSortedList);

      list2 = (ArrayList<Integer>)list.clone();
      Sorting.heapSort(list2);
      check("Heap Sort", list2, defaultSortedList);

      Sorting.mainHelper();
      // Print the output
      printJsonOutput();
   }
}
