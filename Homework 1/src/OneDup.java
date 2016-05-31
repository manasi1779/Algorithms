/*
 * @author Manasi Sunil Bharde and Paridhi Srivastava
 */


import java.util.Scanner;

public class OneDup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		int numsize = s.nextInt(), previous = s.nextInt(), dup = -1;
		Integer numArray[]= new Integer[numsize];
		numArray[0] = previous;
		for(int i = 1; i < numsize; i++){
			numArray[i] = s.nextInt();
		}
		
		for(int i =1; i < numsize; i++){
			int current = numArray[i];
			
			if(previous == current){
				dup = current;
			}
			previous = current;
		}
		
		if(dup !=-1)
			System.out.println(dup);
	}
	
	public void binSearch(int[] numArray){
		int mid = numArray.length/2;
		
			
	}
}
