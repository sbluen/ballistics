package edu.ucsb.umail.sbluen.ballistics;

import edu.ucsb.umail.sbluen.ballistics.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DisplayArea extends View {

	public static final String tag = "DisplayArea";
	
     /* Our Missile together with the location it will be painted*/
     private Missile missile;
     private PointF dragStart;
     private PointF dragEnd;
     private Paint dragPaint;

     public DisplayArea(Context context) {
         super(context);
         // Set the background
         this.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.android));
          
         //set paints
         dragPaint = new Paint();
     	 dragPaint.setColor(Color.BLUE);
     }

     @Override
     protected void onDraw(Canvas canvas) {
    	 //draw the missile
    	 if (missile != null && missile.step()){
    		 missile.draw(canvas);
    	 }
    	 
    	 //draw the line representation of angle and power
    	 if (dragStart != null && dragEnd != null){
    		 canvas.drawLine((int)dragStart.x, (int)dragStart.y, (int)dragEnd.x, (int)dragEnd.y, dragPaint);
    	 }
     }
     
     public void fire(float angle, float power){
    	 Log.i(tag, "angle: "+String.valueOf(angle)+"\npower: "+String.valueOf(power));
    	 missile = new Missile(this.getContext(), new ExtPoint(50, 200), angle, power);
     }
     
     protected void onSizeChanged  (int w, int h, int oldw, int oldh){
    	 if (w!=0&&h!=0){
             Globals.maxX=w;
             Globals.maxY=h;
    	 }
         Log.i(tag, String.format("width: %s, height: %s", this.getWidth(),
         		this.getHeight()));
     }
     
     public boolean onTouchEvent(MotionEvent e){
    	 if (e.getAction() == android.view.MotionEvent.ACTION_DOWN){
    		 this.dragStart = new PointF(e.getX(), e.getY());
    		 return true; //consume event
    	 }else if (e.getAction() == android.view.MotionEvent.ACTION_MOVE){
    		 this.dragEnd = new PointF(e.getX(), e.getY());
    		 return true; //consume event
    	 }else if (e.getAction() == android.view.MotionEvent.ACTION_UP){
    		 this.dragEnd = new PointF(e.getX(), e.getY());    		 
    		 fire(Utility.getAngle(dragStart, dragEnd), Utility.getPower(dragStart, dragEnd));
    		 return true; //consume event
    	 }else{
    		 Log.e(tag, "Invalid MotionEvent type");
    		 return false;
    	 }
     }
     
}