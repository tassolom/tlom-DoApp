package com.example.doapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Tasso on 04/06/2017.
 */

public class CadastroDoador extends Activity {

    EditText editLogin, editSenha, editNome, editCpf, editEndereco, editContato;

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

        editLogin = (EditText) findViewById(R.id.loginDoador);
        editSenha = (EditText) findViewById(R.id.senhaDoador);

        editNome = (EditText) findViewById(R.id.nomeDoador);
        editCpf = (EditText) findViewById(R.id.cpfDoador);
        editEndereco = (EditText) findViewById(R.id.enderecoDoador);
        editContato = (EditText) findViewById(R.id.contatoDoador);

        final Button okButton = (Button) findViewById(R.id.okButtonDoador);
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db.addDoador( new Doador(editLogin.getText().toString(),
                        editSenha.getText().toString(),
                        editNome.getText().toString(),
                        editCpf.getText().toString(),
                        editEndereco.getText().toString(),
                        editContato.getText().toString())
                );

                Toast.makeText(CadastroDoador.this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();

                listarDoadores();

                finish();
            }
        });

        /* TESTE DO CRUD */

        //insert ok
        /*db.addDoador(new Doador("Tasso","Rua Camboim, 941","09029194405","999004401"));
        db.addDoador(new Doador("Tércio","Rua Camboim, 941","09029194405","999004401"));

        Toast.makeText(CadastroDoador.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();*/

        //delete ok
        /*Doador doador = new Doador();
        doador.setNome("Tércio");
        db.apagarDoador(doador);

        Toast.makeText(CadastroDoador.this, "Apagado com sucesso", Toast.LENGTH_LONG).show();*/

        //slect ok
        /*Doador doador = db.selecionarDoador("Tasso");
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

    public void listarDoadores() {
        List<Doador> doadores = db.listaTodosDoadores();

        for(Doador d : doadores) {
            Log.d("Lista", "\nDados: " + d.getLogin() + d.getSenha() + d.getNome() + d.getCpf()
            + d.getEndereco() + d.getContato());
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
