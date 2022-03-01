package com.labyrinth.loopy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AttendanceHome extends AppCompatActivity {

    ImageButton createAttendanceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_home);

        createAttendanceBtn = (ImageButton) findViewById(R.id.createAttendanceBtn);

        createAttendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AttendanceForm.class);
                startActivity(intent);
            }
        });

    }
}