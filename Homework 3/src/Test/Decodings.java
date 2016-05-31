package Test;
import java.util.Scanner;

public class Decodings {

	static String input;
	static int count[];
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		input = s.nextLine();
		count = new int[input.length()+1];
		count[0] = 0;
		count[1] = 1;
	}
	
	public void calculateCount(){
		for(int i = 1; i<input.length(); i++){
			char c = input.charAt(i);
			if(c==input.charAt(i-1)){
				if(c=='1')
					count[i]+=2;
			}
			else{
				
			}
					
		}
	}

}
