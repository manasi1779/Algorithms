/**
 * Calculates max evaluation result
 */
import java.util.Scanner;

public class ParenthesesGreedy {
	static Scanner s = new Scanner(System.in);
	static String equation;
	static Stack<Integer> operands = new Stack<Integer>();
	static Stack<Character> operators = new Stack<Character>();
	public static void main(String[] args) {
		equation = s.nextLine();
		fillStacks();
		System.out.println(solve());
	}
	
	public static void fillStacks(){
		String[] array = equation.split(" "); //"[+|*]"
		for(int i =0; i<array.length; i++){
			if(array[i].charAt(0)=='+' || array[i].charAt(0)=='*'){
				operators.push(array[i].charAt(0));
			}
			else{
				operands.push(Integer.parseInt(array[i]));
			}
		}
	}
	
	public static int solve(){
		while(!operators.isEmpty()){
			int operand1 = operands.pop(), operand2 = operands.pop();
			char operator = operators.pop();
			if(operator == '+')
				operands.push(operand1+operand2);
			else if(operator == '*' && !operators.isEmpty()){
				char nextoper = operators.peek();
				if(nextoper == '+'){
					nextoper = operators.pop();
					int operand3 = operands.pop();
					operands.push(operand3+operand2);
					operands.push(operand1);
					operators.push(operator);
				}
				else
				operands.push(operand1*operand2);
			}
			else{
				operands.push(operand1*operand2);
			}
		}
		return operands.pop();
	}	
}

class Node<T>{
	T s;
	Node<T> previous;
	
	public Node(T t){
		s=t;
	}	
}

class Stack<T>{
	Node<T> top;
	int size = 0;
	
	public void push(T c){
		if(size==0){
			top = new Node<T>(c);
		}
		else{
			Node<T> temp = top;
			top = new Node<T>(c);
			top.previous = temp;
		}
		size++;
	}
	
	public T pop(){
		T op = top.s;
		top = top.previous;
		size--;
		return op;
	}
	
	public T peek(){
		return top.s;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
}
