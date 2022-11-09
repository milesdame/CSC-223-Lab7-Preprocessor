package preprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import geometry_objects.delegates.SegmentDelegate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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


	public Set<Segment> identifyAllMinimalSegments(Set<Point> _implicitPoints2, Set<Segment> _givenSegments2,
			Set<Segment> _implicitSegments2) {
		
		Set<Segment> implicitSegments = _implicitSegments2;
		
		for (Point p : _implicitPoints2) {
			for (Segment s : _givenSegments2) {
				if (SegmentDelegate.pointLiesOnSegment(s, p)) {
					implicitSegments.add(new Segment(s.getPoint1(), p));
					implicitSegments.add(new Segment(s.getPoint2(), p));
				}
			}
		}
		
		return implicitSegments;
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
		
		return constructAllNonMinimalSegmentsHelper(minimalSegList, new ArrayList<Segment>());
	}
	
	private Set<Segment> constructAllNonMinimalSegmentsHelper(ArrayList<Segment> segments, ArrayList<Segment> nonminimal) {

		// for each segment in our list...
		for (int i = 0; i < segments.size(); i++) {
			
			// check against all other segments...
			for (int j = 0; j < segments.size(); j++) {
				if (i == j) continue;
				Segment seg1 = segments.get(i);
				Segment seg2 = segments.get(j);
				
				// if they are collinear, the new non-minimal segment is formed by the two non-shared (outer) points
				if (seg1.isCollinearWith(seg2)) {
					Point midpoint = seg1.sharedVertex(seg2);
					nonminimal.add(new Segment(seg1.other(midpoint), seg2.other(midpoint)));
				}
			}
		}
		// if we found no more non-minimal segments, we are done
		if (nonminimal.isEmpty()) return new LinkedHashSet<Segment>(segments);
		return constructAllNonMinimalSegmentsHelper(nonminimal, nonminimal);
	}
	
	public Set<Segment> computeImplicitBaseSegments(Set<Point> _implicitPoints2) {
		
		return null;
	}
}
