package com.loremjit.centraldechamados.model.Login;

import java.util.Date;

public class Sessao{
    private int codigoUsuario;
    private Date data;
    private String tokenDeAcesso;
    private boolean mantemConectado;
    private static Sessao instance;
    private Usuario usuario;

    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {
    }

    // Método público estático de acesso único ao objeto!
    public static Sessao getInstance() {
        if (instance == null)
            instance = new Sessao();
        return instance;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    public String getTokenDeAcesso() { return tokenDeAcesso; }
    public void setTokenDeAcesso(String tokenDeAcesso) { this.tokenDeAcesso = tokenDeAcesso; }
    public boolean isMantemConectado() { return mantemConectado; }
    public void setMantemConectado(boolean mantemConectado) { this.mantemConectado = mantemConectado; }
    public int getCodigoUsuario() { return codigoUsuario; }
    public void setCodigoUsuario(int codigoUsuario) { this.codigoUsuario = codigoUsuario; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public boolean verificarSessaoAtiva(){
        boolean sessaoAtiva = false;
        LoginDAO loginDAO = LoginDAO.getInstance();
        Sessao sessao = loginDAO.getSessaoAtiva();
        if(sessao != null){
            Autenticacao autenticacao = new Autenticacao(sessao);
            if(autenticacao.connect()){
                sessaoAtiva = true;
            }
        }

        return sessaoAtiva;
    }
}
