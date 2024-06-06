import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class Driver extends DriverBase {
   public static void main(String[] args) {
      // Create an empty list and check isEmpty.
      final MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<>();
      if (!list.isEmpty() || (list.size() != 0)) {
         testOutput.put("Empty List Check", "Size/isEmpty is incorrect");
      }

      // Add some elements to it and check size.
      list.add(1);
      list.add(2);
      list.add(3);
      if (list.size() != 3) {
         testOutput.put("Size check", "Size incorrect for list with elements.");
      } else {
         testOutput.put("Size check", null);
      }
      // Check contains on an object that exists.
      checkDoesNotThrowException(testOutput, "contains", () -> {
         if (!list.contains(1) || !list.contains(2) || !list.contains(3) || list.contains(4) || list.contains("Foo")) {
            testOutput.put("contains", "Returned incorrect value.");
         } else {
            testOutput.put("contains", null);
         }
      });
      // Check toArray
      if (Arrays.equals(list.toArray(), new Object[] { 1, 2, 3 })) {
         testOutput.put("toArray", null);
      } else {
         testOutput.put("toArray", "Got " + list.toArray() + ". Expected [1, 2, 3]");
      }

      // Add an element, verify that the list contains it and has the right size.
      if (list.add(4) == false) {
         testOutput.put("add", "Method returned false.");
      } else {
         checkListContents(testOutput, "add", list, 1, 2, 3, 4);
      }
      // Remove an element that does not exist, check return value and size
      try {
         if (list.remove(Integer.valueOf(5))) {
            testOutput.put("remove non existent element", "Method returned true.");
         } else {
            checkListContents(testOutput, "remove non existent element", list, 1, 2, 3, 4);
         }
      } catch (Exception e) {
         testOutput.put("remove non existent element", "threw an exception");
      }

      // Remove an element that exists in the list, check return value and size.
      try {
         if (list.remove(Integer.valueOf(3)) == false) {
            testOutput.put("remove", "Method returned false.");
         } else {
            checkListContents(testOutput, "add", list, 1, 2, 4);
         }
      } catch (Exception e) {
         testOutput.put("remove", "threw an exception");
      }
      // Check containsAll with an ArrayList that has all elements in our list
      ArrayList<Integer> testList = new ArrayList<>();
      Collections.addAll(testList, 1, 2, 4);
      checkDoesNotThrowException(testOutput, "containsAll has all elements", () -> {
         if (!list.containsAll(testList)) {
            testOutput.put("containsAll has all elements", "containsAll returned false instead of true");
         } else {
            testOutput.put("containsAll has all elements", null);
         }
      });

      // Check contains all with an ArrayList that has some element that is not in our
      // list.
      testList.add(5);
      checkDoesNotThrowException(testOutput, "containsAll has non-existent elements", () -> {
         if (list.containsAll(testList)) {
            testOutput.put("containsAll has non-existent elements", "containsAll returned true instead of false");
         } else {
            testOutput.put("containsAll has non-existent elements", null);
         }
      });

      // Call addAll with the ArrayList, check containsAll again and size.
      checkDoesNotThrowException(testOutput, "addAll", () -> {
         list.addAll(testList);
         checkListContents(testOutput, "addAll", list, 1, 2, 4, 1, 2, 4, 5);
      });

      // Call clear to verify the list is empty (isEmpty and size)
      list.clear();
      if (list.isEmpty() && list.size() == 0) {
         testOutput.put("clear", null);
      } else {
         testOutput.put("clear", "size/isEmpty incorrect after clear.");
      }

      // Reset the List with some elements. Call get on each index and verify the
      // value
      for (int i : testList) {
         list.add(i);
      }
      checkDoesNotThrowException(testOutput, "get(index)", () -> {
         if (list.get(0) == 1 && list.get(1) == 2 && list.get(2) == 4 && list.get(3) == 5) {
            testOutput.put("get(index)", null);
         } else {
            testOutput.put("get(index)", "Gets failed to return correct value");
         }
      });
      // Call set at a particular index and then iterate to verify list is right.
      checkDoesNotThrowException(testOutput, "set", () -> {
         list.set(2, 3);
         list.set(3, 4);
         checkListContents(testOutput, "set", list, 1, 2, 3, 4);
      });
      // Check that setting at index < 0 or > size throws exception.
      checkThrowsException(testOutput, "Setting at index < 0", () -> {
         list.set(-1, 1);
      });
      checkThrowsException(testOutput, "Setting at index >= size", () -> {
         list.set(list.size(), 1);
      });

      // Call add at 0 index, verify its correct. Call add at size() index and verify
      // its correct. Call add at an intermediate value and verify
      checkDoesNotThrowException(testOutput, "add at 0", () -> {
         list.add(0, 0);
      });
      checkListContents(testOutput, "add at 0", list, 0, 1, 2, 3, 4);
      // Create a new list and add at 0.
      list.clear();
      checkDoesNotThrowException(testOutput, "Adding to an empty list at index 0", () -> {
         list.add(0, 0);
      });
      checkListContents(testOutput, "Adding to an empty list at index 0", list, 0);
      // Check that adding <0 and >= size throws an exception.
      checkThrowsException(testOutput, "Adding at index < 0 should throw an exception.", () -> {
         list.add(-1, 1);
      });
      checkThrowsException(testOutput, "Adding at index > size should throw an exception.", () -> {
         list.add(list.size() + 1, 1);
      });

      // Call remove at 0 index and verify its correct, Call remove at size() and
      // verify its correct, call remove somewher ein middle and verify
      list.clear();
      for (int i : testList) {
         list.add(i);
      }
      checkDoesNotThrowException(testOutput, "remove at 0", () -> {
         list.remove(0);
         checkListContents(testOutput, "remove at 0", list, 2, 4, 5);
      });
      checkDoesNotThrowException(testOutput, "remove at size()", () -> {
         list.remove(list.size() - 1);
         checkListContents(testOutput, "remove at size()", list, 2, 4);
      });
      checkDoesNotThrowException(testOutput, "remove in the middle", () -> {
         list.add(0, 1);
         list.remove(1);
         checkListContents(testOutput, "remove in the middle", list, 1, 4);
      });
      // Check removing -1 and check removing at size()
      checkThrowsException(testOutput, "removing at index -1 should throw an exception", () -> {
         list.remove(-1);
      });
      checkThrowsException(testOutput, "removing at index >= size should throw an exception", () -> {
         list.remove(list.size());
      });

      // Call indexOf and verify the right value (list should have multiple identical
      // values in the middle)
      list.clear();
      checkDoesNotThrowException(testOutput, "indexOf", () -> {

         Collections.addAll(list, 1, 2, 3, 4, 3, 2, 1);
         if (list.indexOf(1) == 0 && list.indexOf(2) == 1 && list.indexOf(3) == 2) {
            testOutput.put("indexOf", null);
         } else {
            testOutput.put("indexOf", "Returned incorrect values");
         }
      });

      // Call listIterator and verify each element is in the right order.
      ListIterator<Integer> listIter = list.listIterator();
      int[] expected = new int[] { 1, 2, 3, 4, 3, 2, 1 };
      int index = 0;
      // Assume success. If it fails the loop will set the failure code.
      testOutput.put("listIterator", null);
      while (listIter.hasNext()) {
         Integer actualValue = listIter.next();
         if (expected[index] != actualValue) {
            testOutput.put("listIterator",
                  "Iteration failed at index " + index + ". Expected " + expected[index] + ", Got " + actualValue);
            break;
         }
         index++;
      }

      // Now for extra credits.
      // Extra Credit: Call addAll at a specific index and check values.
      testList.clear();
      Collections.addAll(testList, 5, 4);
      checkDoesNotThrowException("addAll at Index", () -> {
         list.addAll(4, testList);
      });
      checkListContents(extraCredit, "addAll at specific index", list, 1, 2, 3, 4, 5, 4, 3, 2, 1);
      // Extra Credit: Call removeAll with the ArrayList and verify that the list
      // doesnt have any of those values.
      checkDoesNotThrowException(extraCredit, "removeAll", () -> {
         list.removeAll(testList);
      });
      checkListContents(extraCredit, "removeAll", list, 1, 2, 3, 3, 2, 1);

      // Extra Credit: Call retainAll with an ArrayList with some elements and verify
      // that only those elements are present.
      testList.clear();
      Collections.addAll(testList, 2, 3, 4);
      checkDoesNotThrowException(extraCredit, "retainAll", () -> {
         list.retainAll(testList);
      });
      checkListContents(extraCredit, "retainAll", list, 2, 3, 3, 2);
      // Extra Credit: Call lastIndexOf and verify the right value (list should have
      // multiple identical values in the middle)
      checkDoesNotThrowException(extraCredit, "lastIndexOf should not throw an exception", () -> {
         if (list.lastIndexOf(3) == 2) {
            extraCredit.put("lastIndexOf", null);
         } else {
            extraCredit.put("lastIndexOf", "Expected 2, Got " + list.lastIndexOf(3));
         }
      });
      // Extra Credit: Call listIterator at a specific index and verify
      checkDoesNotThrowException(testOutput, "listIterator at index", () -> {
         ListIterator<Integer> listIter2 = list.listIterator(2);
         int[] expected2 = new int[] { 3, 2 };
         int index2 = 0;
         extraCredit.put("listIterator at index", null);
         while (listIter2.hasNext()) {
            Integer actualValue = listIter2.next();
            if (expected2[index2] != actualValue) {
               extraCredit.put("listIterator at index", "Iteration failed at index " + index2 + ". Expected "
                     + expected2[index2] + ", Got " + actualValue);
               break;
            }
            index2++;
         }
      });

      printJsonOutput();
   }
}
