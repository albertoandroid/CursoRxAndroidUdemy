package com.androiddesdecero.rxudemyfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btRX00Introduccion;
    private Button btRX01Disposable;
    private Button btRX02CompositeDisposable;
    private Button btRX03Operadores;
    private Button btRX04TipoObservables;
    private Button btRX05Subject;
    private Button btRX06Bus;
    private Button btRX07Binding;
    private Button btRX08BackPressure;
    private Button btRX09HotAndCold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }

    private void setUpView(){
        btRX00Introduccion = findViewById(R.id.btRX00Introduccion);
        btRX00Introduccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX00IntroActivity.class));
            }
        });
        btRX01Disposable = findViewById(R.id.btRX01Disposable);
        btRX01Disposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX01DisposableActivity.class));
            }
        });
        btRX02CompositeDisposable = findViewById(R.id.btRX02CompositeDisposable);
        btRX02CompositeDisposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX02CompositeDisposableActivity.class));
            }
        });

        btRX03Operadores = findViewById(R.id.btRX03Operadores);
        btRX03Operadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX03OperadoresActivity.class));
            }
        });

        btRX04TipoObservables = findViewById(R.id.btRX04TipoObservables);
        btRX04TipoObservables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX04TiposObservablesActivity.class));
            }
        });

        btRX05Subject = findViewById(R.id.btRX05Subject);
        btRX05Subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX05SubjectActivity.class));
            }
        });

        btRX06Bus = findViewById(R.id.btRX06Bus);
        btRX06Bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX06BusActivity.class));
            }
        });

        btRX07Binding = findViewById(R.id.btRX07Binding);
        btRX07Binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX07BindingActivity.class));
            }
        });

        btRX08BackPressure = findViewById(R.id.btRX08BackPressure);
        btRX08BackPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX08BackPressureActivity.class));
            }
        });

        btRX09HotAndCold = findViewById(R.id.btRX09HotAndCold);
        btRX09HotAndCold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RX09HotAndColdActivity.class));
            }
        });
    }
}
