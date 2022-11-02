import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;





class PointTest {

	Point _point0 = new Point(0 ,0);
	Point _point1NoName = new Point(1 ,1);
	Point _point1 = new Point("point1", 1 , 1);
	Point _point2NoName = new Point(2 ,2);
	Point _point2 = new Point("point2", 2 , 2);
	Point _point3NoName = new Point(3 ,3);
	Point _point3 = new Point("point3", 3 , 3);
	Point _nullPoint = new Point("null" , -1 , -1);



	@Test
	void testGetX() {

		//check that the x values returned are the correct values
		assertEquals(0 , _point0.getX());

		assertEquals(1 , _point1.getX());

		assertEquals(2 , _point2.getX());

		assertEquals(3 , _point3.getX());

		//check that points without names also return the correct value
		assertEquals(1 , _point1NoName.getX());

		assertEquals(2 , _point2NoName.getX());

		assertEquals(3 , _point3NoName.getX());
	}

	@Test
	void testEquals() {
		
		//compare points that have the same x and y coordinates
		assertEquals(0 , _point1.compareTo(_point1NoName)); 
		
		assertFalse(_point0.equals(_point1NoName));
		
		assertTrue(_point1.equals(_point1NoName));
		
		assertTrue(_point2.equals(_point2NoName));
		
		//compare points that have different x and y values
		assertFalse(_point1.equals(_point2NoName));
		
		assertTrue(_point1NoName.equals(_point1));
		
		
		
		
	}

	@Test
	void testGetY() {

		//check that the y values returned are the correct values
		assertEquals(0 , _point0.getY());

		assertEquals(1 , _point1.getY());

		assertEquals(2 , _point2.getY());

		assertEquals(3 , _point3.getY());

		//check that the y values returned are the correct values for unnamed points as well
		assertEquals(1 , _point1NoName.getY());

		assertEquals(2 , _point2NoName.getY());

		assertEquals(3 , _point3NoName.getY());

	}


	@Test
	void testGetName() {

		//check that the name of the orgin point has been updated to 
		//be the orgin
		assertEquals("__UNNAMED" , _point0.getName());

		assertEquals("point1" , _point1.getName());

		assertEquals("point2" , _point2.getName());

		assertEquals("point3" , _point3.getName());

		//test with unnamed points
		//should return the unnamed constant
		assertEquals("__UNNAMED" , _point1NoName.getName());

		assertEquals("__UNNAMED" , _point2NoName.getName());

		assertEquals("__UNNAMED", _point3NoName.getName());
	}




	@Test
	void testIsUnnamed() {
		//ensure that the points are being generated
		assertTrue(_point0.isUnnamed());

		assertFalse(_point1.isUnnamed());

		assertFalse(_point2.isUnnamed());

		assertFalse(_point3.isUnnamed());

		//ensure that the unnamed points are also being generated
		assertTrue(_point1NoName.isUnnamed());

		assertTrue(_point2NoName.isUnnamed());

		assertTrue(_point3NoName.isUnnamed());
	}



	@Test
	void testHashCode() {

		//test with a 0 , 0 point
		assertEquals(0 , _point0.hashCode());

		assertEquals(_point1NoName.hashCode() , _point1.hashCode());

		assertEquals(_point2NoName.hashCode() , _point2.hashCode());

		assertEquals(_point3NoName.hashCode() , _point3.hashCode());

		//points with the 
		assertTrue(_point0.hashCode()==_point0.hashCode());
		assertTrue(_point1.hashCode()==_point1NoName.hashCode());
		assertTrue(_point2.hashCode()==_point2NoName.hashCode());
		assertTrue(_point3.hashCode()==_point3NoName.hashCode());

		//test that different values return different hash code
		assertFalse(_point3.hashCode()==_point1.hashCode());
		assertFalse(_point1.hashCode()==_point2.hashCode());
		assertFalse(_point2.hashCode()==_point3.hashCode());
		assertFalse(_point3.hashCode()==_point0.hashCode());
		
		//test that the two values are the same with different names
		assertEquals(_point0.hashCode(), _point0.hashCode());
		assertEquals(_point1.hashCode(), _point1NoName.hashCode());
		assertEquals(_point2.hashCode(), _point2NoName.hashCode());
		assertEquals(_point3.hashCode(), _point3NoName.hashCode());


	}



	@Test
	void testCompareTo() {

		//make points that have the same x value and different y value
		Point _pointA = new Point(0 ,1);
		Point _pointB = new Point(1 ,2);
		Point _pointC = new Point(2 ,3);

		//make points where the y value is less than the x value
		Point _pointD = new Point(1 ,0);
		Point _pointE = new Point(2 ,1);
		Point _pointF = new Point(3 ,2);
		Point _pointG = new Point(7 ,0);

		//check with the orgin point
		assertEquals(-1 ,_point0.compareTo(_point1) );
		assertEquals(-1 ,_point0.compareTo(_pointA) );


		//compare two points that have the same x and y values
		assertEquals(0 , _point1.compareTo(_point1NoName));

		//compare with a point that has the same y value and different x values
		assertEquals(1 , _point1.compareTo(_pointA));
		assertEquals(-1 , _point1.compareTo(_pointB));
		assertEquals(1 , _point1.compareTo(_pointD));
		assertEquals(-1 , _point1.compareTo(_pointE));


		//do the same tests with a point with no name
		//compare with a point that has the same y value and different x values
		assertEquals(1 , _point1NoName.compareTo(_pointA));
		assertEquals(1 , _point1NoName.compareTo(_pointD));
		assertEquals(-1 , _point1NoName.compareTo(_pointF));


		//compare two points that have the same x and y values
		assertEquals(0 , _point2.compareTo(_point2NoName));

		//compare two points that have different x and y values
		assertEquals(1 , _point2.compareTo(_pointB));
		assertEquals(1 , _point2.compareTo(_pointE));
		assertEquals(-1 , _point2.compareTo(_pointF));

		//do the same tests with a point with no name
		//compare with a point that has the same y value and different x values
		assertEquals(1 , _point2NoName.compareTo(_pointB));
		assertEquals(1 , _point2NoName.compareTo(_pointE));
		assertEquals(-1 , _point2NoName.compareTo(_pointF));

		//compare two points that have different x and y values
		assertEquals(1 , _point3.compareTo(_pointC));
		assertEquals(1 , _point3.compareTo(_pointF));
		assertEquals(-1 , _point3.compareTo(_pointG));

	}




}
