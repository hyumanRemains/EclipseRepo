package com.excer.layouts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityUtils {

	public static void gotoActivity(Context currentActivity,
			Class<?extends Activity> newClass){
		Intent newActivity = new Intent(currentActivity, newClass);
		currentActivity.startActivity(newActivity);
		
	}

}
