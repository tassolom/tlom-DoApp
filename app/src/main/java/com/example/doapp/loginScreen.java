package com.example.doapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tasso on 04/06/2017.
 */

public class loginScreen extends Activity {

    EditText editLogin, editSenha;

    BancoDados db = new BancoDados(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editLogin = (EditText) findViewById(R.id.login);
        editSenha = (EditText) findViewById(R.id.senha);

        final Button login = (Button) findViewById(R.id.logOK);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Doador doador = db.selecionarDoador(editLogin.getText().toString());
                //LOGIN E SENHA A SEREM VALIDADOS
                String validarLS = doador.getLogin() + doador.getSenha();
                //LOGIN E SENHA DIGITADOS NOS CAMPOS login e senha
                String entradaLS = editLogin.getText().toString() + editSenha.getText().toString();

                if (validarLS.equals(entradaLS)){
                    Toast.makeText(loginScreen.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(loginScreen.this, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
                }

            }
        });


        /*final Button logarFb = (Button) findViewById(R.id.logarFB);
        logarFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        final Button cadastro = (Button) findViewById(R.id.cadastro);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastro = new Intent(loginScreen.this, CadastroScreen.class);
                startActivity(cadastro);
            }
        });

    }
}
