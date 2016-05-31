/**
 * @author Manasi Sunil Bharde msb4977]
 * This program prints sum of all even numbers inputed
 */
import java.util.Scanner;

public class Evensum {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int numsize = s.nextInt();
		int sum = 0;
		for(int i = 0; i < numsize; i++){
			int num = s.nextInt(); 
			if(num % 2 == 0){
				sum += num;
			}
		}
		System.out.println(sum);
	}
}
