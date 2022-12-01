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
 * This class will find all angles in a figure. 
 * The figure is given by a map of segments.
 * 
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
			Segment s1 = segments.get(i);
			
			for (int j = i+1; j < segments.size(); j++) {
				Segment s2 = segments.get(j);
				
				// where the action happens. if the segments connect at one endpoint, create a new angle and add it (if measure is not 0 radians)
				Point sharedPt = s1.sharedVertex(s2);
				if (sharedPt != null) {
					addToAngles(makeValidAngle(s1, s2));
				}
			}
		}
	}
	
	/**
	 * Returns an Angle out of two segments, but only if the angle's measure is not epsilon equal to 0 radians. 
	 * @param s1 - first segment
	 * @param s2 - second segment
	 * @return new Angle if angle measure is not 0, otherwise null
	 * @throws FactException 
	 */
	private Angle makeValidAngle(Segment s1, Segment s2) throws FactException {
		Angle a = new Angle(s1, s2);
		if (MathUtilities.doubleEquals(a.getMeasure(), 0.0)) return null;
		return a;
	}
	
	/**
	 * Handles adding an angle to the current collection of Angle equivalence classes.
	 * Will not add if a is null (this will happen if the angle calculated had a measure of 0 radians.
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
