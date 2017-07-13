package studios.codelight.smartlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

/*
import studios.codelight.smartlogin.R.id;
import studios.codelight.smartlogin.R.string;
import studios.codelight.smartloginlibrary.LoginType;
import studios.codelight.smartloginlibrary.SmartLogin;
import studios.codelight.smartloginlibrary.SmartLoginCallbacks;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.SmartLoginFactory;
import studios.codelight.smartloginlibrary.UserSessionManager;
import studios.codelight.smartloginlibrary.users.SmartFacebookUser;
import studios.codelight.smartloginlibrary.users.SmartGoogleUser;
import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;
*/

/**
 * Created by santh on 6/20/2017.
 */


public class MainActivity extends AppCompatActivity {

    private Button facebookLoginButton, googleLoginButton, customSigninButton, customSignupButton, logoutButton;
    private EditText emailEditText, passwordEditText;
    private TextView forgotPasswd;
    private static final int REQUEST_SIGNUP = 0;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

    //SmartUser currentUser;
    //GoogleApiClient mGoogleApiClient;
    //SmartLoginConfig config;
    //SmartLogin smartLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        bindViews();
        setListeners();


        //config = new SmartLoginConfig(this, this);
        //config.setFacebookAppId(getString(string.facebook_app_id));
        //config.setFacebookPermissions(null);
        //config.setGoogleApiClient(null);


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        refreshLayout(currentUser);
    }

    private void refreshLayout(FirebaseUser user) {
        //currentUser = UserSessionManager.getCurrentUser(this);
        if (user != null) {
            Log.d("ExcelForte App", "Logged in user: " + user.toString());
            facebookLoginButton.setVisibility(View.GONE);
            googleLoginButton.setVisibility(View.GONE);
            customSigninButton.setVisibility(View.GONE);
            customSignupButton.setVisibility(View.GONE);
            emailEditText.setVisibility(View.GONE);
            passwordEditText.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
        } else {
            facebookLoginButton.setVisibility(View.VISIBLE);
            googleLoginButton.setVisibility(View.VISIBLE);
            customSigninButton.setVisibility(View.VISIBLE);
            customSignupButton.setVisibility(View.VISIBLE);
            emailEditText.setVisibility(View.VISIBLE);
            passwordEditText.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*
        if (smartLogin != null) {
            smartLogin.onActivityResult(requestCode, resultCode, data, config);
        }
        */
    }

    private void setListeners() {
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform Facebook login
                //smartLogin = SmartLoginFactory.build(LoginType.Facebook);
                //smartLogin.login(config);
                Log.d(TAG, "setListeners()::facebook login");
                Intent intent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                startActivity(intent);
            }
        });

        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform Google login
                //smartLogin = SmartLoginFactory.build(LoginType.Google);
                //smartLogin.login(config);
                Log.d(TAG, "setListeners()::Google login");
                Intent intent = new Intent(MainActivity.this, GoogleSignInActivity.class);
                startActivity(intent);
            }
        });

        customSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform custom sign in
                //smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
                //smartLogin.login(config);
                Log.d(TAG, "setListeners()::Custom login");
                signIn();
            }
        });

        customSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
                //smartLogin.signup(config);
                /*
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivityForResult(intent,REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                */

                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });

        forgotPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
                //smartLogin.signup(config);
                /*
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivityForResult(intent,REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                */

                Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                startActivity(intent);

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if (currentUser != null) {
                    if (currentUser instanceof SmartFacebookUser) {
                        smartLogin = SmartLoginFactory.build(LoginType.Facebook);
                    } else if(currentUser instanceof SmartGoogleUser) {
                        smartLogin = SmartLoginFactory.build(LoginType.Google);
                    } else {
                        smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
                    }
                    boolean result = smartLogin.logout(MainActivity.this);
                    if (result) {
                        refreshLayout();
                        Toast.makeText(MainActivity.this, "User logged out successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                */
                signOut();
            }
        });
    }

    private void signOut() {
        mAuth.signOut();
        refreshLayout(null);
    }

    private void signIn() {

        if (!validate()) {
            return;
        }

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        Log.d(TAG, "signIn: validated " + email + " " + password);
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.Theme_AppCompat);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Signing in...");
        progressDialog.show();
        //showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            refreshLayout(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            refreshLayout(null);
                        }

                        progressDialog.cancel();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void bindViews() {
        facebookLoginButton = (Button) findViewById(R.id.facebook_login_button);
        googleLoginButton = (Button) findViewById(R.id.google_login_button);
        customSigninButton = (Button) findViewById(R.id.custom_signin_button);
        customSignupButton = (Button) findViewById(R.id.custom_signup_button);
        emailEditText = (EditText) findViewById(R.id.email_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        logoutButton = (Button) findViewById(R.id.logout_button);
        forgotPasswd = (TextView) findViewById(R.id.forgot_password);
    }


    public void onLoginSuccess(FirebaseUser user) {
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        refreshLayout(user);
    }

    public void onLoginFailure(FirebaseAuthException e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    public FirebaseUser doCustomLogin() {
        FirebaseUser user = mAuth.getCurrentUser();
        //user.isEmailVerified(emailEditText.getText().toString());
        return user;
    }


    public FirebaseUser doCustomSignup() {
        FirebaseUser user = mAuth.getCurrentUser();
       // user.setEmail(emailEditText.getText().toString());
        return user;
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordEditText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        return valid;
    }

}
