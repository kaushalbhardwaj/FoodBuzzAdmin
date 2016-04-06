package com.example.khome.messadmin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by khome on 3/2/16.
 */
public class SharedPreference {

    public static final String MyPREFERENCES = "AdminInfo" ;
    public static SharedPreferences sharedpreferences;
    public static boolean putSharedPreferInfo(Context con,String s1,String s2)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(s1,s2);
        editor.commit();
        return true;

    }
    public static String getSharedPreferInfo(Context con,String s1)
    {

        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String s= sharedpreferences.getString(s1,null);
        return s;
    }


}
