import java.util.Set;

public class Driver extends DriverBase {

   private static final void checkReversedString(String testCase,
         String stringToReverse, String reversedString) {
      checkDoesNotThrowException(testCase, () -> {
         if (Recursion.reverseString(stringToReverse).equals(reversedString)) {
            testOutput.put(testCase, null);
         } else {
            testOutput.put(testCase,
                  "Expected " + reversedString + ", Got " + Recursion.reverseString(stringToReverse));
         }
      });
   }

   private static final void checkPermutations(String testCase, String str,
         String... perms) {
      checkDoesNotThrowException(testCase, () -> {
         Set<String> result = Recursion.allPermutations(str);
         if (perms.length != result.size()) {
            testOutput.put(testCase, "List of permutations of \"" + str + "\" not of correct size. Expected: "
                  + perms.length + ", Got " + result.size());
            return;
         }
         for (String expected : perms) {
            if (!result.contains(expected)) {
               testOutput.put(testCase, "Did not find \"" + expected + "\" in result set.");
               return;
            }
         }
         testOutput.put(testCase, null);
      });
   }

   public static void main(String[] args) {
      checkReversedString("Reversing empty string", "", "");
      checkReversedString("Reversing string with odd number of characters", "abcde", "edcba");
      checkReversedString("Reversing string with even number of characters", "abcd", "dcba");
      String longString = "asdlakjprio upq38rukasjd>AJdkJAdsklJadsfikjaiwerw8 y7r8923745  8923rihNAKSfnlAJKDSGLasyheri23QH4Y89PY  23489yuqw";
      checkReversedString("Reversing a long string", longString,
            (new StringBuffer(longString)).reverse().toString());

      checkPermutations("Permutations of empty string", "", "");
      checkPermutations("Permutation of string containing duplicate characters", "aba", "aba", "aab",
            "baa");
      checkPermutations("Permutations of one character string", "a", "a");
      checkPermutations("Permutations of two character string", "12", "12", "21");
      checkPermutations("Permutations of three character string", "def", "def", "dfe", "edf", "efd", "fde",
            "fed");

      printJsonOutput();
   }
}
