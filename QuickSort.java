/**
 * Implementation of QuickSort
 * 
 * Time Complexity: Best: O(nlogn), avg: O(nLogn), worst: O(n^2)
 * space: O(1)
 * 
 * stable: Not stable
 * in place: Yes, NO extra storage needed
 * 
 * Use QuickSort over Merge sort because of O(1) space
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = { 150, 11, 13, 5, 6, 7, 344, 621, 62346, 32423, 34, 63, 731, 72, 103, 653, 89, 12 };
        sort(array, 0, array.length - 1);
        print(array);
    }

    /**
     * Partition each array into two part and sorting each of the part recursively.
     * 
     * First select a pivot, then partition the array into two part based on the
     * pivot value less than pivot left and getter than pivot to the right. after
     * that recursive call to each part
     * 
     * @param array actual array
     * @param left  left index of the partition
     * @param right right index of the partition
     */
    public static void sort(int[] array, int left, int right) {
        if (left >= right) { // base case
            return;
        }
        int pivot = findBestPivot(array, left, right);
        int index = partition(array, left, right, pivot);
        sort(array, left, index - 1);
        sort(array, index, right);
    }

    /**
     * Partition an array into two part based on a pivot value. less than pivot to
     * the left and getter than pivot to the right.
     * 
     * Move the left pointer until it found the value getter than pivot. Move the
     * right pointer until it found the value less than pivot. and swap them
     * 
     * @param array The actual array
     * @param left  left index of the array
     * @param right right index of the array
     * @param pivot The pivot
     * @return
     */
    private static int partition(int[] array, int left, int right, int pivot) {
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }
            while (array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * Pivot on bottom 10% or top 10% will perform bad. time complexity is O(n^2).
     * In Quick sort the efficiency depends on the pivot. if randomly selected 5
     * items. it is 98% possible that the middle item will be between top and bottom
     * 10%. and highly possible the algorithm will perform in O(nlogn)
     * 
     * @param array
     * @param left
     * @param right
     * @return pivot from the array
     */
    private static int findBestPivot(int[] array, int left, int right) {
        if ((right - left) <= 5) {
            return array[(right + left) / 2];
        }
        int[] tempArr = new int[5];
        for (int i = 0; i < 5; i++) {
            int rand = (int) (left + Math.random() * (right - left));
            tempArr[i] = array[rand];
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (array[j] > array[i]) {
                    swap(tempArr, i, j);
                }
            }
        }

        return tempArr[2];
    }

    // swap two element on the array
    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    // print a array
    private static void print(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}