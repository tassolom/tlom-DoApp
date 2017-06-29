package com.example.doapp;

/**
 * Created by Tasso on 12/06/2017.
 */

public class Doador {

    int ID;

    String login;
    String senha;

    String nome;
    String endereco;
    String cpf;
    String contato;
    String itens;

    public Doador () {

    }

    public Doador (int _ID, String _login, String _senha, String _nome, String _cpf, String _endereco, String _contato, String _itens) {
        this.ID = _ID;

        this.login = _login;
        this.senha = _senha;

        this.nome = _nome;
        this.cpf = _cpf;
        this.endereco = _endereco;
        this.contato = _contato;
        this.itens = _itens;
    }

    public Doador (String _login, String _senha, String _nome, String _endereco, String _cpf, String _contato, String _itens) {
        this.login = _login;
        this.senha = _senha;

        this.nome = _nome;
        this.endereco = _endereco;
        this.cpf = _cpf;
        this.contato = _contato;
        this.itens = _itens;
    }

    public Doador (String _nome, String _endereco, String _cpf, String _contato, String _itens) {
        this.nome = _nome;
        this.endereco = _endereco;
        this.cpf = _cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
