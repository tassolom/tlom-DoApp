package com.example.doapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Tasso on 29/06/2017.
 */

public class CadastroInstituicao extends Activity {

    EditText editLogin, editSenha, editNome, editCnpj, editEndereco, editContato;
    CheckBox item1, item2, item3, item4, item5, item6, item7, item8, item9;

    public static final String LOGIN = "login";
    public static final String SENNHA = "senha";

    public static final String NOME = "nome";
    public static final String CNPJ = "cnpj";
    public static final String END = "endereco";
    public static final String CONT = "contato";

    BancoDados db = new BancoDados(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // o xml cadastro_instituicao é um Fragmente que chama o método abaixo
        setContentView(R.layout.cadastro_instituicao);

        //---------EDIT TEXTs--------------
        editLogin = (EditText) findViewById(R.id.loginInstituicao);
        editSenha = (EditText) findViewById(R.id.senhaInstituicao);

        editNome = (EditText) findViewById(R.id.nomeInstituicao);
        editCnpj = (EditText) findViewById(R.id.cnpjfInstituicao);
        editEndereco = (EditText) findViewById(R.id.enderecoInstituicao);
        editContato = (EditText) findViewById(R.id.contatoInstituicao);

        //---------CHECKBOXs--------------
        item1 = (CheckBox) findViewById(R.id.item1);
        item2 = (CheckBox) findViewById(R.id.item2);
        item3 = (CheckBox) findViewById(R.id.item3);
        item4 = (CheckBox) findViewById(R.id.item4);
        item5 = (CheckBox) findViewById(R.id.item5);
        item6 = (CheckBox) findViewById(R.id.item6);
        item7 = (CheckBox) findViewById(R.id.item7);
        item8 = (CheckBox) findViewById(R.id.item8);
        item9 = (CheckBox) findViewById(R.id.item9);

        final Button okButton = (Button) findViewById(R.id.okButtonInstituicao);
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String itens = onCheckboxClicked();
                Toast.makeText(CadastroInstituicao.this, itens, Toast.LENGTH_LONG).show();

                db.addInstituicao( new Instituicao(editLogin.getText().toString(),
                        editSenha.getText().toString(),
                        editNome.getText().toString(),
                        editCnpj.getText().toString(),
                        editEndereco.getText().toString(),
                        editContato.getText().toString())
                );

                Toast.makeText(CadastroInstituicao.this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();

                listarInstituicoes();

                finish();
            }
        });

        /* TESTE DO CRUD */

        //insert ok
        /*db.addInstituicao(new Instituicao("tassoeu","tassosenha","Tasso","Rua Camboim, 941","09029194405","999004401"));
        db.addInstituicao(new Instituicao("tercioeu","terciosenha","Tércio","Rua Camboim, 941","09029194401","999004402"));

        Toast.makeText(CadastroInstituicao.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();*/

        //delete ok
        /*Instituicao instituicao = new Instituicao();
        instituicao.setID(1);
        db.apagarInstituicao(instituicao);

        Toast.makeText(CadastroInstituicao.this, "Apagado com sucesso", Toast.LENGTH_LONG).show();*/

        //slect ok
        /*Instituicao instituicao = db.selecionarInstituicao(2);
        Log.d("Instituicao selecionado", "Nome: " + instituicao.getNome() + " Endereço: " + instituicao.getEndereco()
        + " CNPJ: " + instituicao.getCnpj() + " Contato: " + instituicao.getContato());*/

        //update ok
        /*Instituicao instituicao = new Instituicao();
        instituicao.setNome("Tasso");
        instituicao.setCnpj("0000000000");
        instituicao.setEndereco("Rua Baltazar Passos");
        instituicao.setContato("34619020");

        db.atualizarInstituicao(instituicao);

        Toast.makeText(CadastroInstituicao.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();*/

    }

    public String onCheckboxClicked() {

        String itens = "";

        // Check which checkbox was clicked

        if(item1.isChecked())
            itens = itens + "1";
        if(item2.isChecked())
            itens = itens + "2";
        if(item3.isChecked())
            itens = itens + "3";
        if(item4.isChecked())
            itens = itens + "4";
        if(item5.isChecked())
            itens = itens + "5";
        if(item6.isChecked())
            itens = itens + "6";
        if(item7.isChecked())
            itens = itens + "7";
        if(item8.isChecked())
            itens = itens + "8";
        if(item9.isChecked())
            itens = itens + "9";

        return itens;
    }

    public void listarInstituicoes() {
        List<Instituicao> instituicoes = db.listaTodasInstituicoes();

        for(Instituicao d : instituicoes) {
            Log.d("Lista", "\nDados: " + d.getID() + " " + d.getLogin() + " " + d.getSenha() + " "
                    + d.getNome() + " " + d.getCnpj() + " " + d.getEndereco() + " " + d.getContato());
        }
    }
}
