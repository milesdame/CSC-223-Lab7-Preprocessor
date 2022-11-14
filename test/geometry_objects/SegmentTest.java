package geometry_objects;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SegmentTest {
	
	// hasSubsegment
	
	@Test
	void test_hasSubsegment_notOverlapping() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(2,0), new Point(3,0));
		
		boolean expected = false;
		boolean actual = seg1.HasSubSegment(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_hasSubsegment_connectedBy1Endpoint() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(1,0), new Point(2,0));
		
		boolean expected = false;
		boolean actual = seg1.HasSubSegment(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_hasSubsegment_sameSegment() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(0,0), new Point(1,0));
		
		boolean expected = true;
		boolean actual = seg1.HasSubSegment(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_hasSubsegment_only1PointOn() {
		Segment seg1 = new Segment(new Point(0,0), new Point(2,0));
		Segment seg2 = new Segment(new Point(1,0), new Point(3,0));
		
		boolean expected = false;
		boolean actual = seg1.HasSubSegment(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_hasSubsegment_bothPointsOn() {
		Segment seg1 = new Segment(new Point(0,0), new Point(3,0));
		Segment seg2 = new Segment(new Point(1,0), new Point(2,0));
		
		boolean expected = true;
		boolean actual = seg1.HasSubSegment(seg2);
		
		assertEquals(expected, actual);
	}
	
	// coincideWithoutOverlap
	
	@Test
	void test_coincideWithoutOverlap_differentSlopesUnconnected() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(2,0), new Point(3,1));
		
		boolean expected = false;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_connectatvertex_overlap() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(0,0), new Point(2,0));
		
		boolean expected = false;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		System.out.println(seg1.pointLiesBetweenEndpoints(seg2._point1));
		System.out.println(seg1.pointLiesBetweenEndpoints(seg2._point2));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_differentSlopesConnected() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(0,0), new Point(1,1));
		
		boolean expected = false;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_sameSlopeNonCollinear() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(0,1), new Point(1,1));
		
		boolean expected = false;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_sameSlope1EndpointConnected() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(1,0), new Point(2,0));
		
		boolean expected = true;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_sameSegments() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(0,0), new Point(1,0));
		
		boolean expected = false;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_good_case_horizontal() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,0));
		Segment seg2 = new Segment(new Point(2,0), new Point(3,0));
		
		boolean expected = true;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_good_case_vertical() {
		Segment seg1 = new Segment(new Point(0,0), new Point(0,1));
		Segment seg2 = new Segment(new Point(0,2), new Point(0,3));
		
		boolean expected = true;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_coincideWithoutOverlap_good_case_diagonal() {
		Segment seg1 = new Segment(new Point(0,0), new Point(1,1));
		Segment seg2 = new Segment(new Point(2,2), new Point(3,3));
		
		boolean expected = true;
		boolean actual = seg1.coincideWithoutOverlap(seg2);
		
		assertEquals(expected, actual);
	}
	
	// collectOrderedPointsOnSegment
	
	@Test
	void test_collectOrderedPointsOnSegment_empty() {
		Segment seg = new Segment(new Point(0,0), new Point(1,1));
		Set<Point> pts = new HashSet<Point>();
		
		SortedSet<Point> expected = new TreeSet<Point>();
		SortedSet<Point> actual = seg.collectOrderedPointsOnSegment(pts);
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void test_collectOrderedPointsOnSegment_noPointsOn() {
		Segment seg = new Segment(new Point(0,0), new Point(2,0));
		Set<Point> pts = new HashSet<Point>(Arrays.asList(new Point(1,1), new Point(0, 0.1), new Point(2.01, 0), new Point(-0.01, -0.1)));
		
		SortedSet<Point> expected = new TreeSet<Point>();
		SortedSet<Point> actual = seg.collectOrderedPointsOnSegment(pts);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_collectOrderedPointsOnSegment_OnePointOnEndpoint() {
		Segment seg = new Segment(new Point(0,0), new Point(1,1));
		Set<Point> pts = new HashSet<Point>(Arrays.asList(new Point(0,0)));
		
		SortedSet<Point> expected = new TreeSet<Point>(Arrays.asList(new Point(0,0)));
		SortedSet<Point> actual = seg.collectOrderedPointsOnSegment(pts);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_collectOrderedPointsOnSegment_OnePointInMiddle() {
		Segment seg = new Segment(new Point(0,0), new Point(2,0));
		Set<Point> pts = new HashSet<Point>(Arrays.asList(new Point(1,0)));
		
		SortedSet<Point> expected = new TreeSet<Point>(Arrays.asList(new Point(1,0)));
		SortedSet<Point> actual = seg.collectOrderedPointsOnSegment(pts);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_collectOrderedPointsOnSegment_ManyPointsOn() {
		Segment seg = new Segment(new Point(0,0), new Point(2,0));
		Set<Point> pts = new HashSet<Point>(Arrays.asList(new Point(0.1, 0), new Point(1.5, 0), new Point(2,0)));
		
		SortedSet<Point> expected = new TreeSet<Point>(Arrays.asList(new Point(0.1, 0), new Point(1.5, 0), new Point(2,0)));
		SortedSet<Point> actual = seg.collectOrderedPointsOnSegment(pts);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_collectOrderedPointsOnSegment_SomeOnSomeNot() {
		Segment seg = new Segment(new Point(0,0), new Point(2,0));
		Set<Point> pts = new HashSet<Point>(Arrays.asList(new Point(0.1, 0), new Point(0.1, 0.1), new Point(1.5, 0), new Point(2.001, 0), new Point(2,0)));
		
		SortedSet<Point> expected = new TreeSet<Point>(Arrays.asList(new Point(0.1, 0), new Point(1.5, 0), new Point(2,0)));
		SortedSet<Point> actual = seg.collectOrderedPointsOnSegment(pts);
		
		assertEquals(expected, actual);
	}
	
}
