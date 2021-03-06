package com.example.marksapp;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        Button b = (Button)findViewById(R.id.btneasy);
        b.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Goes to next activity
    public void toNextActivity(String level) {
    	
    	RadioButton radioAddition;
    	RadioButton radioSubtract;
    	radioAddition = (RadioButton)findViewById(R.id.radioaddition);
    	radioSubtract = (RadioButton)findViewById(R.id.radiosubtract);
    	
    	boolean add = true;//radioAddition.isChecked();
    	boolean subtract = radioAddition.isChecked();

    	// Go to next activity
    	Intent i = new Intent(this, RandomNumber.class);
    	i.putExtra("level",level );
    	
    	if (add)
    	{    	
    		i.putExtra("type", "add");
    	}
    	else if (subtract)
    	{    	
    		i.putExtra("type", "subtract");
    	}

    	startActivity(i);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btneasy:
			toNextActivity("easy");
			break;
		case R.id.btnmedium:
			toNextActivity("medium");
			break;
		case R.id.btnhard:
			toNextActivity("hard");
			break;
		case R.id.btndbtest:
			// Go to next activity
			Intent i = new Intent(this, Comments.class);
	    	startActivity(i);
			break;
		}
		
	}
}
