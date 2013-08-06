package edu.ucsb.umail.sbluen.ballistics;

import edu.ucsb.umail.sbluen.ballistics.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Game extends Activity {
	
	public static final String tag = "Game";
	//unique identifier
    protected static final int GUIUPDATEIDENTIFIER = 0x101;
    Thread myRefreshThread = null;
    
    //This goes against certain coding standards, but these objects cannot be declared
    //final because the values in R are null until the layout is used.
    DisplayArea displayarea;
    EditText angleedittext;
    EditText poweredittext;
    
    Handler myGUIUpdateHandler = new Handler() {

        // @Override
        public void handleMessage(Message msg) {
             switch (msg.what) {
                  case Game.GUIUPDATEIDENTIFIER:
                       /* Repaint the BounceView
                        * (where the ball is in) */
                       displayarea.invalidate();
                       break;
             }
             super.handleMessage(msg);
        }
   };
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
//        RelativeLayout playarealayout = 
//        	(RelativeLayout) findViewById(R.id.playarealayout);
        final LinearLayout displayarealayout = 
        	(LinearLayout) findViewById(R.id.displayarealayout);
        displayarea = new DisplayArea(this);
        
//        //need to set the displayarea's height manually here because it must depend on
//        //the playarealayout's height so that it doesn't ever overlap with the buttons
//        displayarea.setMinimumHeight(playarealayout.getHeight() - 50);
        
//        displayarea.setWidth("fill_parent");
//        displayarea.setHeight("fill_parent");
        
        displayarealayout.addView(displayarea);
        
        final Button gametomain = (Button)findViewById(R.id.gametomain);
        gametomain.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Log.i(tag, "gametomain clicked");
        		finish();
        	}
        });
        
        final Button firebutton = (Button)findViewById(R.id.firebutton);
        angleedittext = (EditText)findViewById(R.id.angleedittext);
        angleedittext.setText("65");
        poweredittext = (EditText)findViewById(R.id.poweredittext);
        poweredittext.setText("0");
        firebutton.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Log.i(tag, "firebutton clicked");
        		displayarea.fire(
            		(float) Math.toRadians(Integer.parseInt(
            					String.valueOf(angleedittext.getText())
            		)),
        			Integer.parseInt(String.valueOf(poweredittext.getText()))
        		);
        	}
        });
        
        Globals.pos1 = new ExtPoint(50, 200);
        displayarea.fire(30, 30);
        
        new Thread(new RefreshRunner()).start();
        
    }
    
    class RefreshRunner implements Runnable {
        // @Override
        public void run() {
             while (! Thread.currentThread().isInterrupted()) {
                  // Send Message to the Handler which will call the invalidate() method of the BoucneView
                  Message message = new Message();
                  message.what = Game.GUIUPDATEIDENTIFIER;
                  Game.this.myGUIUpdateHandler.sendMessage(message);

                  try {
                       Thread.sleep((long) (Globals.SECONDS_PER_FRAME*Globals.MILLISECONDS_PER_SECOND));
                  } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                  }
             }
        }
   }
}
