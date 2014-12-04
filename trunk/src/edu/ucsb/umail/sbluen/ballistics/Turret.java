package edu.ucsb.umail.sbluen.ballistics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Turret {
	/**
	 * The location of this turret
	 */
	ExtPoint p;
	/**
	 * The angle this turret is facing, in radians.
	 * Boxed so that it can be initialized to null before an angle is set.
	 * Starts at null.
	 * This field is public because it is designed to be set and read from
	 * associated classes. 
	 */
	public Integer angle;
	public Turret(ExtPoint p){
		this.p = p;
	}
	public void draw(Canvas canvas){
		RectF rect = new RectF(p.x-Globals.maxX/50, p.y-Globals.maxY/50, p.x+Globals.maxX/50, p.y+Globals.maxY/50);
		Paint paint = new Paint();
		paint.setStrokeWidth(1);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLUE);
		canvas.drawArc(rect, 0, 180, true, paint);
		if (angle!=null){
			canvas.drawLine(p.x, p.y, (float)(p.x+Math.cos(angle)), (float)(p.y+Math.sin(angle)), paint);
		}
	}
}
