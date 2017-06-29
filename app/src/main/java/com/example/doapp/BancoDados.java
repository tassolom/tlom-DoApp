package com.example.doapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

    static final int VERSAO_BD = 1;
    static final String BANCO_DOADOR = "bd_doador";

    static final String TABELA_DOADOR = "tb_doador";
    static final String TABELA_INSTITUICAO = "tb_instituicao";

    static final String _ID = "_id";

    static final String COLUNA_LOGIN = "login";
    static final String COLUNA_SENHA = "senha";

    static final String COLUNA_NOME = "nome";
    static final String COLUNA_CPF = "cpf";
    static final String COLUNA_CNPJ = "cnpj";
    static final String COLUNA_END = "endereco";
    static final String COLUNA_CONT = "contato";
    static final String COLUNA_ITENS = "itens";

    /*private static final String[] colunas = { _ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_NOME,
            COLUNA_CPF, COLUNA_END, COLUNA_CONT};

    private static final String CREATE_CMD =
            "CREATE TABLE " + TABELA_DOADOR + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_LOGIN + " TEXT NOT NULL, " + COLUNA_SENHA + " TEXT NOT NULL, "
                    + COLUNA_NOME + " TEXT NOT NULL, " + COLUNA_CPF + " TEXT NOT NULL, "
                    + COLUNA_END + " TEXT NOT NULL, " + COLUNA_CONT + " TEXT NOT NULL)";*/

    final private Context mContext;

    public BancoDados(Context context) {

        super(context, BANCO_DOADOR, null, VERSAO_BD);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CMD_DOA = "CREATE TABLE " + TABELA_DOADOR + "("
                + _ID + " TEXT PRIMARY KEY, "
                + COLUNA_LOGIN + " TEXT, " + COLUNA_SENHA + " TEXT, "
                + COLUNA_NOME + " TEXT, " + COLUNA_CPF + " TEXT, "
                + COLUNA_END + " TEXT, " + COLUNA_CONT + " TEXT, "
                + COLUNA_ITENS + " TEXT)";

        db.execSQL(CREATE_CMD_DOA);

        String CREATE_CMD_INST = "CREATE TABLE " + TABELA_INSTITUICAO + "("
                + _ID + " TEXT PRIMARY KEY, "
                + COLUNA_LOGIN + " TEXT, " + COLUNA_SENHA + " TEXT, "
                + COLUNA_NOME + " TEXT, " + COLUNA_CNPJ + " TEXT, "
                + COLUNA_END + " TEXT, " + COLUNA_CONT + " TEXT, "
                + COLUNA_ITENS + " TEXT)";

        db.execSQL(CREATE_CMD_INST);
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
        values.put(COLUNA_ITENS, doador.getItens());

        db.insert(TABELA_DOADOR, null, values);
        db.close();
    }

    void addInstituicao(Instituicao instituicao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_LOGIN, instituicao.getLogin());
        values.put(COLUNA_SENHA, instituicao.getSenha());

        values.put(COLUNA_NOME, instituicao.getNome());
        values.put(COLUNA_CNPJ, instituicao.getCnpj());
        values.put(COLUNA_END, instituicao.getEndereco());
        values.put(COLUNA_CONT, instituicao.getContato());
        values.put(COLUNA_ITENS, instituicao.getItens());

        db.insert(TABELA_INSTITUICAO, null, values);
        db.close();
    }

    void apagarDoador (Doador doador) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_DOADOR, COLUNA_LOGIN + " = ?", new String[]{String.valueOf(doador.getLogin())});

        db.close();
    }

    Doador selecionarDoador (String login) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_DOADOR,
                new String[] {_ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_NOME,
                COLUNA_CPF, COLUNA_END, COLUNA_CONT, COLUNA_ITENS},
                COLUNA_LOGIN + " = ?",
                new String[] {String.valueOf(login)},
                null,
                null,
                null );

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Doador doador = new Doador(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7));

        return doador;
    }

    void atualizarDoador (Doador doador) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, doador.getNome());
        values.put(COLUNA_CPF, doador.getCpf());
        values.put(COLUNA_END, doador.getEndereco());
        values.put(COLUNA_CONT, doador.getContato());
        values.put(COLUNA_ITENS, doador.getItens());

        db.update(TABELA_DOADOR, values, COLUNA_LOGIN + " = ?",
                new String[] { String.valueOf(doador.getNome()) });
    }

    public List<Doador> listaTodosDoadores() {
        List <Doador> listaDoador = new ArrayList<Doador>();

        String query = "SELECT * FROM " + TABELA_DOADOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                Doador doador = new Doador();
                doador.setID(Integer.parseInt(c.getString(0)));
                doador.setLogin(c.getString(1));
                doador.setSenha(c.getString(2));
                doador.setNome(c.getString(3));
                doador.setCpf(c.getString(4));
                doador.setEndereco(c.getString(5));
                doador.setContato(c.getString(6));
                doador.setItens(c.getString(7));

                listaDoador.add(doador);
            } while (c.moveToNext());
        }

        return listaDoador;
    }

    public List<Instituicao> listaTodasInstituicoes() {
        List <Instituicao> listaInstituicao = new ArrayList<Instituicao>();

        return listaInstituicao;
    }

    void deleteBancoDeDados () {
        mContext.deleteDatabase(BANCO_DOADOR);
    }
}
