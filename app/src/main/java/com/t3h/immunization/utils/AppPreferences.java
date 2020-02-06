package com.t3h.immunization.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

public class AppPreferences {
    private static final String TAG = AppPreferences.class.getSimpleName();

    private static AppPreferences instance = null;

    // variable
    private SharedPreferences appSharedPrefs = null;
    private Editor prefsEditor = null;

    /**
     * getInstance
     *
     * @param context
     */
    public static AppPreferences getInstance(Context context) {
        try {
            if (instance == null) {
                synchronized (AppPreferences.class) {
                    if (instance == null) {
                        instance = new AppPreferences(context);
                    }
                }
            }
        } catch (Exception e) {

        }

        return instance;
    }

    /**
     * @param context
     */
    @SuppressLint("CommitPrefEdits")
    public AppPreferences(Context context) {
        try {
            appSharedPrefs = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            prefsEditor = appSharedPrefs.edit();
        } catch (Exception e) {

        }
    }

    /**
     * put value int
     *
     * @param key
     * @param value
     */
    public AppPreferences putInt(String key, int value) {
        try {
            prefsEditor.putInt(key, value);
            prefsEditor.commit();
        } catch (Exception e) {

        }

        return this;
    }

    /**
     * get value int
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        try {
            return appSharedPrefs.getInt(key, 0);
        } catch (Exception e) {

        }

        return 0;
    }

    public AppPreferences putJson(String key, Object value) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(value);
            prefsEditor.putString(key, json);
            prefsEditor.commit();
        } catch (Exception e) {


        }

        return this;
    }

    /**
     * get value int
     *
     * @param key
     * @return
     */
    public <T> T getJson(String key, Class<T> clazz) {
        try {
            Gson gson = new Gson();
            String json = appSharedPrefs.getString(key, "");
            return gson.fromJson(json, clazz);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * put value long
     *
     * @param key
     * @param value
     */
    public AppPreferences putLong(String key, long value) {
        try {
            prefsEditor.putLong(key, value);
            prefsEditor.commit();
        } catch (Exception e) {

        }

        return this;
    }

    /**
     * get value long
     *
     * @param key
     * @return
     */
    public long getLong(String key) {
        try {
            return appSharedPrefs.getLong(key, 0);
        } catch (Exception e) {

        }

        return 0;
    }

    /**
     * put value float
     *
     * @param key
     * @param value
     */
    public AppPreferences putFloat(String key, float value) {
        try {
            prefsEditor.putFloat(key, value);
            prefsEditor.commit();
        } catch (Exception e) {

        }

        return this;
    }

    /**
     * get value long
     *
     * @param key
     * @return
     */
    public float getFloat(String key) {
        try {
            return appSharedPrefs.getFloat(key, 0f);
        } catch (Exception e) {

        }

        return 0f;
    }

    /**
     * put value boolean
     *
     * @param key
     * @param value
     */
    public AppPreferences putBoolean(String key, boolean value) {
        try {
            prefsEditor.putBoolean(key, value);
            prefsEditor.commit();
        } catch (Exception e) {

        }

        return this;
    }

    /**
     * get value boolean
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        try {
            return appSharedPrefs.getBoolean(key, false);
        } catch (Exception e) {

        }

        return false;
    }

    /**
     * put value String
     *
     * @param key
     * @param value
     */
    public AppPreferences putString(String key, String value) {
        try {
            prefsEditor.putString(key, value);
            prefsEditor.commit();
        } catch (Exception e) {

        }

        return this;
    }


    /**
     * get value String
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        try {
            return appSharedPrefs.getString(key, "");
        } catch (Exception e) {

        }

        return "";
    }

    /**
     * clear all cache in SharedPreference
     */
    public AppPreferences clearCache() {
        try {
            prefsEditor.clear().commit();
        } catch (Exception e) {

        }

        return this;
    }
}

