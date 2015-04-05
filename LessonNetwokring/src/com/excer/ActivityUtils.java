package com.excer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/*
 * Switching between Activities
 * Register all new Activities in the Manifest.
 */
public class ActivityUtils {

	public static void goToActivity(Context currentActivity, 
			Class<? extends Activity> newClass){

		Intent newActivity = new Intent(currentActivity, newClass);
		currentActivity.startActivity(newActivity);
	}

}
