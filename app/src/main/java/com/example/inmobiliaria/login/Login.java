package com.example.inmobiliaria.login;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.request.ApiClient;

import java.util.List;


public class Login extends AppCompatActivity {
    private LoginViewModel vmLogin;
    private EditText etUsuario, etPassword;
    private Button btLogin;
    private TextView tvError;
    private ApiClient api;
    private LocationManager manager;
    private SensorManager sensorManager;
    private Sensor sensor;
    private MoverSensor moverSensor;


    private long lastUpdate = 0;
    private float var_x, var_y, var_z;
    private static final int AGITAR= 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVista();
        obtenerSensor();

        vmLogin = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        vmLogin.getErrorVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {

                tvError.setVisibility(visibility);
            }
        });

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }


    }

    private void obtenerSensor() {
        moverSensor = new MoverSensor();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (lista.size() > 0) {

            sensorManager.registerListener(moverSensor, lista.get(0), SensorManager.SENSOR_DELAY_GAME);

        }
    }

    public class MoverSensor  implements SensorEventListener {


        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {


            Sensor s = sensorEvent.sensor;

            if (s.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                long curTime = System.currentTimeMillis();

                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - var_x - var_y - var_z) / diffTime * 10000;

                    if (speed > AGITAR) {
                        hacerLLamada();

                    }
                    var_x = x;
                    var_y = y;
                    var_z = z;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    private void hacerLLamada() {

        Intent llamarIntent = new Intent(Intent.ACTION_CALL);
        llamarIntent.setData(Uri.parse("tel: "+ "2664863615"));
        startActivity(llamarIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new MoverSensor();
        }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(moverSensor);
        super.onStop();
    }

    public void inicializarVista() {
        btLogin = findViewById(R.id.btLogin);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        tvError = findViewById(R.id.tvError);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vmLogin.login(
                        etUsuario.getText().toString(),
                        etPassword.getText().toString()
                );
            }
        });
        etUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });
    }





}