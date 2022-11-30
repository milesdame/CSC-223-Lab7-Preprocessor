package geometry_objects.angle;

import geometry_objects.angle.comparators.AngleStructureComparator;
import utilities.eq_classes.LinkedEquivalenceClass;

/**
 * This implementation requires greater knowledge of the implementing Comparator.
 * 
 * According to our specifications for the AngleStructureComparator, we have
 * the following cases:
 *
 *    Consider Angles A and B
 *    * Integer.MAX_VALUE -- indicates that A and B are completely incomparable
                             STRUCTURALLY (have different measure, don't share sides, etc. )
 *    * 0 -- The result is indeterminate:
 *           A and B are structurally the same, but it is not clear one is structurally
 *           smaller (or larger) than another
 *    * 1 -- A > B structurally
 *    * -1 -- A < B structurally
 *    
 *    We want the 'smallest' angle structurally to be the canonical element of an
 *    equivalence class.
 * 
 * @author Sophie Ngo
 */
public class AngleLinkedEquivalenceClass extends LinkedEquivalenceClass<Angle>
{
	public AngleLinkedEquivalenceClass() {
		
		super(new AngleStructureComparator());
	}
	
	/**
	 * Add to equivalence class. Check to see if this new angle is the smallest structurally than the current canonical. 
	 * If so, replace it
	 */
    @Override
    public boolean add (Angle a) {
		if (_canonical == null) {
			_canonical = a;
			return true;
		}
		// if new angle is structurally smaller than curr canonical, replace with new angle
		if (_comparator.compare(_canonical, a) == -1) {
			demoteAndSetCanonical(a);
			return true;
		}
		if(this.belongs(a)) {
			_rest.addToBack(a);
			return true;
		}
		return false;
    }
    
    /**
     * Return true if the angles are structurally equivalent and therefore belong in this equivalence class.
     */
    @Override
    public boolean belongs (Angle a) {
		if (_canonical == null) return false;
		if (a == null) return false;
		if (a.equals(_canonical)) return true;
		return _comparator.compare(_canonical, a) != AngleStructureComparator.STRUCTURALLY_INCOMPARABLE;
    }
}