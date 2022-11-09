package geometry_objects;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;

import static org.junit.Assert.assertEquals;

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
		
		boolean expected = false;
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
}
