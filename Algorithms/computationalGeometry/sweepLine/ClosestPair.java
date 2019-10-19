package computationalGeometry.sweepLine;

import java.util.*;

import computationalGeometry.*;

/**
 * Point: Class to store (x,y) - findClosestPair: Implement Sweep Line Algo to
 * compute closest pair. - computeShortestDist: compute distance between 2
 * points. - bruteForceComparison: Compares 3 points and returns the shortest
 * distance. - ClosestPair: Class to store closest pair and distance between
 * them -
 * 
 * @author Mrigendra
 *
 */

public class ClosestPair<T extends Number> {
	ArrayList<Point<Double>> sortedX;
	ArrayList<Point<Double>> sortedY;

	ClosestPair(List<Point<Double>> l) {
		sortedX = new ArrayList<>(l);
		sortedY = new ArrayList<>(l);
		// Pre-sort the array on X-points and Y-points.
		Collections.sort( sortedX, new XComparator() );
		Collections.sort( sortedY, new YComparator() );
	}
	
	public static void main(String[] args) {
//		System.out.println("Sweep Algorithm to compute least distance between two points.");
		Scanner scan = new Scanner("System.in");
//		l.add( getPoint(2,3) );
//		l.add( getPoint(2,10) );
//		l.add( getPoint(5,3) );
		int N = 0;
		if( scan.hasNextInt() )
			N = scan.nextInt();

		if( N >= 3 ) {
			ArrayList<Point<Double>> l = new ArrayList<>();
	        for( int i=0; i<N; i++ ) {
	            l.add( getPoint(  scan.nextInt(),  scan.nextInt() ) );
	        }
			
			ClosestPair<Double> sweep = new ClosestPair<>(l);
			PairDistance closestPair = sweep.findClosestPair(0, l.size(), sweep.sortedY );
			System.out.println( closestPair );
		}

		scan.close();
	}

	public PairDistance findClosestPair(int l, int h, ArrayList<Point<Double>> ySorted ) {
		if( h-l <= 3 )
			// For 3 points
			return bruteForceComparison( l, h );
		else {
			int mid = (l+h)/2;
			double midX = sortedX.get(mid).getX();
			
			ArrayList<Point<Double>> lYSorted = new ArrayList<>();
			ArrayList<Point<Double>> rYSorted = new ArrayList<>();
			// Mode points in the ySorted array to left/right side of the vertical sweep/separation
			// line. The points will still be sorted as ySorted array is already sorted and we are 
			// only separating it by comparing their x-coordinate. This is O(n)
			for( int i=0; i<ySorted.size(); i++) {
				if( ySorted.get(i).getX() < midX ) {
					lYSorted.add( ySorted.get(i) );
				} else {
					rYSorted.add( ySorted.get(i) );
				}
			}
			
			PairDistance closestPair = new PairDistance();
			PairDistance closestPair1 = findClosestPair( l, mid, lYSorted );
			PairDistance closestPair2 = findClosestPair( mid, h, rYSorted );
			
			closestPair = min( closestPair1, closestPair2);
			
			// Now combine the two areas and compute the closest of the points which were separated by 
			// the sweep line and which can still be closer still. Side case.
			ArrayList<Point<Double>> strip = new ArrayList<>();
			
			for( int i=0; i<ySorted.size(); i++) {
				if( Math.abs( ySorted.get(i).getX() - midX ) < closestPair.distance ) {
					strip.add( ySorted.get(i) );
				}
			}
			closestPair = stripClosest( strip, l, h, closestPair);
			
			return closestPair;
		}
	}

	private PairDistance stripClosest(ArrayList<Point<Double>> strip, int l, int h, PairDistance closestPair) {
		PairDistance min = closestPair;
		for( int i=0; i<strip.size()-1; i++) {
			for( int j=i+1; ( j<strip.size() && (strip.get(j).getY() - strip.get(i).getY()) < min.distance ); j++) {
				if( min.distance < computeDist(strip.get(j), strip.get(i)) ) {
					min.replace( strip.get(j), strip.get(i), min.distance );
				}
			}
		}
		return min;
	}

	private PairDistance min(PairDistance pair1, PairDistance pair2) {
		if( pair1.distance > pair2.distance ) {
			return pair2;
		} else {
			return pair1;
		}
	}

	private PairDistance bruteForceComparison(int l, int h) {
//		System.out.println(" perform BFC between:  " + l + " "+ h);
		PairDistance closestPair = new PairDistance();

		for (int i = l; i < h - 1; i++) {
			for (int j = i + 1; j < h; j++) {
				Double newDistance = computeDist(sortedX.get(i), sortedX.get(j));

				if ( newDistance < closestPair.getDistance() ) {
					closestPair.replace(sortedX.get(i), sortedX.get(j), newDistance);
				}
			}
		}
		return closestPair;
	}

	public Double computeDist(Point p1, Point p2) {
		// Euclidian distance
		return Math.sqrt(Math.pow(p1.getX().doubleValue() - p2.getX().doubleValue(), 2)
				+ Math.pow(p1.getY().doubleValue() - p2.getY().doubleValue(), 2));
	}

	private static Point<Double> getPoint(double i, double j) {
		Point<Double> p = new Point<>(i, j);
		return p;
	}

}

class PairDistance {
	Point<Double> p1;
	Point<Double> p2;
	Double distance;

	PairDistance() {
		p1 = null;
		p2 = null;
		distance = Double.MAX_VALUE;
	}

	public Double getDistance() {
		return distance;
	}

	public void replace(Point<Double> p1, Point<Double> p2, Double dist) {
		// replace current point with new points with shorter distance
		this.p1 = p1;
		this.p2 = p2;
		this.distance = dist;
	}

	public void replace(PairDistance anotherPair) {
		// replace current point with new pair of points with shorter distance
		this.p1 = anotherPair.p1;
		this.p2 = anotherPair.p2;
		this.distance = anotherPair.distance;
	}
	
	@Override
	public String toString() {
		return "Distance between two points (" + p1.getX() + ", " + p1.getY() +
				") and (" + p2.getX() + ", " + p2.getY() + 
				") is: " + distance;
	}
}

class XComparator implements Comparator<Point<Double>>{

	@Override
	public int compare(Point<Double> a, Point<Double> b) {
		if( a.getX() > b.getX() )
			return 1;
		if( a.getX() < b.getX() )
			return -1;
		return 0;
	}
}

class YComparator implements Comparator<Point<Double>>{

	@Override
	public int compare(Point<Double> a, Point<Double> b) {
//		if( a.getX() > b.getX() )
//			return 1;
//		if( a.getX() < b.getX() )
//			return -1;
		if( a.getY() > b.getY() )
			return 1;
		if( a.getY() < b.getY() )
			return -1;
		return 0;
	}
}

