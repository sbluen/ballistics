package edu.ucsb.umail.sbluen.ballistics;

import main.src.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Help extends Activity {

	public static final String tag = "Help";
	Intent intent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        
        final Button helptomain = (Button)findViewById(R.id.helptomain);
        helptomain.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Log.i(tag, "helptomain clicked");
        		finish();
        		//startActivityForResult(new Intent(v.getContext(), Main.class), 0);
        	}
        });
    }

}
