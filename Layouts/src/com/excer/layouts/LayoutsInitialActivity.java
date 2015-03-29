package com.excer.layouts;


import android.view.View;
import android.app.Activity;
import android.os.Bundle;


public class LayoutsInitialActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	}
	
	public void showNestedLayouts(View clickedButton){
		setEnglishLocale();
		ActivityUtils.gotoActivity(this, NestedLayoutsActivity.class);
		
	}
	
	public void showNestedLayoutsNoColors(View clickedButton){
		setDebugLocale();
		ActivityUtils.gotoActivity(this, NestedLayoutsActivity.class);
		
	}
	
	public void showRelativeLayouts(View clickedButton){
		
		ActivityUtils.gotoActivity(this, RelativeLayoutsActivity.class);
		
	}
	
	public void showLayoutWeights(View clickedButton){
		
		ActivityUtils.gotoActivity(this, LayoutWeightsActivity.class);
	}

	private void setDebugLocale() {
		LocaleUtils.setLocale(this, "es");
		
	}

	private void setEnglishLocale() {
		// TODO Auto-generated method stub
		LocaleUtils.setLocale(this, "en");
		
	}
}
