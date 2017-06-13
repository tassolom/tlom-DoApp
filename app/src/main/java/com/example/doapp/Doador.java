package com.example.doapp;

/**
 * Created by Tasso on 12/06/2017.
 */

public class Doador {

    String login;
    String senha;

    String nome;
    String endereco;
    String cpf;
    String contato;

    public Doador () {

    }

    public Doador (String _login, String _senha, String _nome, String _cpf, String _endereco, String _contato) {
        this.login = _login;
        this.senha = _senha;

        this.nome = _nome;
        this.cpf = _cpf;
        this.endereco = _endereco;
        this.contato = _contato;
    }

    public Doador (String _nome, String _endereco, String _cpf, String _contato) {
        this.nome = _nome;
        this.endereco = _endereco;
        this.cpf = _cpf;
        this.contato = _contato;
    }

    //==============================================================================

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
}
