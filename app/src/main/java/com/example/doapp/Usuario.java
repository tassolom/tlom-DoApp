package com.example.doapp;

/**
 * Created by Tasso on 01/07/2017.
 */

public class Usuario {

    int ID;

    String login;
    String senha;

    String nome;
    String endereco;
    String contato;

    public Usuario () {}

    public Usuario (int _ID, String _login, String _senha, String _nome, String _endereco, String _contato) {
        this.ID = _ID;
        this.login = _login;
        this.senha = _senha;
        this.nome = _nome;
        this.endereco = _endereco;
        this.contato = _contato;
    }

    public Usuario (String _login, String _senha, String _nome, String _endereco, String _contato) {
        this.login = _login;
        this.senha = _senha;
        this.nome = _nome;
        this.endereco = _endereco;
        this.contato = _contato;
    }

    public Usuario (String _nome, String _endereco, String _contato) {
        this.nome = _nome;
        this.endereco = _endereco;
        this.contato = _contato;
    }

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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
