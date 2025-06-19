public class AliquotSumRecursion {

    private final int n;

    public AliquotSum(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        this.n = n;
    }

    public String classify() {
        int factorSum = sumOfFactors();
        if (n == factorSum) {
            return n + " is a Perfect Number";
        } else if (n > factorSum) {
            return n + " is a Deficient Number";
        } else {
            return n + " is an Abundant Number";
        }
    }

    //Private method to calculate the sum of the factors
    private int sumOfFactors() {
        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        AliquotSum aliquotSum = new AliquotSum(28);
        System.out.println(aliquotSum.classify());
    }
}
