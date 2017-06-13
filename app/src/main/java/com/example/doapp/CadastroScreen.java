package com.example.doapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tasso on 04/06/2017.
 */

public class CadastroScreen extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        /*final Button institut = (Button) findViewById(R.id.instituicao);
        institut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroScreen.this, CadastroInstituicao.class);
                startActivity(intent);
            }
        });*/

        final Button doador = (Button) findViewById(R.id.doador);
        doador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroScreen.this, CadastroDoador.class);
                startActivity(intent);
            }
        });

    }
}
