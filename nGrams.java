import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class nGrams {
    public static int n;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word: ");
        String word = scanner.nextLine();
        boolean invalid = true;
        System.out.println("What is your n value?: ");
        while (invalid) {
            n = scanner.nextInt();
            if (n>word.length()){
                System.out.println("n cannot more than the number of words");
                System.out.println("What is your n value? ");
            }
            else{
                invalid = false;
            }
        }
        ArrayList<String> charArr = new ArrayList<>(Arrays.asList(word.split("")));
        while (!charArr.isEmpty()){
            for (int i = 0; i < n; i++){
                if (charArr.size() < n){ break;}
                System.out.print(charArr.get(i));
            }
            charArr.remove(0);
            System.out.println();

        }
    }
}
