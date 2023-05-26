package com.example.testapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class compass_page extends AppCompatActivity implements SensorEventListener {
    ImageView compass;
    SensorManager manager;
    AlertDialog dialog;

    float degree = 0;
    float crrDeg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_page);

        compass = findViewById(R.id.compass);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

//        if(manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
//            Toast.makeText(this, "Move Your Smartphone So That The Compass Will Calibrate", Toast.LENGTH_LONG).show();
//        }else {
//            showAlert();
//        }
        if(manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            Toast.makeText(this, "Pls Rotate Your Phone to Calibrate The Compass", Toast.LENGTH_SHORT).show();
        }else {
            showAlert();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);

    }

    private void showAlert() {
        LayoutInflater inflater = getLayoutInflater();
        View layoutView = inflater.inflate(R.layout.erroralert, null);

        dialog = new AlertDialog.Builder(compass_page.this).setView(layoutView).create();
        dialog.show();

        Button back = layoutView.findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(compass_page.this, home_page.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        degree = Math.round(sensorEvent.values[0]);

        RotateAnimation animation = new RotateAnimation(crrDeg, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(210);
        animation.setFillAfter(true);

        compass.startAnimation(animation);
        crrDeg = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}