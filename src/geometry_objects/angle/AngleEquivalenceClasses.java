package geometry_objects.angle;

import java.util.ArrayList;
import java.util.List;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.eq_classes.EquivalenceClasses;
import utilities.eq_classes.LinkedEquivalenceClass;

/**
 * Given the figure below:
 * 
 *    A-------B----C-----------D
 *     \
 *      \
 *       \
 *        E
 *         \
 *          \
 *           F
 * 
 * Equivalence classes structure we want:
 * 
 *   canonical = BAE
 *   rest = BAF, CAE, DAE, CAF, DAF
 */
public class AngleEquivalenceClasses extends EquivalenceClasses<Angle>
{
	public AngleEquivalenceClasses() {
		super(new AngleStructureComparator());
	}

	/**
	 * Ensure that every class added is an AngleLinkedEquivalenceClass
	 */
	@Override
	public boolean addClass(LinkedEquivalenceClass<Angle> eqclass) {
		if (!(eqclass instanceof AngleLinkedEquivalenceClass)) return false;
		return _classes.add(eqclass);
	}
}
