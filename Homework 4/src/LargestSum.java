/**
 * Calculates sum of largest increasing subsequences
 */

import java.util.Scanner;

public class LargestSum {

	int input[];
	int sumArray[];
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		int len = s.nextInt();
		LargestSum sl = new LargestSum();
		sl.input = new int[len];
		sl.sumArray = new int[len + 1];
		sl.getInput();
		sl.getMaxSum();
	}

	private void getInput() {
		// TODO Auto-generated method stub
		for (int i = 0; i < input.length; i++) {
			input[i] = s.nextInt();
		}
	}

	public void getMaxSum() {
		int maxSum = 0;
		for (int i = 1; i <= input.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i-1]) {
					if (max < sumArray[j+1]) {
						max = sumArray[j+1];
					}
				}
			}
			sumArray[i] = max + input[i - 1];
			if(maxSum < sumArray[i])
				maxSum = sumArray[i];
		}
		System.out.println(maxSum);
	}
}
