package geometry_objects.angle;

import geometry_objects.points.Point;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;


public class AngleTest {

	/**
	 * null
	 * not an angle
	 * ray 1 endpoint not equal
	 * ray 2 endpoinnt not equal
	 * vertex not equal
	 * equal
	 */
	@Test
	void test_equals_null() throws FactException {
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		Angle a2 = null;
		
		boolean expected = false;
		boolean actual = a1.equals(a2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_equals_not_angle() throws FactException {
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		String a2 = "not an angle";
		
		boolean expected = false;
		boolean actual = a1.equals(a2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_equals_ray1_notequal() throws FactException {
		Segment ray1_1 = new Segment(new Point(0,0), new Point(2,0));
		Segment ray1_2 = new Segment(new Point(0,0), new Point(1,1));
		Segment ray2_1 = new Segment(new Point(0,0), new Point(1,0));
		Segment ray2_2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(ray1_1, ray1_2);
		Angle a2 = new Angle(ray2_1, ray2_2);
		
		boolean expected = false;
		boolean actual = a1.equals(a2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_equals_ray2_notequal() throws FactException {
		Segment ray1_1 = new Segment(new Point(0,0), new Point(1,0));
		Segment ray1_2 = new Segment(new Point(0,0), new Point(1,1));
		Segment ray2_1 = new Segment(new Point(0,0), new Point(1,0));
		Segment ray2_2 = new Segment(new Point(0,0), new Point(2,2));
		Angle a1 = new Angle(ray1_1, ray1_2);
		Angle a2 = new Angle(ray2_1, ray2_2);
		
		boolean expected = false;
		boolean actual = a1.equals(a2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_equals_vertex_notequal() throws FactException {
		Segment ray1_1 = new Segment(new Point(0,0), new Point(1,0));
		Segment ray1_2 = new Segment(new Point(0,0), new Point(1,1));
		Segment ray2_1 = new Segment(new Point(-1,0), new Point(1,0));
		Segment ray2_2 = new Segment(new Point(-1,0), new Point(1,1));
		Angle a1 = new Angle(ray1_1, ray1_2);
		Angle a2 = new Angle(ray2_1, ray2_2);
		
		boolean expected = false;
		boolean actual = a1.equals(a2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_equals_true() throws FactException {
		Segment ray1_1 = new Segment(new Point(0,0), new Point(1,0));
		Segment ray1_2 = new Segment(new Point(0,0), new Point(1,1));
		Segment ray2_1 = new Segment(new Point(0,0), new Point(1,0));
		Segment ray2_2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(ray1_1, ray1_2);
		Angle a2 = new Angle(ray2_1, ray2_2);
		
		boolean expected = true;
		boolean actual = a1.equals(a2);
		
		assertEquals(expected, actual);
	}
}
