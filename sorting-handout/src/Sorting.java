import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * This class provides implementations of various sorting algorithms
 * including Selection Sort, Merge Sort, Quick Sort, Heap Sort, and Radix Sort.
 * 
 * @ Samriddhi Matharu
 * 
 */
public class Sorting {
    /**
     * Sorts the input ArrayList using the Selection Sort algorithm.
     *
     * @param input The ArrayList to be sorted.
     */
    public static void selectionSort(ArrayList<Integer> input) {
        int n = input.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // Find the index of the minimum element in the unsorted portion of the array
            for (int j = i + 1; j < n; j++) {
                if (input.get(j) < input.get(minIndex)) {
                    minIndex = j;
                }
            }
            // Swap the minimum element with the first element of the unsorted portion
            Collections.swap(input, i, minIndex);
        }
    }

    /**
     * Sorts the input ArrayList using the Merge Sort algorithm.
     *
     * @param input The ArrayList to be sorted.
     */
    public static void mergeSort(ArrayList<Integer> input) {
        mergeSortHelper(input, 0, input.size() - 1);
    }

    private static void mergeSortHelper(ArrayList<Integer> input, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            // Recursively sort the left and right halves
            mergeSortHelper(input, left, mid);
            mergeSortHelper(input, mid + 1, right);
            // Merge the sorted halves
            merge(input, left, mid, right);
        }
    }

    private static void merge(ArrayList<Integer> input, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = input.get(left + i);
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = input.get(mid + 1 + i);
        }

        int i = 0, j = 0, k = left;
        // Merge the temporary arrays back into the original array
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                input.set(k++, leftArray[i++]);
            } else {
                input.set(k++, rightArray[j++]);
            }
        }

        // Copy the remaining elements of leftArray, if any
        while (i < n1) {
            input.set(k++, leftArray[i++]);
        }

        // Copy the remaining elements of rightArray, if any
        while (j < n2) {
            input.set(k++, rightArray[j++]);
        }
    }

    /**
     * Sorts the input ArrayList using the Quick Sort algorithm.
     *
     * @param input The ArrayList to be sorted.
     */
    public static void quickSort(ArrayList<Integer> input) {
        quickSortHelper(input, 0, input.size() - 1);
    }

    private static void quickSortHelper(ArrayList<Integer> input, int low, int high) {
        if (low < high) {
            // Partition the array and recursively sort the sub-arrays
            int pivotIndex = partition(input, low, high);
            quickSortHelper(input, low, pivotIndex - 1);
            quickSortHelper(input, pivotIndex + 1, high);
        }
    }

    private static int partition(ArrayList<Integer> input, int low, int high) {
        int pivot = input.get(high);
        int i = low - 1;
        // Move all elements smaller than pivot to the left of pivot
        for (int j = low; j < high; j++) {
            if (input.get(j) < pivot) {
                i++;
                Collections.swap(input, i, j);
            }
        }
        // Move pivot to its correct position
        Collections.swap(input, i + 1, high);
        return i + 1;
    }

    /**
     * Sorts the input ArrayList using the Heap Sort algorithm.
     *
     * @param input The ArrayList to be sorted.
     */
    public static void heapSort(ArrayList<Integer> input) {
        int n = input.size();
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(input, n, i);
        // Heap sort
        for (int i = n - 1; i > 0; i--) {
            Collections.swap(input, 0, i);
            heapify(input, i, 0);
        }
    }

    private static void heapify(ArrayList<Integer> input, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        // Find the largest element among root, left child, and right child
        if (left < n && input.get(left) > input.get(largest))
            largest = left;
        if (right < n && input.get(right) > input.get(largest))
            largest = right;
        // If root is not the largest, swap with the largest and heapify the affected sub-tree
        if (largest != i) {
            Collections.swap(input, i, largest);
            heapify(input, n, largest);
        }
    }

    /**
     * Sorts the input ArrayList using the Radix Sort algorithm.
     *
     * @param input The ArrayList to be sorted.
     */
    public static void radixSort(ArrayList<Integer> input) {
        if (input.size() == 0)
            return;

        int max = Collections.max(input);

        // Perform counting sort for every digit (from least significant to most significant)
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSort(input, exp);
    }

    private static void countingSort(ArrayList<Integer> input, int exp) {
        int n = input.size();
        int[] count = new int[20]; // Count array with size 20 to handle both positive and negative numbers

        // Count the occurrences of each digit
        for (int i = 0; i < n; i++)
            count[(input.get(i) / exp) % 10 + 10]++; // Offset to handle negative numbers

        // Compute cumulative counts
        for (int i = 1; i < 20; i++)
            count[i] += count[i - 1];

        ArrayList<Integer> output = new ArrayList<>(Collections.nCopies(n, 0)); // Output array

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output.set(count[(input.get(i) / exp) % 10 + 10] - 1, input.get(i));
            count[(input.get(i) / exp) % 10 + 10]--;
        }

        // Copy the output array to the input array
        for (int i = 0; i < n; i++)
            input.set(i, output.get(i));
    }

    /**
     * Generates a random number.
     *
     * @return A random integer.
     */
    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * Helper method to print the execution time of each sorting algorithm for various input sizes.
     */
    public static void mainHelper() {
        System.out.println("Array size | Selection  | Merge      | Quick      | Heap       | Radix");
        System.out.println("----------------------------------------------------------------------------");

        int[] arraySizes = {50000, 100000, 150000, 200000, 250000, 300000};

        for (int size : arraySizes) {
            ArrayList<Integer> input = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                input.add(generateRandomNumber());
            }

            long startTime, endTime, executionTime;

            // Measure execution time for Selection Sort
            ArrayList<Integer> selectionInput = new ArrayList<>(input);
            startTime = System.currentTimeMillis();
            selectionSort(selectionInput);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            long selectionExecutionTime = executionTime;

            // Measure execution time for Merge Sort
            ArrayList<Integer> mergeInput = new ArrayList<>(input);
            startTime = System.currentTimeMillis();
            mergeSort(mergeInput);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            long mergeExecutionTime = executionTime;

            // Measure execution time for Quick Sort
            ArrayList<Integer> quickInput = new ArrayList<>(input);
            startTime = System.currentTimeMillis();
            quickSort(quickInput);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            long quickExecutionTime = executionTime;

            // Measure execution time for Heap Sort
            ArrayList<Integer> heapInput = new ArrayList<>(input);
            startTime = System.currentTimeMillis();
            heapSort(heapInput);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            long heapExecutionTime = executionTime;

            // Measure execution time for Radix Sort
            ArrayList<Integer> radixInput = new ArrayList<>(input);
            startTime = System.currentTimeMillis();
            radixSort(radixInput);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            long radixExecutionTime = executionTime;

            // Print the execution times for each sorting algorithm
            System.out.printf("%-11d| %-11d| %-11d| %-11d| %-11d| %-11d\n", size, selectionExecutionTime, mergeExecutionTime, quickExecutionTime, heapExecutionTime, radixExecutionTime);
        }
    }
    
    /**
     * Main method to execute the mainHelper method.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
       mainHelper();
    }
}