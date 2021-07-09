package com.loremjit.centraldechamados.model.Login;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SessaoRealm extends RealmObject {
    @PrimaryKey
    private String id;
    private int codigoUsuario;
    private Date data;
    private String tokenDeAcesso;
    private boolean mantemConectado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTokenDeAcesso() {
        return tokenDeAcesso;
    }

    public void setTokenDeAcesso(String tokenDeAcesso) {
        this.tokenDeAcesso = tokenDeAcesso;
    }

    public boolean isMantemConectado() {
        return mantemConectado;
    }

    public void setMantemConectado(boolean mantemConectado) {
        this.mantemConectado = mantemConectado;
    }
}
