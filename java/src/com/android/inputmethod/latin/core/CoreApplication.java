package com.android.inputmethod.latin.core;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.android.material.color.DynamicColors;
import androidx.appcompat.app.AppCompatDelegate;

public class CoreApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
		DynamicColors.applyToActivitiesIfAvailable(this);
		
	}

}
