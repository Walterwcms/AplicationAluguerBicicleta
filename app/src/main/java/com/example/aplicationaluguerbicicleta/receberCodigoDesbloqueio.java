package com.example.aplicationaluguerbicicleta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class receberCodigoDesbloqueio extends AppCompatActivity {


    private Button btchage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receber_codigo_desbloqueio);
        btchage = findViewById(R.id.btchange);

    }

    public void changeactiviti(View view) {
        Intent a = new Intent(this, coneccaoServidor.class);
        startActivity(a);
    }

}