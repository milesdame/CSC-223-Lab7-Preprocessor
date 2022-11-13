package preprocessor;

import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.HashMap;
import geometry_objects.delegates.SegmentDelegate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import preprocessor.delegates.ImplicitPointPreprocessor;
import geometry_objects.Segment;

public class Preprocessor
{
	// The explicit points provided to us by the user.
	// This database will also be modified to include the implicit
	// points (i.e., all points in the figure).
	protected PointDatabase _pointDatabase;

	// Minimal ('Base') segments provided by the user
	protected Set<Segment> _givenSegments;

	// The set of implicitly defined points caused by segments
	// at implicit points.
	protected Set<Point> _implicitPoints;

	// The set of implicitly defined segments resulting from implicit points.
	protected Set<Segment> _implicitSegments;

	// Given all explicit and implicit points, we have a set of
	// segments that contain no other subsegments; these are minimal ('base') segments
	// That is, minimal segments uniquely define the figure.
	protected Set<Segment> _allMinimalSegments;

	// A collection of non-basic segments
	protected Set<Segment> _nonMinimalSegments;

	// A collection of all possible segments: maximal, minimal, and everything in between
	// For lookup capability, we use a map; each <key, value> has the same segment object
	// That is, key == value.
	protected Map<Segment, Segment> _segmentDatabase;
	public Map<Segment, Segment> getAllSegments() { return _segmentDatabase; }

	public Preprocessor(PointDatabase points, Set<Segment> segments)
	{
		_pointDatabase  = points;
		_givenSegments = segments;

		_segmentDatabase = new HashMap<Segment, Segment>();

		analyze();
	}

	/**
	 * for testing purposes ONLY
	 */
	public Preprocessor() {
		_pointDatabase  = new PointDatabase();
		_givenSegments = new LinkedHashSet<Segment>();
		_segmentDatabase = new HashMap<Segment, Segment>();
	}

	/**
	 * Invoke the precomputation procedure.
	 */
	public void analyze()
	{
		//
		// Implicit Points
		//
		_implicitPoints = ImplicitPointPreprocessor.compute(_pointDatabase, _givenSegments.stream().toList());

		//
		// Implicit Segments attributed to implicit points
		//
		_implicitSegments = computeImplicitBaseSegments(_implicitPoints);

		//
		// Combine the given minimal segments and implicit segments into a true set of minimal segments
		//     *givenSegments may not be minimal
		//     * implicitSegmen
		//
		_allMinimalSegments = identifyAllMinimalSegments(_implicitPoints, _givenSegments, _implicitSegments);


		//
		// Construct all segments inductively from the base segments
		//
		_nonMinimalSegments = constructAllNonMinimalSegments(_allMinimalSegments);

		//
		// Combine minimal and non-minimal into one package: our database
		//
		_allMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
		_nonMinimalSegments.forEach((segment) -> _segmentDatabase.put(segment, segment));
	}

	/**
	 *
	 * @param _implicitPoints2
	 * @param _givenSegments2
	 * @param _implicitSegments2
	 * @return
	 */
	public Set<Segment> identifyAllMinimalSegments(Set<Point> _implicitPoints2, Set<Segment> _givenSegments2,

			Set<Segment> _implicitSegments2) {
		Set<Segment> allMinimalSegments = _implicitSegments2;
		Set<Point> points = this.combinePoints(_implicitPoints2, _pointDatabase.getPoints());

		// Loop through all of the given segments
		for (Segment s : _givenSegments2) {

			// Get a sorted array of all the points on the segment
			SortedSet<Point> pointsOn = s.collectOrderedPointsOnSegment(points);
			Object[] pArray = pointsOn.toArray();

			// Loop through the points and create segments from the current point and the next
			// point until the second to last point
			for (int i = 0; i < pArray.length - 1; i++) {
				Point p1 = (Point) pArray[i];
				Point p2 = (Point) pArray[i+1];

				// Add the points as a new segment to the allMinimalSegments Set
				allMinimalSegments.add(new Segment(p1, p2));
			}
		}


		return allMinimalSegments;

	}

