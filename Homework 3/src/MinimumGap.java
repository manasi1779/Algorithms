/**
 * @author Manasi Bharde and Paridhi Srivastava
 * 
 * This program schedules intervals such that there is total minimum gap between the intervals.
 *  Intervals are first sorted as per the earliest finish time.
 *  Using dynamic programming array, gaps for next intervals are calculated.
 *  Rho is index of previous interval which is best compatible with the current interval.
 *  As the new interval is added and rho index is calculated, gap is reduced by its own length for new interval.
 *  Which is stored in dynamic programming array.  
 */

import java.util.Scanner;

public class MinimumGap {

	static Scanner s = new Scanner(System.in);
	static double start, end;
	static double gaps[];
	static Interval sortedIntervals[]; 

	public static void main(String[] args) {
		int n = s.nextInt();
		start = s.nextDouble();
		end = s.nextDouble();
		Interval intervals[] = new Interval[n];
		for (int i = 0; i < n; i++) {
			intervals[i] = new Interval(s.nextDouble(), s.nextDouble());
		}
		sortedIntervals = mergeSortArray(intervals);
		calculateGaps(sortedIntervals);
		System.out.println((int)(gaps[n-1]));
	}

	public static Interval[] mergeSort(Interval left[], Interval right[]) {

		int leftIndex = 0, rightIndex = 0;
		Interval result[] = new Interval[left.length + right.length];
		int i = 0;

		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex].end <= right[rightIndex].end)
				result[i++] = left[leftIndex++];
			else
				result[i++] = right[rightIndex++];
		}

		if (leftIndex < left.length) {
			System.arraycopy(left, leftIndex, result, i, left.length - leftIndex);
		} else
			System.arraycopy(right, rightIndex, result, i, right.length - rightIndex);
		return result;
	}

	public static Interval[] mergeSortArray(Interval[] data) {
		if (data.length < 2)
			return data;
		else {
			Interval left[] = new Interval[data.length / 2];
			Interval right[] = new Interval[data.length - data.length / 2];
			System.arraycopy(data, 0, left, 0, data.length / 2);
			System.arraycopy(data, data.length / 2, right, 0, data.length - data.length / 2);
			// displayArray(left);
			// displayArray(right);
			return mergeSort(mergeSortArray(left), mergeSortArray(right));
		}
	}

	public static void displayArray(Interval[] data) {
		System.out.println(data.length);
		for (int i = 0; i < data.length; i++) {
			System.out.print("{" + data[i].end + " " + data[i].start + "}, ");
		}
		System.out.println();
	}
	
	// Creates dynamic programming array.
	public static void calculateGaps( Interval[] interval ) {
		gaps = new double[ interval.length+1 ];
		gaps[0] = interval[0].start-start + end-interval[0].end;
		for ( int i = 1; i<interval.length; i++) {
			int rho1 = rho(i);
			double gap;
			double rho2;
			if(rho1==-1){
				rho2 = interval[i].start-start+end-interval[i].end;
				gap = 0;
			}
			else{
				rho2 = gaps[rho1]-(interval[i].end-interval[i].start);
			}
			gaps[i] = Math.min(gaps[i-1], rho2);
		}		
	}
	
	// Finds most compatible previous interval
	public static int rho( int index ) {
		double min = sortedIntervals[index].start;
		int rhoi = -1;
		for(int i =index-1; i>=0; i--){
			if(sortedIntervals[i].end <= sortedIntervals[index].start){
				double gap = sortedIntervals[index].start-sortedIntervals[i].end;
				if( min > gap){
					min = gap;
					rhoi = i;
				}
			}
		}
		return rhoi;
	}
}

class Interval {
	double start, end;

	public Interval(double start, double end) {
		this.start = start;
		this.end = end;
	}

}