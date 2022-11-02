import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointNamingFactory;

public class PointNamingFactoryTest {

	
	
@Test
public void updateNameTest() {
	PointNamingFactory factory = new PointNamingFactory();
	
	// Check the initial name is "*_A" and number of letters is 1
	String name = factory.getCurrName();
	assertEquals(name, "*_A");
	assertEquals(factory.getNumLetters(), 1);
	
	// Check remaining letters for single letter names
	for (int i = 2; i <= 26; i++) {
		String name3 = factory.getCurrName();
		
		if (i == 26) {
			assertEquals(name3, "*_Z");
		}
	}
	
	// Check that the number of letters has updated to 2
	// Check that the two letter names work properly
	assertEquals(factory.getNumLetters(), 2);
	String name2 = factory.getCurrName();
	assertEquals(name2, "*_AA");
	
	for (int i = 28; i <= 52; i++) {
		String name3 = factory.getCurrName();
		
		if (i == 52) {
			assertEquals(name3, "*_ZZ");
		}
	}
	
	// Check that the number of letters has updated to 3
	// Check that the three letter names work properly
	assertEquals(factory.getNumLetters(), 3);
	String name4 = factory.getCurrName();
	assertEquals(name4, "*_AAA");
	
	for (int i = 28; i <= 52; i++) {
		String name3 = factory.getCurrName();
		
		if (i == 52) {
			assertEquals(name3, "*_ZZZ");
		}
	}
	
	
}

@Test
public void PointNamingFactoryConstructorTest() {
	Point _point0 = new Point(0 ,0);
	Point _point1NoName = new Point(1 ,1);
	Point _point1 = new Point("point1", 1 , 1);
	Point _point2NoName = new Point(2 ,2);
	Point _point2 = new Point("point2", 2 , 2);
	Point _point3NoName = new Point(3 ,3);
	Point _point3 = new Point("point3", 3 , 3);
	Point _nullPoint = new Point(null , -1 , -1);
	
	ArrayList<Point> arr = new ArrayList<Point>();
	
	arr.add(_point0);
	arr.add(_point2);
	arr.add(_point2NoName);
	arr.add(_point1);
	arr.add(_point1NoName);
	arr.add(_point3);
	arr.add(_point3NoName);
	arr.add(_nullPoint);
	
	// Add Points to check constructor with list argument
	PointNamingFactory factory = new PointNamingFactory(arr);
	
	// Check that size is equal to the correct size
	assertEquals(5, factory.size());
	
	// Check that all of the points have been added to the factory
	assertTrue(factory.contains(_point3));
	assertTrue(factory.contains(_point2));
	assertTrue(factory.contains(_point1));
	assertTrue(factory.contains(_point0));
	assertTrue(factory.contains(_point1NoName));
	assertTrue(factory.contains(_point2NoName));
	assertTrue(factory.contains(_point3NoName));
	assertTrue(factory.contains(_nullPoint));
}

@Test
public void putTest() {
	PointNamingFactory factory = new PointNamingFactory();
	
	factory.put(1.0, 1.0);
	factory.put(0.0, 0.0);
	factory.put(-1.0, 12.0);
	factory.put(2.0, 11.0);
	
	Set<Point> pointSet =  factory.getAllPoints();

	assertTrue(factory.contains(1.0, 1.0));
	assertTrue(factory.contains(0.0, 0.0));
	assertTrue(factory.contains(-1.0, 12.0));
	assertTrue(factory.contains(2.0, 11.0));

}

@Test
public void getAllPointsTest() {
	Point _point0 = new Point(0 ,0);
	Point _point1NoName = new Point(1 ,1);
	Point _point1 = new Point("point1", 1 , 1);
	Point _point2NoName = new Point(2 ,2);
	Point _point2 = new Point("point2", 2 , 2);
	Point _point3NoName = new Point(3 ,3);
	Point _point3 = new Point("point3", 3 , 3);
	Point _nullPoint = new Point(null , -1 , -1);
	
	ArrayList<Point> arr = new ArrayList<Point>();
	
	arr.add(_point0);
	arr.add(_point2);
	arr.add(_point2NoName);
	arr.add(_point1);
	arr.add(_point1NoName);
	arr.add(_point3);
	arr.add(_point3NoName);
	arr.add(_nullPoint);
	
	PointNamingFactory factory = new PointNamingFactory(arr);
	
	Set<Point> pointSet =  factory.getAllPoints();
	
	assertTrue(pointSet.contains(_point0));
	assertTrue(pointSet.contains(_point1));
	assertTrue(pointSet.contains(_point2));
	assertTrue(pointSet.contains(_point1NoName));
	assertTrue(pointSet.contains(_point2NoName));
	assertTrue(pointSet.contains(_point3NoName));
	assertTrue(pointSet.contains(_nullPoint));
	
}

@Test
public void toStringTest() {

	Point _point1 = new Point("point1", 1 , 1);
	Point _point2 = new Point("point2", 2 , 2);
	Point _point3NoName = new Point(3 ,3);
	
	ArrayList<Point> arr = new ArrayList<Point>();
	
	arr.add(_point1);
	arr.add(_point2);
	arr.add(_point3NoName);

	PointNamingFactory factory = new PointNamingFactory(arr);
	
	String expected = "Name: point1, X : 1.0, Y : 1.0\n"
			+ "Name: point2, X : 2.0, Y : 2.0\n"
			+ "Name: *_A, X : 3.0, Y : 3.0\n";
	
	assertEquals(factory.toString(), expected);
	
	
}

	
}
