/*
 * Implementation of a merge sort
 * It split into two part
 * 	i. devide ii. merge
 *
 * Time complaxity: best/avg/worst: O(nlogn)
 * space: O(n)
 * 
 * User merge sort on linkedList sorting will be always O(nlogn) and space is O(1)
 * because insertion in the middle is O(1)
 */

public class MergeSort{
	public static void main(String[] args){
		int lst[] = {12, 11, 13, 5, 6, 7};
		print(devide(lst));
	}


	/*
	 * Devide an array into two part recursively 
	 * and sort from very small array to big one
	 */
	public static int[] devide(int[] lst){
		if(lst == null) return new int[]{};

		if(lst.length > 1){
			int mid = lst.length/2;
			int[] left = new int[mid];
			int[] right = new int[lst.length - mid];
			for(int i = 0; i < mid; i++){
				left[i] = lst[i];
			}
			for(int i = mid; i < lst.length; i++){
				right[i - mid] = lst[i];
			}
			left = devide(left);
			right = devide(right);
			return merge(left, right);  // to use less memory send a reference of lst to the merge method
		}
		return lst;
	}
	
	/*
	 * Merging two sorted array and return a new array
	 * To save memory in merge sort send the array 
	 * reference instead of return a new array might imporove the space use.
	 */
	public static int[] merge(int[] left, int[] right){
		int[] lst = new int[left.length + right.length];
		int i=0, j=0, k=0;
		while(i < left.length && j < right.length){
			if(left[i] < right[j]){
				lst[k++] = left[i++];
			}else{
				lst[k++] = right[j++];
			}
		}
		while(i < left.length) lst[k++] = left[i++];
		while(j < right.length) lst[k++] = right[j++];
		
		return lst;
	}

	public static void print(int[] lst){
		for(int i: lst){
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
