package com.example.evoca.evocaforandroidlearning.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {
    public static final String PREFS_LOGIN_USERNAME_KEY = "__USERNAME__" ;
    public static final String PREFS_LOGIN_PASSWORD_KEY = "__PASSWORD__" ;
    public static String KEY = "SESSION";

    public static boolean isAuthanticated = false;

    /**
     * Called to save supplied value in shared preferences against given key.
     * @param context Context of caller activity
     * @param key Key of value to save against
     * @param value Value to save
     */
    public static void saveToPrefs(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, Activity.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }
//
    public static void removeToPrefs(Context context,String key, String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, Activity.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * Called to retrieve required value from shared preferences, identified by given key.
     * Default value will be returned of no value found or error occurred.
     * @param context Context of caller activity
     * @param key Key to find value against
     * @param defaultValue Value to return if no data found against given key
     * @return Return the value found against given key, default if not found or any error occurs
     */
    public static String getFromPrefs(Context context, String key, String defaultValue) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(KEY, Activity.MODE_PRIVATE);
        try {
            return sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }
}
