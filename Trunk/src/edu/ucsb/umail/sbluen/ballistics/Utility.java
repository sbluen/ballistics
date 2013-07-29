package edu.ucsb.umail.sbluen.ballistics;

import android.graphics.PointF;

/*
 * Utility functions for isolating complex calculations 
 */
public class Utility {

	/*
	 * Multiples a point and a scalar value.
	 * @return the result of this multiplication
	 */
	public static PointF scalarMultiply(PointF point, float scale) {
		PointF temp = new PointF(point.x*scale, point.y*scale); 
		return temp;
	}

}
