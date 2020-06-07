package com.example.JFood_Android;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SESSION_APP_KEY = "JFood_session";
    public static final String NAME_KEY = "nameKey";
    public static final String EMAIL_KEY = "emailKey";
    public static final String ID_KEY = "idKey";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    public SharedPreferences sp;
    public SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SESSION_APP_KEY, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(NAME_KEY, "");
    }

    public String getSPEmail(){
        return sp.getString(EMAIL_KEY, "");
    }

    public String getSPId() {
        return sp.getString(ID_KEY, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
