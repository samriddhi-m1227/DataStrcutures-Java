import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Driver extends DriverBase {
   private static void checkCount(CountWords cw, String key, Integer value) {
      String testCase = "Checking count of " + key;
      try {
         if (cw.getCount(key) != value) {
            testOutput.put(testCase, "Expected " + value + ", Got " + cw.getCount(key));
         } else {
            testOutput.put(testCase, null);
         }
      } catch (Exception ex) {
         testOutput.put(testCase, "getCount threw an unexpected exception");
      }
   }

   public static void main(String[] args) {
      // Create one with a non-existent file. Should throw exception.
      // Check to make sure that all words return the appropriate count.
      // Check that words that dont start with a letter return a 0 count.
      checkThrowsException("Creating CountWords with non-existent files should throw exception",
                        throwingConsumerWrapper(() -> new CountWords(null)));
      checkThrowsException("Creating CountWords with non-existent files should throw exception",
                        throwingConsumerWrapper(() -> new CountWords("/tmp/non-existent-file")));

      checkDoesNotThrowException("Creating countwords", () -> {
          CountWords cw= new CountWords("hamlet.txt");
          Map<String, Integer> expectedCounts = new HashMap<String, Integer>();
          expectedCounts.put("horatio", 158);
          expectedCounts.put("FORTINBRAS", 21);
          expectedCounts.put("mouth", 6);
          expectedCounts.put("thanks", 10);
          expectedCounts.put("and", 966);
          expectedCounts.put("foo", 0);
          expectedCounts.put("mou", 0);
          expectedCounts.put("bernardo?", 0);
          expectedCounts.put("france", 5);
          
          for (Map.Entry<String, Integer> entry : expectedCounts.entrySet()) {
             checkCount(cw, entry.getKey(), entry.getValue());
          }
      });
      // Print the output
      printJsonOutput();

   }
}
