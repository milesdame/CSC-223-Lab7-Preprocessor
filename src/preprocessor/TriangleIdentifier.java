package preprocessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;
import geometry_objects.points.Point;

public class TriangleIdentifier
{
	protected Set<Triangle>         _triangles;
	protected Map<Segment, Segment> _segments;

	public TriangleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested;
	 * memoize results for subsequent calls.
	 */
	public Set<Triangle> getTriangles() throws FactException
	{
		if (_triangles != null) return _triangles;

		_triangles = new HashSet<Triangle>();

		computeTriangles();

		return _triangles;
	}


	private void computeTriangles() throws FactException {
		Set<Segment> segmentSet = _segments.keySet();
		Object[] segmentArr = segmentSet.toArray();
		int i;;
		
		Point sharedPoint;
		Point point1;
		Point point2;
		
		List<Segment> segmentList;
		
		// Loop through all of the segments
		for (i = 0; i < segmentArr.length; i++) {
			// Get the first segment
			Segment seg1 = (Segment) segmentArr[i];
			
			// Loop through all of the remaining segments
			for (int j = (i + 1); j < segmentArr.length; j++) {
				// Get the next segment
				Segment seg2 = (Segment) segmentArr[j];
				
				// Check if the second segment shares a vertex with the first segment 
				if (seg1.hasSharedVertex(seg2)) {
					
					// If so then get that shared point
					sharedPoint = seg1.sharedVertex(seg2);
					
					// Get the other points from each segment (the two that don't match between the segments)
					point1 = seg1.other(sharedPoint);
					point2 = seg2.other(sharedPoint);
					
					// Create a segment from those two points to use for finding a third segment
					Segment matchMaker = new Segment(point1, point2);	
					
					// Loop through the remaining segments after the point where the second segment is found
					for (int k = (j + 1); j < segmentArr.length; k++) {
						
						// Check if the current segment matches the matchMaker segment
						if (segmentArr[k].equals(matchMaker)) {
							
							// If it does then get it
							Segment seg3 = (Segment) segmentArr[k];
							
							// Create a list of the three matching segments
							segmentList = createSegList(seg1, seg2, seg3);
							
							// Create a triangle from the list
							Triangle tri = new Triangle(segmentList);
							
							// Add the triangle to the database
							_triangles.add(tri);
						}
					}
				}
				
				
			}
		}
	}
	
	private List<Segment> createSegList(Segment s1, Segment s2, Segment s3) {
		ArrayList<Segment> list = new ArrayList<Segment>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		return list;
	}
}
