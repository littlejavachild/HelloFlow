package com.example.helloflow;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

public final class PrefUtils {
    // We WeakReference the context to avoid memory leaks
    private static WeakReference<Context> context;
    public static final String SHARED_PREF_NAME = "shared_preferences";
    //----------------------------------------------------------------------------------------------
    public static void init(Context con){
        context = new WeakReference<Context>(con);
    }
    //----------------------------------------------------------------------------------------------
    public static void putBoolean(String key,boolean value){
        Context con = context.get();
        if( con == null ) return;
        SharedPreferences sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    //----------------------------------------------------------------------------------------------
    public static boolean getBoolean(String key, boolean defVal){
        Context con = context.get();
        if( con == null ) return defVal;
        SharedPreferences sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME,0);
        return sharedPreferences.getBoolean(key,defVal);
    }
    //----------------------------------------------------------------------------------------------
    public static String getString(String key,String defVal){
        Context con = context.get();
        if( con == null ) return defVal;
        SharedPreferences sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME,0);
        return sharedPreferences.getString(key, defVal);
    }
    //----------------------------------------------------------------------------------------------
    public static void putString(String key, String value){
        Context con = context.get();
        if( con == null ) return;
        SharedPreferences sharedPreferences = con.getSharedPreferences(SHARED_PREF_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    //----------------------------------------------------------------------------------------------
    public static final class Key{
        public static final String IS_USER_LOGGED_IN = "is_user_logged_in";
        public static final String USER_LOG_IN_METHOD = "user_login_method";
    }
    //----------------------------------------------------------------------------------------------
    public static final class Values{
        public static final String LOG_IN_METHOD_FACEBOOK = "facebook";
        public static final String LOG_IN_METHOD_GOOGLE = "google";
    }
}

