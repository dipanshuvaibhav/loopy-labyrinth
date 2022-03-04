package com.labyrinth.loopy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.database.sqlite.*;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;
import java.util.Random;

public class AttendanceForm extends AppCompatActivity {

    EditText eventName, sessionNumber, sessionHead, sessionCoHead, locationName, eventDate, fromTime, toTime, otherType;
    ImageButton backBtn, generateQrBtn, verifyBtn;
    //Spinner eventType;
    Switch futureSwitch;
    String eventName_var, sessionNum_var, sessionHead_var, sessionCoHead_var, locationName_var, otherType_var;
    Date eventDate_var;
    String qrcodeData;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_form);

        changeStatusBarColor();

        mAuth=FirebaseAuth.getInstance();

        backBtn = (ImageButton) findViewById(R.id.back_btn4);
        eventName = (EditText) findViewById(R.id.eventName);
        sessionNumber = (EditText) findViewById(R.id.sessionNo);
        sessionHead = (EditText) findViewById(R.id.sessionHead);
        sessionCoHead = (EditText) findViewById(R.id.sessCoHead);
        locationName = (EditText) findViewById(R.id.locationName);
        eventDate = (EditText) findViewById(R.id.eventDate);
        fromTime = (EditText) findViewById(R.id.fromTime);
        toTime = (EditText) findViewById(R.id.toTime);
        //eventType = (Spinner) findViewById(R.id.eventType);
        otherType = (EditText) findViewById(R.id.otherEventType);
        verifyBtn = (ImageButton) findViewById(R.id.verifyBtn);
        futureSwitch = (Switch) findViewById(R.id.futureRegisterSwitch);
        generateQrBtn = (ImageButton) findViewById(R.id.generateQRBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        generateQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventName_var = eventName.getText().toString().trim();
                sessionNum_var = sessionNumber.getText().toString().trim();
                sessionHead_var = sessionHead.getText().toString().trim();
                sessionCoHead_var = sessionCoHead.getText().toString().trim();
                locationName_var = locationName.getText().toString().trim();
                otherType_var = otherType.getText().toString().trim();



                if(eventName_var.isEmpty())
                {
                    eventName.setError("Required");
                    eventName.requestFocus();
                    return;
                }
                if(sessionNum_var.isEmpty())
                {
                    sessionNumber.setError("Required");
                    sessionNumber.requestFocus();
                    return;
                }
                if(sessionHead_var.isEmpty())
                {
                    sessionHead.setError("Required");
                    sessionHead.requestFocus();
                    return;
                }
                if(locationName_var.isEmpty())
                {
                    locationName.setError("Required");
                    locationName.requestFocus();
                    return;
                }

                qrcodeData = getRandomNumberString();
                Intent intent = new Intent(AttendanceForm.this, Attendance_QR_Screen.class);
                // uname2 = username.getText().toString();
                intent.putExtra("Value", qrcodeData);
                startActivity(intent);
                finish();

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

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

}