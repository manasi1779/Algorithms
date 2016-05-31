/*
 * @author: Paridhi Srivastava
 * @author: Manasi Sunil Bharde
 * This program determines the number of different decodings of a given 
 * code in linear time.
 */

import java.util.Scanner;

public class Decodings {
	
	static int numberofdecodings[];
 	public static void main(String[] args) {
		Scanner s = new Scanner( System.in );
		String encoding = s.nextLine();
		numberofdecodings = new int[encoding.length()];
		System.out.println(decode(encoding));
	}
	
 	public static int decode( String s ) {
		numberofdecodings[0] = 1;
		int n;
		for ( int i = 1; i < s.length(); i++ ) {
			n=0;
			if(i>=1) {
				String newString = s.substring(i-1, i) + s.substring(i, i+1) ;
				
				if ( newString.equals("10") || newString.equals("01") ) {
					n += 1;
					if ( i >= 3 ) {
						n += numberofdecodings[i-2] -1;
					}
				}				
				if (i>=2){
					newString = s.substring(i-2, i) + s.substring(i,i+1);
					if ( newString.equals("111") || newString.equals("011") ) {
						n += 1;
						if ( i >= 4 ) {
							n += numberofdecodings[i-3] -1;
						}
					}
				}				
			}
			numberofdecodings[i] = n + numberofdecodings[i-1];
			System.out.println(numberofdecodings[i]);
		}
		return numberofdecodings[s.length()-1];
	}
}