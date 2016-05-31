/*
 * @author: Paridhi Srivastava
 * @author: Manasi Sunil Bharde
 * This program finds the largest possible cost of the items that can be added 
 * to two backpacks having capacities W1 and W2, such that the weight of the 
 * items added to the backpack is less than or equal to the capacity of the 
 * backpack.  
 */

import java.util.Scanner;

public class DoubleKnapsack {

	static int[][][] knapsack;
	static int n;
	static int W1;
	static int W2;
	static Item[] items;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt() + 1;
		W1 = s.nextInt() + 1;
		W2 = s.nextInt() + 1;

		knapsack = new int[n][W1][W2];
		items = new Item[n - 1];

		for (int i = 0; i < n - 1; i++) {
			items[i] = new Item(s.nextInt(), s.nextInt());
		}

		System.out.println(maxValue());
	}

	public static int maxValue() {
		for (int k = 0; k < W2; k++) {
			for (int j = 0; j < W1; j++) {
				for (int i = 1; i < n; i++) {
					knapsack[i][j][k] = knapsack[i-1][j][k];
						if (j >= items[i-1].weight) {
							knapsack[i][j][k] = Math.max(
									knapsack[i - 1][j - items[i-1].weight][k] + items[i-1].cost,
									knapsack[i - 1][j][k]);
						} 
						if (k >= items[i-1].weight) {
							knapsack[i][j][k] = Math.max(
									knapsack[i - 1][j][k - items[i-1].weight] + items[i-1].cost,
									knapsack[i - 1][j][k]);
						}
					}
			}
		}
		return knapsack[n-1][W1-1][W2-1];
	}
}

class Item{
	int weight, cost;
	
	public Item(int weight, int cost){
		this.weight = weight;
		this.cost = cost;
	}	
}