package com.example.khome.messadmin;


        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Color;
        import android.support.design.widget.CoordinatorLayout;
        import android.support.design.widget.Snackbar;
        import android.support.design.widget.TextInputLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.text.Editable;
        import android.text.TextUtils;
        import android.text.TextWatcher;
        import android.text.method.LinkMovementMethod;
        import android.view.View;
        import android.view.WindowManager;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.firebase.client.AuthData;
        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;

        import org.w3c.dom.Text;

        import java.util.Map;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    EditText emaillogin,passwordlogin;
    Button login;
    String uid;
    TextView signup_login,forgot_login;
    private Toolbar mToolbar;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_day_after);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cladmin);

        login=(Button)findViewById(R.id.login);
        emaillogin=(EditText)findViewById(R.id.emaillogin);
        passwordlogin=(EditText)findViewById(R.id.passwordlogin);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        emaillogin.addTextChangedListener(new MyTextWatcher(emaillogin));
        passwordlogin.addTextChangedListener(new MyTextWatcher(passwordlogin));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                //boolean valid = true;
                boolean valid = submitForm();
                if (valid) {

                    //Toast.makeText(getApplicationContext(), "okk login ", Toast.LENGTH_SHORT).show();
                    if (NetworkTool.isConnectingToInternet(login.this)) {
                        //Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                        loginFirebase();
                    } else {
                        Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection!", Snackbar.LENGTH_LONG);
                        //snackbar.setActionTextColor(Color.YELLOW);
                        snackbar.show();
                        //showDialogBox();
                    }
                }

            }
        });


    }
    private boolean submitForm() {

        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }
        return true;
    }
    public void loginFirebase()
    {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(login.this, "Please wait ...", "Connecting....", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    String em=emaillogin.getText().toString();
                    final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");
                    final String pass=passwordlogin.getText().toString();
                    ref.authWithPassword(em, pass, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            //System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                            //Toast.makeText(getApplicationContext(),"Successfully logged in with uid: " + authData.getUid(),Toast.LENGTH_LONG).show();
                            uid=authData.getUid();
                            //fetchData();
                            SharedPreference.putSharedPreferInfo(getApplicationContext(),"uid",uid);
                            SharedPreference.putSharedPreferInfo(getApplicationContext(),"password",pass);

                            ringProgressDialog.dismiss();
                            Intent i=new Intent(getApplicationContext(),MainPage.class);
                            startActivity(i);
                            finish();
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {

                            Snackbar snackbar;
                            switch (firebaseError.getCode()) {
                                case FirebaseError.USER_DOES_NOT_EXIST:
                                    snackbar = Snackbar.make(coordinatorLayout, "User Does Not Exist!", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    break;
                                case FirebaseError.INVALID_PASSWORD:
                                    snackbar = Snackbar.make(coordinatorLayout, "Invalid Password!", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    break;
                                case FirebaseError.NETWORK_ERROR:
                                    snackbar = Snackbar.make(coordinatorLayout, "Network Error!", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    break;


                                default:
                                    snackbar = Snackbar.make(coordinatorLayout, "Login Error!", Snackbar.LENGTH_LONG);
                                    snackbar.show();

                                    break;
                            }

                            //Toast.makeText(getApplicationContext(),"cannot login !!",Toast.LENGTH_LONG).show();
                            ringProgressDialog.dismiss();

                            // there was an error
                        }
                    });



                    // Thread.sleep(10000);
                } catch (Exception e) {
                }
                // ringProgressDialog.dismiss();
            }
        }).start();

    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validateEmail() {
        String email = emaillogin.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(emaillogin);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePassword() {
        if (passwordlogin.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(passwordlogin);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.emaillogin:
                    validateEmail();
                    break;
                case R.id.passwordlogin:
                    validatePassword();
                    break;
            }
        }
    }

}
