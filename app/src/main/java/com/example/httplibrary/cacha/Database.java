package com.example.httplibrary.cacha;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class Database {

	public static SharedPreferences preferences;

	public static void putString(final Context context, final String key, final String value) {

		if (context == null||value==null) {
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				preferences = context.getSharedPreferences("ads_12306", Context.MODE_PRIVATE);
				Editor editor = preferences.edit();
				editor.putString(key, value);
				Log.i("Database", "putString:::" + key + ":" + value);
				editor.commit();
			}
		}).start();

	}

	public static void putBoolean(Context context, String key, boolean value) {
		if (context == null)
			return;
		preferences = context.getSharedPreferences("ads_12306", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		Log.i("Database","putString:::"+key+"  value:"+value);
		editor.commit();
	}

	public static String getString(Context context, String key) {
		if (context == null)
			return null;
		preferences = context.getSharedPreferences("ads_12306", Context.MODE_PRIVATE);
		String result = preferences.getString(key, "");
		Log.i("Database","getString:::"+key+":"+result);
		return result;
	}

	public static void putInt(Context context, String key, int value) {
		if (context == null)
			return;
		preferences = context.getSharedPreferences("ads_12306", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static int getInt(Context context, String key) {
		if (context == null)
			return 0;
		preferences = context.getSharedPreferences("ads_12306", Context.MODE_PRIVATE);
		return preferences.getInt(key, 0);
	}

	public static boolean getBoolean(Context context, String key) {
		if (context == null)
			return false;
		preferences = context.getSharedPreferences("ads_12306", Context.MODE_PRIVATE);
		return preferences.getBoolean(key, false);
	}
	public static boolean getBoolean(Context context, String key, boolean defulat) {
		if (context == null)
			return false;
		preferences = context.getSharedPreferences("ads_12306", Context.MODE_PRIVATE);
		return preferences.getBoolean(key, defulat);
	}





}
