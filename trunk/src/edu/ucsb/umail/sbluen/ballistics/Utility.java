package edu.ucsb.umail.sbluen.ballistics;

import java.util.Random;

import android.graphics.PointF;

/*
 * Utility functions for isolating complex calculations 
 */
public class Utility {

	/**
	 * Multiples a point and a scalar value.
	 * @return the result of this multiplication
	 */
	public static PointF scalarMultiply(PointF point, float scale) {
		PointF temp = new PointF(point.x*scale, point.y*scale); 
		return temp;
	}

/**
 * Returns the angle from dragStart to dragEnd
 */
	public static float getAngle(PointF dragStart, PointF dragEnd) {
		return (float) Math.atan2(dragEnd.x-dragStart.x, dragEnd.y-dragStart.y);
	}

	/**
	 * Returns the distance from 
	 * @param dragStart
	 * @param dragEnd
	 * @return
	 */
	public static float getPower(PointF dragStart, PointF dragEnd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Generates the landscape surface
	 * @return An array of y-coordinates that represent pixels at the top of the
	 * landscape, with the tank locations at the end of the array after maxX.
	 */
	//TODO
	public static int[] makeLandscape(){
		int surfaceheight1 = (int) randrange(0.05f, 0.55f);
		int surfaceheight2 = (int) randrange(0.05f, 0.55f);
		//int hillstart =  
		return null;
	}
	
	/**
	 * Returns a random floating point number between low and high
	 * @param low the lower parameter
	 * @param high the higher parameter
	 * @return the result of the calculation low+x*(high-low) where x is a random
	 * number between 0 and 1.
	 */
	public static float randrange(float low, float high){
		return (float) (low + Math.random()*(high-low));
	}

}
