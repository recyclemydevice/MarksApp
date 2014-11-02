package com.example.marksapp;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RandomNumber extends Activity implements OnClickListener {

	Button btnanswer;
	TextView number1;
	TextView number2;
	TextView correctanswers;
	TextView incorrectanswers;
	TextView totalquestions;
	TextView questionnumber;
	EditText answer;
	Chronometer timer;
	TextView levelText;
	
	int calculatedAnswer=0;
	int givenAnswer=0;
	int correctScore=0;
	int incorrectScore=0;
    int totalQuestions=0;
    int currentQuestion=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_random_number);

		btnanswer = (Button)findViewById(R.id.checkanswer);
		 number1 = (TextView)findViewById(R.id.number1);
		 number2 = (TextView)findViewById(R.id.number2);
		 correctanswers = (TextView)findViewById(R.id.correctanswers);
		 incorrectanswers = (TextView)findViewById(R.id.incorrectanswers);
		 questionnumber = (TextView)findViewById(R.id.questionnumber);
		 totalquestions = (TextView)findViewById(R.id.totalquestions);
		 answer =  (EditText)findViewById(R.id.answer);
		 timer = (Chronometer) findViewById(R.id.chronometer1);
		 levelText = (TextView)findViewById(R.id.level);

		 determineLevel();
		 
		 totalquestions.setText(String.valueOf(totalQuestions));
		 
		 Intent intent = getIntent();
		
		 levelText.setText(String.valueOf("Level: " + intent.getExtras().getString("level")));
		 
		
		 
		 btnanswer.setOnClickListener(this);
	}
	
public void determineLevel() {
		
		Intent intent = getIntent();
		String level = intent.getExtras().getString("level");
		
		int maxNumber = 10;

		if (level.contains("easy"))
		{
			totalQuestions = 10;
			maxNumber = 10;
		}
		if (level.contains("medium"))
		{
			totalQuestions = 15;
			maxNumber = 20;
		}
		if (level.contains("hard"))
		{
			totalQuestions = 20;
			maxNumber = 30;
		}

		 number1.setText(String.valueOf(returnGen(maxNumber,1)));
		 number2.setText(String.valueOf(returnGen(maxNumber,1)));
		 
		 answer.setText("");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.random_number, menu);
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
	
	
	
	
	public int returnGen(int maxNumber, int minNumber) {
		int random = (int )(Math.random() * maxNumber + minNumber);
		return random;
	}
	
	public void onClick(View v) {

		if (Integer.parseInt(questionnumber.getText().toString()) == 1)
		{
			timer.setBase(SystemClock.elapsedRealtime());
			timer.start();
		}
			
					
		switch(v.getId()) {
			case R.id.checkanswer:
	
				calculatedAnswer= Integer.parseInt(number1.getText().toString()) + Integer.parseInt(number2.getText().toString());
				givenAnswer=Integer.parseInt(answer.getText().toString());

				if (calculatedAnswer == givenAnswer)
				{
					Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();

					correctanswers.setText(String.valueOf(correctScore+=1));
		
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Incorrect, answer is " + calculatedAnswer, Toast.LENGTH_SHORT).show();
	
					incorrectanswers.setText(String.valueOf(incorrectScore+=1));
				}
				
				break;
			}
		
		determineLevel();
		
		questionnumber.setText(String.valueOf(currentQuestion+=1));
		
		if (Integer.parseInt(questionnumber.getText().toString()) == totalQuestions + 1)
		{
			questionnumber.setText(String.valueOf(currentQuestion-=1));
			
			Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
			
			timer.stop();
			
			String[] results = new String[]{correctanswers.getText().toString(), 
					incorrectanswers.getText().toString(), 
					timer.getText().toString(),totalquestions.getText().toString() };

			Intent i = new Intent(this, Results.class);
			i.putExtra("Results",results );
			
			startActivity(i);
		}

	}
}
