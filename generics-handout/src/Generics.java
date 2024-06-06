import java.util.ArrayList;

/**
 *This class provides a method to sort an ArrayList using generics.
 * It implements a selection sort algorithm.
 * 
 * @author Samriddhi Matharu
 */
public class Generics {
	
    /**
     * Sorts the passed in ArrayList in ascending order.
     * 
     * @param list The ArrayList to be sorted.
     * @param <E>  The type of elements in the ArrayList. Must implement Comparable interface.
     */
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        // Variables to keep track of the current minimum element and its index
        E currentMin;
        int currentMinIndex;
       
        // Outer loop iterates through the list elements
        for (int i = 0; i < list.size() - 1; i++) {
            currentMin = list.get(i);
            currentMinIndex = i;
            
            // Inner loop finds the minimum element in the unsorted portion of the list
            for (int j = i + 1; j < list.size(); j++) {
                if (currentMin.compareTo(list.get(j)) > 0) {
                    currentMin = list.get(j);
                    currentMinIndex = j;   
                }  
            }
            
            // Swapping elements if necessary to place the minimum element at its correct position
            if (currentMinIndex != i) {
                E temporary = list.get(currentMinIndex);
                list.set(currentMinIndex, list.get(i));
                list.set(i, temporary);
            }
        }
    }
}