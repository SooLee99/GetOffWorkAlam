package com.example.getoffworkalam_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity_ClosedSetUp extends AppCompatActivity {
    Button btnBack, btnSetUp;
    CheckBox cbMon, cbTue, cbWed, cbThu, cbFri, cbSat, cbSun;
    String checked = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closedsetup);

        btnBack = findViewById(R.id.btnBack);
        cbMon = findViewById(R.id.cbMon);
        cbTue = findViewById(R.id.cbTue);
        cbWed = findViewById(R.id.cbWed);
        cbThu = findViewById(R.id.cbThu);
        cbFri = findViewById(R.id.cbFri);
        cbSat = findViewById(R.id.cbSat);
        cbSun = findViewById(R.id.cbSun);
        btnSetUp = findViewById(R.id.btnSetUp);

        // 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(0, intent);
                finish();
            }
        });

        // 확인버튼
        btnSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                String dayOfWeek = sendCheck(cbMon, cbTue, cbWed, cbThu, cbFri, cbSat, cbSun);
                intent2.putExtra("dayOfWeek", dayOfWeek);
                setResult(2, intent2);
                finish();
                Toast.makeText(getApplicationContext(), "설정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 휴무일 체크박스 값 받는 메소드
    private String sendCheck(CheckBox cbMon, CheckBox cbTue, CheckBox cbWed, CheckBox cbThu, CheckBox cbFri, CheckBox cbSat, CheckBox cbSun) {
        if (cbMon.isChecked()) {
            checked += ("MONDAY,");
        }
        if (cbTue.isChecked()) {
            checked += ( "TUESDAY");
        }
        if (cbWed.isChecked()) {
            checked += ("WEDNESDAY");
        }
        if (cbThu.isChecked()) {
            checked += ("THURSDAY");
        }
        if (cbFri.isChecked()) {
            checked += ("FRIDAY");
        }
        if (cbSat.isChecked()) {
            checked += ("SATURDAY");
        }
        if (cbSun.isChecked()) {
            checked += ("SUNDAY");
        }
        return checked;
    }
}
