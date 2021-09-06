package com.example.agecalculator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button datePickButton;
    TextView answerText;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePickButton = findViewById(R.id.button2);
        answerText = findViewById(R.id.textView2);
        int[] months = {
            31, 30, 29, 28, 27,
            26, 25, 24, 23, 22,
            21, 20, 19, 18, 17,
            16, 15, 14, 13, 12,
            11, 10, 9, 8, 7, 6,
            5, 4, 3, 2, 1
        };
        datePickButton.setOnClickListener((view) -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(this, (datePicker, birthYear, birthMonth, birthDay) -> {
                StringBuilder ans = new StringBuilder();
                int currentMonth = month;
                int currentDay = day;
                if (birthDay > day) {
                    currentMonth--;
                    currentDay += months[birthMonth-1];
                }
                if(birthMonth > currentMonth) {
                    currentMonth--;
                    currentMonth += 12;
                }
                ans.append("Your age is: \n");
                ans.append(year-birthYear).append(" year(s) ");
                ans.append(currentMonth-birthMonth).append(" month(s) ");
                ans.append(currentDay-birthDay).append(" day(s)");

                answerText.setText(ans.toString());

            }, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        });

    }
}