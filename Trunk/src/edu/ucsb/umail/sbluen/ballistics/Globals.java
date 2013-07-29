package edu.ucsb.umail.sbluen.ballistics;

public class Globals {
	
	//maxX and maxY are in pixels and are set by class DisplayArea.
	public static int maxX = 0;
	public static int maxY = 0;
	
	//Mask may be used for terrain destruction
	public static boolean[][] mask;
	
	public static final double GRAVITY = 5;
	public static final double SCALE = 0.04;
	
	//In fractions of the screen. Used in the utility class functions
	public static final double BUMP_HEIGHT_MAX = 0.4;
	public static final double BUMP_WIDTH_MAX = 0.3;
}
