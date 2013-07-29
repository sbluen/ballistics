package edu.ucsb.umail.sbluen.ballistics;

import main.src.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DisplayArea extends View {

	public static final String tag = "DisplayArea";
	
     /* Our Missile together with the location it will be painted*/
     protected Missile missile;

     public DisplayArea(Context context) {
          super(context);
          // Set the background
          this.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.android));
     }

     @Override
     protected void onDraw(Canvas canvas) {
    	 if (missile != null && missile.step()){
    		 missile.draw(canvas);
    	 }
     }
     
     public void fire(double angle, double power){
    	 Log.i(tag, "angle: "+String.valueOf(angle)+"\npower: "+String.valueOf(power));
    	 missile = new Missile(this.getContext(), new DPoint(50, 200), angle, power);
     }
     
     protected void onSizeChanged  (int w, int h, int oldw, int oldh){
    	 if (w!=0&&h!=0){
             Globals.maxX=w;
             Globals.maxY=h;
    	 }
         Log.i(tag, String.format("width: %s, height: %s", this.getWidth(),
         		this.getHeight()));
     }
     
     //TODO: make this the method that determines firing parameters
     public boolean onTouchEvent(MotionEvent e){
    	 return super.onTouchEvent(e);
     }
}