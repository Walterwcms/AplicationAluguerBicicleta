package com.example.aplicationaluguerbicicleta;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class formulario extends AppCompatActivity {

    EditText inputNome;
    EditText inputTelefone;
    EditText inputEmail;
    EditText inputMorada;
    EditText inputTempo;
    TextView textErroCampos;
    boolean errosCampos = false;

    Button enviar;
    Button btVerValorPago;

    public static final String nameSharedPrefFile = "DadosUsuario";
    SharedPreferences Preferences;


    int precoDoAluguer = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        inputNome = findViewById(R.id.inputNome);
        inputTelefone = findViewById(R.id.inputTelefone);
        inputEmail = findViewById(R.id.inputEmail);
        inputMorada = findViewById(R.id.inputMorada);
        inputTempo = findViewById(R.id.inputTempo);
        enviar = findViewById(R.id.buttonEnviar);
        btVerValorPago = findViewById(R.id.btVerValorPago);
        textErroCampos = findViewById(R.id.textErroCampos);
        String nomeMostrar;

        SharedPreferences nhaPreferences2;
        nhaPreferences2 = getSharedPreferences(nameSharedPrefFile, MODE_PRIVATE);

        //--------------------------------a ultimo pessoa no formulario---------------
        inputNome.setText(nhaPreferences2.getString("nome", " "));
        inputEmail.setText(nhaPreferences2.getString("email", " "));
        //----------------------------------------------------------------------------
    }


    @SuppressLint("SetTextI18n")
    public void enviarDados(View view) {

        //-------------------------------------------------validacao do formulario--------------------------------------------------------------------
        if (inputNome.getText().toString().length() < 7)
            mudarCorEditText(inputNome, "vermelho");
        else
            mudarCorEditText(inputNome, "");

        if (inputTelefone.getText().toString().length() < 7 || inputTelefone.getText().toString().length() > 7)
            mudarCorEditText(inputTelefone, "vermelho");
        else
            mudarCorEditText(inputTelefone, "");

        if (inputMorada.getText().toString().length() < 4)
            mudarCorEditText(inputMorada, "vermelho");
        else
            mudarCorEditText(inputMorada, "");

        if (inputEmail.getText().toString().length() < 4 || !(inputEmail.getText().toString().contains("@")))
            mudarCorEditText(inputEmail, "vermelho");
        else
            mudarCorEditText(inputEmail, "");

        if (inputTempo.getText().toString().length() < 1 || inputTempo.getText().toString().equals("0")) {
            mudarCorEditText(inputTempo, "vermelho");
        } else
            mudarCorEditText(inputTempo, "");

        if (errosCampos) {
            textErroCampos.setText("Campos(s) invalido(s)");
            textErroCampos.setTextColor(Color.rgb(200, 0, 0));
        } else
            textErroCampos.setText("");

        //-------------se tudo estiver guarda os dados e chama actividade------
        if (!errosCampos) {
            guardar();
            startActivity(ActividadeAserMostrada());

        }
        //--------------------------------------------------------------------

        //----reset da vareavel
        errosCampos = false;
    }

    private void mudarCorEditText(EditText editText, String cor) {
        if (cor.equalsIgnoreCase("vermelho")) {
            editText.setTextColor(Color.rgb(200, 0, 0));
            editText.getBackground().mutate().setColorFilter(Color.rgb(200, 0, 0), PorterDuff.Mode.SRC_ATOP);
            errosCampos = true;
        } else {
            editText.setTextColor(Color.rgb(0, 0, 0));
            editText.getBackground().mutate().setColorFilter(Color.rgb(0, 0, 0), PorterDuff.Mode.SRC_ATOP);
        }
    }
//------------------------------------O codigo acima e da validacao do formulario--------------------------------------------------------------


    private void guardar() {
        Preferences = getSharedPreferences(nameSharedPrefFile, MODE_PRIVATE);
        SharedPreferences.Editor nhaEditor = Preferences.edit();

        nhaEditor.putString("nome", inputNome.getText().toString());
        nhaEditor.putString("tempo", inputTempo.getText().toString());
        nhaEditor.putString("email", inputEmail.getText().toString());
        nhaEditor.putString("morada", inputMorada.getText().toString());
        nhaEditor.putString("telefone", inputTelefone.getText().toString());

        nhaEditor.apply();

    }

    private Intent ActividadeAserMostrada() {

        return new Intent(this,receberCodigoDesbloqueio.class);
    }

    public void verValorASerPago(View view) {
        if (inputTempo.getText().toString().isEmpty() || inputTempo.getText().toString().equals("0")) {
            textErroCampos.setText("Indica um tempo correto");
            textErroCampos.setTextColor(Color.rgb(200, 0, 0));
            mudarCorEditText(inputTempo, "vermelho");
            btVerValorPago.setText("Clique para ver valor a ser pago.   Preço: ?$00");
        } else {
            int valor = Integer.parseInt(inputTempo.getText().toString()) * precoDoAluguer;
            String texto = "valor a ser pago. Preço: " + valor + "$00";
            btVerValorPago.setText(texto);
            textErroCampos.setText("");
            mudarCorEditText(inputTempo, "");
        }
    }
}
