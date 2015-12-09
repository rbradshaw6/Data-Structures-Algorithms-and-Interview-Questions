import java.util.Comparator;
import java.util.Random;

public class Sorts<T> {
    public Sorts() {

    }

    public static void mergeSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        if (arr.length >= 2) {

            int[] front = new int[arr.length / 2];
            int[] back = new int[arr.length - arr.length / 2];

            for (int i = 0; i < arr.length / 2; i++) {
                front[i] = arr[i];
            }
            for (int j = 0; j < back.length; j++) {
                back[j] = arr[j + (arr.length / 2)];
            }

            mergeSort(front);
            mergeSort(back);
            merge(arr, front, back);
        }

        display(arr);
    }

    private static void merge(int[] merged, int[] front, int[] back) {
        int i = 0;
        int j = 0;

        while (i < front.length && j < back.length) {
            int z = i + j;
            if (front[i] > back[j]) {
                merged[z] = back[j];
                j++;
            } else {
                merged[z] = front[i];
                i++;
            }
        }

        for (int g = i; g < front.length; g++) {
            merged[g + j] = front[g];
        }
        for (int k = j; k < back.length; k++) {
            merged[i + k] = back[k];
        }
    }
    public static int[] selectionSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        if (arr.length <= 1) {
            return arr;
        }

        for (int i = 0; i < arr.length; i++) {
            int smallest = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    smallest = j;
                }
            }

            if (i != smallest) {
                swap(arr, i, smallest);
            }
        }

        display(arr);
        return arr;
    }

    public static int[] radixSort(int[] arr) {
        display(arr);
        return arr;
    }

    private static int findMaxDigits(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {

        }
        return max;
    }

    public static int[] insertionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        int i = 0;
        while (i < arr.length) {
            int j = i;
             while (j > 0 && arr[j] < arr[j - 1]) {
                 swap(arr, j, j -1);
                 j--;
             }
            i++;
        }

        display(arr);
        return arr;
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        boolean bool = true;
        int i = 0;

        while (i < arr.length && bool) {
            bool = false;

            int j = 1;
            while (j < arr.length) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    bool = true;
                    display(arr);
                }
                j++;
            }
        }

        display(arr);
        return arr;
    }


    public static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static void display(int[] arr) {
        String newString = "[";
        for (int i = 0; i < arr.length; i++) {
            newString += arr[i];
            newString += (i != arr.length - 1) ? ", " : "]";
        }
        System.out.println(newString);
    }

    public static void main(String[] args) {
        Sorts sort = new Sorts();
        int[] swag = {6, 10, 100, 12};

        sort.bogoSort(swag);
    }

    public static int[] bogoSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        Random rand = new Random();
        for (int i = 1; i < arr.length; i++) {
            int index = rand.nextInt(i + 1);
            swap(arr, index, i - 1);
        }

        display(arr);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return bogoSort(arr); //if it aint sorted right the first time, sort it again :^)
            }
        }

        return arr;
    }

}
