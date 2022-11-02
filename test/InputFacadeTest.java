import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import input.InputFacade;
import input.components.FigureNode;
import input.visitor.UnparseVisitor;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

class InputFacadeTest {
	
//Extract Figure Tests
	
	@Test
	void pointTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/point.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"A single point\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void SingleLineSegmentTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/lineseg.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"One line segment consisting of two points on y-axis.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(0.0, 1.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void TriangleSegmentTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/single_triangle.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"Right Triangle in the first quadrant.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(1.0, 1.0)\n"
				+ "        Point(C)(1.0, 0.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  C  \n"
				+ "        B : C  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void SquareSegmentTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/square.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"Square.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(0.0, 6.0)\n"
				+ "        Point(C)(6.0, 0.0)\n"
				+ "        Point(D)(6.0, 6.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  C  \n"
				+ "        B : D  \n"
				+ "        C : D  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void Collinear_line_segmentTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/collinear_line_segments.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"A seqeunce of collinear line segments mimicking one line with 6 points.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(4.0, 0.0)\n"
				+ "        Point(C)(9.0, 0.0)\n"
				+ "        Point(D)(11.0, 0.0)\n"
				+ "        Point(E)(16.0, 0.0)\n"
				+ "        Point(F)(26.0, 0.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  \n"
				+ "        B : C  \n"
				+ "        C : D  \n"
				+ "        D : E  \n"
				+ "        E : F  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void crossing_symmetric_triangleTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/crossing_symmetric_triangle.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"Crossing symmetric triangle construction.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(3.0, 6.0)\n"
				+ "        Point(B)(2.0, 4.0)\n"
				+ "        Point(C)(4.0, 4.0)\n"
				+ "        Point(D)(0.0, 0.0)\n"
				+ "        Point(E)(6.0, 0.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  C  \n"
				+ "        B : C  D  E  \n"
				+ "        C : D  E  \n"
				+ "        D : E  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void fully_connected_irregular_polygonTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/fully_connected_irregular_polygon.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"Irregular pentagon in which each vertex is connected to each other.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(4.0, 0.0)\n"
				+ "        Point(C)(6.0, 3.0)\n"
				+ "        Point(D)(3.0, 7.0)\n"
				+ "        Point(E)(-2.0, 4.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  C  D  E  \n"
				+ "        B : C  D  E  \n"
				+ "        C : D  E  \n"
				+ "        D : E  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void snakeTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/snake.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"Three triangles glued by vertex in a row\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(0.0, 1.0)\n"
				+ "        Point(C)(1.0, 0.0)\n"
				+ "        Point(D)(2.0, 0.0)\n"
				+ "        Point(E)(2.0, 1.0)\n"
				+ "        Point(F)(3.0, 1.0)\n"
				+ "        Point(G)(3.0, 0.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  C  \n"
				+ "        B : C  \n"
				+ "        C : D  E  \n"
				+ "        D : E  \n"
				+ "        E : F  G  \n"
				+ "        F : G  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}
	
	@Test
	void Tri_QuadTest() {
		FigureNode figNode = InputFacade.extractFigure("jsonfiles/Tri_Quad.json");
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		
		unparser.visitFigureNode(figNode, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"Tri Quad Shape.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(4.0, 0.0)\n"
				+ "        Point(B)(8.0, 0.0)\n"
				+ "        Point(C)(4.0, 5.0)\n"
				+ "        Point(D)(8.0, 5.0)\n"
				+ "        Point(E)(0.0, 10.0)\n"
				+ "        Point(F)(12.0, 10.0)\n"
				+ "        Point(G)(4.0, 12.0)\n"
				+ "        Point(H)(8.0, 12.0)\n"
				+ "        Point(I)(6.0, 10.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  C  \n"
				+ "        B : D  \n"
				+ "        C : D  E  I  \n"
				+ "        D : F  I  \n"
				+ "        E : G  \n"
				+ "        F : H  \n"
				+ "        G : I  \n"
				+ "        H : I  \n"
				+ "    }\n"
				+ "}\n";
		
		assertEquals(expected, sb.toString());
	}

//GeometryRep Tests
	
	public void addPoints(LinkedHashSet<Segment> set, Point point1, Point point2){
		Segment seg1 = new Segment(point1,point2);
	
		set.add(seg1);
	}
	
	@Test
	void PointGeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/point.json");		
		
		//Point
		PointDatabase PDB = newMap.getKey(); 
		
		Set<Point> SetPoint = PDB.getPoints();
		
		
		PointDatabase pointTest = new PointDatabase(); 
		
		pointTest.put("A", 0, 0);

		Set<Point> TestSet = pointTest.getPoints(); 
		
		
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
				
		assertEquals(SetPoint.size(), TestSet.size());
		
	}
	
	@Test
	void LineSegGeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/lineseg.json");
		
		
		//Point
		PointDatabase PDB = newMap.getKey(); 
		Set<Point> SetPoint = PDB.getPoints();		
		
		PointDatabase pointTest = new PointDatabase(); 
		pointTest.put("A", 0, 0);
		pointTest.put("B", 0, 1);
		Set<Point> TestSet = pointTest.getPoints(); 
				
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
						
		assertEquals(SetPoint.size(), TestSet.size());
		
		
		//Segment
		Set<Segment> SGT = newMap.getValue();
		LinkedHashSet<Segment> segTest = new LinkedHashSet<Segment>();
						
		Point point1 = new Point(0, 0);
		Point point2 = new Point(0, 1);
				
		this.addPoints(segTest, point1, point2);
						
		for (Segment seg : SGT) {
			assertTrue(segTest.contains(seg));
		}
			
		assertEquals(SGT.size(), segTest.size());
		
	}
	
