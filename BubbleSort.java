/**
 * Implementation of the bubble sort.
 * Also helps to find first N max/min number
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] lst = new int[] { 5, 2, 6, 7, 3, 1, 4, 356, 0 };
        bubbleSort(lst);
        printList(lst);

    }

    public static int[] bubbleSort(int[] lst) {
        // needToSort is the number of item need to be sorted from the array.
        // if (n-1) item already sorted, then of course the last item will be sorted too.
        // if needed only first k item to sort, just change needToSorted = k, BOOM!
        int needToSort = lst.length - 1;
        for (int i = 0; i < needToSort; i++) {
            for (int j = i; j < lst.length; j++) {
                // what need to change to make is descending order?
                // just lst[i] < lst[j]
                if (lst[i] > lst[j]) {
                    swap(lst, i, j);
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