package com.labyrinth.loopy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    String email_var, password_var;
    EditText email, password;
    ImageButton login_btn, google_signin_btn, back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        login_btn = (ImageButton) findViewById(R.id.login_btn);
        google_signin_btn = (ImageButton) findViewById(R.id.google_signin_btn);
        back_btn = (ImageButton) findViewById(R.id.login_back_btn);

        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        mAuth=FirebaseAuth.getInstance();
        login_btn.setOnClickListener(v -> {
            email_var = email.getText().toString().trim();
            password_var = password.getText().toString().trim();
            if(email_var.isEmpty())
            {
                email.setError("Email is empty");
                email.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email_var).matches())
            {
                email.setError("Enter the valid email");
                email.requestFocus();
                return;
            }
            if(password_var.isEmpty())
            {
                password.setError("Password is empty");
                password.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                password.setError("Length of password is more than 6");
                password.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email_var,password_var).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(LoginActivity.this, OTP_Screen.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });
        //login_btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,Register.class )));


    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}