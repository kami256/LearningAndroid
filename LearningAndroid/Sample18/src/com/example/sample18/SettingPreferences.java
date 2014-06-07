package com.example.sample18;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingPreferences extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.preferences);
	}
}
