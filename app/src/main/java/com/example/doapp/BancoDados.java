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
    static final String TABELA_ITENS_DOADOR = "tb_itens_doador";
    static final String TABELA_ITENS_INSTITUICAO = "tb_itens_instituicao";
    static final String TABELA_USUARIO = "tb_usuario";
    static final String TABELA_ITENS = "tb_itens";
    static final String TABELA_REGISTRO = "tb_registro";

    static final String _ID = "_id";
    static final String _ID_INST = "_id_isntituicao";
    static final String _ID_DOAD = "_id_doador";
    static final String _ID_ITEM = "_id_item";

    static final String COLUNA_LOGIN = "login";
    static final String COLUNA_SENHA = "senha";

    static final String COLUNA_NOME = "nome";
    static final String COLUNA_CPF = "cpf";
    static final String COLUNA_CNPJ = "cnpj";
    static final String COLUNA_END = "endereco";
    static final String COLUNA_CONT = "contato";
    static final String COLUNA_ITEM = "item";

    /*private static final String[] colunas = { _ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_NOME,
            COLUNA_CPF, COLUNA_END, COLUNA_CONT};

    private static final String CREATE_CMD =
            "CREATE TABLE " + TABELA_DOADOR + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_LOGIN + " TEXT NOT NULL, " + COLUNA_SENHA + " TEXT NOT NULL, "
                    + COLUNA_NOME + " TEXT NOT NULL, " + COLUNA_CPF + " TEXT NOT NULL, "
                    + COLUNA_END + " TEXT NOT NULL, " + COLUNA_CONT + " TEXT NOT NULL)";*/

    final private Context mContext;

    // CLASSE COM ID E CPF
    public class TbDoadores {
        int ID;
        String CPF;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCpf() {
            return CPF;
        }

        public void setCpf(String CPF) {
            this.CPF = CPF;
        }
    }


    // CLASSE COM ID E CNPJ
    public class TbInstituicoes {
        int ID;
        String CNPJ;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCnpj() {
            return CNPJ;
        }

        public void setCnpj(String CNPJ) {
            this.CNPJ = CNPJ;
        }
    }

    public class TbDoisIDs {
        int ID1;
        int ID2;

        public void setID1(int ID1) {
            this.ID1 = ID1;
        }

        public int getID1() {
            return ID1;
        }

        public void setID2(int ID2) {
            this.ID2 = ID2;
        }

        public int getID2() {
            return ID2;
        }
    }

    public BancoDados(Context context) {

        super(context, BANCO_DOADOR, null, VERSAO_BD);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //TABELA DE USUARIOS
        String CREATE_CMD_USUARIO = "CREATE TABLE " + TABELA_USUARIO + "("
                + _ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_LOGIN + " TEXT NOT NULL, "
                + COLUNA_SENHA + " TEXT NOT NULL, "
                + COLUNA_NOME + " TEXT NOT NULL, "
                + COLUNA_END + " TEXT NOT NULL, "
                + COLUNA_CONT + " TEXT NOT NULL)";

        db.execSQL(CREATE_CMD_USUARIO);

        //TABELA DE DOADORES
        String CREATE_CMD_DOADOR = "CREATE TABLE " + TABELA_DOADOR + "("
                + _ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUNA_CPF + " TEXT NOT NULL,"
                + "FOREIGN KEY(" + _ID + ") "
                + "REFERENCES " + TABELA_USUARIO + "(" + _ID + "))";

        db.execSQL(CREATE_CMD_DOADOR);

        //TABELA DE INSTITUIÇÕES
        String CREATE_CMD_INSITUICAO = "CREATE TABLE " + TABELA_INSTITUICAO + "("
                + _ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUNA_CNPJ + " TEXT NOT NULL,"
                + "FOREIGN KEY(" + _ID + ") "
                + "REFERENCES " + TABELA_USUARIO + "(" + _ID + "))";

        db.execSQL(CREATE_CMD_INSITUICAO);

        //TABELA DE INTENS
        String CREATE_CMD_INTENS = "CREATE TABLE " + TABELA_ITENS + "("
                + _ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUNA_ITEM + " TEXT NOT NULL)";

        db.execSQL(CREATE_CMD_INTENS);

        //TABELA DE INTENS DAS INSTITUIÇÕES
        String CREATE_CMD_INTENS_INST = "CREATE TABLE " + TABELA_ITENS_INSTITUICAO + "("
                + _ID_ITEM + " INTEGER NOT NULL, "
                + _ID_INST + " INTEGER NOT NULL, PRIMARY KEY (" + _ID_ITEM + ", " + _ID_INST + "), "
                + "FOREIGN KEY(" + _ID_ITEM + ") "
                + "REFERENCES " + TABELA_ITENS + "(" + _ID_ITEM + "), "
                + "FOREIGN KEY(" + _ID_INST + ") "
                + "REFERENCES " + TABELA_ITENS_INSTITUICAO + "(" + _ID_INST + "))";

        db.execSQL(CREATE_CMD_INTENS_INST);

        //TABELA DE INTENS DOS DOADORES
        String CREATE_CMD_INTENS_DOAD = "CREATE TABLE " + TABELA_ITENS_DOADOR + "("
                + _ID_ITEM + " INTEGER NOT NULL, "
                + _ID_DOAD + " INTEGER NOT NULL, PRIMARY KEY (" + _ID_ITEM + ", " + _ID_DOAD + "), "
                + "FOREIGN KEY(" + _ID_ITEM + ") "
                + "REFERENCES " + TABELA_ITENS + "(" + _ID_ITEM + "), "
                + "FOREIGN KEY(" + _ID_DOAD + ") "
                + "REFERENCES " + TABELA_ITENS_DOADOR + "(" + _ID_DOAD + "))";

        db.execSQL(CREATE_CMD_INTENS_DOAD);

        //TABELA DE REGISTRO DE DOAÇÕES
        String CREATE_CMD_REGITRO = "CREATE TABLE " + TABELA_REGISTRO + "("
                + _ID_ITEM + " INTEGER NOT NULL, "
                + _ID_INST + " INTEGER NOT NULL, "
                + _ID_DOAD + " INTEGER NOT NULL, PRIMARY KEY (" + _ID_ITEM + ", " + _ID_INST + ", " + _ID_DOAD + "), "
                + "FOREIGN KEY(" + _ID_ITEM + ") "
                + "REFERENCES " + TABELA_ITENS + "(" + _ID_ITEM + "), "
                + "FOREIGN KEY(" + _ID_INST + ") "
                + "REFERENCES " + TABELA_ITENS_INSTITUICAO + "(" + _ID_INST + "), "
                + "FOREIGN KEY(" + _ID_DOAD + ") "
                + "REFERENCES " + TABELA_ITENS_DOADOR + "(" + _ID_DOAD + "))";

        db.execSQL(CREATE_CMD_REGITRO);



        /*String CREATE_CMD_DOA = "CREATE TABLE " + TABELA_DOADOR + "("
                + _ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_LOGIN + " TEXT NOT NULL, " + COLUNA_SENHA + " TEXT NOT NULL, "
                + COLUNA_NOME + " TEXT NOT NULL, " + COLUNA_CPF + " TEXT NOT NULL, "
                + COLUNA_END + " TEXT NOT NULL, " + COLUNA_CONT + " TEXT NOT NULL)";

        db.execSQL(CREATE_CMD_DOA);

        String CREATE_CMD_INST = "CREATE TABLE " + TABELA_INSTITUICAO + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_LOGIN + " TEXT NOT NULL, " + COLUNA_SENHA + " TEXT NOT NULL, "
                + COLUNA_NOME + " TEXT NOT NULL, " + COLUNA_CNPJ + " TEXT NOT NULL, "
                + COLUNA_END + " TEXT NOT NULL, " + COLUNA_CONT + " TEXT NOT NULL)";

        db.execSQL(CREATE_CMD_INST);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    /* CRUD ABAIXO */

    // CRUD USUARIO
    void addUsuario (Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_LOGIN, usuario.getLogin());
        values.put(COLUNA_SENHA, usuario.getSenha());

        values.put(COLUNA_NOME, usuario.getNome());
        values.put(COLUNA_END, usuario.getEndereco());
        values.put(COLUNA_CONT, usuario.getContato());

        db.insert(TABELA_USUARIO, null, values);

        //pega o ultimo id adicionado a tabela usuário
        String query = "SELECT MAX(" + _ID + ") as maximo FROM " + TABELA_USUARIO;
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            //atibui ao objeto usuário sua ID
            usuario.setID(c.getInt(0));
        }

        // verifica se o usuário é do tipo doador ou instituição
        if(usuario instanceof Doador){

            //System.out.println(">>>>>>>>> id doador: " + usuario.getID());
            addDoador((Doador) usuario);
        } else {

            //System.out.println(">>>>>>>>> id instituição: " + usuario.getID());
            addInstituicao((Instituicao) usuario);
        }

        db.close();
    }

    void apagarUsuario (Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_DOADOR, COLUNA_LOGIN + " = ?", new String[]{String.valueOf(usuario.getLogin())});

        db.close();
    }

    Usuario selecionarUsuario (String login) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_USUARIO,
                new String[] {_ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_NOME,
                        COLUNA_END, COLUNA_CONT},
                COLUNA_LOGIN + " = ?",
                new String[] {String.valueOf(login)},
                null,
                null,
                null );

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Usuario usuario = new Usuario(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return usuario;
    }

    void atualizarUsuario (Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, usuario.getNome());
        values.put(COLUNA_END, usuario.getEndereco());
        values.put(COLUNA_CONT, usuario.getContato());

        db.update(TABELA_USUARIO, values, COLUNA_LOGIN + " = ?",
                new String[] { String.valueOf(usuario.getNome()) });
    }

    public List<Usuario> listaTodosUsuarios() {
        List <Usuario> listaUsuario = new ArrayList<Usuario>();

        String query = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();

                usuario.setID(Integer.parseInt(c.getString(0)));
                usuario.setLogin(c.getString(1));
                usuario.setSenha(c.getString(2));
                usuario.setNome(c.getString(3));
                usuario.setEndereco(c.getString(4));
                usuario.setContato(c.getString(5));

                listaUsuario.add(usuario);
            } while (c.moveToNext());
        }

        return listaUsuario;
    }

    //CRUD DOADOR
    void addDoador(Doador doador) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(_ID, doador.getID());
        values.put(COLUNA_CPF, doador.getCpf());

        db.insert(TABELA_DOADOR, null, values);
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
                        COLUNA_CPF, COLUNA_END, COLUNA_CONT},
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
                cursor.getString(6));

        return doador;
    }

    void atualizarDoador (Doador doador) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, doador.getNome());
        values.put(COLUNA_CPF, doador.getCpf());
        values.put(COLUNA_END, doador.getEndereco());
        values.put(COLUNA_CONT, doador.getContato());
        //values.put(COLUNA_ITENS, doador.getItens());

        db.update(TABELA_DOADOR, values, COLUNA_LOGIN + " = ?",
                new String[] { String.valueOf(doador.getNome()) });
    }

    // lista a tabela de doadores que possui o id do doador e seu cpf
    public List<TbDoadores> listaTodosDoadores() {
        List <TbDoadores> listaDoador = new ArrayList<TbDoadores>();

        String query = "SELECT * FROM " + TABELA_DOADOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                TbDoadores doador = new TbDoadores();

                //System.out.println(">>>>>>>>>>>>>>>>>>>>>> doador id = " + c.getString(0));

                doador.setID(Integer.parseInt(c.getString(0)));
                doador.setCpf(c.getString(1));

                listaDoador.add(doador);
            } while (c.moveToNext());
        }

        return listaDoador;
    }

    //CRUD INSTITUIÇÃO
    void addInstituicao(Instituicao instituicao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(_ID, instituicao.getID());
        values.put(COLUNA_CNPJ, instituicao.getCnpj());

        db.insert(TABELA_INSTITUICAO, null, values);
        db.close();
    }

    void apagarInstituicao (Instituicao instituicao) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_DOADOR, COLUNA_LOGIN + " = ?", new String[]{String.valueOf(instituicao.getLogin())});

        db.close();
    }

    Doador selecionarInstituicao (String login) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA_INSTITUICAO,
                new String[] {_ID, COLUNA_LOGIN, COLUNA_SENHA, COLUNA_NOME,
                        COLUNA_CNPJ, COLUNA_END, COLUNA_CONT},
                COLUNA_LOGIN + " = ?",
                new String[] {String.valueOf(login)},
                null,
                null,
                null );

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Doador instituicao = new Doador(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6));

        return instituicao;
    }

    void atualizarInstituicao (Instituicao instituicao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, instituicao.getNome());
        values.put(COLUNA_CNPJ, instituicao.getCnpj());
        values.put(COLUNA_END, instituicao.getEndereco());
        values.put(COLUNA_CONT, instituicao.getContato());
        //values.put(COLUNA_ITENS, doador.getItens());

        db.update(TABELA_INSTITUICAO, values, COLUNA_LOGIN + " = ?",
                new String[] { String.valueOf(instituicao.getNome()) });
    }

    // lista a tabela de instituições que possui o id do doador e seu cpf
    public List<TbInstituicoes> listaTodasInstituicoes() {
        List <TbInstituicoes> listaInstituicao = new ArrayList<TbInstituicoes>();

        String query = "SELECT * FROM " + TABELA_INSTITUICAO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                TbInstituicoes instituicoes = new TbInstituicoes();

                //System.out.println(">>>>>>>>>>>>>>>>>>>>>> doador id = " + c.getString(0));

                instituicoes.setID(Integer.parseInt(c.getString(0)));
                instituicoes.setCnpj(c.getString(1));

                listaInstituicao.add(instituicoes);
            } while (c.moveToNext());
        }

        return listaInstituicao;
    }

    //A TABELA DE ITENS É UMA TABELA FIXA
    void addItens() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_ITEM, "Alimentos Não Perecíveis");
        values.put(COLUNA_ITEM, "Água");
        values.put(COLUNA_ITEM, "Higiene Pessoal");
        values.put(COLUNA_ITEM, "Roupas");
        values.put(COLUNA_ITEM, "Roupas de cama");
        values.put(COLUNA_ITEM, "Livros");
        values.put(COLUNA_ITEM, "Brinquedos");
        values.put(COLUNA_ITEM, "Móveis");
        values.put(COLUNA_ITEM, "Eletrônicos");

        db.insert(TABELA_ITENS, null, values);
        db.close();
    }

    //CRUD ITENS DA INSTITUIÇÃO
    void addItensInst (int id_item, int id_inst){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(_ID_ITEM, id_item);
        values.put(_ID_INST, id_inst);

        db.insert(TABELA_ITENS_INSTITUICAO, null, values);
        db.close();
    }

    // lista a tabela de Itens das Instituições que possuem o ID do item e o ID da instituição que precisa daquele item
    public List<TbDoisIDs> listaTodosItensInst() {
        List <TbDoisIDs> listaItensInst = new ArrayList<TbDoisIDs>();

        String query = "SELECT * FROM " + TABELA_ITENS_INSTITUICAO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                TbDoisIDs itensDoador = new TbDoisIDs();

                //System.out.println(">>>>>>>>>>>>>>>>>>>>>> doador id = " + c.getString(0));

                itensDoador.setID1(Integer.parseInt(c.getString(0)));
                itensDoador.setID2(Integer.parseInt(c.getString(1)));

                listaItensInst.add(itensDoador);
            } while (c.moveToNext());
        }

        return listaItensInst;
    }

    //CRUD ITENS DO DOADOR
    void addItensDoador (int id_item, int id_doador){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(_ID_ITEM, id_item);
        values.put(_ID_DOAD, id_doador);

        db.insert(TABELA_ITENS_DOADOR, null, values);
        db.close();
    }

    // lista a tabela de Itens dos Doadores que possuem o ID do item e o ID do doador que disponibliza aquele item
    public List<TbDoisIDs> listaTodosItensDoad() {
        List <TbDoisIDs> listaItensDoador = new ArrayList<TbDoisIDs>();

        String query = "SELECT * FROM " + TABELA_ITENS_DOADOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()) {
            do {
                TbDoisIDs itensDoador = new TbDoisIDs();

                //System.out.println(">>>>>>>>>>>>>>>>>>>>>> doador id = " + c.getString(0));

                itensDoador.setID1(Integer.parseInt(c.getString(0)));
                itensDoador.setID2(Integer.parseInt(c.getString(1)));

                listaItensDoador.add(itensDoador);
            } while (c.moveToNext());
        }

        return listaItensDoador;
    }


    //CRUD REGISTRO
    void addRegistro (int id_item, int id_inst, int id_doador){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(_ID_ITEM, id_item);
        values.put(_ID_INST, id_inst);
        values.put(_ID_DOAD, id_doador);

        db.insert(TABELA_REGISTRO, null, values);
        db.close();
    }

    // ----------------- SELECTs ----------------

    /*void matchDoadorInstituicoes (Usuario usuario) {
        SQLiteDatabase db= this.getWritableDatabase();

        *//*String query = "SELEC FROM "
                + TABELA_ITENS_DOADOR + " TbItDo, "
                + "WHERE "
                + "TbItDo." + _ID_DOAD + " = " + usuario.getID() + ";";

        Cursor c = db.rawQuery(query,null);


        if(c.moveToFirst()) {
            do {

            } while (c.moveToNext());
        }*//*

        String query = "SELECT "
                + TABELA_ITENS + "." + COLUNA_ITEM
                + " FROM " + TB_ITEM TITEM, TB_DOADOR TDO, TB_ITENS_DOADOR TID
        WHERE TDO._ID = TID._ID_DOADOR AND
        TITEM._ID_ITEM = TID._ID_ITEM;";
    }*/

    void deleteBancoDeDados () {
        mContext.deleteDatabase(BANCO_DOADOR);
    }
}
