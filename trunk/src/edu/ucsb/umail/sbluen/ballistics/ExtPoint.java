package edu.ucsb.umail.sbluen.ballistics;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * 
 * @author Steven
 *Utility class used as a substitute because java.awt.geom.Point2d.Double isn't part of 
 *the android library
 */
public class ExtPoint extends PointF{
	
	/**
	 * Copy Constructor
	 * @param other the class to make an ExtPoint out of.
	 */
	public ExtPoint(PointF other){
		this(other.x, other.y);
	}
	
	public ExtPoint(float x, float y) {
		this.x=x;
		this.y=y;
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
	public void add(ExtPoint other){
		x+=other.x;
		y+=other.y;
	}
	
	/**
	 * would be the += operator, overloaded for addition of parameters directly
	 * @param x
	 * @param y
	 */
	public void add(float x, float y){
		add(new ExtPoint(x, y));
	}
	
	/**
	 * would be the + operator
	 * @param dp
	 * @return a DPoint object whose x and y values are the sum of those of this and
	 * of other.
	 */
	public ExtPoint plus(ExtPoint other){
		return new ExtPoint(this.x+other.x, this.y+other.y);
	}
	
	/**
	 * would be the + operator, overloaded for
	 * @param x
	 * @param y
	 * @return a DPoint object whose x and y values are the sum of those of this and
	 * of the parameters.
	 */
	public ExtPoint plus(float x, float y){
		return this.plus(new ExtPoint(x, y));
	}
	
	/**
	 * would be the - operator
	 * @param dp
	 * @return a DPoint object whose x and y values are the difference between that of
	 * this point and of other
	 */
	public ExtPoint minus(ExtPoint other){
		return new ExtPoint(this.x-other.x, this.y-other.y);
	}
	
	/**
	 * Normalized the coordinate pair
	 * @return this point, normalized
	 */
	public ExtPoint normalized(float scale){
		float factor = (float) (scale/Math.sqrt(x*x+y*y));
		return this.times(factor);
	}
	
	/**
	 * Returns the result of multiplying the coordinates of the point by a scalar value
	 * @return this point, multiplied by value
	 */
	public ExtPoint times(float value){
		return new ExtPoint(this.x * value, this.y * value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return String.format("ExtPoint(%s, %s)", this.x, this.y);
	}
	
	/*
	 * Returns the Euclidean distance between this point and other.
	 * @return the distance as a double
	 */
	public float getDistance(ExtPoint other){
		return (float) Math.sqrt(Math.pow(other.x-x, 2) + Math.pow(other.y-y, 2));
	}
}
