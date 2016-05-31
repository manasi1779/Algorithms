/**
 * This program finds minimum cost to convert one string into other
 *  
 */

import java.util.Scanner;

public class StringConvert {

	static Scanner s = new Scanner(System.in);
	String X, Y;
	int[][] dpArray;
	
	public static void main(String[] args) {
		StringConvert sc = new StringConvert();
		sc.getInput();
		System.out.println(sc.getMinCost());
	}
	
	private int getMinCost(){
		init();
		for(int i = 1; i<=Y.length(); i++){
			for(int j = 1; j<=X.length(); j++){
				int cost1 = 50000;
				if(X.charAt(j-1) == Y.charAt(i-1))
					cost1 = 0;
				int value1 = dpArray[i-1][j-1] + cost1;
				int value2 = dpArray[i][j-1] + 3;
				int value3 = dpArray[i-1][j] + 4;
				int value4 = 50000; 
				if(j>1)
					value4 = dpArray[i-1][j-2] + 5;
				int min1 = Math.min(value1, value2);
				int min2 = Math.min(value3, value4);
				dpArray[i][j] = Math.min(min1, min2);
			}
		}
		return dpArray[Y.length()][X.length()];
	}
	
	private void init() {
		dpArray = new int[Y.length()+1][X.length()+1];
		for(int i =0; i <= X.length(); i++){
			dpArray[0][i] =  (i)*3;
		}
		for(int i = 0; i<= Y.length(); i++){
			dpArray[i][0] =  (i)*4;
		}
	}

	public void getInput(){
		X = s.nextLine();
		Y = s.nextLine();
	}
}
