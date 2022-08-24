package com.example.getoffworkalam_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SubActivity_WorkSetUp extends AppCompatActivity {
    Button btnBack, btnSetUp;
    TimePicker tpGTW, tpGOW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksetup);

        btnBack = findViewById(R.id.btnBack);
        btnSetUp = findViewById(R.id.btnSetUp);

        tpGTW = findViewById(R.id.tpGTW);
        tpGOW = findViewById(R.id.tpGOW);

        // 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(0, intent);
                finish();
            }
        });

        // 출근/퇴근 시간 설정 버튼
        btnSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                int getGTWHour = tpGTW.getHour();
                int getGTWMin = tpGTW.getMinute();

                int getGOWHour = tpGOW.getHour();
                int getGOWMin = tpGOW.getMinute();

                intent.putExtra("getGTWHour", getGTWHour);
                intent.putExtra("getGTWMin", getGTWMin);
                intent.putExtra("getGOWHour", getGOWHour);
                intent.putExtra("getGOWMin", getGOWMin);

                setResult(1, intent);
                finish();
                //Toast.makeText(getApplicationContext(), "설정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

