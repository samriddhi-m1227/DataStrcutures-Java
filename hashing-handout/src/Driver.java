import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class Driver extends DriverBase {

   /**
    * Checks the size of the table, keySet, values, entrySet, containsKey and containsValue methods.
    * @param testCase
    * @param sizeOfTable
    * @param expectedMap
    * @param actualMap
    */
   private static void check(String testCase, int sizeOfTable, Map<Integer, Integer> expectedMap, MyHashMap<Integer, Integer> actualMap) {
      try {
        if (actualMap.sizeOfTable() != sizeOfTable) {
           testOutput.put(testCase + ": Size of table", "Expected " + sizeOfTable + ", Got " + actualMap.sizeOfTable());
        }
      } catch (Exception e) {
           testOutput.put(testCase + ": Size of table", "Threw an exception");
      }

      // Check the keys method.
      Set<Integer> expectedKeySet = expectedMap.keySet();
      Set<Integer> actualKeySet = actualMap.keySet();

      if (actualKeySet.size() != expectedKeySet.size()) {
	 
         testOutput.put(testCase + ": Size of keySet", "Expected " + expectedKeySet.size() + ", Got " + actualKeySet.size() + ". Expected Map " + expectedMap + ", actual map " + actualMap);
      } else {
         testOutput.put(testCase + ": Size of keySet",  null);
         testOutput.put(testCase + ": keySet", null);
         testOutput.put(testCase  + ": containsKey", null);

         for (int key : expectedKeySet) {
            if (!actualKeySet.contains(key)) {
               testOutput.put(testCase + ": keySet", "Actual keySet does not contain " + key + " that is in expectedKeySet");
               break;
            }
            if (!actualMap.containsKey(key)) {
               testOutput.put(testCase  + ": containsKey", "containsKey(" + key + ") returned false.");
               break;
            }
         }
      }

      // Check the values() method.
      Collection<Integer> expectedValues = expectedMap.values();
      Collection<Integer> actualValues = actualMap.values();

      if (actualValues.size() != expectedValues.size()) {
         testOutput.put(testCase + ": Size of values", "Expected " + expectedValues.size() + ", Got " + actualValues.size());
      } else {
         testOutput.put(testCase + ": Size of values",  null);
         testOutput.put(testCase + ": values", null);
         testOutput.put(testCase  + ": containsValue", null);

         for (int value : expectedValues) {
            if (!actualValues.contains(value)) {
               testOutput.put(testCase + ": values", "Actual values does not contain " + value + " that is in expectedValues");
               break;
            }
            if (!actualMap.containsKey(value)) {
               testOutput.put(testCase  + ": containsValue", "containsValue(" + value + ") returned false.");
               break;
            }
         }
      }

      // Check the entries() method.
      Set<Map.Entry<Integer, Integer>> expectedEntries = expectedMap.entrySet();
      Set<Map.Entry<Integer, Integer>> actualEntries = actualMap.entrySet();

      if (actualEntries.size() != expectedEntries.size()) {
         testOutput.put(testCase + ": Size of entries", "Expected " + expectedEntries.size() + ", Got " + actualEntries.size());
      } else {
         testOutput.put(testCase + ": Size of entries",  null);
         testOutput.put(testCase + ": contains entry", null);

         for (Map.Entry<Integer, Integer> entry : expectedEntries) {
            if (!actualEntries.contains(entry)) {
               testOutput.put(testCase + ": entries", "Actual entries does not contain " + entry + " that is in expectedEntries");
               break;
            }
         }
      }


   }

   public static void main(String[] args) {
      // Test putting 2 elements with different hash values
      final MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();
      Map<Integer, Integer> expectedHashMap = new HashMap<>();

      checkDoesNotThrowException("Adding two entries", () -> {
          myHashMap.put(1, 1);
          myHashMap.put(2, 2);
          expectedHashMap.put(1, 1);
          expectedHashMap.put(2, 2);
          check("Adding two entries. ", MyHashMap.INITIAL_TABLE_SIZE, expectedHashMap, myHashMap);
      });

      // Test putting 5 elements with same hash value
      final MyHashMap<Integer, Integer> myHashMap1 = new MyHashMap<>();
      checkDoesNotThrowException("Adding five entries with colliding hashes", () -> {
          myHashMap1.put(1 + MyHashMap.INITIAL_TABLE_SIZE, 1 + MyHashMap.INITIAL_TABLE_SIZE);
          myHashMap1.put(1 + 2*MyHashMap.INITIAL_TABLE_SIZE, 1 + 2*MyHashMap.INITIAL_TABLE_SIZE);
          myHashMap1.put(1 + 3*MyHashMap.INITIAL_TABLE_SIZE, 1 + 3*MyHashMap.INITIAL_TABLE_SIZE);
          myHashMap1.put(1 + 4*MyHashMap.INITIAL_TABLE_SIZE, 1 + 4*MyHashMap.INITIAL_TABLE_SIZE);
          myHashMap1.put(1 + 5*MyHashMap.INITIAL_TABLE_SIZE, 1 + 5*MyHashMap.INITIAL_TABLE_SIZE);
          expectedHashMap.clear();
          expectedHashMap.put(1 + MyHashMap.INITIAL_TABLE_SIZE, 1 + MyHashMap.INITIAL_TABLE_SIZE);
          expectedHashMap.put(1 + 2*MyHashMap.INITIAL_TABLE_SIZE, 1 + 2*MyHashMap.INITIAL_TABLE_SIZE);
          expectedHashMap.put(1 + 3*MyHashMap.INITIAL_TABLE_SIZE, 1 + 3*MyHashMap.INITIAL_TABLE_SIZE);
          expectedHashMap.put(1 + 4*MyHashMap.INITIAL_TABLE_SIZE, 1 + 4*MyHashMap.INITIAL_TABLE_SIZE);
          expectedHashMap.put(1 + 5*MyHashMap.INITIAL_TABLE_SIZE, 1 + 5*MyHashMap.INITIAL_TABLE_SIZE);

          check("Adding five entries with colliding hashes", MyHashMap.INITIAL_TABLE_SIZE * 2, expectedHashMap, myHashMap1);
      });


      // Test removing non-existent value.
      checkDoesNotThrowException("Removing non-existent value", () -> {
          if (myHashMap1.remove(1) != null) {
             testOutput.put("Removing non-existent value", "returned a value when it should have returned null");
          } else {
             testOutput.put("Removing non-existent value", null);
          }
      });
      
      // Test removing 1 element.
      checkDoesNotThrowException("Removing value", () -> {
          if (myHashMap1.remove(1 + MyHashMap.INITIAL_TABLE_SIZE) != (1 + MyHashMap.INITIAL_TABLE_SIZE)) {
             testOutput.put("Removing value", "Failed to return old value");
          } else {
             testOutput.put("Removing value", null);
          } 
          expectedHashMap.remove(1 + MyHashMap.INITIAL_TABLE_SIZE);
          check("Removing one element. ", MyHashMap.INITIAL_TABLE_SIZE * 2, expectedHashMap, myHashMap1);
      });

      checkDoesNotThrowException("get after remove", () -> {
          testOutput.put("get after remove", null);
          if ((myHashMap1.get(1 + MyHashMap.INITIAL_TABLE_SIZE) != null) || myHashMap1.containsKey(1 + MyHashMap.INITIAL_TABLE_SIZE)) {
             testOutput.put("get after remove", "Did not return null");
          }
      });

      // Test removing 2 element.
      checkDoesNotThrowException("Remove", ()-> myHashMap1.remove(1 + 2*MyHashMap.INITIAL_TABLE_SIZE));
      expectedHashMap.remove(1 + 2*MyHashMap.INITIAL_TABLE_SIZE);
      check("Removing two elements. ", MyHashMap.INITIAL_TABLE_SIZE * 2, expectedHashMap, myHashMap1);
      if ((myHashMap1.get(1 + 2*MyHashMap.INITIAL_TABLE_SIZE) != null) || myHashMap1.containsKey(1 + 2*MyHashMap.INITIAL_TABLE_SIZE)) {
         testOutput.put("get after remove", "Did not return null");
      }

      // Test putAll with 5 elements and test.
      final MyHashMap<Integer, Integer> myHashMap2 = new MyHashMap<>();
      checkDoesNotThrowException("putAll", () -> {
          myHashMap2.putAll(expectedHashMap);
          check("putAll", MyHashMap.INITIAL_TABLE_SIZE, expectedHashMap, myHashMap2);
      });

      // Test clear.
      checkDoesNotThrowException("clear", () -> {
          myHashMap2.clear();
          expectedHashMap.clear();
          check("clear", MyHashMap.INITIAL_TABLE_SIZE, expectedHashMap, myHashMap2);
      });

      // Test putting the same key twice and make sure we get the previous value
      final MyHashMap<Integer, Integer> myHashMap3 = new MyHashMap<>();
      checkDoesNotThrowException("putting same key twice", () -> {
          myHashMap3.put(1, 100);
 
          if (myHashMap3.put(1, 200) != 100) {
             testOutput.put("putting same key twice", "does not return previous value");
          } else {
             testOutput.put("putting same key twice", null);
          }
      });
      checkDoesNotThrowException("putting new key", () -> {
          if (myHashMap3.put(2, 100) != null) {
             testOutput.put("putting new key", "returns non-null");
          } else {
             testOutput.put("putting new key", null);
          }      
      });

      // Test removing and putting the same key and make sure we get null value
      checkDoesNotThrowException("removing and putting same key", () -> {
          myHashMap3.remove(1);
          if (myHashMap3.put(1, 101) != null) {
             testOutput.put("remvoving and putting same key", "returns non-null");
          } else {
             testOutput.put("removing and putting same key", null);
          }      
      });
      printJsonOutput();
   }
}
