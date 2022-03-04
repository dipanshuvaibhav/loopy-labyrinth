package com.labyrinth.loopy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class StudentInfo_InputScreen extends AppCompatActivity {

    String rollNum, userName, courseName, courseYear, currentSemester;
    EditText rollTextField;
    ImageButton saveProfileBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info_input_screen);

        changeStatusBarColor();

        saveProfileBtn = (ImageButton) findViewById(R.id.saveProfileBtn);
        rollTextField = (EditText) findViewById(R.id.rollNumber);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(StudentInfo_InputScreen.this);

        saveProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rollNum = rollTextField.getText().toString().trim();

                if(rollNum.isEmpty())
                {
                    rollTextField.setError("Enter roll number!");
                    rollTextField.requestFocus();
                    return;
                }


            }
        });

        userName = "";
        //This line is to add data to the sql database pls check carefully
        dbHandler.addNewUser(rollNum, userName);

        
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

}

// Receive all the data in variables and then send it to the sqlite database
// Use validations for the entry fields
// Edit the spinners in the UI screen to show an unelectable hint
// m
