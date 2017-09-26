import java.util.Arrays;

public class Main {

    public static void main(String args[]){
        BBB();
    }

    static int[] array = new int[]{-3,-2,2,1,-3,5};
    static int maxSum = 0;
    static int finalLeft = 0;
    static int finalRight = 0;

    public static void AAA(int[] array, int left, int right) {
        if (array.length == 1){
            return;
        }
        if (left == right){
            if (array[right] > maxSum){
                maxSum = array[right];
                finalRight = right;
                finalLeft = right;
            }
        }
        else {
            int center = (left + right) / 2;
            int counter = 0;
            int LeftSum = array[center];
            int leftCount = center;
            for (int i = center; i >= 0; i--) {
                counter += array[i];
                if (LeftSum < counter) {
                    LeftSum = counter;
                    leftCount = i;
                }
            }

            counter = 0;
            int rightSum;
            if (center != array.length - 1) rightSum = array[center + 1];
                    else rightSum = array[center];
            int rightCount = center + 1;
            for (int j = center + 1; j < array.length; j++) {
                counter += array[j];
                if (rightSum < counter) {
                    rightSum = counter;
                    rightCount = j;
                }
            }

            if (maxSum < rightSum + LeftSum) {
                finalLeft = leftCount;
                finalRight = rightCount;
                maxSum = rightSum + LeftSum;
            }
            if (maxSum < rightSum){
                finalLeft = center + 1;
                finalRight = rightCount;
                maxSum = rightSum;
            }
            if (maxSum < LeftSum){
                finalLeft = leftCount;
                finalRight = center;
                maxSum = LeftSum;
            }

            AAA(array, left, center);
            AAA(array, center + 1, right);
        }
    }

    public static void BBB(){
        AAA(array ,0 , 5);
        System.out.println(Arrays.toString(Arrays.copyOfRange(array, finalLeft , finalRight + 1)));
    }
}
