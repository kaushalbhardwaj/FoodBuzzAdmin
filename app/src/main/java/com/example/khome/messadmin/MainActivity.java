package com.example.khome.messadmin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;
    public static final String MyPREFERENCES = "AdminInfo" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences wmbPreference1 = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun1 = wmbPreference1.getBoolean("FIRSTRUN", true);
        if (isFirstRun1)
        {
            SharedPreferencePing.putSharedPreferInfoFirst(getApplicationContext());
            SharedPreferenceFav.putSharedPreferInfoFirst(getApplicationContext());

            SharedPreferences.Editor editor = wmbPreference1.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {


                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String s=SharedPreference.getSharedPreferInfo(getApplicationContext(), "uid");


                if(s==null)
                {
                    Intent i = new Intent(getApplicationContext(), login.class);
                    startActivity(i);
                    finish();


                }

                else
                {
                    Intent i = new Intent(getApplicationContext(), MainPage.class);
                    startActivity(i);
                    finish();


                }

               
            }
        }, SPLASH_TIME_OUT);



    }
}
