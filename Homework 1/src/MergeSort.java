
public class MergeSort {

	public static void main(String[] args) {

		int data[] = {5,3,4,5};
		data = mergeSortArray(data);
		displayArray(data);
	}

	public static int[] mergeSort(int left[], int right[]){
		
		int leftIndex = 0, rightIndex = 0;
		int result[] = new int[left.length+right.length];
		int i=0;
		while(leftIndex<left.length && rightIndex<right.length){
			if(left[leftIndex]<=right[rightIndex])
				result[i++] = left[leftIndex++];
			else
				result[i++] = right[rightIndex++];
		}
		if(leftIndex<left.length){
			System.arraycopy(left, leftIndex, result, i, left.length-leftIndex);}
		else
			System.arraycopy(right, rightIndex, result, i, right.length-rightIndex);
		displayArray(result);
		return result;
	}
	
	public static int[] mergeSortArray(int[] data){
		if(data.length<2)
			return data;
		else{
			int left[] = new int[data.length/2];
			int right[] = new int[data.length - data.length/2]; 
			System.arraycopy(data, 0, left, 0, data.length/2);
			System.arraycopy(data, data.length/2, right, 0, data.length - data.length/2);
			displayArray(left);
			displayArray(right);
			return mergeSort(mergeSortArray(left),mergeSortArray(right));
		}
	}
	
	public static void displayArray(int[] data){
		for(int i=0; i<data.length;i++){
			System.out.print(data[i]+" ");
		}
		System.out.println();
	}
		
}
