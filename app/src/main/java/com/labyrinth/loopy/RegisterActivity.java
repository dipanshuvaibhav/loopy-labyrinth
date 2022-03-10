package com.labyrinth.loopy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import android.util.Patterns;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import android.database.sqlite.*;


public class RegisterActivity extends AppCompatActivity {

    EditText fullName, email, phone, password, confirm_password, username;
    ImageButton register_btn, google_signup_btn, back_btn;
    FirebaseAuth mAuth;
    String email_var, password_var, name_var, phone_var, confirm_password_var, username_var;
    String rollNum, userName, courseName, courseYear, currentSemester;
    private DBHandler dbHandler;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        changeStatusBarColor();

        fullName = (EditText) findViewById(R.id.full_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        username = (EditText) findViewById(R.id.username);
        register_btn = (ImageButton) findViewById(R.id.register_btn);
        google_signup_btn = (ImageButton) findViewById(R.id.google_signup_btn);
        back_btn = (ImageButton) findViewById(R.id.back_btn);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(RegisterActivity.this);

        mAuth=FirebaseAuth.getInstance();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_var = email.getText().toString().trim();
                password_var = password.getText().toString().trim();
                name_var = fullName.getText().toString().trim();
                phone_var = phone.getText().toString().trim();
                confirm_password_var = confirm_password.getText().toString().trim();
                username_var = username.getText().toString().trim();

                if(email_var.isEmpty())
                {
                    email.setError("Email is empty");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email_var).matches())
                {
                    //user_name.setError("Enter the valid email address");
                    Toast.makeText(getApplicationContext(),"Enter a valid email address",Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                    return;
                }
                if(password_var.isEmpty())
                {
                    password.setError("Enter the password");
                    password.requestFocus();
                    return;
                }
                if(password_var.length()<6)
                {
                    password.setError("Length of the password should be more than 6");
                    password.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email_var,password_var).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //This line is to add data to the sql database pls check carefully
                dbHandler.addNewUser(name_var, userName);

            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}

/* TO-DO Tasks
* Make confirm password code
* Make live username check code
* Make Google Sign UP code
*  */