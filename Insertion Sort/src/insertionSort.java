
public class insertionSort {

	public static void sortInsertion(int [] sort_arr) {
		for(int i = 0; i < sort_arr.length; i++) {
			int j = i;
			
			while(j > 0 && sort_arr[j-1] > sort_arr[j]) {
				int key = sort_arr[j];
				sort_arr[j] = sort_arr[j-1];
				sort_arr[j-1] = key;
				j = j - 1;
			}
		}
	}
	
	public static void main(String[] args) {
		int [] arr = {2,5,6,1,8,16,3,8,7,19,25,21,4,17};
		sortInsertion(arr);
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
	
}
