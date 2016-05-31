/*
 * @author: Paridhi Srivastava
 * @author: Manasi Sunil Bharde
 * This program tests different sorting algorithms for increasing number of values
 */

import java.util.Iterator;
import java.util.Random;


public class SortingTest {

	public static void main(String[] args) {
		SortingTest st = new SortingTest();
		st.testUniform(1000000);
		st.testGaussian(1000000);
	}
	
	
	public Double[] insertionSort(Double[] data){
		int size = data.length;
		for(int i = 1; i < size; i++){
			Double key = data[i]; 
			int j = i-1;
			for(; j>=0 && data[j]>key ; j--){				
				data[j+1] = data[j];
			}
			data[j+1] = key;
		}
		return data;
	}

	public Double[] bucketSort(Double[] data){
		int numOfBuckets = data.length;
		LinkedListNew<Double> buckets[] = new LinkedListNew[numOfBuckets];
		for(int i = 0 ; i < data.length ; i++ ){
			int index = (int) (numOfBuckets * data[i]);
			if(buckets[index] == null)
				buckets[index] = new LinkedListNew<Double>();
			buckets[index].add(data[i]);						
		}
		sortBuckets(buckets);
		int dataIndex = 0;
		for(int  i = 0; i < numOfBuckets; i++){
			if(buckets[i] == null)
				continue;
			for(int j = 0; j < buckets[i].size; j++){
				data[dataIndex++] = buckets[i].get(j);
			}
		}
		return data;
	}
	
	
	public void sortBuckets(LinkedListNew buckets[]){
		for(int i = 0; i < buckets.length; i++){
			if(buckets[i]!= null){
//				System.out.println("here");
				Node current  = buckets[i].front;
				Node pointer;
				while(current.hasNext()){
					pointer = current;
					current  = current.next;
					Double temp = (Double) current.value;
					boolean flag = false;
					while((Double)pointer.value > (Double)current.value){
						flag = true;
						pointer.next.setValue(pointer.value);	
						if(pointer.previous==null)
							break;
						pointer = pointer.previous;
					}		
					if(flag == true)
						pointer.setValue(temp);
				}
			}
		}
	}

	
	public Double[] mergeSortArray(Double[] data){
		if(data.length<2)
			return data;
		else{
			Double left[] = new Double[data.length/2];
			Double right[] = new Double[data.length - data.length/2]; 
			System.arraycopy(data, 0, left, 0, data.length/2);
			System.arraycopy(data, data.length/2, right, 0, data.length - data.length/2);
			return mergeSort(mergeSortArray(left),mergeSortArray(right));
		}
	}
	
	public static Double[] mergeSort(Double left[], Double right[]){
		
		int leftIndex = 0, rightIndex = 0;
		Double result[] = new Double[left.length+right.length];
		int i=0;
		while(leftIndex<left.length && rightIndex<right.length){
			if(left[leftIndex]<=right[rightIndex])
				result[i++] = left[leftIndex++];
			else
				result[i++] = right[rightIndex++];
		}
		if(leftIndex<left.length){
			System.arraycopy(left, leftIndex, result, i, left.length-leftIndex);}
		else
			System.arraycopy(right, rightIndex, result, i, right.length-rightIndex);
		return result;
	}
	
	
	
	public void displayArray(Double[] data){
		for(int i=0; i<data.length;i++){
			System.out.print(data[i]+" ");
		}
		System.out.println();
	}

	public void testUniform(int size){		
		Double[] randomArray = new Double[size];
		for(int i =0; i < size; i++){
			int index = size;
			do{
				index  = (int)(Math.random()*size);
			} while (randomArray[index] != null);
			randomArray[index] = 1.0 * i/size;
		}
		System.out.println("Uniform tests:" + System.currentTimeMillis());
		long time1 = System.currentTimeMillis();
		insertionSort(randomArray);
		long time2 = System.currentTimeMillis();
		System.out.println("Insertion completed in " + (time2-time1)+"ms");
		bucketSort(randomArray);
		long time3 = System.currentTimeMillis();
		System.out.println("Bucket sort completed in " + (time3-time2)+" ms");
		mergeSortArray(randomArray);
		long time4 = System.currentTimeMillis();
		System.out.println("Merge completed in " + (time4-time3));
	}
	
	public void testGaussian(int size){
		Double[] randomArray = new Double[size];
		Double a = 0.5;
		
		Random x = new Random();
		for(int i =0; i < size; i++){
			randomArray[i] = x.nextGaussian()*0.0001 + 0.5;
		}
		long time1 = System.currentTimeMillis();
		System.out.println("Gaussian tests:");
		insertionSort(randomArray);
		long time2 = System.currentTimeMillis();
		System.out.println("Insertion completed in " + (time2-time1)+"ms");
		bucketSort(randomArray);
		long time3 = System.currentTimeMillis();
		System.out.println("Bucket sort completed in " + (time3-time2)+" ms");
		mergeSortArray(randomArray);
		long time4 = System.currentTimeMillis();
		System.out.println("Merge completed in " + (time4-time3));
		
		
	}	
}

class Node<T>{
	T value;
	Node<T> next, previous;
	
	public Node(){
		
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}
	
	public Node getPrevious(){
		return previous;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(T value){
		this.value = value;
	}
	
	public boolean hasNext(){
		if(this.next!=null)
			return true;
		else 
			return false;
	}
	
	public Node next(){
		return this.next;
	}
}

class LinkedListNew<T> implements Iterable{
	
	Node<T> front, end;
	int size = 0;
	Node<T> iter;
	
	public LinkedListNew(){
		front = new Node();
		end = front;
		iter = front;
	}
	
	public void add(T nodeValue){		
		if(size == 0){
			front.setValue(nodeValue);
		}
		else{
			Node temp = front;
			while(temp.next != null)
				temp = temp.next();
			temp.next = new Node(nodeValue);
			end = temp.next;
			temp.next.previous = temp;
		}
		size++;
	}
	
	public void addFirst(T nodeValue){
		Node temp = front;
		front = new Node(nodeValue);
		front.next = temp;
		size++;
	}
	
	public void add(int index, T nodeValue){
		Node temp = front;
		for(int i =0; i < index-1; i++){
			temp = temp.next;
		}
		Node temp2 = temp.next;
		temp.next = new Node(nodeValue);		
		temp.next.next = temp2;
		size++;
	}
	
	public T get(int index){
		Node temp = front;
		for(int i =0; i < index; i++){
			temp = temp.next;
		}
		return (T)temp.value;
	}
	
	public void remove(Object node){
		Node temp = front;
		if(temp.equals(node))
			front = front.next();
		else
			while(temp.hasNext())
				if(temp.next().equals(node)){
					temp.next = temp.next.next();
					break;
				}
		size--;
	}

	@Override
	public Iterator iterator() {
		iter = front;
		
		Iterator it = new Iterator(){
			Node prev = iter;
			@Override
			public boolean hasNext(){
				return iter!=null & size!=0 ;
			}

			@Override
			public Object next(){
				prev = iter; 
				if(iter != null)
					iter = iter.next();
				return prev;
			}
			
		};
		return it;
	}

}
