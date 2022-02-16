package com.example.aplicationaluguerbicicleta;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LerQRCode extends AppCompatActivity {

    Button btscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_qrcode);

        btscan = findViewById(R.id.bLerQRC);


        btscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(LerQRCode.this);
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setCaptureActivity(Capture.class);
                intentIntegrator.initiateScan();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //inicializar intent Result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //--------------------verificar o codigo QR e apresentar o resultado--------------------------------
        if (intentResult.getContents() != null) {
            intentResult.getContents();
            if (intentResult.getContents().equals("bikeDESK19UT9os")) {
                Intent actividade = new Intent(this, DetalhesBike.class);
                startActivity(actividade);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(LerQRCode.this);
                builder.setTitle("Desconhecido");
                builder.setMessage("Não consegui identificar a bicicleta");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        }
        //------------------------------------------------------------------------------------------
        else {
            //Quando result content e´ null
            //Mostra toast
            Toast.makeText(getApplicationContext(), "OOPs...Nao foi possivel scanear", Toast.LENGTH_SHORT).show();
        }

    }


}



