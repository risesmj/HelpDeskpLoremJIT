package com.loremjit.centraldechamados.model.Chamado;

import com.loremjit.centraldechamados.model.Login.Usuario;

import java.util.ArrayList;
import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Chamado extends RealmObject{
    @PrimaryKey
    private int numero;
    private String titulo;
    private Date dataAbertura;
    private Date dataFechamento;
    private Date dataPrevista;
    private int codigoSolicitante;
    private String nomeSolicitante;
    private int codigoResponsavel;
    private String nomeResponsavel;
    private String codigoArea;
    private int prioridade;
    private String status;
    @Ignore
    private ArrayList<Comentario> comentarios;
    @Ignore
    private ArrayList<Usuario> usuariosVinculados;

    //Status
    public static String STATUS_AGUARDANDO_ATENDIMENTO = "A";
    public static String STATUS_EM_ANALISE = "B";
    public static String STATUS_EM_DESENVOLVIMENTO = "C";
    public static String STATUS_AGUARDANDO_RESPOSTA_COLABORADOR = "D";
    public static String STATUS_AGUARDANDO_RESPOSTA_TECNICO = "E";
    public static String STATUS_ENCERRADO = "F";
    public static String STATUS_CANCELADO = "G";

    public Chamado(){
        comentarios = new ArrayList();
    }

    //ResultCode
    public static int CHAMADO_CRIADO = 200;
    public static int CHAMADO_ALTERADO = 201;

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Date getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public Date getDataFechamento() {
        return dataFechamento;
    }
    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
    public Date getDataPrevista() {
        return dataPrevista;
    }
    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }
    public int getCodigoSolicitante() {
        return codigoSolicitante;
    }
    public void setCodigoSolicitante(int codigoSolicitante) { this.codigoSolicitante = codigoSolicitante; }
    public int getCodigoResponsavel() {
        return codigoResponsavel;
    }
    public void setCodigoResponsavel(int codigoResponsavel) { this.codigoResponsavel = codigoResponsavel; }
    public String getCodigoArea() {
        return codigoArea;
    }
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    public int getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(ArrayList<Comentario> comentarios) { this.comentarios = comentarios; }
    public void addComentario(Comentario c){
        comentarios.add(c);
    }
    public ArrayList<Usuario> getUsuariosVinculados() {
        return usuariosVinculados;
    }
    public void setUsuariosVinculados(ArrayList<Usuario> usuariosVinculados) { this.usuariosVinculados = usuariosVinculados; }
    public String getNomeSolicitante() { return nomeSolicitante; }
    public void setNomeSolicitante(String nomeSolicitante) { this.nomeSolicitante = nomeSolicitante; }
    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }
}
