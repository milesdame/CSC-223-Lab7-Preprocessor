package geometry_objects.angle;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.points.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AngleLinkedEquivalenceClassTest {
	
	/**
	 * belongs
	 * 
	 * canonical is null
	 * angle is null
	 * equals canonical
	 * structurally incomparable
	 * structurally comparable
	 */
	@Test
	void test_belongs_canonical_null() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		
		boolean expected = false;
		boolean actual = eq.belongs(a1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_belongs_angle_null() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		eq.add(a1);
		
		boolean expected = false;
		boolean actual = eq.belongs(null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_belongs_equals_canonical() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		Angle a2 = new Angle(s1,s2);
		eq.demoteAndSetCanonical(a1);
		
		boolean expected = true;
		boolean actual = eq.belongs(a2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_belongs_structurally_incomparable() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		Segment s3 = new Segment(new Point(0,0), new Point(1,0));
		Segment s4 = new Segment(new Point(0,0), new Point(0,1));
		
		boolean expected = false;
		boolean actual = eq.belongs(a1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_belongs_structurally_comparable() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		Segment s3 = new Segment(new Point(0,0), new Point(1,0));
		Segment s4 = new Segment(new Point(0,0), new Point(2,2));
		Angle a2 = new Angle(s3,s4);
		eq.demoteAndSetCanonical(a1);
		
		boolean expected = true;
		boolean actual = eq.belongs(a2);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * add
	 * 
	 * canonical is null
	 * structurally smaller
	 * not structurally smaller
	 * does not belong
	 * belongs
	 */
	@Test
	void test_add_canonical_null() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(1,0));
		Segment s2 = new Segment(new Point(0,0), new Point(1,1));
		Angle a1 = new Angle(s1,s2);
		
		boolean expected = true;
		boolean actual = eq.add(a1);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_add_structurally_smaller() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(2,0));
		Segment s2 = new Segment(new Point(0,0), new Point(0,2));
		Angle a1 = new Angle(s1,s2);
		Segment s3 = new Segment(new Point(0,0), new Point(1,0));
		Segment s4 = new Segment(new Point(0,0), new Point(0,1));
		Angle a2 = new Angle(s3,s4); // structurally smaller angle
		
		eq.demoteAndSetCanonical(a1); // first set canonical
		eq.add(a2);
		
		assertEquals(eq.size(), 2);
		assertEquals(eq.canonical(), a1); 
	}
	
	@Test
	void test_add_not_structurally_smaller() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(2,0));
		Segment s2 = new Segment(new Point(0,0), new Point(0,2));
		Angle a1 = new Angle(s1,s2);
		Segment s3 = new Segment(new Point(0,0), new Point(3,0));
		Segment s4 = new Segment(new Point(0,0), new Point(0,3));
		Angle a2 = new Angle(s3,s4); // structurally smaller angle
		
		eq.demoteAndSetCanonical(a1); // first set canonical
		eq.add(a2);
		
		assertEquals(eq.size(), 2);
	}
	
	@Test
	void test_add_does_not_belong() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(2,0));
		Segment s2 = new Segment(new Point(0,0), new Point(0,2));
		Angle a1 = new Angle(s1,s2);
		Segment s3 = new Segment(new Point(0,0), new Point(2,0));
		Segment s4 = new Segment(new Point(0,0), new Point(1,2));
		Angle a2 = new Angle(s3,s4); 
		
		eq.demoteAndSetCanonical(a1); // first set canonical
		
		boolean expected = false;
		boolean actual = eq.add(a2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_add_belongs() throws FactException {
		AngleLinkedEquivalenceClass eq = new AngleLinkedEquivalenceClass();
		Segment s1 = new Segment(new Point(0,0), new Point(2,0));
		Segment s2 = new Segment(new Point(0,0), new Point(0,2));
		Angle a1 = new Angle(s1,s2);
		Segment s3 = new Segment(new Point(0,0), new Point(2,0));
		Segment s4 = new Segment(new Point(0,0), new Point(0,3));
		Angle a2 = new Angle(s3,s4); 
		
		eq.demoteAndSetCanonical(a1); // first set canonical
		
		boolean expected = true;
		boolean actual = eq.add(a2);
		
		assertEquals(expected, actual);
	}
}
