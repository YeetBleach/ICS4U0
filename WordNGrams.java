import java.util.Scanner;
import java.util.ArrayList;

public class WordNGrams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("String Text: ");
        String sentence = scanner.nextLine();


        //Character Trigram
        int n=3;
        ArrayList<String> charTrigrams = new ArrayList<>();
        if (sentence.length() < n) {
            System.out.println("Not enough characters for trigrams");
        }
        else{
            for (int i = 0; i <= sentence.length() - n; i++) {
                charTrigrams.add(sentence.substring(i, i + n));
            }
            System.out.println("Character Trigrams:" + charTrigrams);
        }

        //Word Bigram
        String[] words = sentence.split(" ");
        ArrayList<String> wordBigrams = new ArrayList<>();
        if (words.length >= 2) {
            for (int i = 0; i <= words.length - 2; i++) {
                String bigram = words[i] + " " + words[i + 1];
                wordBigrams.add(bigram);
            }
            System.out.println("Word bigrams: " + wordBigrams);
        }
        else {
            System.out.println("Word bigrams: Not enough words for bigram");
        }

        //Word Trigram
        ArrayList<String> wordTrigrams = new ArrayList<>();
        if (words.length >= 3) {
            for (int i = 0; i <= words.length - 3; i++) {
                String trigram = words[i] + " " + words[i + 1] + " " + words[i + 2];
                wordTrigrams.add(trigram);
            }
            System.out.println("Word Trigrams: " + wordTrigrams);
        }
        else {
            System.out.println("Word Trigrams: Not enough words for trigram");
        }

    }
}
