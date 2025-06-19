import java.util.Scanner;

public class ArmstrongNumber {

    private int n;

    public ArmstrongNumberChecker(int number) {
        this.n = number;
    }

    private int[] intSeperate() {
        String str = String.valueOf(n);
        int[] digits = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            digits[i] = Character.getNumericValue(str.charAt(i));
        }
        return digits;
    }

    private int convertArmstrong() {
        int[] arr = intSeperate();
        int power = arr.length;
        int armstrongNum=0;
        for (int i = 0; i < power; i++) {
            armstrongNum += Math.pow(arr[i], power);
        }
        return armstrongNum;
    }

    public String isArmstrongNum() {
        int armstrongNum = convertArmstrong();
        return (armstrongNum == this.n) ? this.n + " is an Armstrong Number" : this.n + " is not an Armstrong Number";
    }


  public void static main(String[] args){
    Scanner scanner = new Scanner(System.in);
        System.out.print("Enter num to check: ");
        int num = scanner.nextInt();
        
        ArmstrongNumberChecker numCheck = new ArmstrongNumberChecker(num);
        System.out.println(numCheck.isArmstrongNum());
    }
}
