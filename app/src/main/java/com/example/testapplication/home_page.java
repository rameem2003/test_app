package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class home_page extends AppCompatActivity {

    Button qibla, btn33, btn100, btn500, btnInf;
    ImageView aboutBtn;

    CardView counterBtn, refresh;
    TextView counter;

    Vibrator vibrate;

    int count = 0;
    int limit = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        counter = findViewById(R.id.counter);
        counterBtn = findViewById(R.id.counterBtn);
        refresh = findViewById(R.id.refresh);

        btn33 = findViewById(R.id.btn33);
        btn100 = findViewById(R.id.btn100);
        btn500 = findViewById(R.id.btn500);
        btnInf = findViewById(R.id.btnInf);


        qibla = findViewById(R.id.qibla);
        aboutBtn = findViewById(R.id.aboutBtn);


        vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        counterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //count = count + 1;
                //count++;

                if(limit == 33 && count < 33){
                    count++;
                } else if (limit == 100 && count < 100) {
                    count++;
                } else if (limit == 500 && count < 500) {
                    count++;
                } else if (limit == 0) {
                    count++;
                }else {
                    vibrate.vibrate(150);
                    Toast.makeText(home_page.this, "Limit fullfill", Toast.LENGTH_LONG).show();
                }


                counter.setText(String.valueOf(count));
            }
        });

        btn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limit = 33;
            }
        });


        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limit = 100;
            }
        });

        btn500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limit = 500;
            }
        });

        btnInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limit = 0;
            }
        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;

                counter.setText(String.valueOf(count));

                vibrate.vibrate(150);
            }
        });




        qibla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home_page.this, compass_page.class);
                startActivity(i);
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home_page.this, about.class);
                startActivity(i);
            }
        });
    }
}