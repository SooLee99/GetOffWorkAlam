/*
 * © 2022. 이수 all rights reserved.
 * Create by year 2022-06-12
 * Select Hardware : Pixel 3a
 * System Image :  Pie
 * 모바일 테스트 기종 : 갤럭시 S9+
 * 개발자 : 이수
*/

// 이미지 버튼의 이미지 : ©OGU
// 오구 캐릭터 - (문랩작가)

package com.example.getoffworkalam_application;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ProgressBar pbGOW;
    TextView tvDate, tvTime, tvGTW, tvGOW, percent, tvTodayWork, tvPayday;
    ImageButton imgbtn;
    Button btn_WSU, btn_CSU, btn_PSU;
    // 프로그레스바 관리
    int secGTW = 0, secGOW = 0, secNow = 0;

    // 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbGOW = findViewById(R.id.progressBar_GetOffWork);
        tvGTW = findViewById(R.id.tvGTW);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        tvGOW = findViewById(R.id.tvGOW);

        percent = findViewById(R.id.percent);
        tvTodayWork = findViewById(R.id.tvTodayWork);
        tvPayday = findViewById(R.id.payday);
        imgbtn = findViewById(R.id.imageBtn);
        btn_WSU = findViewById(R.id.btnWorkSetUp);
        btn_CSU = findViewById(R.id.btnClosedSetUp);
        btn_PSU = findViewById(R.id.btnPaySetUp);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted())
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                // 현재 일정 관리
                                tvTime.setText(getCurrentTime());
                                tvDate.setText(getCurrentDay());

                                // 휴무일 설정 시 출근/퇴근 시간 설정 못함.
                                if (tvTodayWork.getText().equals("휴무일")) {
                                    tvGTW.setText("00시 00분");
                                    tvGOW.setText("00시 00분");
                                } else {
                                    // 프로그레스바 관리
                                    // 출근시간 <= 퇴근시간
                                    if (secGTW <= secGOW) {
                                        pbGOW.setMin(secGTW);
                                        pbGOW.setProgress(getNowSec());
                                        pbGOW.setMax(secGOW);
                                    }
                                    // 출근시간 > 퇴근시간
                                    else {
                                        pbGOW.setMin(secGTW);
                                        pbGOW.setMax(86400 - secGTW + secGOW + secGTW);
                                        if (getNowSec() < 86400) {
                                            pbGOW.setProgress(getNowSec());
                                        } else
                                            pbGOW.setProgress(getNowSec() + 86400);
                                    }

                                    // 현재 진행률
                                    if(getNowPersent() >= 100)
                                        percent.setText("100%");
                                    else if(getNowPersent() <= 0)
                                        percent.setText("0%");
                                    else
                                        percent.setText(getNowPersent() + "%");
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                    }
            }
        })).start();

        // 출근, 퇴근 시간설정 버튼 이벤트
        btn_WSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity_WorkSetUp.class);
                startActivityForResult(intent, 1);
            }
        });

        // 휴무일 설정 버튼 이벤트
        btn_CSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity_ClosedSetUp.class);
                startActivityForResult(intent, 2);
            }
        });

        // 월급날 설정 버튼 이벤트
        btn_PSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity_PaySetUp.class);
                startActivityForResult(intent, 3);
            }
        });

    }

    // 설정 변경 시 화면 변경
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 출근, 퇴근 시간 변경 시
        if (requestCode == 1 && resultCode == 1) {
            int getGTWHour = 0, getGOWHour = 0, getGTWMin = 0, getGOWMin = 0;
            int tHour = 0, tMin = 0, oHour = 0, oMin = 0;
            getGTWHour = data.getIntExtra("getGTWHour", tHour);
            getGTWMin = data.getIntExtra("getGTWMin", tMin);
            getGOWHour = data.getIntExtra("getGOWHour", oHour);
            getGOWMin = data.getIntExtra("getGOWMin", oMin);

            secGTW = getGTWHour * 3600 + getGTWMin * 60;
            secGOW = getGOWHour * 3600 + getGOWMin * 60;
            secNow = getNowSec();

            tvGTW.setText(getGTWHour + "시 " + getGTWMin + "분");
            tvGOW.setText(getGOWHour + "시 " + getGOWMin + "분");

            if (tvTodayWork.getText().equals("휴무일")) {
                Toast.makeText(getApplicationContext(), "휴무일에는 시간설정이 안됩니다.", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getApplicationContext(), "설정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        }

        // 휴무일 설정 변경 시
        else if (requestCode == 2 && resultCode == 2) {
            String getDay = data.getStringExtra("dayOfWeek");
            // 현재 날짜
            LocalDate nowDay = LocalDate.now();
            String dayOfWeek = nowDay.getDayOfWeek().toString();
            // 휴무일, 근무일 확인
            boolean contains = getDay.contains(dayOfWeek);
            if (contains) {
                tvTodayWork.setText("휴무일");
                imgbtn.setImageResource(R.drawable.play);
                secGTW = 0;
                secGOW = 0;

                pbGOW.setMin(0);
                pbGOW.setProgress(0);
                pbGOW.setMax(0);

                tvGTW.setText("00시 00분");
                tvGOW.setText("00시 00분");

                percent.setText("00%");

            }
            else {
                tvTodayWork.setText("근무일");
                imgbtn.setImageResource(R.drawable.mybutton);
            }
        }

        // 월급 설정 버튼 이벤트설정 변경 시
        else if (requestCode == 3 && resultCode == 3) {
            int payday = 0, last_day, now_day,  d_day = 0;

            // 월급날
            int ipayday = data.getIntExtra("payday", payday);

            // 현재 날짜 기준 일
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
            Date now = new Date();
            now_day = Integer.parseInt(dateFormat.format(now));

            // 현재 달의 말일
            Calendar cal = Calendar.getInstance();
            last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            // 월급날이 말일인 경우
            if(ipayday == 29)
                ipayday = last_day;

            // 월급날 D-Day 계산
            d_day = ipayday - now_day;

            if (d_day >= 0)
                tvPayday.setText(d_day + "일");
            else {
                // 만약 월급날 D-Day가 음수인 경우
                d_day = last_day - now_day + ipayday ;
                tvPayday.setText(d_day + "일");
            }
        }
    }

    // 메뉴 아이템 이벤트 메서드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_item_help:
                Toast.makeText(this, "dltn990930@naver.com", Toast.LENGTH_SHORT).show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 현재 시간을 반환
    public String getCurrentTime() { // 현재 날짜 관리
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("HH시 mm분 ss초");
        String str = dayTime.format(new Date(time));
        return str;
    }

    // 현재 날짜를 반환
    public String getCurrentDay() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayDate = new SimpleDateFormat("yyyy년 MM월 dd일");
        String str = dayDate.format(new Date(time));

        return str;
    }

    // 현재시간 초로 반환
    public int getNowSec() {
        long time = System.currentTimeMillis();
        SimpleDateFormat nowhour = new SimpleDateFormat("HH");
        SimpleDateFormat nowmin = new SimpleDateFormat("mm");
        SimpleDateFormat nowsec = new SimpleDateFormat("ss");
        int sec = Integer.parseInt(nowhour.format(new Date(time))) * 3600
                + Integer.parseInt(nowmin.format(new Date(time))) * 60
                + Integer.parseInt(nowsec.format(new Date(time)));

        return sec;
    }

    // 진행률 환산 메소드
    public double getNowPersent() {
        long time = System.currentTimeMillis();
        double runTimePer;
        SimpleDateFormat nowhour = new SimpleDateFormat("HH");
        SimpleDateFormat nowmin = new SimpleDateFormat("mm");
        SimpleDateFormat nowsec = new SimpleDateFormat("ss");
        long lsec = Long.parseLong(nowhour.format(new Date(time))) * 3600
                + Long.parseLong(nowmin.format(new Date(time))) * 60
                + Long.parseLong(nowsec.format(new Date(time)));

        if(secGTW < secGOW) {
            double nowPersent = lsec;                   // 현재시간
            double diffTimePer = secGOW - secGTW;       // 총 근무시간
            runTimePer = nowPersent - secGTW;    // 지금까지의 근무시간
            double lpercent = (runTimePer / diffTimePer) * 100;
            return Math.round(lpercent * 100) / 100.0;
        }
        else if(secGTW == secGOW) {
            return 0;
        }
        else {
            double nowPersent = lsec;
            double diffTimePer = 86400 - secGTW + secGOW ;       // 총 근무시간

            if (nowPersent <= secGTW) {
                runTimePer = 86400 - secGTW + nowPersent  - secGTW;    // 지금까지의 근무시간
            }
            else {
                runTimePer = nowPersent - secGTW;    // 지금까지의 근무시간
            }
            double lpercent = (runTimePer / diffTimePer) * 100;
            return Math.round(lpercent * 100) / 100.0;
        }
    }

    // 이미지 버튼 이벤트 처리 메소드
    public void onClick (View target) {
        Toast.makeText(getApplicationContext(), "화이팅!!", Toast.LENGTH_SHORT).show();
    }
}