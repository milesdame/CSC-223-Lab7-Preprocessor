package preprocessor.delegates;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;


public class ImplicitPointPreprocessorTest {
	
	protected Point a = new Point(0.0, 0.0);
	protected Point b = new Point(0.0, 1.0);
	protected Point c = new Point(1.0, 0.0);
	protected Point d = new Point(1.0, 1.0);
	protected Segment ab = new Segment(a, b);
	protected Segment ac = new Segment(a, c);
	protected Segment ad = new Segment(a, d);
	protected Segment bc = new Segment(b, c);
	protected Segment bd = new Segment(b, d);
	protected Segment cd = new Segment(c, d);

	@Test
	void test_compute_empty() {
		PointDatabase pts = new PointDatabase();
		ArrayList<Segment> segs = new ArrayList<Segment>();
		
		Set<Point> expected = new LinkedHashSet<Point>();
		Set<Point> actual = ImplicitPointPreprocessor.compute(pts, segs);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * b-----d
	 *  \   /
	 *    x  <- implied point
	 *  /   \
	 * a-----c
	 * 
	 */
	@Test
	void test_compute_bowtie() {
		PointDatabase pts = new PointDatabase(Arrays.asList(a, b, c));
		ArrayList<Segment> segs = new ArrayList<Segment>(Arrays.asList(ac, bc, bd, ad));
		
		Set<Point> expected = new LinkedHashSet<Point>(Arrays.asList(bc.segmentIntersection(ad)));
		Set<Point> actual = ImplicitPointPreprocessor.compute(pts, segs);
		
		assertEquals(expected, actual);
	}
}
