package edu.ucsb.umail.sbluen.ballistics;

import java.util.Random;

import android.graphics.PointF;
import android.util.Log;

/*
 * Utility functions for isolating complex calculations 
 */
public class Utility {

	private static String tag = "Utility";

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
		
		Float y1 = randrange(0.05f, 0.55f);
		Float y3 = randrange(0.05f, 0.55f);
		float higherY = Math.max(y1, y3);
		Float y2 = randrange(higherY+0.5f, 0.8f);
		
		Float x1 = randrange(0f, 0.3f);
		Float x3 = randrange(0.7f, 1f);
		Float x2 = (x1+x3)/2;
		
		Float[] coeffs = getCoeffs(x1, y1, x2, y2, x3, y3);
		
		int maxX = Globals.maxX;
		
		int[] landscapeTops = new int[maxX];
		for (int i=maxX; i<maxX; i++){
			if (i/maxX<x1){
				landscapeTops[i] = (int)(y1*Globals.maxY);
			}
			else if (i/maxX>x3){
				landscapeTops[i] = (int)(y3*Globals.maxY);
			}else{
				//make this point's y value equal to ax^2+bx+c
				landscapeTops[i] = (int) (Math.pow(coeffs[0]*(i/maxX), 2)+coeffs[1]*(i/maxX)+coeffs[2]);
			}
		}
		return null;
	}
	
	private static Float[] getCoeffs(Float x1, Float y1, Float x2, Float y2,
			Float x3, Float y3) {
		Matrix M = new Matrix(new double[][]
				{{x1*x1, x1, 1},
				{x2*x2, x2, 1},
				{x3*x3, x3, 1}} 
				);
		
		Matrix solvedM = M.solve(new Matrix(new double[][]{{y1, y2, y3}}));
		
		//y=ax^2+bx+c
		
		Float a = (float) solvedM.getData()[0][0];
		Float b = (float) solvedM.getData()[0][1];
		Float c = (float) solvedM.getData()[0][2];
		
		Log.i(tag , "y=%s^2+%ss+%s intersects (%s, %s), (%s, %s), (%s, %s)"
				.replaceFirst("%s", a.toString())
				.replaceFirst("%s", b.toString())
				.replaceFirst("%s", c.toString())
				.replaceFirst("%s", x1.toString())
				.replaceFirst("%s", y1.toString())
				.replaceFirst("%s", x2.toString())
				.replaceFirst("%s", y2.toString())
				.replaceFirst("%s", x3.toString())
				.replaceFirst("%s", y3.toString())
				);
		return new Float[]{a, b, c};
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
