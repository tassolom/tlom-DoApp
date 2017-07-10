package com.example.doapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tasso on 08/07/2017.
 */

public class ListaInst extends Activity {

    public ListView listaInst;
    public ArrayList <Usuario> listInstArrayAdapter = new ArrayList<Usuario>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listaInst = (ListView) findViewById( R.id.lv_Elementos );
        List<Usuario> instituicoes = new ArrayList<>();

        listInstArrayAdapter = (ArrayList<Usuario>) instituicoes;
        ItemAdapter adapter = new ItemAdapter ( ListaInst.this, listInstArrayAdapter );
        listaInst.setAdapter( adapter );
    }


}
