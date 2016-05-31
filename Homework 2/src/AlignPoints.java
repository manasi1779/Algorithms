/**
 * @author Manasi Bharde and Paridhi Srivastava
 * This program calculates maximum pairs of points that can be aligned from given set of points by making a single folding.
 * Approach taken is: 
 * Derive equations of perpendicular bisectors of line joining each pair. - O(n^2)
 * Group same equations by using merge sort - O(mlogm) m = n^2
 * Find maximum occurring equation from sorted list - O(m) where m = n^2
 * Therefore total complexity as per input size is: O(n^2logn)
 */

import java.util.Scanner;

public class AlignPoints {

	int size;
	Point points[];
	LineEquation perpBisectors[];
	
	static Scanner s = new Scanner(System.in);
	
	public AlignPoints(int size){
		this.size = size;
		points = new Point[size];
		perpBisectors = new LineEquation[size*(size-1)/2];
	}
	
	public static void main(String[] args) {
		AlignPoints align = new AlignPoints(s.nextInt());
		align.getPoints();
		align.getPairs();
		align.perpBisectors = mergeSortArray(align.perpBisectors);
		int maxCount = align.getMaxRepeatedEquations();
		System.out.println(maxCount);
	}	
	
	private int getMaxRepeatedEquations() {
		int max = 1, count = 1;
		String previous = perpBisectors[0].perpBisector;
		for(int  i = 1; i < perpBisectors.length; i++){
			if(perpBisectors[i].perpBisector.equals(previous)){
				count++;
			}
			else{
				previous = perpBisectors[i].perpBisector;
				if(count>max)
					max = count;
				count  = 1;
			}
		}
		if(count>max)
			max = count;
		return max;
	}

	private void getPairs() {
		int k = 0;
		for(int i = 0; i < size; i++){
			for(int j = i+1; j < size; j++){
				perpBisectors[k++] = new LineEquation(points[i], points[j]);
			}
		}
	}
	

	private  void getPoints() {
		for(int i = 0; i < size; i++)
			points[i] = new Point(s.nextInt(),s.nextInt());
	}
	
public static LineEquation[] mergeSort(LineEquation left[], LineEquation right[]){
		
		int leftIndex = 0, rightIndex = 0;
		LineEquation result[] = new LineEquation[left.length + right.length];
		int i=0;
		while(leftIndex<left.length && rightIndex<right.length){
			if(left[leftIndex].perpBisector.compareTo(right[rightIndex].perpBisector)<=0)
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
	
	public static LineEquation[] mergeSortArray(LineEquation[] data){
		if(data.length<2)
			return data;
		else{
			LineEquation left[] = new LineEquation[data.length/2];
			LineEquation right[] = new LineEquation[data.length - data.length/2]; 
			System.arraycopy(data, 0, left, 0, data.length/2);
			System.arraycopy(data, data.length/2, right, 0, data.length - data.length/2);
			return mergeSort(mergeSortArray(left),mergeSortArray(right));
		}
	}

	public static void displayArray(LineEquation[] data){
		for(int i=0; i<data.length;i++){
			System.out.print(data[i].perpBisector+"; ");
		}
		System.out.println();
	}	
}

class Point implements Comparable<Point>{
	double x, y;
	Double distance, polarAngle;
	
	public Point(double d, double e){
		this.x = d;
		this.y = e;
		this.distance = Math.sqrt(d*d + e*e);
		this.polarAngle = Math.atan2(d, e);
		this.polarAngle = this.polarAngle*180/Math.PI;
	}

	@Override
	public int compareTo(Point arg0) {
		return this.distance.compareTo(((Point)arg0).distance);
	}	
	
	@Override
	public String toString(){
		return this.x+" "+y+" "+distance+" "+polarAngle;
	}
}

class LineEquation{
	String perpBisector;
	Double intercept;
	public LineEquation(Point a, Point b){
		Point midPoint = new Point((a.x+b.x)/2,(a.y+b.y)/2);
		Double slope = -(1.0 * (b.x-a.x)/(b.y-a.y));
		
		if( b.y-a.y != 0 ) {
			intercept = (midPoint.y * (b.y-a.y) + (b.x-a.x)*midPoint.x) / ((b.y-a.y));
			if (slope == -0.0) {
				slope = 0.0;
			}
		}
		else{
			slope = Double.POSITIVE_INFINITY;
			intercept = -midPoint.x;
		}
		if(intercept == -0.0)
			intercept  = 0.0;
		perpBisector = slope+"#"+intercept;
	}	
}