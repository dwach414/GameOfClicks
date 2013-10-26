package com.example.clickingthrones;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity{
	int gold;
	int lumber;
	int stone;
	int mana;
	int food;
	Display display;
	int width;
	int height;
	int color;
	int color2;
	int x = 400;
	int y = 350;
	boolean direction = false;
	boolean twoButtonsBoolean;
	public static final String PREFS_NAME = "ResourcesCountPreferences";
	String itemSelected;
	int clickCount;
	String itemSelected2;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//LayoutInflater infl = getLayoutInflater();

		
		
		display = getWindowManager().getDefaultDisplay();
		Point p = new Point();
		display.getSize(p);
		
		width = p.x;
		height = p.y;
		
		SharedPreferences resourceCount = getSharedPreferences(PREFS_NAME, 0);
		gold = resourceCount.getInt("gold", 0);
		lumber = resourceCount.getInt("lumber", 0);
		stone = resourceCount.getInt("stone", 0);
		food = resourceCount.getInt("food", 0);
		mana = resourceCount.getInt("mana", 0);
		twoButtonsBoolean = resourceCount.getBoolean("twoButtonsBoolean", false);
		
		if(twoButtonsBoolean)
			setContentView(R.layout.activity_main_twobuttons);
		else
			setContentView(R.layout.activity_main);
	
		
		
		
		final TextView tv = (TextView) findViewById(R.id.animation_text);
		tv.setVisibility(View.INVISIBLE);
		
		refreshCount();
		
		
		Spinner resourceSpinner = (Spinner) findViewById(R.id.clickerSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.resouces_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resourceSpinner.setAdapter(adapter);
		
		
		
		final Handler mClockHandler = new Handler();
		Runnable mUpdateClockTask = new Runnable() {
			   public void run() {
			    
				   refreshClicks();
			       mClockHandler.postDelayed(this, 1000);
			   }
			};
			//
		
		mClockHandler.post(mUpdateClockTask);
		resourceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch (arg2) {
				case 0:
					itemSelected = "food";
					color = 0xFFFF0000;
					break;
				case 1:
					itemSelected = "lumber";
					color = 0xFF996633;
					break;
				case 2:
					itemSelected = "stone";
					color = 0xFF999999;
					break;
				case 3:
					itemSelected = "gold";
					color = 0xFFffd700;
					break;
				case 4:
					itemSelected = "mana";
					color = 0xFF0000FF;
					break;
				default:
					break;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				itemSelected = "food";
				
			}
			
		});
		
		
		if(twoButtonsBoolean){
		
		Spinner resourceSpinner2 = (Spinner) findViewById(R.id.clickerSpinner2);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.resouces_array, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resourceSpinner2.setAdapter(adapter2);
		
		resourceSpinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch (arg2) {
				case 0:
					itemSelected2 = "food";
					color2 = 0xFFFF0000;
					break;
				case 1:
					itemSelected2 = "lumber";
					color2 = 0xFF996633;
					break;
				case 2:
					itemSelected2 = "stone";
					color2 = 0xFF999999;
					break;
				case 3:
					itemSelected2 = "gold";
					color2 = 0xFFffd700;
					break;
				case 4:
					itemSelected2 = "mana";
					color2 = 0xFF0000FF;
					break;
				default:
					break;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				itemSelected2 = "food";
				
			}
			
		});
		}
		
		final Activity act = this;
		
	
		final FrameLayout frame;
		if(twoButtonsBoolean){
			frame = (FrameLayout) findViewById(R.id.container2);
		}
		else{
			frame= (FrameLayout) findViewById(R.id.container);
		}
		
		Button clicker = (Button) findViewById(R.id.clickerButton);
		clicker.setOnClickListener(new OnClickListener() {
				
			
			@Override
			public void onClick(View v) {
				++clickCount; 
				
				v.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						x = (int) event.getX();
						y = (int) event.getY();
						return false;
					}
				});
				
				int increment = (int) (Math.random()*125);
				if(increment >= 0 && increment < 75)
					increment = 1;
				else if(increment >= 75 && increment < 100)
					increment = 2;
				else if(increment >= 100 && increment < 110)
					increment = 3;
				else if (increment >= 100 && increment < 120)
					increment = 4;
				else 
					increment = 5;
				
				TextView tv2 = new TextView(act);
				tv2.setTextAppearance(act, android.R.style.TextAppearance_DeviceDefault_Large);
				tv2.setText("+"+ increment);
				tv2.setTextColor(color);
				tv2.setTextSize(increment*4 + 20);
				
				if(increment == 5)
					tv2.setText("+"+ increment + "!");
				else
					tv2.setText("+" + increment);
				frame.addView(tv2);
				
				int xfinal = (int) (Math.random() * (width-200));
				

				Animation translate = new TranslateAnimation(Animation.ABSOLUTE, x, Animation.ABSOLUTE, xfinal, Animation.ABSOLUTE, y+150, Animation.ABSOLUTE, 0 );	
				translate.setInterpolator(act, android.R.interpolator.accelerate_cubic);
				translate.setDuration(500);
				tv2.startAnimation(translate);
				tv2.setVisibility(View.INVISIBLE);
				
				
				if(itemSelected.equals("food"))
					food += increment;
				else if(itemSelected.equals("stone"))
					stone += increment;
				else if(itemSelected.equals("lumber"))
					lumber += increment;
				else if(itemSelected.equals("gold"))
					gold += increment;
				else if(itemSelected.equals("mana"))
					mana += increment;
				
				refreshCount();
				
			}
		});
		
		if(twoButtonsBoolean){
			Button clicker2 = (Button) findViewById(R.id.clickerButton2);
			
			clicker2.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					++clickCount; 
					
					v.setOnTouchListener(new OnTouchListener() {
						
						@Override
						public boolean onTouch(View v, MotionEvent event) {
							x = (int) event.getX();
							y = (int) event.getY();
							return false;
						}
					});
					
					int increment = (int) (Math.random()*125);

					if(increment >= 0 && increment < 75)
						increment = 1;
					else if(increment >= 75 && increment < 100)
						increment = 2;
					else if(increment >= 100 && increment < 110)
						increment = 3;
					else if (increment >= 100 && increment < 120)
						increment = 4;
					else 
						increment = 5;
					
					TextView tv2 = new TextView(act);
					tv2.setTextAppearance(act, android.R.style.TextAppearance_DeviceDefault_Large);
					
					if(increment == 5)
						tv2.setText("+"+ increment + "!");
					else
						tv2.setText("+" + increment);
					
					tv2.setTextSize(increment*4 + 20);
					tv2.setTextColor(color2);
					frame.addView(tv2);
					int xfinal = (int) (Math.random() * (width-200));
					

					Animation translate = new TranslateAnimation(Animation.ABSOLUTE, x+width/2, Animation.ABSOLUTE, xfinal, Animation.ABSOLUTE, y+150, Animation.ABSOLUTE, 0 );	
					translate.setInterpolator(act, android.R.interpolator.accelerate_cubic);
					translate.setDuration(500);
					tv2.startAnimation(translate);
					tv2.setVisibility(View.INVISIBLE);
					
					
					if(itemSelected2.equals("food"))
						food += increment;
					else if(itemSelected2.equals("stone"))
						stone += increment;
					else if(itemSelected2.equals("lumber"))
						lumber += increment;
					else if(itemSelected2.equals("gold"))
						gold += increment;
					else if(itemSelected2.equals("mana"))
						mana += increment;
					
					refreshCount();
					
				}
			});
			

		}
		
		Button b = (Button) findViewById(R.id.upgradeButton);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(act, UpgradeActivity.class);
				Activity activity = act;
				activity.startActivity(intent);
				activity.finish();
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		SharedPreferences resourceCount = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = resourceCount.edit();
		editor.putInt("gold", gold);
		editor.putInt("food", food);
		editor.putInt("lumber", lumber);
		editor.putInt("stone", stone);
		editor.putInt("mana", mana);
		editor.putBoolean("twoButtonsBoolean", twoButtonsBoolean);
		editor.commit();
		super.onPause();
	}
	
	private void refreshCount() {
		TextView view = (TextView) findViewById(R.id.goldcount);
		view.setText("" + gold);
		
		view = (TextView) findViewById(R.id.lumbercount);
		view.setText("" + lumber);
		
		view = (TextView) findViewById(R.id.stonecount);
		view.setText("" + stone);
		
		view = (TextView) findViewById(R.id.manacount);
		view.setText("" + mana);
		
		view = (TextView) findViewById(R.id.foodcount);
		view.setText("" + food);
	}
	
	@Override
	protected void onResume() {
		SharedPreferences resourceCount = getSharedPreferences(PREFS_NAME, 0);
		gold = resourceCount.getInt("gold", 0);
		lumber = resourceCount.getInt("lumber", 0);
		stone = resourceCount.getInt("stone", 0);
		food = resourceCount.getInt("food", 0);
		mana = resourceCount.getInt("mana", 0);
		twoButtonsBoolean = resourceCount.getBoolean("twoButtonsBoolean", false);
		
		super.onResume();
	}
	
public void	refreshClicks(){
	TextView v = (TextView) findViewById(R.id.clickCounter);
	v.setText("Clicks Per Second:" + clickCount);
	clickCount = 0;
		
	}


	


}
