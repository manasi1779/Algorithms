import java.util.Scanner;

public class MinimumGap1 {

	static Scanner s = new Scanner(System.in);
	static double start, end;
	static double gaps[];
	static Interval sortedIntervals[]; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = s.nextInt();
		start = s.nextDouble();
		end = s.nextDouble();
		Interval intervals[] = new Interval[n];
		for (int i = 0; i < n; i++) {
			intervals[i] = new Interval(s.nextDouble(), s.nextDouble());
		}
		displayArray(intervals);
		sortedIntervals = mergeSortArray(intervals);
		displayArray(sortedIntervals);
		calculateGaps(sortedIntervals);
	}

	public static Interval[] mergeSort(Interval left[], Interval right[]) {

		int leftIndex = 0, rightIndex = 0;
		Interval result[] = new Interval[left.length + right.length];
		int i = 0;

		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex].end >= right[rightIndex].end)
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
	
	public static void calculateGaps( Interval[] interval ) {
		gaps = new double[ interval.length+1 ];
		gaps[0] = end-start;
		for ( int i = 0; i<interval.length; i++) {
			gaps[i] = rho(i);
			//System.out.println(gaps[i]);
		}
		for ( int i = 0; i<interval.length; i++) {
			gaps[i] += interval[i].start;
			System.out.println(gaps[i]);
		}
	}
	
	public static double rho( int index ) {
		double gap;
		double min = end-start;
		for(int i =index-1; i>0; i--){
			if(sortedIntervals[i].start<=sortedIntervals[index].end){
				return (sortedIntervals[index].end-sortedIntervals[i].start) + gaps[i];
			}
		}
		return end-sortedIntervals[index].end;
	}

}

/*class Interval {
	double start, end;

	public Interval(double start, double end) {
		this.start = start;
		this.end = end;
	}

}*/