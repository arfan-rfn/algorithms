/**
 * Implementation of the Insertion Sort Take a pointer and assume the left side
 * of the pointer is sorted. Now take a item and insert it into a appropriate
 * position.
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] lst = new int[] { 5, 2, 6, 7, 3, 1, 4, 356, 0 };
        insertionSort(lst);
        printList(lst);
    }

    public static int[] insertionSort(int[] lst) {
        // the first element is always sorted, so i = 1
        for (int i = 1; i < lst.length; i++) {
            for (int j = i; j > 0; j--) {
                if (lst[j - 1] > lst[j]) {
                    swap(lst, j - 1, j);
                } else { // this else is for efficiency. May changed it with while loop
                    break;
                }
            }
        }
        return lst;
    }

    /**
     * Swap two element form the list
     */
    public static void swap(int[] lst, int i, int j) {
        int temp = lst[i];
        lst[i] = lst[j];
        lst[j] = temp;
    }

    public static void printList(int[] lst) {
        for (int num : lst) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}