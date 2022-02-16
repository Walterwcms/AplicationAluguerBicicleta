package com.example.aplicationaluguerbicicleta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void Visualizar_estacoes_bicicletas(View view) {
        Uri gmmIntentUri = Uri.parse("geo:16.864306921743868,-24.986002642863692?q=UNICV-Campus-Ribeira-Juliao+View");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void VerDetalhesBicicleta (View view){
        Intent lerqrcode;
        lerqrcode  = new Intent(this,LerQRCode.class);
        startActivity(lerqrcode);
    }
    public void alugarBike (View view){
        Intent lerqrcode;
        lerqrcode  = new Intent(this,formulario.class);
        startActivity(lerqrcode);
    }

}