package com.example.clickingthrones;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.support.v4.app.NavUtils;

public class UpgradeActivity extends Activity {
	public static final String PREFS_NAME = "ResourcesCountPreferences";
	Boolean twoButtonsBoolean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upgrade_activty);
		// Show the Up button in the action bar.
		setupActionBar();
		
		SharedPreferences resourceCount = getSharedPreferences(PREFS_NAME, 0);
		twoButtonsBoolean = resourceCount.getBoolean("twoButtonsBoolean", false);

		
		
		ToggleButton toggle = (ToggleButton) findViewById(R.id.ToggleButton1);
		
		if(twoButtonsBoolean)
			toggle.setChecked(true);
		else
			toggle.setChecked(false);
		
		toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		            twoButtonsBoolean = true;
		        } else {
		            twoButtonsBoolean = false;
		        }
		    }
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upgrade_activty, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		SharedPreferences resourceCount = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = resourceCount.edit();
		editor.putBoolean("twoButtonsBoolean", twoButtonsBoolean);
		editor.commit();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
		super.onBackPressed();
		
	}

}
