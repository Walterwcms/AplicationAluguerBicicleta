package com.example.aplicationaluguerbicicleta;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class coneccaoServidor extends AppCompatActivity {


    // declaring required variables
    private Socket client;
    private PrintWriter printwriter;
    private TextView textField;
    private TextView textn;
    private Button button;
    private CheckBox ligarLuzTraz;
    private CheckBox ligarLuzFrente;
    private String messageS;
    private int n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coneccao_servidor);

        button = findViewById(R.id.btSendMessage);
        textField = findViewById(R.id.ed1);
        ligarLuzFrente = findViewById(R.id.luzFrente);
        ligarLuzTraz = findViewById(R.id.luzTraz);
        textn = findViewById(R.id.edn);


        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (ligarLuzFrente.isChecked()) {
                    messageS = messageS + " frente";
                    n = 1;
                }
                if (ligarLuzTraz.isChecked()) {
                    messageS = messageS + " traz";
                    n = 1;
                }

                if(n != 1)
                    messageS = messageS + " nada";
                    n=0;


                // start the Thread to connect to server
                new Thread(new ClientThread(messageS)).start();
            }
        });
    }

    // the ClientThread class performs
    // the networking operations
    class ClientThread implements Runnable {
        private String message;

        ClientThread(String message) {
            this.message = messageS;
        }

        @Override
        public void run() {
            try {
                client = new Socket("192.168.43.99", 1234); // connect to server
                if (client != null) {
                    printwriter = new PrintWriter(client.getOutputStream(), true);
                    printwriter.write(message);
                    messageS = "";
                    printwriter.flush();
                    printwriter.close();
                    textField.setText("");
                }else{
                    textField.setText("erro a o conectar ao servidor");
                }

            } catch (Exception e) {
                textField.setText("Erro ao criar socket");
            }

            // updating the UI
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textn.setText("");
                }
            });
        }
    }

}