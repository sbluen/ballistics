package edu.ucsb.umail.sbluen.ballistics;

public class Globals {
	
	/**maxX and maxY are in pixels and are set by class DisplayArea.*/
	public static int maxX = 0;
	/**maxX and maxY are in pixels and are set by class DisplayArea.*/
	public static int maxY = 0;
	
	/**Mask may be used for terrain destruction*/
	public static boolean[][] mask;
	
	/**In fractions of the screen. Used in the utility class functions*/
	public static final float BUMP_HEIGHT_MAX = 0.4f;
	/**In fractions of the screen. Used in the utility class functions*/
	public static final float BUMP_WIDTH_MAX = 0.3f;
	public static final int FRAMES_PER_SECOND = 30;
	public static final float SECONDS_PER_FRAME = 1f/FRAMES_PER_SECOND;
	public static final int MILLISECONDS_PER_SECOND = 1000;
	public static final float POWER_SCALE_RATIO = 0.15f;
	
	/**The acceleration due to gravity, in pixels per frame.*/
	//set to 10% of the screen per second
	public static float gravity;
	
	static ExtPoint pos1, pos2;
}
