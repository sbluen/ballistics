package edu.ucsb.umail.sbluen.ballistics;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

/**
 * 
 * @author Steven Bluen
 * A collection of methods related to the landscape.
 */
public class Landscape{

	private static String tag = "Landscape";
	

	/**
	 * An array representing y coordinate for the top of the landscape at 
	 * each x coordinate.
	 */
	private int[] tops;
	
	
	/**
	 * Flag to represent whether or not the landscape has been generated so far. 
	 */
	private boolean generated = false;
	
	/**
	 * Place where present map is stored
	 */
	private Bitmap bitmap;
	
	/**
	 * Points where the turrets are placed. Modified by generate().
	 * Stored here for temporary use.
	 */
	private ExtPoint[] positions = new ExtPoint[Globals.numTurrets];
	
	/**
	 * default ctor
	 */
	Landscape(){
		
	};
	
	/**
	 * Generates the landscape surface
	 */
	public void generate(){
		
		int maxX = Globals.maxX;
		int maxY = Globals.maxY;
		
		Float y1 = Utility.randrange(0.05f*maxY, 0.55f*maxY);
		Float y3 = Utility.randrange(0.05f*maxY, 0.55f*maxY);
		float higherY = Math.max(y1, y3);
		Float y2 = Utility.randrange(higherY+0.5f*maxY, 0.8f*maxY);
		
		Float x1 = Utility.randrange(0f*maxX, 0.3f*maxX);
		Float x3 = Utility.randrange(0.7f*maxX, 1f*maxX);
		Float x2 = (x1+x3)/2;
		
		Float[] coeffs = getCoeffs(x1, y1, x2, y2, x3, y3);
		
		tops = new int[maxX];
		for (int i=0; i<maxX; i++){
			if (i<x1){
				tops[i] = y1.intValue();
			}
			else if (i>x3){
				tops[i] = y3.intValue();
			}else{
				//make this point's y value equal to ax^2+bx+c
				tops[i] = (int) (coeffs[0]*Math.pow(i, 2) + coeffs[1]*i + coeffs[2]);
			}
		}
		
		positions[0] = new ExtPoint(Utility.randrange(0.001f*maxX, x1 - 0.001f*maxX), y1);
		positions[1] = new ExtPoint(Utility.randrange(x3 + 0.001f*maxX, maxX - 0.001f*maxX), y3);
		
		bitmap = Bitmap.createBitmap(Globals.maxX, Globals.maxY, Bitmap.Config.ARGB_8888);
		for (int i=0; i<tops.length; i++){
			for (int j=0; j<tops[i]; j++){
				bitmap.setPixel(i, j, Color.GREEN);
			}
		}
		generated = true;
	}
	
	private static Float[] getCoeffs(Float x1, Float y1, Float x2, Float y2,
			Float x3, Float y3) {
		Matrix M = new Matrix(new double[][]
				{{x1*x1, x1, 1},
				{x2*x2, x2, 1},
				{x3*x3, x3, 1}} 
				);
		
		Matrix solvedM = M.solve(new Matrix(new double[][]{{y1}, {y2}, {y3}}));
		
		//y=ax^2+bx+c
		
		Float a = (float) solvedM.getData()[0][0];
		Float b = (float) solvedM.getData()[1][0];
		Float c = (float) solvedM.getData()[2][0];
		
		Log.i(tag  , "y=%s^2+%ss+%s intersects (%s, %s), (%s, %s), (%s, %s)"
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
	 * Draws the landscape.
	 * No longer used.
	 */
	public void drawLandscape(){

	}
	
	/**
	 * Draws the bitmap onto a canvas.
	 */
	public void draw(Canvas canvas){
		
		//To avoid degenerate cases.
		if (!generated){
			Log.i(tag, "tried to draw before generating a landscape");
			return;
		}
		
		canvas.drawBitmap(bitmap, 0, 0, null);
	}
	
	/**
	 * Writes the landscape to a file. Currently unused.
	 * 
	 * @param filename The relative filename that the data is written to.
	 */
	public void write(String filename){	
	      File file = new File(Environment.getExternalStorageDirectory() + "/"+ filename);

	      try {
	           bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
	      } catch (Exception e) {
	           e.printStackTrace();
	      }
	}

	/**
	 * @return the generated
	 */
	public boolean isGenerated() {
		return generated;
	}

	/**
	 * @return the positions
	 */
	public ExtPoint[] getPositions() {
		return positions;
	}
}
