package com.example.doapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.checked;

/**
 * Created by Tasso on 04/06/2017.
 */

public class CadastroDoador extends Activity {

    EditText editLogin, editSenha, editNome, editCpf, editEndereco, editContato;
    CheckBox item1, item2, item3, item4, item5, item6, item7, item8, item9;

    public static final String LOGIN = "login";
    public static final String SENNHA = "senha";

    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String END = "endereco";
    public static final String CONT = "contato";

    BancoDados db = new BancoDados(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // o xml cadastro_instituicao é um Fragmente que chama o método abaixo
        setContentView(R.layout.cadastro_doador);

        //db.deleteBancoDeDados();

        //---------EDIT TEXTs--------------
        editLogin = (EditText) findViewById(R.id.loginDoador);
        editSenha = (EditText) findViewById(R.id.senhaDoador);

        editNome = (EditText) findViewById(R.id.nomeDoador);
        editCpf = (EditText) findViewById(R.id.cpfDoador);
        editEndereco = (EditText) findViewById(R.id.enderecoDoador);
        editContato = (EditText) findViewById(R.id.contatoDoador);

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

        final Button okButton = (Button) findViewById(R.id.okButtonDoador);
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Toast.makeText(CadastroDoador.this, itens, Toast.LENGTH_LONG).show();

                Doador doador = new Doador (editLogin.getText().toString(),
                        editSenha.getText().toString(),
                        editNome.getText().toString(),
                        editCpf.getText().toString(),
                        editEndereco.getText().toString(),
                        editContato.getText().toString());

                // addUsuario pega tudo menos cpf
                db.addUsuario( doador );
                // addDoador pega somente o cpf
                //db.addDoador( doador );

                onCheckboxClicked( doador );

                Toast.makeText(CadastroDoador.this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();

                listarUsuarios();
                listarDoadores();
                listarItensDoador();

                finish();
            }
        });

        /* TESTE DO CRUD */

        //insert ok
        /*db.addDoador(new Doador("tassoeu","tassosenha","Tasso","Rua Camboim, 941","09029194405","999004401"));
        db.addDoador(new Doador("tercioeu","terciosenha","Tércio","Rua Camboim, 941","09029194401","999004402"));

        Toast.makeText(CadastroDoador.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();*/

        //delete ok
        /*Doador doador = new Doador();
        doador.setID(1);
        db.apagarDoador(doador);

        Toast.makeText(CadastroDoador.this, "Apagado com sucesso", Toast.LENGTH_LONG).show();*/

        //slect ok
        /*Doador doador = db.selecionarDoador(2);
        Log.d("Doador selecionado", "Nome: " + doador.getNome() + " Endereço: " + doador.getEndereco()
        + " CPF: " + doador.getCpf() + " Contato: " + doador.getContato());*/

        //update ok
        /*Doador doador = new Doador();
        doador.setNome("Tasso");
        doador.setCpf("0000000000");
        doador.setEndereco("Rua Baltazar Passos");
        doador.setContato("34619020");

        db.atualizarDoador(doador);

        Toast.makeText(CadastroDoador.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();*/

    }

    // quando a CheckBox é selecionada, é adicionada a tabela intem doador o id do respectivo item e do doador
    void onCheckboxClicked(Doador doador) {

        if(item1.isChecked())
            db.addItensDoador(1, doador.getID());
        if(item2.isChecked())
            db.addItensDoador(2, doador.getID());
        if(item3.isChecked())
            db.addItensDoador(3, doador.getID());
        if(item4.isChecked())
            db.addItensDoador(4, doador.getID());
        if(item5.isChecked())
            db.addItensDoador(5, doador.getID());
        if(item6.isChecked())
            db.addItensDoador(6, doador.getID());
        if(item7.isChecked())
            db.addItensDoador(7, doador.getID());
        if(item8.isChecked())
            db.addItensDoador(8, doador.getID());
        if(item9.isChecked())
            db.addItensDoador(9, doador.getID());
    }

    public void listarDoadores() {
        List<BancoDados.TbDoadores> doadores = db.listaTodosDoadores();

        for(BancoDados.TbDoadores d : doadores) {
            Log.d("Lista", "\nTABELA DOADOR: " + d.getID() + " " + d.getCpf());
        }
    }

    public void listarUsuarios() {
        List<Usuario> usuarios = db.listaTodosUsuarios();

        for(Usuario u : usuarios) {
            Log.d("Lista", "\nTABELA USUÁRIO: " + u.getID() + " " + u.getLogin() + " " + u.getSenha() + " "
                    + u.getNome() + " " + u.getEndereco() + " " + u.getContato());
        }
    }

    public void listarItensDoador() {
        List<BancoDados.TbDoisIDs> usuarios = db.listaTodosItensDoad();

        for(BancoDados.TbDoisIDs id : usuarios) {
            Log.d("Lista", "\nTABELA ITENS USUÁRIO: " + id.getID1() + " " + id.getID2());
        }
    }

    //Fragmento que mostra o cadastro da instituição
    /*public static class InstitPreferenceFragment extends PreferenceFragment {
        private SharedPreferences.OnSharedPreferenceChangeListener mListener;
        private Preference mNome;
        private Preference mCPF;
        private Preference mEndereco;
        private Preference mContato;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Carrega preferences a partir do XML expecificado
            addPreferencesFromResource(R.xml.instituicao_preferencias);

            // pega as Preferences
            mNome = (Preference) getPreferenceManager().findPreference(NOME);
            mCPF = (Preference) getPreferenceManager().findPreference(CPF);
            mEndereco = (Preference) getPreferenceManager().findPreference(END);
            mContato = (Preference) getPreferenceManager().findPreference(CONT);

            // Define um listener para atualizar descricao do nome ao modificar preferences
            mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
                    mNome.setSummary( sharedPreferences.getString( NOME, "Nome" ));
                }
            };

            // Define um listener para atualizar descricao do CPF ao modificar preferences
            mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
                    mCPF.setSummary( sharedPreferences.getString( CPF, "CPF" ));
                }
            };

            // Define um listener para atualizar descricao do endereço ao modificar preferences
            mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
                    mEndereco.setSummary( sharedPreferences.getString( END, "Endereço" ));
                }
            };

            // Define um listener para atualizar descricao do contato ao modificar preferences
            mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
                    mContato.setSummary( sharedPreferences.getString( CONT, "Contato" ));
                }
            };

            // Pega objeto SharedPreferences gerenciado pelo PreferenceManager para este Fragmento
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

            // Registra listener no objeto SharedPreferences
            prefs.registerOnSharedPreferenceChangeListener(mListener);
        }
    }*/
}
