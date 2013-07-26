package edu.ucsb.umail.sbluen.ballistics;

import main.src.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
	
	public static final String tag = "Main";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button play = (Button)findViewById(R.id.play);
        final Button help = (Button)findViewById(R.id.help);
        final Button exit = (Button)findViewById(R.id.exit);
        
        play.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Log.i(tag, "play clicked");
        		startActivityForResult(new Intent(v.getContext(), Game.class), 0);
        	}
        });
        
        help.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Log.i(tag, "help clicked");
        		startActivityForResult(new Intent(v.getContext(), Help.class), 0);
        	}
        });
        
        exit.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Log.i(tag, "exit clicked");
        		finish();
        	}
        });
    }
}