package net.loguide.loguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView tvSignup;
    private TextView resetPassword;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    private static int RC_SIGN_IN = 1;
    private static final String TAG = "LoginActivity";

    FirebaseAuth firebaseAuth;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.Email_login);
        edtPassword = findViewById(R.id.Password_login);
        btnLogin = findViewById(R.id.btn_login);
        tvSignup = findViewById(R.id.tv_signup);
        resetPassword = findViewById(R.id.reset_password);

        loadingBar = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowingUserLogin();
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegisterActivity();
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToResetPAsswordActivity();
            }
        });
    }



    private void AllowingUserLogin()
    {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Please wait, while we are allowing you to login into your Account...");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();




            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                SendUserToMainActivity();

                                Toast.makeText(LoginActivity.this, "you are Logged In successfully.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error occured: " + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }


    @Override
    protected void onStart() {

        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null)
        {
            SendUserToMainActivity();
        }
    }

//    private void checkEmail() {
//        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
//
//        Boolean emailVerify = firebaseUser.isEmailVerified();
//
//    }
//
//    private boolean valid() {
//        Boolean result = false;
//
//        if (email.equals("")||password.equals("")){
//            Toast.makeText(getApplicationContext(),"Please fill all the field",Toast.LENGTH_SHORT).show();
//        }else{
//            result = true;
//        }
//        return result;
//    }

    private void SendUserToMainActivity()
    {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToRegisterActivity() {
        Intent registerIntenet = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(registerIntenet);
    }

    private void SendUserToResetPAsswordActivity() {
        Intent registerIntenet = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(registerIntenet);
    }
}
