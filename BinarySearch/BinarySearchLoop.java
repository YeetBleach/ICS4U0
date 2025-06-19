import java.util.Arrays;
import static java.util.Collections.sort;
import java.util.Random;

public class BinarySearchLoop {


    public static int binarySearch(int[] arr, int target, int leftBounds, int rightBounds) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; //not found
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[51];
        for (int i=0;i<51;i++){
            arr[i] = Math.abs(random.nextInt(500));
        }
        Arrays.sort(arr);
        int target = arr[random.nextInt(51)];
        System.out.println(Arrays.toString(arr));
        System.out.println("Target is " + target);
        
        System.out.println("Position of "+ target + " is " + binarySearch(arr,target,0, arr.length-1));

    }
}