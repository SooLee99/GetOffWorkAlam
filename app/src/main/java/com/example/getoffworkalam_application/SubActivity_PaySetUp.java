package com.example.getoffworkalam_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity_PaySetUp extends AppCompatActivity {
    RadioGroup rd1, rd2, rd3, rd4, rd5;
    Button btnBack, btnSetUp;
    int payday = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysetup);

        btnBack = findViewById(R.id.btnBack);
        rd1 = findViewById(R.id.radioGroup1);
        rd2 = findViewById(R.id.radioGroup2);
        rd3 = findViewById(R.id.radioGroup3);
        rd4 = findViewById(R.id.radioGroup4);
        rd5 = findViewById(R.id.radioGroup5);
        btnSetUp = findViewById(R.id.btnSetUp);

        rd1.clearCheck();
        rd2.clearCheck();
        rd3.clearCheck();
        rd4.clearCheck();
        rd5.clearCheck();

        // 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(0, intent);
                finish();
            }
        });

        // void clearCheck()

        // 라디오버튼 체크 이벤트(1~7)
        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(payday>=8 && payday<=14)
                    rd2.clearCheck();
                else if(payday>=15 && payday<=21)
                    rd3.clearCheck();
                else if(payday>=22 && payday<=28)
                    rd4.clearCheck();
                else if(payday==29)
                    rd5.clearCheck();

                if(checkedId == R.id.radioButton1)
                    payday = 1;
                else if(checkedId == R.id.radioButton2)
                    payday = 2;
                else if(checkedId == R.id.radioButton3)
                    payday = 3;
                else if(checkedId == R.id.radioButton4)
                    payday = 4;
                else if(checkedId == R.id.radioButton5)
                    payday = 5;
                else if(checkedId == R.id.radioButton6)
                    payday = 6;
                else if(checkedId == R.id.radioButton7)
                    payday = 7;
            }
        });
        // 라디오버튼 체크 이벤트(8~14)
        rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(payday>=1 && payday<=7)
                    rd1.clearCheck();
                else if(payday>=15 && payday<=21)
                    rd3.clearCheck();
                else if(payday>=22 && payday<=28)
                    rd4.clearCheck();
                else if(payday==29)
                    rd5.clearCheck();

                if(checkedId == R.id.radioButton8)
                    payday = 8;
                else if(checkedId == R.id.radioButton9)
                    payday = 9;
                else if(checkedId == R.id.radioButton10)
                    payday = 10;
                else if(checkedId == R.id.radioButton11)
                    payday = 11;
                else if(checkedId == R.id.radioButton12)
                    payday = 12;
                else if(checkedId == R.id.radioButton13)
                    payday = 13;
                else if(checkedId == R.id.radioButton14)
                    payday = 14;
            }
        });
        // 라디오버튼 체크 이벤트(15~21)
        rd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(payday>=1 && payday<=7)
                    rd1.clearCheck();
                else if(payday>=8 && payday<=14)
                    rd2.clearCheck();
                else if(payday>=22 && payday<=28)
                    rd4.clearCheck();
                else if(payday==29)
                    rd5.clearCheck();

                if(checkedId == R.id.radioButton15)
                    payday = 15;
                else if(checkedId == R.id.radioButton16)
                    payday = 16;
                else if(checkedId == R.id.radioButton17)
                    payday = 17;
                else if(checkedId == R.id.radioButton18)
                    payday = 18;
                else if(checkedId == R.id.radioButton19)
                    payday = 19;
                else if(checkedId == R.id.radioButton20)
                    payday = 20;
                else if(checkedId == R.id.radioButton21)
                    payday = 21;
            }
        });
        // 라디오버튼 체크 이벤트(22~28)
        rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(payday>=1 && payday<=7)
                    rd1.clearCheck();
                else if(payday>=8 && payday<=14)
                    rd2.clearCheck();
                else if(payday>=15 && payday<=21)
                    rd3.clearCheck();
                else if(payday==29)
                    rd5.clearCheck();

                if(checkedId == R.id.radioButton22)
                    payday = 22;
                else if(checkedId == R.id.radioButton23)
                    payday = 23;
                else if(checkedId == R.id.radioButton24)
                    payday = 24;
                else if(checkedId == R.id.radioButton25)
                    payday = 25;
                else if(checkedId == R.id.radioButton26)
                    payday = 26;
                else if(checkedId == R.id.radioButton27)
                    payday = 27;
                else if(checkedId == R.id.radioButton28)
                    payday = 28;
            }
        });

        // 라디오버튼 체크 이벤트(29~31)
        rd5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(payday>=1 && payday<=7)
                    rd1.clearCheck();
                if(payday>=8 && payday<=14)
                    rd2.clearCheck();
                if(payday>=15 && payday<=21)
                    rd3.clearCheck();
                if(payday>=22 && payday<=28)
                    rd4.clearCheck();

                if(checkedId == R.id.radioButton29)
                    payday = 29;
            }
        });

        // 설정확인 버튼
        btnSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                intent3.putExtra("payday", payday);

                setResult(3, intent3);
                finish();
                Toast.makeText(getApplicationContext(), "설정이 완료되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}