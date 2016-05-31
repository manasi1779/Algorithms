/**
 * @author Manasi Bharde and Paridhi Srivastava 
 * 
 *  This program calculates number of swaps required to re-arrange the row of students and teacher 
 *  as per specified criteria by making only adjacent swaps.
 *  This can be achieved using merge sort. However merge sort involves inserting elements to new array as per order
 *  So inserting element from left array into target array can be considered as no swap.
 *  Whereas putting element from right array into target array can be considered as 
 *  requiring swaps equal to remaining left array.
 *  Since we are only counting the swaps necessary by actually arranging using merge sort complexity is same as merge sort O(nlogn)
 *  And complexity of creating row is O(n)
 */

import java.util.Scanner;

public class Picture {

	static Scanner s = new Scanner(System.in);
	Person persons[];
	static int swapCount = 0;
	
	public Picture(int size) {
		persons = new Person[size];
	}

	public static void main(String[] args) {
		Picture clas = new Picture(s.nextInt());
		clas.getPersons();
		clas.persons = clas.mergeSort(clas.persons);
		System.out.println(swapCount);
	}

	public void getPersons() {
		for (int i = 0; i < persons.length; i++) {
			persons[i] = new Person(s.nextInt(), s.nextDouble());
		}
	}

	public Person[] merge(Person[] left, Person[] right) {
		int leftIndex = 0, rightIndex = 0, i = 0;
		Person result[] = new Person[left.length + right.length];
		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex].age == right[rightIndex].age) {
				if (left[leftIndex].age == 8) {
					if (left[leftIndex].height < right[rightIndex].height) {
						result[i++] = right[rightIndex++];
						swapCount += left.length- leftIndex; 
					}
					else
						result[i++] = left[leftIndex++];
				} else if (left[leftIndex].age == 7) {
					if (left[leftIndex].height > right[rightIndex].height) {
						result[i++] = right[rightIndex++];
						swapCount += left.length- leftIndex; 
					}
					else
						result[i++] = left[leftIndex++];
				}
			} else if (left[leftIndex].age == 44 && right[rightIndex].age == 7) {
				result[i++] = right[rightIndex++];
				swapCount += left.length - leftIndex;
			}
			else if (left[leftIndex].age == 44 && right[rightIndex].age == 8)
				result[i++] = left[leftIndex++];
			else if (left[leftIndex].age == 8) {
				result[i++] = right[rightIndex++];
				swapCount += left.length - leftIndex;
			} else {
				result[i++] = left[leftIndex++];
			}
		}
		if (leftIndex < left.length) {
			System.arraycopy(left, leftIndex, result, i, left.length - leftIndex);
		} else
			System.arraycopy(right, rightIndex, result, i, right.length - rightIndex);
		return result;
	}

	public Person[] mergeSort(Person persons[]) {
		if (persons.length < 2)
			return persons;
		else {
			Person left[] = new Person[persons.length / 2];
			Person right[] = new Person[persons.length - persons.length / 2];

			System.arraycopy(persons, 0, left, 0, persons.length / 2);
			System.arraycopy(persons, persons.length / 2, right, 0, persons.length - persons.length / 2);
			return merge(mergeSort(left), mergeSort(right));
		}
	}

	public static void displayArray(Person[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}		
	}
}

class Person {
	int age;
	Double height;

	public Person(int age, Double height) {
		this.age = age;
		this.height = height;
	}

	public String toString() {
		return age + " " + height;
	}
}