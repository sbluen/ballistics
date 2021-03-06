package edu.ucsb.umail.sbluen.ballistics;

import java.lang.Math;
import java.util.ArrayList;

import edu.ucsb.umail.sbluen.ballistics.R;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

/**
 * Class to represent missiles fired by the players.
 * @author Steven
 *
 */
public class Missile extends View {
	public static final String tag = "Missile";
	private ExtPoint acc, vel, pos;
	private Drawable sprite;
	private Paint paint;
	private ExtPoint oldPos;
	
	public Missile(Context context, ExtPoint startPosition, float angle, float power){
		super(context);
		this.paint = new Paint();
		paint.setARGB(255, 0, 255, 0);	//(alpha, red, green, blue)
		paint.setStrokeWidth(5);
		
		pos = startPosition;
		oldPos = startPosition;
		vel = new ExtPoint(power*(float)Math.cos(angle), power*(float)Math.sin(angle));
		Log.i(tag, String.format("angle, power = %f, %f", angle, power));
		//Log.i(tag, vel.toString());
		acc = new ExtPoint(0, -Globals.gravity*Globals.maxY*Globals.SECONDS_PER_FRAME);
		sprite = this.getResources().getDrawable(R.drawable.world);
	}
	
	private boolean inBounds(){
		return 0<pos.x && pos.x<Globals.maxX &&
			   0<pos.y && pos.y<Globals.maxY;
	}
	
	/**
	 * moves the missile to the next location that it should be on.
	 * @return whether or not the missile still is within the bounds
	 */
	public boolean step(){
		//don't even want to do the math if we're off screen, just for now
		//TODO: handle offscreen points
		oldPos = pos;
		pos = pos.plus(vel);
		vel = vel.plus(acc);
		//int tempx = (int)pos.x;
		//int tempy = Globals.maxY - (int)pos.y;
		//this.sprite.setBounds(tempx, tempy, tempx+50, tempy+50);
		Log.i(tag, pos.toString());
		return inBounds();
	}
	
	/**
	 * Traces the path of the missile and gives a list of points of the pixels
	 * in the path of the missile.
	 * TODO
	 */
	public void Trace(Canvas canvas){
		//maxX*2 is a value chosen so that most shots will have less points than that.
		ArrayList<PointF> points = new ArrayList<PointF>(Globals.maxX*2);
		while (this.inBounds()){
			//no point in checking the same spot twice
			if (points.isEmpty() || points.get(points.size()-1)
			== pos) continue;
			points.add(pos);
			this.step();
			}
		for (int i=0; i<points.size(); i++){
			int tempx=(int) points.get(i).x;
			int tempy=(int) points.get(i).y;
			this.sprite.setBounds(tempx, tempy, tempx+50, tempy+50);
			this.sprite.draw(canvas);
		}
	}
	
	/**
	 * draws the sprite
	 */
	public void draw(Canvas canvas){
		if (inBounds()) canvas.drawLine(oldPos.x, oldPos.y, pos.x, pos.y, paint);
	}
}
