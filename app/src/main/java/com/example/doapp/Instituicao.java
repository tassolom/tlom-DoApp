package com.example.doapp;

/**
 * Created by Tasso on 12/06/2017.
 */

public class Instituicao extends Usuario {

    String cnpj;

    public Instituicao (){ }

    public Instituicao (int _ID, String _login, String _senha, String _nome,  String _cnpj, String _endereco, String _contato) {
        super(_ID, _login, _senha, _nome, _endereco, _contato);

        this.cnpj = _cnpj;
    }

    public Instituicao (String _login, String _senha, String _nome, String _cnpj, String _endereco, String _contato) {
        super(_login, _senha, _nome, _endereco, _contato);

        this.cnpj = _cnpj;
    }

    public Instituicao (String _nome, String _cnpj, String _endereco, String _contato) {
        super(_nome, _endereco, _contato);

        this.cnpj = _cnpj;
    }

    //==============================================================================

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
