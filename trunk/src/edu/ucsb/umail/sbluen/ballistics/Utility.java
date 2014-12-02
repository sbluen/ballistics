package edu.ucsb.umail.sbluen.ballistics;

import android.util.Log;
import java.util.Random;

/*
 * Utility functions for isolating complex calculations 
 */
public class Utility {

	private static String tag = "Utility";

//	/**
//	 * Multiples a point and a scalar value.
//	 * @return the result of this multiplication
//	 */
//	public static ExtPoint scalarMultiply(ExtPoint point, float scale) {
//		ExtPoint temp = new ExtPoint(point.x*scale, point.y*scale); 
//		return temp;
//	}

/**
 * Returns the angle from dragStart to dragEnd
 */
	public static float getAngle(ExtPoint dragStart, ExtPoint dragEnd) {
		return (float) Math.atan2(dragEnd.y-dragStart.y, dragEnd.x-dragStart.x);
	}

	/**
	 * Returns the distance from 
	 * @param dragStart
	 * @param dragEnd
	 * @return
	 */
	public static float getPower(ExtPoint dragStart, ExtPoint dragEnd) {
		return dragEnd.getDistance(dragStart) * Globals.POWER_SCALE_RATIO;
	}
	


	/**
	 * Returns a random floating point number between low and high
	 * @param low the lower parameter
	 * @param high the higher parameter
	 * @return the result of the calculation low+x*(high-low) where x is a random
	 * number between 0 and 1.
	 */
	public static float randrange(float low, float high){
		Float temp = new Random(System.currentTimeMillis()).nextFloat();
		return (low + new Random(System.currentTimeMillis()).nextFloat()*(high-low));
	}

}
