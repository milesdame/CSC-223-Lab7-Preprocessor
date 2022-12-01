/**
 * Write a succinct, meaningful description of the class here. You should avoid wordiness    
 * and redundancy. If necessary, additional paragraphs should be preceded by <p>,
 * the html tag for a new paragraph.
 *
 * <p>Bugs: (a list of bugs and / or other problems)
 *
 * @author <your name>
 * @date   <date of completion>
 */

package geometry_objects.angle.comparators;

import java.util.Comparator;

import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.points.Point;
import utilities.math.MathUtilities;
import utilities.math.analytic_geometry.GeometryUtilities;

public class AngleStructureComparator implements Comparator<Angle>
{
	public static final int STRUCTURALLY_INCOMPARABLE = Integer.MAX_VALUE;
	
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
	 * What we care about is the fact that angle BAE is the smallest angle (structurally)
	 * and DAF is the largest angle (structurally). 
	 * 
	 * If one angle X has both rays (segments) that are subsegments of an angle Y, then X < Y.
	 * 
	 * If only one segment of an angle is a subsegment, then no conclusion can be made.
	 * 
	 * So:
	 *     BAE < CAE
   	 *     BAE < DAF
   	 *     CAF < DAF

   	 *     CAE inconclusive BAF
	 * 
	 * @param left -- an angle
	 * @param right -- an angle
	 * @return -- according to the algorithm above:
 	 *            Integer.MAX_VALUE will refer to our error result
 	 *            0 indicates an inconclusive result
	 */
	@Override
	public int compare(Angle left, Angle right)
	{
        // Case 1: Same vertex and rays 
		if (left.overlays(right)) {
			
			// Get the ray from the left angle that corresponds with ray1 from the right angle
			Segment lr1 = left.overlayingRay(right.getRay1());
			
			// Get the ray from the left angle that corresponds with ray2 from the right angle
			Segment lr2 = left.overlayingRay(right.getRay2());
			
			// Case 2: Same vertex and rays in left are >= rays in the right
			if ((lr1.length() >= right.getRay1().length()) && (lr2.length() >= left.getRay2().length()) ) return 1;
			
			// Case 3: Same vertex and rays in left are <= rays in the right
			if ((lr1.length() <= right.getRay1().length()) && (lr2.length() <= left.getRay2().length())) return -1;
			
			// Case 4: corresponding rays are different lengths
			if ((lr1.length() > right.getRay1().length()) && (lr2.length() < left.getRay2().length())) return 0;
			if ((lr1.length() < right.getRay1().length()) && (lr2.length() > left.getRay2().length())) return 0;	
				
		} 
		int error = Integer.MAX_VALUE;
		return error;
		
		
		
		
	}
}
