package com.example.judekayode.homeautomation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by Judekayode on 1/6/2016.
 */
public class Session {
    SharedPreferences pref;

    Editor editor;

    Context _context;

    int PRIVATE_MODE;

    private static final String PREF_NAME = "PostHttp";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "username";

    public static final String KEY_EMAIL = "email";


    public Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

   /*Create login Session*/
    public void createloginSession(String username, String email)
    {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, username);

        editor.putString(KEY_EMAIL, email);

        editor.commit();
    }


    /*Check login method if user is logged in*/
    public void checkLogin()
    {
        if(!this.isLoggedIn()){
            //user is not logged in
            Intent i = new Intent(_context,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

        }

    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }

    /*Logout */

    public void logoutUser()
    {
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);

    }

    public boolean isLoggedIn()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }

}
