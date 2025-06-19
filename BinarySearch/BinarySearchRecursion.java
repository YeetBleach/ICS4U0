import java.util.Arrays;
import static java.util.Collections.sort;
import java.util.Random;

public class BinarySearch {


    public static int binarySearch(int[] arr, int target, int leftBounds, int rightBounds) {
        if (rightBounds < leftBounds) {
            return -1; // Target not found
        }
        int mid = leftBounds + (rightBounds - leftBounds) / 2;

        if (arr[mid] == target) {
            return mid; // Target found
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, rightBounds);
        } else {
            return binarySearch(arr, target, leftBounds, mid - 1);
        }
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