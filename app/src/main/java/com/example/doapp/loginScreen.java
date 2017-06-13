package com.example.doapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tasso on 04/06/2017.
 */

public class loginScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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
