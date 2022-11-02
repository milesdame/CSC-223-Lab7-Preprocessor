import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

class PointDatabaseTest {


	PointDatabase _pointDatabase = new PointDatabase();


	Point _point0 = new Point("point0" ,0 ,0);
	Point _point1NoName = new Point(1 ,1);
	Point _point1 = new Point("point1", 1 , 1);
	Point _point2NoName = new Point(2 ,2);
	Point _point2 = new Point("point2", 2 , 2);
	Point _point3NoName = new Point(3 ,3);
	Point _point3 = new Point("point3", 3 , 3);
	Point _nullPoint = new Point("null" , -1 , -1);
	Point _point4NoName = new Point(1 ,2);



	@Test
	void testSize() {
		//test on an emtpy set
		assertEquals(0 , _pointDatabase.size());

		//add elements to the database
		_pointDatabase.put("point0", 0, 0);
		assertEquals(1 , _pointDatabase.size());

		_pointDatabase.put("point1", 1, 1);
		assertEquals(2 , _pointDatabase.size());

		_pointDatabase.put("point2", 2, 2);
		assertEquals(3 , _pointDatabase.size());

		//add elements that already exist and the size shouldnt change
		_pointDatabase.put("point2", 2, 2);
		assertEquals(3 , _pointDatabase.size());

		_pointDatabase.put(null, 0, 0);
		assertEquals(3 , _pointDatabase.size());

		_pointDatabase.put("point2", 2, 2);
		assertEquals(3 , _pointDatabase.size());

		_pointDatabase.put("point2", 2, 2);
		assertEquals(3 , _pointDatabase.size());

	}

	@Test
	void testPut() {

		//add elements to the database
		_pointDatabase.put("point0", 0, 0);
		//ensure the size after each addition
		assertEquals(1 , _pointDatabase.size());

		_pointDatabase.put("point1", 1, 1);
		assertEquals(2 , _pointDatabase.size());

		_pointDatabase.put("point2", 2, 2);
		assertEquals(3 , _pointDatabase.size());
		
		
		//put an object that is already in database
		_pointDatabase.put("point0", 0, 0);
		assertEquals(3 , _pointDatabase.size());
		
		_pointDatabase.put("point1", 1, 1);
		assertEquals(3 , _pointDatabase.size());
		
		
		//add points that have a null name
		_pointDatabase.put(null, 4, 4);
		assertEquals(4 , _pointDatabase.size());
		
		//with this one being points that already exist in database
		_pointDatabase.put(null, 2, 2);
		assertEquals(4 , _pointDatabase.size());
		
		_pointDatabase.put(null, 3, 2);
		assertEquals(5 , _pointDatabase.size());
		
		
		//check if the name has been updated for the point
		assertEquals("point2" , _pointDatabase.getName(2, 2));
		
		

	}

	@Test
	void testGetName() {

		//add points to the database
		_pointDatabase.put("point0", 0, 0);
		
		_pointDatabase.put("point1", 1, 1);
		
		_pointDatabase.put("point2", 2, 2);
		
		_pointDatabase.put("point3", 3, 3);
		
		_pointDatabase.put("point4", 0, 1);
		
		_pointDatabase.put("point5", 1, 2);


		//ensure that all points have been added
		assertEquals(6 , _pointDatabase.size());


		//test getting the name of some points
		assertEquals("point1" ,_pointDatabase.getName(1, 1));
		
		assertEquals("point2" ,_pointDatabase.getName(2, 2));
		
		assertEquals("point3" ,_pointDatabase.getName(3, 3));
		
		assertEquals("point4" ,_pointDatabase.getName(0, 1));
		
		assertEquals("point5" ,_pointDatabase.getName(1, 2));

	
	}

	@Test
	void testGetNamePoint() {

		//add points to the database
		_pointDatabase.put("point0", 0, 0);
		_pointDatabase.put("point1", 1, 1);
		_pointDatabase.put("point2", 2, 2);
		_pointDatabase.put("point3", 3, 3);
		_pointDatabase.put("point4", 0, 1);
		_pointDatabase.put("point5", 1, 2);


		//ensure that all points have been added
		assertEquals(6 , _pointDatabase.size());

		//test getting the names with named points
		assertEquals("point1" , _pointDatabase.getName(_point1));
		
		assertEquals("point2" , _pointDatabase.getName(_point2));
		
		assertEquals("point3" , _pointDatabase.getName(_point3));
		
		assertEquals("point0" , _pointDatabase.getName(_point0));

		
		//test getting names with unnamed points
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point1NoName));
		
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point2NoName));
		
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point3NoName));
		
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point4NoName));

		//test with a point that is not in the database
		assertEquals("null", _pointDatabase.getName(_nullPoint));


	}


	@Test
	void testGetPoint() {

		//add points to the database
		_pointDatabase.put("point0", 0, 0);
		_pointDatabase.put("point1", 1, 1);
		_pointDatabase.put("point2", 2, 2);
		_pointDatabase.put("point3", 3, 3);
		_pointDatabase.put("point4", 0, 1);
		_pointDatabase.put("point5", 1, 2);


		//ensure that all points have been added
		assertEquals(6 , _pointDatabase.size());


		//test getting the names with named points
		//assertTrue( _pointDatabase.getPoint("point1").equals(_point1));
		
		assertEquals("point2" , _pointDatabase.getName(_point2));
		
		assertEquals("point3" , _pointDatabase.getName(_point3));
		
		assertEquals("point0" , _pointDatabase.getName(_point0));


		//test getting names with unnamed points
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point1NoName));
		
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point2NoName));
		
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point3NoName));
		
		assertEquals("__UNNAMED" , _pointDatabase.getName(_point4NoName));

	}


	
	@Test
	void testGetPointXY() {
		//add points to the database
		_pointDatabase.put("point0", 0, 0);
		_pointDatabase.put("point1", 1, 1);
		_pointDatabase.put("point2", 2, 2);
		_pointDatabase.put("point3", 3, 3);
		_pointDatabase.put("point4", 0, 1);
		_pointDatabase.put("point5", 1, 2);


		//ensure that all points have been added
		assertEquals(6 , _pointDatabase.size());


		//test with a point that is not in the database
		assertEquals(null , _pointDatabase.getPoint(5,6));

		//test getting the names with named points
		assertEquals(_point0 , _pointDatabase.getPoint(0,0));
		
		assertEquals(_point1 , _pointDatabase.getPoint(1,1));
		
		assertEquals(_point2 , _pointDatabase.getPoint(2,2));
		
		assertEquals(_point3 , _pointDatabase.getPoint(3,3));


		//test with points that are not in the database
		//should return null
		assertEquals(null, _pointDatabase.getPoint(5,6));
		
		assertEquals(null, _pointDatabase.getPoint(2,4));


	}



}
