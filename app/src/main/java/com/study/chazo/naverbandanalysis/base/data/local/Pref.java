package com.study.chazo.naverbandanalysis.base.data.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by Chazo on 2018-07-12
 */

public class Pref {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Pref(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void put(String key, String val) {
        edit();
        editor.putString(key, val);
        commit();
    }

    public void put(String key, int val) {
        edit();
        editor.putInt(key, val);
        commit();
    }

    public void put(String key, long val) {
        edit();
        editor.putLong(key, val);
        commit();
    }

    public void put(String key, boolean val) {
        edit();
        editor.putBoolean(key, val);
        commit();
    }

    public void put(String key, float val) {
        edit();
        editor.putFloat(key, val);
        commit();
    }

    public <T> void put(String key, T obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        put(key, json);
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public <GenericClass> GenericClass getObject(String key, Class<GenericClass> classType) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        if (json == null) return null;
        return gson.fromJson(json, classType);
    }

    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @SuppressLint("CommitPrefEdits")
    private void edit() {
        if (editor == null) {
            editor = sharedPreferences.edit();
        }
    }

    private void commit() {
        if (editor != null) {
            editor.apply();
            editor = null;
        }
    }
}

