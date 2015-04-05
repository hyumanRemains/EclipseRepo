package com.excer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class InitialActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_main);
		
	}
	
	
	public void switchToNistTimeActivity(View clickedButton){
		ActivityUtils.goToActivity(this, NistTimeActivity.class);
	}
	
	
	public void switchToFtpMessageViewer(View clickedButton){
		ActivityUtils.goToActivity(this, FtpMessageActivity.class);
	}
	
	public void switchToUrlChecker(View clickedButton){
		ActivityUtils.goToActivity(this, UrlCheckerActivity.class);
	}
	
	public void switchToUrlSearcher1(View clickedButton){
		ActivityUtils.goToActivity(this, UrlSearcher1Activity.class);
	}
	
	
	
}
