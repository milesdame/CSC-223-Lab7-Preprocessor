package geometry_objects.angle;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.eq_classes.LinkedEquivalenceClass;


public class AngleEquivalenceClassesTest {

	@Test
	void test_addClass_nonAngleEqClass() {
		AngleEquivalenceClasses classes = new AngleEquivalenceClasses();
		
		boolean expected = false;
		boolean actual = classes.addClass(new LinkedEquivalenceClass(new AngleStructureComparator()));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_addClass_AngleEqClass() {
		AngleEquivalenceClasses classes = new AngleEquivalenceClasses();
		
		boolean expected = true;
		boolean actual = classes.addClass(new AngleLinkedEquivalenceClass());
		
		assertEquals(expected, actual);
	}
	
}
