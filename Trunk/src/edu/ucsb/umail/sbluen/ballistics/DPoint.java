package edu.ucsb.umail.sbluen.ballistics;

import android.graphics.Point;

/**
 * 
 * @author Steven
 *Utility class used as a substitute because java.awt.geom.Point2d.Double isn't part of 
 *the android library
 */
public class DPoint implements Cloneable{
	public double x, y;
	
	public DPoint(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Copy Constructor
	 * @param dp
	 */
	public DPoint(DPoint dp){
		this(dp.x, dp.y);
	}
	
	/**
	 * gets a Point of this class usable in graphics classes that require the use of
	 * android.graphics.Point objects
	 * @return an android.graphics.Point object with casted (floored) values 
	 */
	public Point getPoint(){
		return new Point((int)x, (int)y);
	}
	
	/**
	 * would be the += operator
	 * @param dp The DPoint to add to this class
	 */
	public void add(DPoint dp){
		x+=dp.x;
		y+=dp.y;
	}
	
	/**
	 * would be the += operator, overloaded for addition of parameters directly
	 * @param x
	 * @param y
	 */
	public void add(double x, double y){
		add(new DPoint(x, y));
	}
	
	/**
	 * would be the + operator
	 * @param dp
	 * @return a DPoint object whose x and y values are the sum of those of this and
	 * of dp.
	 */
	public DPoint plus(DPoint dp){
		return new DPoint(this.x+dp.x, this.y+dp.y);
	}
	
	/**
	 * would be the + operator, overloaded for
	 * @param x
	 * @param y
	 * @return a DPoint object whose x and y values are the sum of those of this and
	 * of the parameters.
	 */
	public DPoint plus(double x, double y){
		return plus(new DPoint(x, y));
	}
	
	/**
	 * Shrinks the coordinate so that their distance from (0, 0) is 1 at maximum.
	 * Stub right now.
	 */
	public void normalized(){
		//dist=
		//return new DPoint(x)
		return;
	}
	
	/**
	 * Returns the result of multiplying the coordinates of the point by a scalar value
	 * @return 
	 */
	public DPoint times(double value){
		return new DPoint(this.x * value, this.y * value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return String.format("DPoint(%s, %s)", this.x, this.y);
	}
	
	/*
	 * Returns the Euclidean distance between this point and other.
	 * @return the distance as a double
	 */
	public double getDistance(DPoint other){
		return Math.sqrt(Math.pow(other.x-x, 2) + Math.pow(other.y-y, 2));
	}
}
