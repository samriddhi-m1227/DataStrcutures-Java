import java.io.*;
import java.util.*;

/**
 * Assignment for Sets and Maps Module.
 * 
 * @author Samriddhi Matharu
 */
public class CountWords {
    private final Map<String, Integer> wordCounts; // Map to store word counts

    /**
     * Constructs a CountWords object and initializes the wordCounts map.
     * Reads the words from the specified file and populates the wordCounts map.
     * 
     * @param fileName The name of the file to read words from.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public CountWords(String fileName) throws IOException {
        wordCounts = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             Scanner scanner = new Scanner(fis)) {
            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine()); // Process each line to count words
            }
        }
    }

    /**
     * Processes a line of text, tokenizes it into words, and updates word counts.
     * 
     * @param line The line of text to process.
     */
    private void processLine(String line) {
        String[] words = line.split("[\\s\\p{Punct}]+"); // Split line into words
        for (String word : words) {
            if (!word.isEmpty() && Character.isLetter(word.charAt(0))) {
                // Only consider words starting with a letter
                String wordLowerCase = word.toLowerCase();
                wordCounts.put(wordLowerCase, wordCounts.getOrDefault(wordLowerCase, 0) + 1);
            }
        }
    }

    /**
     * Gets the count of occurrences of a specific word.
     * 
     * @param word The word to get the count for.
     * @return The count of occurrences of the specified word.
     */
    public int getCount(String word) {
        String wordLowerCase = word.toLowerCase();
        return wordCounts.getOrDefault(wordLowerCase, 0); // Return the count of the specified word
    }
}