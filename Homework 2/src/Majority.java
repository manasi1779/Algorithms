/**
 * @author Paridhi Srivastava and Manasi Bharde
 * This program uses random k - select algorithm to find if there exists number having n/2 or n/3 majority  
 * To choose pivot:
 * 1. Creates array using every 3rd element of original array.
 * 2. Selects middle element of such array as pivot
 * which is O(n) operation
 * Create new array of same length and add elements at start if it is lesser than pivot or at end if it is greater than pivot
 * remaining part is pivot area 
 * if k is lesser than pivot start index, recursively call the method with lesser sub -array
 * if k is greater than pivot end index recursively call the method with greater sub-array
 * if k is within pivot range set isMajaority flag true. 
 * Cost of each call is:
 * T(n) = T(n/3) + n
 * Hence it is O(n) by applying master theorem
 */

import java.util.Scanner;

public class Majority {
	
	static Scanner sc = new Scanner( System.in );
	private int size;
	static int[] numbers;
	static boolean isMajority = false;
	
	public Majority(int nextInt) {
		size = nextInt;
		numbers = new int[size];
	}

	public static void main(String[] args){
		Majority maj = new Majority( sc.nextInt());
		maj.getNumbers();
		calculateMajority(numbers, 2);
		if (isMajority)
			System.out.println("YES");
		else
			System.out.println("NO");
		isMajority = false;
		calculateMajority(numbers, 3);
		if (isMajority)
			System.out.println("YES");
		else
			System.out.println("NO");
	}

	private void getNumbers() {
		for (int i = 0; i < size; i++) {
			numbers[i] = sc.nextInt();
		}
	}
	
	//Recursively called with array and factor to calculate if majority exists
	public static void calculateMajority(int[] num, int k) {
		if( num.length < 3 ) {
			if( num.length == 2 ) 
				isMajority =  num[0] == num[1];
			else 
				isMajority  = true;
		}
		int i =0;
		int splits[] = new int[num.length/3];
		while(i<num.length/3){
			splits[i] = num[i*3+1];
			i++;
		}

		//Choosing middle element as pivot
		int pivot  = splits[splits.length/2];
		int newNum[] = new int[num.length]; 
		int lessIndex =0, greaterIndex  = num.length-1;
		
		//Classifying elements as lesser or greater than pivot
		for(int j =0; j< num.length; j++){
			if(num[j] < pivot){
				newNum[lessIndex++] = num[j];
			}
			else if(num[j]>pivot){
				newNum[greaterIndex--] = num[j];
			}
		}
		if(greaterIndex!=num.length-1)
			greaterIndex++;
		if(lessIndex!=0)
			lessIndex--;
		
		int[] majNum;
		
		//Recursive call
		if(lessIndex > numbers.length/k){
			majNum = new int[lessIndex+1];
			System.arraycopy(newNum, 0, majNum, 0, lessIndex+1);
			calculateMajority(majNum, k);
		} else if ( num.length -1 - greaterIndex> numbers.length/k) {
			majNum = new int[num.length - greaterIndex];
			System.arraycopy(newNum, greaterIndex, majNum, 0, num.length-greaterIndex);
			calculateMajority(majNum, k);
		} else if(greaterIndex-lessIndex > numbers.length/k){
			isMajority = true; 
		}
	}
	
	public static void displayArray(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
}
