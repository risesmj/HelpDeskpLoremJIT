package com.loremjit.centraldechamados.model.Login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class Autenticacao {
    private String usuario;
    private String senha;
    private boolean manterConectado;

    public Autenticacao(String usuario,String senha,boolean manterConctado){
        this.usuario = usuario;
        this.senha = senha;
        this.manterConectado = manterConctado;
    }

    public Autenticacao(Sessao sessao){
        Usuario usuario = LoginDAO.getInstance().getUsuario(sessao.getCodigoUsuario());
        this.usuario = usuario.getLogin();
        this.senha = "123";
        this.manterConectado = sessao.isMantemConectado();
    }

    //Conecta o usuário
    public boolean connect(){
        boolean sucess = false;
        if(!usuario.trim().isEmpty() && !senha.trim().isEmpty() ){
            Usuario usuario = LoginDAO.getInstance().getUsuario(this.usuario,this.senha);
            if(usuario != null) {
                String token = generateToken();
                if (!token.isEmpty()) {
                    createSession(token,usuario);
                    //Verifica se o token foi gerado
                    if(!Sessao.getInstance().getTokenDeAcesso().isEmpty()){
                        sucess = true;
                    }
                }
            }
        }

        return sucess;
    }

    //Gera o token de acesso
    private String generateToken(){
        String combinacao = this.usuario+this.senha+Calendar.getInstance().getTime().toString();
        String token = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            token = messageDigest.digest(combinacao.getBytes()).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return token;
    }

    //Cria uma sessão de login
    private void createSession(String token,Usuario usuario){
        //Objeto de instância única para manipular no app
        Sessao sessao = Sessao.getInstance();
        sessao.setTokenDeAcesso(token);
        sessao.setUsuario(usuario);
        sessao.setCodigoUsuario(usuario.getCodigo());
        sessao.setMantemConectado(this.manterConectado);

        //Objeto para salvar no banco
        SessaoRealm sessaoRealm = new SessaoRealm();
        sessaoRealm.setTokenDeAcesso(token);
        sessaoRealm.setCodigoUsuario(usuario.getCodigo());
        sessaoRealm.setMantemConectado(this.manterConectado);

        //Salva a sessão no banco
        LoginDAO.getInstance().criarSessao(sessaoRealm);
    }

}
