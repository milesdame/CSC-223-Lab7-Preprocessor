/**
 * A class for identifying all triangles within a given figure
 * 
 * @author milesdame
 * @date 12/6/2022
 */

package preprocessor;

import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * Computes all of the possible triangles within a figure using the segments that make up that figure
	 * @throws FactException
	 */
	private void computeTriangles() throws FactException {
		
		// Get the segments as an ArrayList
		ArrayList<Segment> segmentArr = new ArrayList<Segment>(_segments.keySet());
		int i;
		
		// Loop through all of the segments
		for (i = 0; i < segmentArr.size(); i++) {
			
			// Get the segment
			Segment seg1 = (Segment) segmentArr.get(i);
			
			// Find the next segment that matches the first and then look to see 
			// if a third segment exists that matches both and then create a triangle using all three segments
			this.findMatchingSegs(i + 1, segmentArr, seg1);
		}
	}
	
	/**
	 * Finds the second matching segment in a potential triangle
	 * @param index
	 * @param segmentArr
	 * @param seg1
	 * @throws FactException
	 */
	private void findMatchingSegs(int index, ArrayList segmentArr, Segment seg1) throws FactException {
		
		// Loop through all of the remaining segments
		for (int j = index; j < segmentArr.size(); j++) {
			
			// Get the next segment
			Segment seg2 = (Segment) segmentArr.get(j);
			
			// Check if the second segment shares a vertex with the first segment 
			if (seg1.hasSharedVertex(seg2) && !(seg1.isCollinearWith(seg2))) {
				
				// If so then get that shared point
				Point sharedPoint = seg1.sharedVertex(seg2);
				
				// Create a segment from the two points the first segments don't share 
				// to use for finding a third segment
				Segment matchMaker = new Segment(seg1.other(sharedPoint), seg2.other(sharedPoint));	
				
				// Find the third and final segment 
				this.findThirdSeg(j + 1, segmentArr, matchMaker, seg1, seg2);
				
			}
		}
	}
	
	/**
	 * Searches for the third segment of a triangle and if found constructs a Triangle and adds it to the Set
	 * @param index
	 * @param segmentArr
	 * @param matchMaker
	 * @param seg1
	 * @param seg2
	 * @throws FactException
	 */
	private void findThirdSeg(int index, ArrayList segmentArr, Segment matchMaker, Segment seg1, Segment seg2) throws FactException {
		
		// Loop through the remaining segments after the point where the second segment is found
		for (int k = index; k < segmentArr.size(); k++) {
			
			
			// Check if the current segment matches the matchMaker segment
			if (segmentArr.get(k).equals(matchMaker)) {
				
				// If it does then get it
				Segment seg3 = (Segment) segmentArr.get(k);
				_triangles.add(new Triangle(Arrays.asList(seg1, seg2, seg3)));
			}						
		}
	}
}
