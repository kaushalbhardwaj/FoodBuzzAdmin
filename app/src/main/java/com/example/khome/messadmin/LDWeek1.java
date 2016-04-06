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

import java.util.ArrayList;

public class LDWeek1 extends AppCompatActivity {


    private Toolbar mToolbar;
    private CoordinatorLayout coordinatorLayout;
    TextView pm111,pm112,pm113,pm114,pm121,pm122,pm123,pm124,pm131,pm132,pm133,pm134,pm141,pm142,pm143,pm144,
            pm151,pm152,pm153,pm154,pm161,pm162,pm163,pm164,pm171,pm172,pm173,pm174;


    public ArrayList<LikeDislike> likedislike=new ArrayList<LikeDislike>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ldweek1);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cladmin);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_day_after);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MessAdmin-Week1");

        getTextViews();

        if (NetworkTool.isConnectingToInternet(LDWeek1.this)) {
            //Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
            updateMessInfo();

        } else {

            setTextView();
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection! Showing old data", Snackbar.LENGTH_LONG);
            //snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
            //showDialogBox();
        }



    }

    public void getTextViews()
    {
        pm111=(TextView)findViewById(R.id.pm111);
        pm112=(TextView)findViewById(R.id.pm112);
        pm113=(TextView)findViewById(R.id.pm113);
        pm114=(TextView)findViewById(R.id.pm114);
        pm121=(TextView)findViewById(R.id.pm121);
        pm122=(TextView)findViewById(R.id.pm122);
        pm123=(TextView)findViewById(R.id.pm123);
        pm124=(TextView)findViewById(R.id.pm124);
        pm131=(TextView)findViewById(R.id.pm131);
        pm132=(TextView)findViewById(R.id.pm132);
        pm133=(TextView)findViewById(R.id.pm133);
        pm134=(TextView)findViewById(R.id.pm134);
        pm141=(TextView)findViewById(R.id.pm141);
        pm142=(TextView)findViewById(R.id.pm142);
        pm143=(TextView)findViewById(R.id.pm143);
        pm144=(TextView)findViewById(R.id.pm144);
        pm151=(TextView)findViewById(R.id.pm151);
        pm152=(TextView)findViewById(R.id.pm152);
        pm153=(TextView)findViewById(R.id.pm153);
        pm154=(TextView)findViewById(R.id.pm154);
        pm161=(TextView)findViewById(R.id.pm161);
        pm162=(TextView)findViewById(R.id.pm162);
        pm163=(TextView)findViewById(R.id.pm163);
        pm164=(TextView)findViewById(R.id.pm164);
        pm171=(TextView)findViewById(R.id.pm171);
        pm172=(TextView)findViewById(R.id.pm172);
        pm173=(TextView)findViewById(R.id.pm173);
        pm174=(TextView)findViewById(R.id.pm174);

    }

    public void setTextView()
    {
        String s11=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm111");
        String s12=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm111");

        String s21=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm112");
        String s22=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm112");

        String s31=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm113");
        String s32=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm113");

        String s41=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm114");
        String s42=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm114");

        String s51=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm121");
        String s52=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm121");
        String s61=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm122");
        String s62=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm122");
        String s71=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm123");
        String s72=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm123");
        String s81=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm124");
        String s82=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm124");

        String s91=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm131");
        String s92=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm131");
        String s101=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm132");
        String s102=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm132");
        String s111=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm133");
        String s112=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm133");
        String s121=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm134");
        String s122=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm134");

        String s131=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm141");
        String s132=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm141");
        String s141=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm142");
        String s142=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm142");
        String s151=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm143");
        String s152=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm143");
        String s161=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm144");
        String s162=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm144");

        String s171=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm151");
        String s172=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm151");
        String s181=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm152");
        String s182=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm152");
        String s191=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm153");
        String s192=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm153");
        String s201=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm154");
        String s202=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm154");

        String s211=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm161");
        String s212=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm161");
        String s221=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm162");
        String s222=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm162");
        String s231=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm163");
        String s232=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm163");
        String s241=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm164");
        String s242=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm164");

        String s251=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm171");
        String s252=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm171");
        String s261=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm172");
        String s262=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm172");
        String s271=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm173");
        String s272=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm173");
        String s281=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm174");
        String s282=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm174");

        pm111.setText(s11+"/"+s12);
        pm112.setText(s21+"/"+s22);
        pm113.setText(s31+"/"+s32);
        pm114.setText(s41+"/"+s42);

        pm121.setText(s51+"/"+s52);
        pm122.setText(s61+"/"+s62);
        pm123.setText(s71+"/"+s72);
        pm124.setText(s81+"/"+s82);

        pm131.setText(s91+"/"+s92);
        pm132.setText(s101+"/"+s102);
        pm133.setText(s111+"/"+s112);
        pm134.setText(s121+"/"+s122);

        pm141.setText(s131+"/"+s132);
        pm142.setText(s141+"/"+s142);
        pm143.setText(s151+"/"+s152);
        pm144.setText(s161+"/"+s162);

        pm151.setText(s171+"/"+s172);
        pm152.setText(s181+"/"+s182);
        pm153.setText(s191+"/"+s192);
        pm154.setText(s201+"/"+s202);

        pm161.setText(s211+"/"+s212);
        pm162.setText(s221+"/"+s222);
        pm163.setText(s231+"/"+s232);
        pm164.setText(s241+"/"+s242);

        pm171.setText(s251+"/"+s252);
        pm172.setText(s261+"/"+s262);
        pm173.setText(s271+"/"+s272);
        pm174.setText(s281+"/"+s282);

        //pm111.setText();


    }
    public void updateMessInfo() {


        final ProgressDialog ringProgressDialog = ProgressDialog.show(LDWeek1.this, "Please wait ...", "Updating Data....", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/likedislike");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            System.out.println("There are " + snapshot.getChildrenCount() + "likedislike");

                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                LikeDislike post = postSnapshot.getValue(LikeDislike.class);
                                System.out.println(post.getL()  + " - " + post.getD());
                                likedislike.add(post);
                            }

                            int z=0;
                            for(int i=1;i<=2;i++)
                            {
                                for(int j=1;j<=7;j++)
                                {

                                    for(int k=1;k<=4;k++)
                                    {
                                        LikeDislike ld= likedislike.get(z);
                                        int l=Integer.parseInt(ld.getL());
                                        int d=Integer.parseInt(ld.getD());



                                        SharedPreferenceFav.putSharedPreferInfo(getApplicationContext(), "fm" + i + "" + j + "" + k,l+"");
                                        SharedPreferenceFav.putSharedPreferInfo(getApplicationContext(), "dm" + i + "" + j + "" + k,d+"");

                                        z++;
                                    }

                                }



                            }
                            setTextView();

                            ringProgressDialog.dismiss();
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Data Updated", Snackbar.LENGTH_LONG);
                            //snackbar.setActionTextColor(Color.YELLOW);
                            snackbar.show();
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            System.out.println("The read failed: " + firebaseError.getMessage());
                            //Toast.makeText(MainPage.this, "Error Menu Cannot Be Updated", Toast.LENGTH_SHORT).show();
                            // ringProgressDialog.dismiss();
                        }
                    });


                    // Thread.sleep(10000);
                } catch (Exception e) {
                }
                // ringProgressDialog.dismiss();
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

                return true;
            case R.id.likedislikeweek2:

                Intent i2=new Intent(getApplicationContext(),LDWeek2.class);
                startActivity(i2);
                finish();
                return true;
            case R.id.ping:
                Intent i=new Intent(getApplicationContext(),MainPage.class);
                startActivity(i);
                finish();
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
