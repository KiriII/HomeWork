import java.util.Arrays;

public class Main {

    public static void main(String args[]){
        BBB();
    }

    // для проверки                                                    ответ должен быть:
    static int[] array1 = new int[]{1, 4, 6, 8, 9, 6, 11};          // купить при 1 , продать при 11
    static int[] array2 = new int[]{14 , 5 , 7 , 2 , 2 , 1};        // купить при 5 , продать при 7
    static int[] array3 = new int[]{9 , 8 , 7 , 6 , 5};             // ошибка(Нет выгоды)


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
            for (int i = center; i >= left; i--) {
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

    // проверка (в скобках можно ограничить массив)
    public static void BBB(){
        AAA(CCC(array1) ,0 , 5);
        System.out.println(Arrays.toString(Arrays.copyOfRange(array1, finalLeft , finalRight + 2)) + " Выручка: " + Sum(array1 , finalLeft , finalRight + 2));
        maxSum = 0;
        finalRight = 0;
        finalLeft = 0;
        AAA(CCC(array2) ,0 , 4);
        System.out.println(Arrays.toString(Arrays.copyOfRange(array2, finalLeft , finalRight + 2)) + " Выручка: " + Sum(array2, finalLeft , finalRight + 2));
        maxSum = 0;
        finalRight = 0;
        finalLeft = 0;
        AAA(CCC(array3) ,0 , 3);
        System.out.println(Arrays.toString(Arrays.copyOfRange(array3, finalLeft , finalRight + 2)) + " Выручка: " + Sum(array3, finalLeft , finalRight + 2));
    }

    public static int[] CCC(int[] array){
        if (array.length == 0) throw new NullPointerException("Нет массива");
        int[] result = new int[array.length - 1];
        for (int i = 0; i < array.length - 1; i++){
            result[i] = array[i + 1] - array[i];
        }
        int countError = 0;
        for (int j = 0; j < array.length - 1; j++ ){
            if (result[j] < 0) countError++;
        }
        if (countError == result.length) throw new NullPointerException("Нет выгоды");
        return result;
    }

    public static int Sum(int[] array , int left , int right){
        return array[right - 1] - array[left];
    }
}
