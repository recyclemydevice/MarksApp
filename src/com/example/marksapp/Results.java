package com.example.marksapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends Activity implements OnClickListener {

	TextView correctanswers;
	TextView totalquestions;
	TextView incorrectanswers;
	TextView timetaken;
	
	Button btntryagain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_results);
		
		btntryagain = (Button)findViewById(R.id.btntryagain);
		
		totalquestions = (TextView)findViewById(R.id.totalquestions);
		correctanswers = (TextView)findViewById(R.id.correctanswers);
		incorrectanswers = (TextView)findViewById(R.id.incorrectanswers);
		timetaken = (TextView)findViewById(R.id.timetaken);
		
		Intent intent = getIntent();
		String[] results = intent.getStringArrayExtra("Results");
		
		correctanswers.setText(results[0]);
		incorrectanswers.setText(results[1]);
		timetaken.setText(results[2]);
		totalquestions.setText(results[3]);

		btntryagain.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
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
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btntryagain:
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);

			break;
		}
	}
	
	
}
