package com.excer.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.view.View;

public class NestedLayoutsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nested_layouts);
	}
	
	public void showInitialScreen(View clickedButton){
		ActivityUtils.gotoActivity(this, LayoutsInitialActivity.class);
		
	}
	
	public void showLayoutWeights(View clickedButton){
		Intent newIntent = new Intent(this, LayoutWeightsActivity.class);
		this.startActivity(newIntent);
		
//		ActivityUtils.gotoActivity(this, LayoutWeightsActivity.class);
	}

	

}
