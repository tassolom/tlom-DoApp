package com.example.doapp;

/**
 * Created by Tasso on 12/06/2017.
 */

public class Doador extends Usuario {

    String cpf;

    public Doador () {}

    public Doador (int _ID, String _login, String _senha, String _nome, String _cpf, String _endereco, String _contato) {
        super(_ID, _login, _senha, _nome, _endereco, _contato);

        this.cpf = _cpf;
    }

    public Doador (String _login, String _senha, String _nome, String _cpf, String _endereco, String _contato) {
        super(_login, _senha, _nome, _endereco, _contato);

        this.cpf = _cpf;
    }

    public Doador (String _nome, String _cpf, String _endereco, String _contato) {
        super(_nome, _endereco, _contato);

        this.cpf = _cpf;
    }

    //==============================================================================

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
