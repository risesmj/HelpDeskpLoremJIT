package com.loremjit.centraldechamados.model.Chamado;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Vinculo extends RealmObject {
    @PrimaryKey
    private int id;
    private int numeroChamado;
    private int codigoUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroChamado() {
        return numeroChamado;
    }

    public void setNumeroChamado(int numeroChamado) {
        this.numeroChamado = numeroChamado;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
