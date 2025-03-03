import java.util.Scanner;

public class nGrams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word: ");
        String word = scanner.nextLine();

        int n;
        System.out.println("What is your n value?: ");
        while (true) {
            n = scanner.nextInt();
            if (n > word.length()) {
                System.out.println("n cannot be more than the length of the word");
                System.out.println("What is your n value? ");
            } else {
                break;
            }
        }
        for (int i = 0; i <= word.length() - n; i++) {
            System.out.println(word.substring(i, i + n));
        }
    }
}
