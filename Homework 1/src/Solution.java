
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Solution.compress("aaaaaaaabaaabccd"));
		System.out.println(Solution.compress(""));
		System.out.println(Solution.compress("abb "));
		System.out.println(Solution.compress("abcdefghijklmnoo"));
	}

	static String compress(String str){
		if(str.length() == 0)
			return "";
		StringBuffer buffer = new StringBuffer();
		char previous = str.charAt(0);
		buffer.append(previous);
		int count = 1;
		for(int i =1; i< str.length(); i++){
			if(str.charAt(i)==previous)
				count++;
			else{
				if(count != 1)
					buffer.append(count);
				count = 1;
				buffer.append(str.charAt(i));
				previous = str.charAt(i);
			}
		}
		if(count != 1)
			buffer.append(count);
		return buffer.toString();
	}
}