	@Test
	void TriangleGeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/single_triangle.json");
		
		//Point
		PointDatabase PDB = newMap.getKey(); 
		Set<Point> SetPoint = PDB.getPoints();
		
		PointDatabase pointTest = new PointDatabase(); 
		pointTest.put("A", 0, 0);
		pointTest.put("B", 1, 1);
		pointTest.put("C", 1, 0);
		Set<Point> TestSet = pointTest.getPoints(); 
		
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
				
		assertEquals(SetPoint.size(), TestSet.size());
		
		//Segment
		Set<Segment> SGT = newMap.getValue();
		LinkedHashSet<Segment> segTest = new LinkedHashSet<Segment>();
				
		Point point1 = new Point(0, 0);
		Point point2 = new Point(1, 1);
		Point point3 = new Point(1, 0);

		this.addPoints(segTest, point1, point2);
		this.addPoints(segTest, point2, point3);
		this.addPoints(segTest, point1, point3);
				
		for (Segment seg : SGT) {
			assertTrue(segTest.contains(seg));
		}
			
		assertEquals(SGT.size(), segTest.size());
		
	}
	
	@Test
	void SquareGeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/square.json");
		
		//Point
		PointDatabase PDB = newMap.getKey(); 
		Set<Point> SetPoint = PDB.getPoints();
		
		PointDatabase pointTest = new PointDatabase(); 
		pointTest.put("A", 0, 0);
		pointTest.put("B", 0, 6);
		pointTest.put("C", 6, 0);
		pointTest.put("D", 6, 6);
		Set<Point> TestSet = pointTest.getPoints(); 
		
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
				
		assertEquals(SetPoint.size(), TestSet.size());
		
		//Segment
		Set<Segment> SGT = newMap.getValue();
		LinkedHashSet<Segment> segTest = new LinkedHashSet<Segment>();
				
		Point point1 = new Point(0, 0);
		Point point2 = new Point(0, 6);
		Point point3 = new Point(6, 0);
		Point point4 = new Point(6, 6);

		this.addPoints(segTest, point1, point2);
		this.addPoints(segTest, point2, point4);
		this.addPoints(segTest, point1, point3);
		this.addPoints(segTest, point3, point4);
				
		for (Segment seg : SGT) {
			assertTrue(segTest.contains(seg));
		}
			
		assertEquals(SGT.size(), segTest.size());
		
	}
	
	@Test
	void Collinear_Line_Seg_GeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/collinear_line_segments.json");
		
		//Point
		PointDatabase PDB = newMap.getKey(); 
		Set<Point> SetPoint = PDB.getPoints();
		
		PointDatabase pointTest = new PointDatabase(); 
		pointTest.put("A", 0, 0);
		pointTest.put("B", 4, 0);
		pointTest.put("C", 9, 0);
		pointTest.put("D", 11, 0);
		pointTest.put("E", 16, 0);
		pointTest.put("F", 26, 0);
		Set<Point> TestSet = pointTest.getPoints(); 
		
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
				
		assertEquals(SetPoint.size(), TestSet.size());
		
		//Segment
		Set<Segment> SGT = newMap.getValue();
		LinkedHashSet<Segment> segTest = new LinkedHashSet<Segment>();
				
		Point point1 = new Point(0, 0);
		Point point2 = new Point(4, 0);
		Point point3 = new Point(9, 0);
		Point point4 = new Point(11, 0);
		Point point5 = new Point(16, 0);
		Point point6 = new Point(26, 0);

		this.addPoints(segTest, point1, point2);
		this.addPoints(segTest, point2, point3);
		this.addPoints(segTest, point3, point4);
		this.addPoints(segTest, point4, point5);
		this.addPoints(segTest, point5, point6);
				
		for (Segment seg : SGT) {
			assertTrue(segTest.contains(seg));
		}
			
		assertEquals(SGT.size(), segTest.size());
		
	}
	

	@Test
	void Crossing_Symmetric_GeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/crossing_symmetric_triangle.json");
	
		//Point
		PointDatabase PDB = newMap.getKey(); 
		Set<Point> SetPoint = PDB.getPoints();
	
		PointDatabase pointTest = new PointDatabase(); 
		pointTest.put("A", 3, 6);
		pointTest.put("B", 2, 4);
		pointTest.put("C", 4, 4);
		pointTest.put("D", 0, 0);
		pointTest.put("E", 6, 0);
		Set<Point> TestSet = pointTest.getPoints(); 
	
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
			
		assertEquals(SetPoint.size(), TestSet.size());
	
		//Segment
		Set<Segment> SGT = newMap.getValue();
		LinkedHashSet<Segment> segTest = new LinkedHashSet<Segment>();
			
		Point pointA = new Point(3, 6);
		Point pointB = new Point(2, 4);
		Point pointC = new Point(4, 4);
		Point pointD = new Point(0, 0);
		Point pointE = new Point(6, 0);

		this.addPoints(segTest, pointA, pointB);
		this.addPoints(segTest, pointA, pointC);
		this.addPoints(segTest, pointB, pointC);
		this.addPoints(segTest, pointB, pointD);
		this.addPoints(segTest, pointB, pointE);
		this.addPoints(segTest, pointC, pointD);
		this.addPoints(segTest, pointC, pointE);
		this.addPoints(segTest, pointD, pointE);
			
		for (Segment seg : SGT) {
			assertTrue(segTest.contains(seg));
		}
		
		assertEquals(SGT.size(), segTest.size());
	
	}
	
	@Test
	void fully_connected_ireg_GeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/fully_connected_irregular_polygon.json");
		
		//Point
		PointDatabase PDB = newMap.getKey(); 
		Set<Point> SetPoint = PDB.getPoints();
	
		PointDatabase pointTest = new PointDatabase(); 
		pointTest.put("A", 0, 0);
		pointTest.put("B", 4, 0);
		pointTest.put("C", 6, 3);
		pointTest.put("D", 3, 7);
		pointTest.put("E", -2, 4);
		Set<Point> TestSet = pointTest.getPoints(); 
	
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
			
		assertEquals(SetPoint.size(), TestSet.size());
	
		//Segment
		Set<Segment> SGT = newMap.getValue();
		LinkedHashSet<Segment> segTest = new LinkedHashSet<Segment>();
			
		Point pointA = new Point(0, 0);
		Point pointB = new Point(4, 0);
		Point pointC = new Point(6, 3);
		Point pointD = new Point(3, 7);
		Point pointE = new Point(-2, 4);

		this.addPoints(segTest, pointA, pointB);
		this.addPoints(segTest, pointA, pointC);
		this.addPoints(segTest, pointA, pointD);
		this.addPoints(segTest, pointA, pointE);
		this.addPoints(segTest, pointB, pointC);
		this.addPoints(segTest, pointB, pointD);
		this.addPoints(segTest, pointB, pointE);
		this.addPoints(segTest, pointC, pointD);
		this.addPoints(segTest, pointC, pointE);
		this.addPoints(segTest, pointD, pointE);
			
		for (Segment seg : SGT) {
			assertTrue(segTest.contains(seg));
		}
		
		assertEquals(SGT.size(), segTest.size());
	
	}

	@Test
	void Tri_Quad_GeometryRepresentationTest() {
		Map.Entry<PointDatabase, Set<Segment>> newMap = InputFacade.toGeometryRepresentation("jsonfiles/Tri_Quad.json");
		
		//Point
		PointDatabase PDB = newMap.getKey(); 
		Set<Point> SetPoint = PDB.getPoints();
	
		PointDatabase pointTest = new PointDatabase(); 
		pointTest.put("A", 4, 0);
		pointTest.put("B", 8, 0);
		pointTest.put("C", 4, 5);
		pointTest.put("D", 8, 5);
		pointTest.put("E", 0, 10);
		pointTest.put("F", 12, 10);
		pointTest.put("G", 4, 12);
		pointTest.put("H", 8, 12);
		pointTest.put("I", 6, 10);
		Set<Point> TestSet = pointTest.getPoints(); 
	
		for (Point point : SetPoint) { 
			assertTrue(TestSet.contains(point)); 
		}
			
		assertEquals(SetPoint.size(), TestSet.size());
	
		//Segment
		Set<Segment> SGT = newMap.getValue();
		LinkedHashSet<Segment> segTest = new LinkedHashSet<Segment>();
			
		Point pointA = new Point(4, 0);
		Point pointB = new Point(8, 0);
		Point pointC = new Point(4, 5);
		Point pointD = new Point(8, 5);
		Point pointE = new Point(0, 10);
		Point pointF = new Point(12, 10);
		Point pointG = new Point(4, 12);
		Point pointH = new Point(8, 12);
		Point pointI = new Point(6, 10);
		
		

		this.addPoints(segTest, pointA, pointB);
		this.addPoints(segTest, pointA, pointC);
		this.addPoints(segTest, pointB, pointD);
		this.addPoints(segTest, pointC, pointD);
		this.addPoints(segTest, pointC, pointE);
		this.addPoints(segTest, pointC, pointI);
		this.addPoints(segTest, pointD, pointF);
		this.addPoints(segTest, pointD, pointI);
		this.addPoints(segTest, pointE, pointG);
		this.addPoints(segTest, pointF, pointH);
		this.addPoints(segTest, pointG, pointI);
		this.addPoints(segTest, pointH, pointI);
			
		for (Segment seg : SGT) {
			assertTrue(segTest.contains(seg));
		}
		
		assertEquals(SGT.size(), segTest.size());
	
	}
	

}
