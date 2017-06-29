package com.example.doapp;

/**
 * Created by Tasso on 12/06/2017.
 */

public class Instituicao {

    int ID;

    String login;
    String senha;

    String nome;
    String endereco;
    String cnpj;
    String contato;
    String itens;

    public Instituicao () {

    }

    public Instituicao (int _ID, String _login, String _senha, String _nome, String _cnpj, String _endereco, String _contato, String _itens) {
        this.ID = _ID;

        this.login = _login;
        this.senha = _senha;

        this.nome = _nome;
        this.cnpj = _cnpj;
        this.endereco = _endereco;
        this.contato = _contato;
        this.itens = _itens;
    }

    public Instituicao (String _login, String _senha, String _nome, String _endereco, String _cnpj, String _contato) {
        this.login = _login;
        this.senha = _senha;

        this.nome = _nome;
        this.endereco = _endereco;
        this.cnpj = _cnpj;
        this.contato = _contato;
    }

    public Instituicao (String _login, String _senha, String _nome, String _endereco, String _cnpj, String _contato, String _itens) {
        this.login = _login;
        this.senha = _senha;

        this.nome = _nome;
        this.endereco = _endereco;
        this.cnpj = _cnpj;
        this.contato = _contato;
        this.itens = _itens;
    }

    //==============================================================================

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getItens() {
        return contato;
    }

    public void setItens(String itens) {
        this.itens = itens;
    }
}
