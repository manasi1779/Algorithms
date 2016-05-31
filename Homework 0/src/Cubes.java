/**
 * @author Manasi Sunil Bharde msb4977
 * This program prints all cubes between 0 and 100
 */
import java.io.IOException;
import java.util.Scanner;

public class Cubes {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int number = 0;
		Scanner s = new Scanner(System.in);
		number = s.nextInt();		
		
		if( number > 1000000 ){
			System.exit(1);
		}
			
		for(int i = 0 ; i < 100; i++ ){
			int x = i*i*i;
			if(x>number)
				break;
			System.out.println(x);
		}
	}
}
