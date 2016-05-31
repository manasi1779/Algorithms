package Test;
import java.util.Scanner;

public class DoubleKnapsack {
	static int noOfItems, weight1, weight2;
	static Scanner s = new Scanner(System.in);
	static Item items[];
	static int[][][] S;
	
	public static void main(String[] args) {
		noOfItems = s.nextInt();
		weight1 = s.nextInt();
		weight2 = s.nextInt();
		S =new int[noOfItems+1][weight1+1][weight2+1];
		getRecords();
		addItems();
		System.out.println(S[noOfItems][weight1][weight2]);
		/*for(int k=0; k<=noOfItems; k++){
			if(k>0)
			System.out.println(items[k-1].weight+" "+items[k-1].cost);
			for(int j=0; j<=weight1; j++){
				for(int i=0; i<=weight2; i++){
					System.out.print(S[k][j][i]+" ");
					}
				System.out.println();
			}
			System.out.println("###############");
		}*/
	}

	private static void getRecords() {
		items = new Item[noOfItems];
		for(int i=0; i<noOfItems; i++){
			items[i] = new Item(s.nextInt(),s.nextInt());
		}
	}		
	
	public static void addItems(){
		int c1=0, c2 = 0, c3 = 0;
		for(int i=0; i<=weight2; i++){
			for(int j=0; j<=weight1; j++){
				for(int k = 0; k<noOfItems; k++){
					if((i==0&&j==0) && k>0){
						S[k][i][j] = 0;
					}
					else{
				c1 = S[k-1][i][j];
				c2 = 0;
				c3 = 0;
				
				if(j>=items[k].weight){
					c2 = Math.max(S[k-1][i][j-items[k].weight]+items[k].cost,c1);
				}
				if(i>=items[k].weight){
					c3 = Math.max(S[k-1][i-items[k].weight][j]+items[k].cost,c1);
				}
				S[k][i][j] = Math.max(c2, c3); }
			}
		}
	}
	}	
}

class Item{
	int weight, cost;
	
	public Item(int weight, int cost){
		this.weight = weight;
		this.cost = cost;
	}	
}