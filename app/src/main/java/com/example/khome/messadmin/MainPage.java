package com.example.khome.messadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.Map;

public class MainPage extends AppCompatActivity {
    private Toolbar mToolbar;
    TextView bv,lv,sv,dv;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bv=(TextView)findViewById(R.id.breakfastvalue);
        lv=(TextView)findViewById(R.id.lunchvalue);
        sv=(TextView)findViewById(R.id.snacksvalue);
        dv=(TextView)findViewById(R.id.dinnervalue);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cladmin);


        mToolbar = (Toolbar) findViewById(R.id.toolbar_day_after);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MessAdmin");

        bv.setText(SharedPreferencePing.getSharedPreferInfo(getApplicationContext(), "pm1"));
        lv.setText(SharedPreferencePing.getSharedPreferInfo(getApplicationContext(),"pm2"));
        sv.setText(SharedPreferencePing.getSharedPreferInfo(getApplicationContext(),"pm3"));
        dv.setText(SharedPreferencePing.getSharedPreferInfo(getApplicationContext(), "pm4"));


        if (NetworkTool.isConnectingToInternet(MainPage.this)) {
            //Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
            updatePing();

        } else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection! Showing old data", Snackbar.LENGTH_LONG);
            //snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
            //showDialogBox();
        }




    }
    public void updatePing()
    {


        final ProgressDialog ringProgressDialog = ProgressDialog.show(MainPage.this, "Please wait ...", "Updating Data....", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);

        new Thread(new Runnable() {
            public void run() {
                try {

                    Calendar c2=Calendar.getInstance();
                    int date=c2.get(Calendar.DAY_OF_MONTH);
                    final int d5=date;

                    final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/ping/date"+date);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {


                            Map<String, Object> newUser = (Map<String, Object>) snapshot.getValue();
                            PingInfo user2 = new PingInfo();
                            Object o = newUser.get("m1");
                            String s = o.toString();
                            user2.setM1(s);
                            o = newUser.get("m2");
                            s = o.toString();
                            user2.setM2(s);
                            o = newUser.get("m3");
                            s = o.toString();
                            user2.setM3(s);
                            o = newUser.get("m4");
                            s = o.toString();
                            user2.setM4(s);


                            System.out.println("Author: " + user2.getM1() + user2.getM2() + user2.getM3());


                            bv.setText(user2.getM1());
                            lv.setText(user2.getM2());
                            sv.setText(user2.getM3());
                            dv.setText(user2.getM4());

                            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(), "pm1", user2.getM1());
                            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(), "pm2", user2.getM2());
                            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(), "pm3", user2.getM3());
                            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(), "pm4", user2.getM4());


                            System.out.println("There are " + snapshot.getChildrenCount() + " blog posts" + "value==" + snapshot.getValue());

                            ringProgressDialog.dismiss();
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Data Updated", Snackbar.LENGTH_LONG);
                            //snackbar.setActionTextColor(Color.YELLOW);
                            snackbar.show();

                        }


                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            System.out.println("The read failed: " + firebaseError.getMessage());
                           // ringProgressDialog.dismiss();
                        }


                    });
                    //ringProgressDialog.dismiss();

                    // Thread.sleep(10000);
                        } catch (Exception e) {
                        }
                         //ringProgressDialog.dismiss();
                    }
                }).start();






    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.likedislikeweek1:

                Intent i=new Intent(getApplicationContext(),LDWeek1.class);
                startActivity(i);
                finish();
                return true;
            case R.id.likedislikeweek2:

                Intent i2=new Intent(getApplicationContext(),LDWeek2.class);
                startActivity(i2);
                finish();
                return true;
            case R.id.ping:
                return true;
            case R.id.logout:

                SharedPreference.putSharedPreferInfo(getApplicationContext(),"uid",null);
                SharedPreference.putSharedPreferInfo(getApplicationContext(),"password",null);
                Intent i3=new Intent(getApplicationContext(),login.class);
                startActivity(i3);
                finish();
                return true;

            case R.id.refresh:

                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                return true;




            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
