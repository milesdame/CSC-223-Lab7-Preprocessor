package preprocessor.delegates;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

public class ImplicitPointPreprocessor
{
	/**
	 * It is possible that some of the defined segments intersect
	 * and points that are not named; we need to capture those
	 * points and name them.
	 * 
	 * Algorithm:
	 *    - for each segment:
	 *    	- for each other segment, check if intersection exists between this segment and that segment
	 *    	- add to list of implicitPoints if so
	 * 
	 */
	public static Set<Point> compute(PointDatabase givenPoints, List<Segment> givenSegments)
	{
		Set<Point> implicitPoints = new LinkedHashSet<Point>();
        
		// for each segment in the list of segments...
		for (int i = 0; i < givenSegments.size(); i++) {
			System.out.println("index:" + i);
			// check for an intersection between this point (i) and that point (j)
			for (int j = 0; j < givenSegments.size(); i++) {
				
				if (i == j) continue;
        		Point impPoint = givenSegments.get(i).segmentIntersection(givenSegments.get(j));
        		if (!givenPoints.contains(impPoint)) implicitPoints.add(impPoint);
			}
		}
		
		return implicitPoints;
	}
}