	/**
	 *
	 * @param ps1
	 * @param ps2
	 * @return
	 */
	private Set<Point> combinePoints(Set<Point> ps1, Set<Point> ps2) {
		Set<Point> allPoints = new LinkedHashSet<Point>(ps1);

		for (Point p : ps2) {
			allPoints.add(p);
		}

		return allPoints;
	}

	/**
	 * given all minimal segments, find all non-minimal segments
	 *
	 * @param minimalSegs - all minimal segments
	 * @return set of all non-minimal segments
	 */
	public Set<Segment> constructAllNonMinimalSegments(Set<Segment> minimalSegs) {

		ArrayList<Segment> minimalSegList = new ArrayList<Segment>();
		minimalSegList.addAll(minimalSegs);

		return constructAllNonMinimalSegmentsHelper(minimalSegList);
	}

	private Set<Segment> constructAllNonMinimalSegmentsHelper(ArrayList<Segment> segments) {
		
		ArrayList<Segment> newNonMinimal = new ArrayList<Segment>();
		
		// for each segment in our list...
		for (int i = 0; i < segments.size(); i++) {

			// check against all other segments...
			for (int j = 0; j < segments.size(); j++) {
				if (i == j) continue;
				Segment seg1 = segments.get(i);
				Segment seg2 = segments.get(j);

				// if they are collinear, the new non-minimal segment is formed by the two non-shared (outer) points
				if (seg1.isCollinearWith(seg2) && seg1.sharedVertex(seg2) != null) {
					Point midpoint = seg1.sharedVertex(seg2);
					newNonMinimal.add(new Segment(seg1.other(midpoint), seg2.other(midpoint)));
				}
			}
		}
		// if we found no more non-minimal segments, we are done
		if (newNonMinimal.isEmpty()) return new LinkedHashSet<Segment>(segments);
		
		// repeat process until all possible non minimal segments are found
		return constructAllNonMinimalSegmentsHelper(newNonMinimal);
	}

	/*

	/**
	 *
	 * @param _implicitPoints2
	 * @param _givenSegments2
	 * @param _implicitSegments2
	 * @return
	 */
	public Set<Segment> computeImplicitBaseSegments(Set<Point> _implicitPoints2) {
		Set<Segment> implicitSegments = new HashSet<Segment>();
		Set<Point> points = this.combinePoints(_implicitPoints2, _pointDatabase.getPoints());
		// Check each implicit point to see if it lies on any of the given segments
		// If it does then create segments using the implicit point and the points that make up that segment
		// But how do I know that the end points of a given segment are the correct end points for the minimal segments for the implicit points

		// Loop through the implicit points
		for (Point p : _implicitPoints2) {
			int loop = 1;
			// Loop through the given segments
			for (Segment s : _givenSegments) {

				// Get a sorted set of all points on the current segment
				Set<Point> pointsOn = s.collectOrderedPointsOnSegment(points);
				Object[] segmentPoints = pointsOn.toArray();

				//Check if the implicit point lies on the segment
				if(s.pointLiesOn(p)) {

					// Loop through the array of points
					for (int i = 0; i < segmentPoints.length; i++) {
						
						// Check to see if  the implicit point is on the segment
						if (segmentPoints[i].equals(p)) {

							// If it is then create two new segments containing the implicit point and the points before and after it
							Point p1 = (Point) segmentPoints[i - 1];
							Point p2 = (Point) segmentPoints[i];
							Point p3 = (Point) segmentPoints[i + 1];

							// Add these new segments to the implicitSegments Set
							implicitSegments.add(new Segment(p1, p2));
							implicitSegments.add(new Segment(p2, p3));
						}
					}
				}
				loop += 1;
			}

		}

		return implicitSegments;
	}

}
