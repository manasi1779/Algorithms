

public class Sort<E extends Comparable<E>>{
	
	public E[] mergeSort(E left[], E right[]){
		
		int leftIndex = 0, rightIndex = 0;
		Object result[] = new Object[left.length+right.length];
		int i=0;
		while(leftIndex<left.length && rightIndex<right.length){
			if((left[leftIndex]).compareTo(right[rightIndex])<=1)
				result[i++] = left[leftIndex++];
			else
				result[i++] = right[rightIndex++];
		}
		if(leftIndex<left.length){
			System.arraycopy(left, leftIndex, result, i, left.length-leftIndex);}
		else
			System.arraycopy(right, rightIndex, result, i, right.length-rightIndex);
		return (E[])result;
	}
	
	public E[] mergeSortArray(E[] data){
		if(data.length<2)
			return (E[])data;
		else{
			Object left[] = new Object[data.length/2];
			Object right[] = new Object[data.length - data.length/2]; 
			System.arraycopy(data, 0, left, 0, data.length/2);
			System.arraycopy(data, data.length/2, right, 0, data.length - data.length/2);
			return mergeSort(mergeSortArray((E[])left),mergeSortArray((E[])right));
		}
	}

}
