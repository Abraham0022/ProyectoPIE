package com.example.proyectopie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityPreguntas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("me llamo pedro");
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.tv_nombre);
        textView.setText(nombre);

        String hola ="hola Abraham!!!";
        String adios ="adiossssssss";


    }
}