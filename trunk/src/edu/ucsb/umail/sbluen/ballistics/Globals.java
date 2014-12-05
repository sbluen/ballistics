package edu.ucsb.umail.sbluen.ballistics;

public class Globals {
	
	/**maxX and maxY are in pixels and are set by class DisplayArea.*/
	public static int maxX = 0;
	/**maxX and maxY are in pixels and are set by class DisplayArea.*/
	public static int maxY = 0;
	
	/**In fractions of the screen. Used in various class methods*/
	public static final float BUMP_HEIGHT_MAX = 0.4f;
	public static final float BUMP_WIDTH_MAX = 0.3f;
	public static final int FRAMES_PER_SECOND = 30;
	public static final float SECONDS_PER_FRAME = 1f/FRAMES_PER_SECOND;
	public static final int MILLISECONDS_PER_SECOND = 1000;
	public static final float POWER_SCALE_RATIO = 0.15f;
	
	/**The acceleration due to gravity, in pixels per frame.*/
	//set to 0.1% of the screen per second per second by default
	public static float gravity = 0.1f;
	
	/**number of turrets in a game by default*/
	public static final int numTurrets = 2;
	
}
