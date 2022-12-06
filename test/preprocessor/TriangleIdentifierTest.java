package preprocessor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.components.FigureNode;
import input.InputFacade;

class TriangleIdentifierTest { 
	protected PointDatabase _points;
	protected Preprocessor _pp;
	protected Map<Segment, Segment> _segments;
	
	protected void init(String filename) {
		FigureNode fig = InputFacade.extractFigure(filename);

		Map.Entry<PointDatabase, Set<Segment>> pair = InputFacade.toGeometryRepresentation(fig);

		_points = pair.getKey();

		_pp = new Preprocessor(_points, pair.getValue());

		_pp.analyze();

		_segments = _pp.getAllSegments();
		
		
		//System.out.println(_segments.toString());
		
		for (Segment s : _segments.keySet()) {
			System.out.println(s.toString());
		}
		
	}
	
	//      A                                 
	//     / \                                
	//    B___C                               
	//   / \ / \                              
	//  /   X   \  X is not a specified point (it is implied) 
	// D_________E
	//
	// This figure contains 12 triangles
	//
	@Test
	void test_crossing_symmetric_triangle() throws FactException {
		init("jsonfiles/crossing_symmetric_triangle.json");

		TriangleIdentifier triIdentifier = new TriangleIdentifier(_segments);

		Set<Triangle> computedTriangles = triIdentifier.getTriangles();

		System.out.println(computedTriangles);

		assertEquals(12, computedTriangles.size());

		//
		// ALL original segments: 8 in this figure.
		//
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ac = new Segment(_points.getPoint("A"), _points.getPoint("C"));
		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));

		Segment bd = new Segment(_points.getPoint("B"), _points.getPoint("D"));
		Segment ce = new Segment(_points.getPoint("C"), _points.getPoint("E"));
		Segment de = new Segment(_points.getPoint("D"), _points.getPoint("E"));

		Segment be = new Segment(_points.getPoint("B"), _points.getPoint("E"));
		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));

		//
		// Implied minimal segments: 4 in this figure.
		// Issue with the commented out version
		//Point a_star = _points.getPoint(3,3);
		Point a_star = new Point(3,3);

		Segment a_star_b = new Segment(a_star, _points.getPoint("B"));
		Segment a_star_c = new Segment(a_star, _points.getPoint("C"));
		Segment a_star_d = new Segment(a_star, _points.getPoint("D"));
		Segment a_star_e = new Segment(a_star, _points.getPoint("E"));

		//
		// Non-minimal, computed segments: 2 in this figure.
		//
		Segment ad = new Segment(_points.getPoint("A"), _points.getPoint("D"));
		Segment ae = new Segment(_points.getPoint("A"), _points.getPoint("E"));

		//
		// Triangles we expect to find
		//
		List<Triangle> expectedTriangles = new ArrayList<Triangle>();
		try {
			expectedTriangles.add(new Triangle(Arrays.asList(ab, bc, ac)));
			expectedTriangles.add(new Triangle(Arrays.asList(bd, a_star_d, a_star_b)));
			expectedTriangles.add(new Triangle(Arrays.asList(bc, a_star_b, a_star_c)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, a_star_c, a_star_e)));
			expectedTriangles.add(new Triangle(Arrays.asList(de, a_star_d, a_star_e)));

			expectedTriangles.add(new Triangle(Arrays.asList(bd, cd, bc)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, be, bc)));

			expectedTriangles.add(new Triangle(Arrays.asList(bd, be, de)));
			expectedTriangles.add(new Triangle(Arrays.asList(ce, cd, de)));

			expectedTriangles.add(new Triangle(Arrays.asList(ab, be, ae)));
			expectedTriangles.add(new Triangle(Arrays.asList(ac, cd, ad)));

			expectedTriangles.add(new Triangle(Arrays.asList(ad, de, ae)));
		}
		catch (FactException te) { System.err.println("Invalid triangles in triangle test."); }

		assertEquals(expectedTriangles.size(), computedTriangles.size());
		
		for (Triangle computedTriangle : computedTriangles)
		{
			assertTrue(expectedTriangles.contains(computedTriangle));
		}
	}
	
	//
	//  A-----------B
	//  | \E__|__F/ |
	//  |  |\ | /|  |
	//  |  | /X\ |  |
	//  | /G__|__H\ |
	//  D-----------C
	//
	//
	//
	//  An square containing another smaller square and multiple triangles
	//
	@Test
	void test_triangles_within_a_square() throws FactException {
		init("jsonfiles/triangles_within_a_square.json");

		TriangleIdentifier triIdentifier = new TriangleIdentifier(_segments);

		Set<Triangle> computedTriangles = triIdentifier.getTriangles();

		System.out.println(computedTriangles);

		assertEquals(16, computedTriangles.size());

		//
		// ALL original segments: 12 in this figure.
		//
		Segment ab = new Segment(_points.getPoint("A"), _points.getPoint("B"));
		Segment ac = new Segment(_points.getPoint("A"), _points.getPoint("C"));
		Segment ad = new Segment(_points.getPoint("A"), _points.getPoint("D"));
		
		Segment bc = new Segment(_points.getPoint("B"), _points.getPoint("C"));
		Segment bd = new Segment(_points.getPoint("B"), _points.getPoint("D"));
		
		Segment cd = new Segment(_points.getPoint("C"), _points.getPoint("D"));

		Segment ef = new Segment(_points.getPoint("E"), _points.getPoint("F"));
		Segment eh = new Segment(_points.getPoint("E"), _points.getPoint("H"));
		Segment eg = new Segment(_points.getPoint("E"), _points.getPoint("G"));
		
		Segment fh = new Segment(_points.getPoint("F"), _points.getPoint("H"));
		Segment fg = new Segment(_points.getPoint("F"), _points.getPoint("G"));

		Segment hg = new Segment(_points.getPoint("H"), _points.getPoint("G"));

		//
		// Implied minimal segments: 8 in this figure.
		// Issue with the commented out version
		//Point a_star = _points.getPoint(3,3);
		Point a_star = new Point(3,3);

		Segment a_star_e = new Segment(a_star, _points.getPoint("E"));
		Segment a_star_f = new Segment(a_star, _points.getPoint("F"));
		Segment a_star_g = new Segment(a_star, _points.getPoint("G"));
		Segment a_star_h = new Segment(a_star, _points.getPoint("H"));
		

		//
		// Non-minimal, computed segments: 12 in this figure.
		//
		Segment ae = new Segment(_points.getPoint("A"), _points.getPoint("E"));
		Segment bf = new Segment(_points.getPoint("B"), _points.getPoint("F"));
		Segment dg = new Segment(_points.getPoint("D"), _points.getPoint("G"));
		Segment ch = new Segment(_points.getPoint("C"), _points.getPoint("H"));
		
		Segment d__a_star = new Segment(_points.getPoint("A"), a_star);
		Segment c__a_star = new Segment(_points.getPoint("A"), a_star);
		Segment b__a_star = new Segment(_points.getPoint("A"), a_star);
		Segment a__a_star = new Segment(_points.getPoint("A"), a_star);
		
		Segment ah = new Segment(_points.getPoint("A"), _points.getPoint("H"));
		Segment bg = new Segment(_points.getPoint("B"), _points.getPoint("G"));
		Segment ce = new Segment(_points.getPoint("C"), _points.getPoint("E"));
		Segment df = new Segment(_points.getPoint("F"), _points.getPoint("F"));
		
		//
		// Triangles we expect to find
		//
		List<Triangle> expectedTriangles = new ArrayList<Triangle>();
		try {
			expectedTriangles.add(new Triangle(Arrays.asList(a__a_star, b__a_star, ab)));
			expectedTriangles.add(new Triangle(Arrays.asList(b__a_star, c__a_star, bc)));
			expectedTriangles.add(new Triangle(Arrays.asList(c__a_star, d__a_star, cd))); // not being computed
			expectedTriangles.add(new Triangle(Arrays.asList(d__a_star, a__a_star, ad))); 
			
			expectedTriangles.add(new Triangle(Arrays.asList(ab, bc, ac)));
			expectedTriangles.add(new Triangle(Arrays.asList(ad, cd, ac))); // not being computed
			expectedTriangles.add(new Triangle(Arrays.asList(ad, bd, ab))); 
			expectedTriangles.add(new Triangle(Arrays.asList(cd, bd, bc))); // not being computed
			
			expectedTriangles.add(new Triangle(Arrays.asList(a_star_e, a_star_f, ef)));
			expectedTriangles.add(new Triangle(Arrays.asList(a_star_f, a_star_h, fh)));
			expectedTriangles.add(new Triangle(Arrays.asList(a_star_h, a_star_g, hg)));
			expectedTriangles.add(new Triangle(Arrays.asList(a_star_g, a_star_e, eg)));
			
			
			expectedTriangles.add(new Triangle(Arrays.asList(ef, fg, eg)));
			expectedTriangles.add(new Triangle(Arrays.asList(ef, fh, eh)));
			expectedTriangles.add(new Triangle(Arrays.asList(eg, hg, fh)));
			expectedTriangles.add(new Triangle(Arrays.asList(fg, hg, fh)));
		}
		catch (FactException te) { System.err.println("Invalid triangles in triangle test."); }

		assertEquals(expectedTriangles.size(), computedTriangles.size());
		
		for (Triangle computedTriangle : computedTriangles)
		{
			assertTrue(expectedTriangles.contains(computedTriangle));
		}
	}
}
