import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * This program find the word that occurs the most in a txt file and censors it with **** in a new file
 */
 

public class MostCommonWord {
    public static void main(String[] args) {
        String filePath = "macbeth.txt"; // File to be read from
        String outputFilePath = "macbeth_censored.txt"; // New file to be written to
        HashMap<String, Integer> freqMap = new HashMap<>();
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            ArrayList<String> wordArr = new ArrayList<>(
                    Arrays.asList((content.toLowerCase()).replaceAll("[^a-z'\\s]", "").split("\\s+")));
                    // Converts to lowercase, removes all punctuation except apostrophes and splits them on newlines/empty lines
            for (String word : wordArr){
                if(freqMap.containsKey(word)){
                    freqMap.put(word, freqMap.get(word) +1); // Adds 1 to value if key already exists
                }
                else{
                    freqMap.put(word,1); // Creates new key value pair with value of 1 if word not already in hashmap
                }
            }
            Map.Entry<String, Integer> maxEntry = Collections.max(freqMap.entrySet(), Map.Entry.comparingByValue()); // Finds key with the highest value aka the most common word
            System.out.println("Most frequent word: " + maxEntry.getKey() + " (" + maxEntry.getValue() + " times)");

            String censoredContent = content.replaceAll("(?i)\\b" + maxEntry.getKey() + "\\b", "****");
            // Replaces all instance of the most common with **** (finds whole words of the word and changes it regardless of capitalization

            Files.write(Paths.get(outputFilePath), censoredContent.getBytes(), StandardOpenOption.CREATE);
            // Rewrites txt file into new output file or overwrites it if it already exists

            System.out.println("All occurrences of \"" + maxEntry.getKey() + "\" have been replaced with \"****\" in " + outputFilePath);

        }
        catch (IOException e){
            System.out.println("File not found");
            // Error handling
        }
    }
}