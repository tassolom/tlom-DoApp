package com.example.doapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tasso on 12/06/2017.
 */

public class BancoDados extends SQLiteOpenHelper{

    private static final int VERSAO_BD = 1;
    private static final String BANCO_DOADOR = "bd_doador";


    private static final String TABELA_DOADOR = "tb_doador";

    private static final String COLUNA_LOGIN = "login";
    private static final String COLUNA_SENHA = "senha";

    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_CPF = "cpf";
    private static final String COLUNA_END = "endereco";
    private static final String COLUNA_CONT = "contato";

    public BancoDados(Context context) {
        super(context, BANCO_DOADOR, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_DOADOR + "("
                + COLUNA_LOGIN + " TEXT PRIMARY KEY, " + COLUNA_SENHA + " TEXT, "
                + COLUNA_NOME + " TEXT, " + COLUNA_CPF + " TEXT, "
                + COLUNA_END + " TEXT, " + COLUNA_CONT + " TEXT)";

        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    /* CRUD ABAIXO */

    void addDoador(Doador doador) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_LOGIN, doador.getLogin());
        values.put(COLUNA_SENHA, doador.getSenha());

        values.put(COLUNA_NOME, doador.getNome());
        values.put(COLUNA_CPF, doador.getCpf());
        values.put(COLUNA_END, doador.getEndereco());
        values.put(COLUNA_CONT, doador.getContato());

        db.insert(TABELA_DOADOR, null, values);
        db.close();
    }

    void apagarDoador (Doador doador) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_DOADOR, COLUNA_LOGIN + " = ?", new String[]{doador.getLogin()});

        db.close();
    }

    Doador selecionarDoador (String nome) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_DOADOR, new String[] {COLUNA_LOGIN, COLUNA_SENHA, COLUNA_NOME,
                COLUNA_CPF, COLUNA_END, COLUNA_CONT}, COLUNA_LOGIN + " = ?",
                new String[] {String.valueOf(nome)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Doador doador = new Doador(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return doador;
    }

    void atualizarDoador (Doador doador) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, doador.getNome());
        values.put(COLUNA_CPF, doador.getCpf());
        values.put(COLUNA_END, doador.getEndereco());
        values.put(COLUNA_CONT, doador.getContato());

        db.update(TABELA_DOADOR, values, COLUNA_LOGIN + " = ?",
                new String[] { String.valueOf(doador.getNome()) });
    }

    public List<Doador> listaTodosDoadores() {
        List <Doador> listaDoador = new ArrayList<Doador>();

        String query = "SELECT + FROM " + TABELA_DOADOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                Doador doador = new Doador();
                doador.setLogin(c.getString(0));
                doador.setSenha(c.getString(1));
                doador.setNome(c.getString(2));
                doador.setCpf(c.getString(3));
                doador.setEndereco(c.getString(4));
                doador.setContato(c.getString(5));

                listaDoador.add(doador);
            } while (c.moveToNext());
        }

        return listaDoador;
    }
}
