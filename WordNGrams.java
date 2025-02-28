import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordNGrams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("String text: ");
        String sentence = scanner.nextLine();

        //character trigram
        ArrayList<String> charTrigramsTemp = new ArrayList<>(Arrays.asList(sentence.split("")));
        ArrayList<String> charTrigrams = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        while (!charTrigramsTemp.isEmpty()) {
            if (charTrigramsTemp.size() < 3) {
                break; // Stop when fewer than 3 characters remain
            }
            for (int i = 0; i < 3; i++) {
                temp.append(charTrigramsTemp.get(i));
            }
            charTrigrams.add(temp.toString()); // Add only complete trigrams
            charTrigramsTemp.remove(0);
            temp = new StringBuilder();
        }
        System.out.println("Character trigrams: " + charTrigrams);

        //word bigram
        ArrayList<String> wordBigramsTemp = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        ArrayList<String> wordBigrams = new ArrayList<>();
        StringBuilder temp2 = new StringBuilder();
        if (wordBigramsTemp.size() >= 2) {
            while (!wordBigramsTemp.isEmpty()) {
                if (wordBigramsTemp.size() < 2) {
                    break; // Stop when fewer than 2 words remain
                }
                for (int i = 0; i < 2; i++) {
                    temp2.append(wordBigramsTemp.get(i)).append(" ");
                }
                wordBigrams.add(temp2.toString().stripTrailing()); // Add only complete bigrams
                wordBigramsTemp.remove(0);
                temp2 = new StringBuilder();
            }
            System.out.println("Word bigrams: " + wordBigrams);
        }
        else{
            System.out.println("Word bigrams: Not enough words for bigram");
        }

        //word trigram
        ArrayList<String> wordTrigramsTemp = new ArrayList<>(Arrays.asList(sentence.split(" ")));
        ArrayList<String> wordTrigrams = new ArrayList<>();
        StringBuilder temp3 = new StringBuilder();
        if (wordTrigramsTemp.size() >= 3) {
            while (!wordTrigramsTemp.isEmpty()) {
                if (wordTrigramsTemp.size() < 3) {;
                    break; // Stop when fewer than 3 words remain
                }
                for (int i = 0; i < 3; i++) {
                    temp3.append(wordTrigramsTemp.get(i)).append(" ");
                }
                wordTrigrams.add(temp3.toString().stripTrailing()); // Add only complete trigrams
                wordTrigramsTemp.remove(0);
                temp3 = new StringBuilder();
            }
            System.out.println("Word trigrams: " + wordTrigrams);
        }
        else{
            System.out.println("Word trigrams: Not enough words for trigram");
        }

    }
}
