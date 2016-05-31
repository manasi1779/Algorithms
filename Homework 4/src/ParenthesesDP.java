/*
 * @author: Manasi Sunil Bharade
 * @author: Paridhi Srivastava
 * 
 * This program takes in an expression and computes the maximum
 * possible value of the expression by fully parenthesizing it.
 */
import java.util.Scanner;

public class ParenthesesDP {

	static int[] operands;
	static String[] operators;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String exp = s.nextLine();
		String[] arrayExp = exp.split("\\s+");
		operands = new int[(arrayExp.length + 1) / 2];
		operators = new String[(arrayExp.length + 1) / 2 - 1];
		int indexOperand = 0;
		int indexOperator = 0;
		for (int i = 0; i < arrayExp.length; i++) {
			if (i % 2 == 0) {
				operands[indexOperand++] = Integer.parseInt(arrayExp[i]);
			} else {
				operators[indexOperator++] = arrayExp[i];
			}
		}
		System.out.println(maxSum());
	}

	public static int maxSum() {
		int[][] maxSumArray = new int[operands.length][operands.length];
		int[][] minSumArray = new int[operands.length][operands.length];
		int op = 0;
		for (int i = 0; i < operands.length; i++) {
			for (int L = 0; L< operands.length-i; L++) {
				int R = L+i;
				if (R == L) {
					if (R == 0) {
						minSumArray[L][R] = operands[op];
						maxSumArray[L][R] = operands[op++];
						
					} else {
						minSumArray[L][R] = operands[op];
						maxSumArray[L][R] = operands[op++];
					}
				} else {
					maxSumArray[R][L] = Integer.MIN_VALUE;
					minSumArray[R][L] = Integer.MAX_VALUE;
					for (int k = L; k <= R - 1; k++) {
						int tempMax = 0;
						int tempMin = 0;
						if(k >= 0 && operators[k].equals("-")){
							tempMax = maxSumArray[k][L] + (-1) * minSumArray[R][k + 1];
							tempMin = minSumArray[k][L] + (-1) * maxSumArray[R][k + 1];
						} else {
							tempMax = maxSumArray[k][L] + maxSumArray[R][k + 1];
							tempMin = minSumArray[k][L] + minSumArray[R][k + 1];
						}
						if (maxSumArray[R][L] < tempMax) {
							maxSumArray[R][L] = tempMax;
						}
						if (minSumArray[R][L] > tempMin) {
							minSumArray[R][L] = tempMin;
						}
					}
				}
			}
		}
		
		return maxSumArray[operands.length - 1][0];
	}
}
