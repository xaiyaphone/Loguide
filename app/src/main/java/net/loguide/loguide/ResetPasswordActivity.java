package net.loguide.loguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private Button ResetPassword;
    private EditText EmailReset;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        ResetPassword = (Button) findViewById(R.id.btn_reset);
        EmailReset = (EditText) findViewById(R.id.Email_Reset);

        mAuth = FirebaseAuth.getInstance();

        ResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = EmailReset.getText().toString();

                if (TextUtils.isEmpty(userEmail)){
                    Toast.makeText(ResetPasswordActivity.this,"Please write your emaail address first",Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this,"We already sent reset password link to your email.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
                            }else {
                                String message = task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this,"Error Occured: " + message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
