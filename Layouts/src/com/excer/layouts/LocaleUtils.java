package com.excer.layouts;

import java.util.Locale;

import android.content.Context;
import android.content.res.Configuration;

/*
 * Class to change the locale. 
 */

public class LocaleUtils {

	/*
	 * sets the locale to given String. 
	 */
	public static void setLocale(Context context, String localeName){
		setLocale(context, new Locale(localeName));

	}

	/*
	 * Sets the locale to the given locale object
	 */
	private static void setLocale(Context context, Locale locale) {
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		context.getResources().updateConfiguration(config, null);

	}
}
