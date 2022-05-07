package com.example.proyectopie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_iniciar;
    public static final String EXTRA_MESSAGE = "com.example.proyectopie.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    Intent empezarTest=new Intent(MainActivity.this, ActivityPreguntas.class);
                startActivity(empezarTest);*/
                empezarTest(v);
            }
        });
    }

    public void empezarTest(View v){
        Intent intent = new Intent(this, ActivityPreguntas.class);
        EditText editText = (EditText) findViewById(R.id.et_nombre);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}