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
        String temp = "";
        while (!charTrigramsTemp.isEmpty()){
            for (int i = 0; i < 3; i++){
                if (charTrigramsTemp.size() < 3){
                    break;
                }
                temp += charTrigramsTemp.get(i); // spacing cases
            }
            charTrigramsTemp.remove(0);
            charTrigrams.add(temp);
            temp = "";
        }
        System.out.println("Character trigrams: " + charTrigrams);
    }
}
