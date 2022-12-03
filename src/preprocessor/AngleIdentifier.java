package preprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.angle.AngleEquivalenceClasses;
import geometry_objects.angle.AngleLinkedEquivalenceClass;
import geometry_objects.points.Point;
import utilities.math.MathUtilities;

/**
 * The purpose of this class is to find all angles in a figure. 
 * The figure is represented by a map of segments. 
 * 
 * @author Sophie Ngo
 *
 */
public class AngleIdentifier
{
	/**
	 * This variable will contain all found angles, as a result of computeAngles() 
	 */
	protected AngleEquivalenceClasses _angles;
	
	/**
	 * Given this collection of segments of the figure, we can use
	 * getAngles() to get Angle object representations of all the angles
	 * in the figure.
	 * (key and value are the same Segment object, so key == value)
	 */
	protected Map<Segment, Segment> _segments;

	public AngleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested; memoize results for subsequent calls.
	 */
	public AngleEquivalenceClasses getAngles() throws FactException
	{
		if (_angles != null) return _angles;

		_angles = new AngleEquivalenceClasses();

		computeAngles();

		return _angles;
	}

	/**
	 * From the given segments, compute Angle objects for all angles found within these segments.
	 * @throws FactException 
	 */
	private void computeAngles() throws FactException
	{
		ArrayList<Segment> segments = (ArrayList<Segment>) _segments.values();
		
		// loop through list of segments. grab one at a time, check it against all other segments after it
		for (int i = 0; i < segments.size(); i++) {

			for (int j = i+1; j < segments.size(); j++) {

				computeAndAddAngle(segments.get(i), segments.get(j));
			}
		}
	}
	
	/**
	 * Creates a new Angle made from the two given segments. If the measure of the angle is not 0, add it to the 
	 * collection of Angles.
	 * @param s1 - first segment
	 * @param s2 - second segment
	 * @throws FactException
	 */
	private void computeAndAddAngle(Segment s1, Segment s2) throws FactException {
		// if they overlay, then their measure is 0. this is bad
		if (Segment.overlaysAsRay(s1, s2)) return;

		if (s1.sharedVertex(s2) != null) {
			addToAngles(new Angle(s1, s2));
		}
	}
	
	/**
	 * Handles adding an angle to the current collection of Angle equivalence classes.
	 * Will create a new equivalence class if the new angle does not belong to any existing equivalence classes.
	 * @param a - angle to add
	 */
	private void addToAngles(Angle a) {
		if (a == null) return;
		
		// attempt to add to existing eq class
		boolean result = _angles.add(a);
		
		// if it did not belong to any of them, create a new eq class
		if (result == false) {
			AngleLinkedEquivalenceClass newClass = new AngleLinkedEquivalenceClass();
			newClass.add(a);
			_angles.addClass(newClass);
		}
	}
}
