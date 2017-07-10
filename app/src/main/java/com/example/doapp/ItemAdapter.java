package com.example.doapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tasso on 09/07/2017.
 */

public class ItemAdapter extends ArrayAdapter <Usuario> {
    public ItemAdapter(Context context, ArrayList<Usuario> instituicoes) { super (context, 0, instituicoes);}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Pega o dado do item para esta posição
        Usuario user = getItem( position );
        //Verifica se uma VIew existete está sendo usada, caso contrário, preenche a View
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate( R.layout.itemlista, parent, false );
        }
        // Checa View para popular
        TextView item_titulo = (TextView) convertView.findViewById(R.id.item_insituicao);
        TextView item_endereco = (TextView) convertView.findViewById(R.id.item_endereco);
        TextView item_itens = (TextView) convertView.findViewById(R.id.item_itens);

        item_titulo.setText(user.getNome());
        item_endereco.setText(user.getEndereco());
        //fazer uma pesquisa pelos itens desse usuário e retornar uma string concatendada do itens
        item_itens.setText("");

        return convertView;
    }

}
