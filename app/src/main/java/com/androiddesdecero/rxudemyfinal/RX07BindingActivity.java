package com.androiddesdecero.rxudemyfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class RX07BindingActivity extends AppCompatActivity {

    private Button btRXBinding;
    private Button btRXBinding1;
    private EditText etRXBinding;
    private EditText etRXBinding1;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx07_binding);


        btRXBinding = findViewById(R.id.btRXBinding);
        disposable = RxView.clicks(btRXBinding)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        //Manejamos el Evento onClick
                        Log.d("TAG1", "onClick utilizando RX");
                    }
                });

        etRXBinding = findViewById(R.id.etRXBinding);
        RxTextView.textChanges(etRXBinding)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        Log.d("TAG1", "onTextChange");
                    }
                });

        btRXBinding1 = findViewById(R.id.btRXBinding1);
        RxView.clicks(btRXBinding1)
                .debounce(1, TimeUnit.SECONDS)
                .subscribe(
                        e->Log.d("TAG1", "onClic con un segundo")
                );

        etRXBinding1 = findViewById(R.id.etRXBinding1);
        RxTextView.textChanges(etRXBinding1)
                .debounce(1, TimeUnit.SECONDS)
                .map(e->etRXBinding1.getText().toString())
                .subscribe(
                        e->Log.d("TAG1", e)
                );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
