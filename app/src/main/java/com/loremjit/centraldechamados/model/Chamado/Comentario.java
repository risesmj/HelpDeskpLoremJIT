package com.loremjit.centraldechamados.model.Chamado;

import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Comentario extends RealmObject {
    @PrimaryKey
    private int id;
    private int numeroChamado;
    private int sequencia;
    private int codigoUsuario;
    private String nomeUsuario;
    private Date dataDB;
    private String comentario;
    private String evento;

    //Eventos
    public static String EVENTO_VINCULO_USUARIO = "0050";
    public static String EVENTO_DESVINCULO_USUARIO = "0051";
    public static String EVENTO_COMPLEMENTO = "";
    public static String EVENTO_BASE_CONHECIMENTO = "";
    public static String EVENTO_EM_DESENVOLVIMENTO = "";
    public static String EVENTO_EM_ANALISE = "";
    public static String EVENTO_ENCERRAMENTO = "";
    public static String EVENTO_ABERTURA = "";
    public static String EVENTO_REABERTURA = "";
    public static String EVENTO_CANCELAMENTO = "";
    public static String EVENTO_ALOCACAO_RESPONSAVEL = "";

    //ResultCode
    public static int COMENTARIO_INSERIDO = 200;

    public int getNumeroChamado() {
        return numeroChamado;
    }
    public void setNumeroChamado(int numeroChamado) {
        this.numeroChamado = numeroChamado;
    }
    public int getSequencia() {
        return sequencia;
    }
    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
    }
    public int getCodigoUsuario() {
        return codigoUsuario;
    }
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    public Date getDataDB() {
        return dataDB;
    }
    public void setDataDB(Date dataDB) {
        this.dataDB = dataDB;
    }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
}