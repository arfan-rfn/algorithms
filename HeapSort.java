
/**
 * Implementation of the Heap sort (Max heap).
 *
 * Time complexity: O(nlogn)
 * space: O(1), [O(n) with manually adding and removing]
 */
import java.util.Arrays;

public class HeapSort {

    private int[] array; // array need to be sort
    private int size; // number of item haven't been sorted yet

    /**
     * array started with 1 size. double the size as needed
     */
    public HeapSort() {
        array = new int[1];
        size = 0;
    }

    public HeapSort(int[] array) {
        this.array = array;
        size = array.length;
    }

    /**
     * First build max heap O(nlogn) heapify each item O(nlogn)
     */
    public void sort() {
        buildMaxHeap();
        int n = size;
        for (int i = n - 1; i >= 0; i--) {
            swap(0, --size);
            heapify(0); // heapifyDown
        }
    }

    // helper methods
    private int leftChildIndex(int idx) {
        return idx * 2 + 1;
    }

    private int rightChildIndex(int idx) {
        return idx * 2 + 2;
    }

    private int getParentIndex(int idx) {
        return idx / 2;
    }

    private boolean hasParent(int idx) {
        return getParentIndex(idx) >= 0;
    }

    /**
     * Visit every parent nodes (size/2-1) and heapify each items
     * 
     * time complexity: O(nlogn)
     */
    public void buildMaxHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * Visit every child node form a node, and heapify recursively. If the sub-tree
     * is not valid tree (parentNode < childNode) array[parent] = max{leftNode,
     * rightNode}
     * 
     * Find the largest child index, and swap with parent
     * 
     * @param idx node index
     */
    private void heapify(int idx) {
        int largest = idx;
        int left = leftChildIndex(idx);
        int right = rightChildIndex(idx);

        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != idx) {
            swap(idx, largest);
            heapify(largest);
        }
    }

    /**
     * Swap the first item to the last then heapify the whole tree until the last
     * element [aka. heapifyDown]
     * 
     * unsorted size will be decreased
     * 
     * @return last item
     */
    public int removeHeap() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        swap(0, --size);
        heapify(0);
        return array[size];
    }

    /**
     * Adding item to the end of the tree Then heapifyUp until find the best place
     */
    public void addItem(int item) {
        ensureArrayCapacity();
        array[size++] = item;
        int index = size - 1;
        while (hasParent(index) && array[getParentIndex(index)] < array[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /**
     * Just a helper method to make sure array have enough capacity
     */
    private void ensureArrayCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    // swap two item in the array
    private void swap(int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = { 4, 10, 3, 5, 5, 1 };
        System.out.println("With a list");
        HeapSort heapSort = new HeapSort(array);
        heapSort.print();
        heapSort.sort();
        heapSort.print();

        System.out.println("\nManually adding item and sort one item at a time");
        HeapSort heapSortManual = new HeapSort();
        for (int num : array) {
            heapSortManual.addItem(num);
        }
        heapSortManual.print();
        for (int i = 0; i < array.length; i++) {
            heapSortManual.removeHeap();
        }
        heapSortManual.print();
    }
}