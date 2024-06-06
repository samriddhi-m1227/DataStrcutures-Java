import java.util.HashSet;
import java.util.Set;

/**
 * Recursion assignment.
 * @author Samriddhi Matharu
 */
public class Recursion {

    /**
     * Reverses the given string.
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverseString(String str) {
    	 if (str.isEmpty()) {
    	        return ""; // Return an empty string if the input is empty
    	    }
    	    // Call the helper method to perform the actual reversal
    	    return reverseString(str, str.length() - 1);
    	}
    /**
     * Helper method to reverse a string recursively.
     * @param value The string to reverse
     * @param highIndex The index of the character being currently considered for reversal
     * @return The reversed string
     */
    public static String reverseString(String value, int highIndex) {
        // Base case: if highIndex reaches 0, return the character at that index
        if (highIndex == 0) {
            return String.valueOf(value.charAt(0));
        } else {
            // Recursive step: append the character at highIndex to the result of the recursive call
            // with highIndex - 1
            return value.charAt(highIndex) + reverseString(value, highIndex - 1);
        }
    }

    /**
     * Returns a set of all permutations of the given string
     * @param value The string to find permutations of
     * @return A set containing all permutations of the given string
     */
    public static Set<String> allPermutations(String value) {
        return allPermutations("", value);
    }

    /**
     * Helper method to generate all permutations recursively.
     * @param s1 The prefix string representing a partial permutation
     * @param s2 The remaining characters available for permutation
     * @return A set containing all permutations of the given prefix and remaining characters
     */
    private static Set<String> allPermutations(String s1, String s2) {
        Set<String> permutations = new HashSet<>();
        if (s2.isEmpty()) {
            // Base case: if s2 is empty, add s1 to the set of permutations
            permutations.add(s1);
        } else {
            // Recursive step: Iterate through each character in s2
            for (int i = 0; i < s2.length(); i++) {
                // Append the character at index i of s2 to s1 to create a new partial permutation
                String newS1 = s1 + s2.charAt(i);
                // Generate a new s2 by removing the character at index i from s2
                String newS2 = s2.substring(0, i) + s2.substring(i + 1);
                // Recursively generate permutations using the updated s1 and s2
                permutations.addAll(allPermutations(newS1, newS2));
            }
        }
        return permutations;
    }
}