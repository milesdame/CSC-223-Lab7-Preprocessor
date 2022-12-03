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
		
		for (i = 0; i < segmentArr.length; i++) {
			Segment seg1 = (Segment) segmentArr[i];
			
			for (int j = (i + 1); j < segmentArr.length; j++) {
				Segment seg2 = (Segment) segmentArr[j];
				
				if (seg1.hasSharedVertex(seg2)) {
					sharedPoint = seg1.sharedVertex(seg2);
					point1 = seg1.other(sharedPoint);
					point2 = seg2.other(sharedPoint);
					Segment matchMaker = new Segment(point1, point2);	
					
					for (int k = (j + 1); j < segmentArr.length; k++) {
						if (segmentArr[k].equals(matchMaker)) {
							Segment seg3 = (Segment) segmentArr[k];
							segmentList = createSegList(seg1, seg2, seg3);
							Triangle tri = new Triangle(segmentList);
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
