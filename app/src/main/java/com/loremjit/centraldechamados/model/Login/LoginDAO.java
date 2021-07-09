package com.loremjit.centraldechamados.model.Login;

import java.util.Calendar;
import java.util.UUID;

import io.realm.Realm;
import io.realm.Sort;

public class LoginDAO {
    private Realm db;

    private static LoginDAO instance;
    // Construtor privado (suprime o construtor público padrão).
    private LoginDAO() {
        db = Realm.getDefaultInstance();
    }

    // Método público estático de acesso único ao objeto!
    public static LoginDAO getInstance() {
        if (instance == null)
            instance = new LoginDAO();
        return instance;
    }

    //Cria uma sessao de login
    public boolean criarSessao(SessaoRealm sessao){
        boolean sucess = false;
        sessao.setId(UUID.randomUUID().toString());
        sessao.setData(Calendar.getInstance().getTime());
        db.beginTransaction();
        db.insert(sessao);
        db.commitTransaction();
        if(db.where(SessaoRealm.class).equalTo("id",sessao.getId()).findFirst() != null){
            sucess = true;
        }
        return sucess;
    }

    //Resgata uma sessão ativa do banco
    public Sessao getSessaoAtiva(){
        Sessao sessao = Sessao.getInstance();
        SessaoRealm sessaoRealm = db.where(SessaoRealm.class).equalTo("mantemConectado",true).sort("data", Sort.DESCENDING).findFirst();

        //Prepara o objeto de sessão
        sessao.setCodigoUsuario(sessaoRealm.getCodigoUsuario());
        sessao.setUsuario(getUsuario(sessaoRealm.getCodigoUsuario()));
        sessao.setTokenDeAcesso(sessaoRealm.getTokenDeAcesso());
        sessao.setData(sessaoRealm.getData());
        sessao.setMantemConectado(sessaoRealm.isMantemConectado());

        return sessao;
    }

    //Resgata um usuário específico
    public Usuario getUsuario(int codigoUsuario){
        Usuario usuario = new Usuario();
        usuario.setCodigo(10);
        usuario.setLogin("lorem");
        usuario.setNomeCompleto("LoremJ IT");
        usuario.setDepartamento("TI");
        usuario.setEmail("lorem@loremjit.com.br");

        return usuario;
    }

    //Resgata um usuário específico
    public Usuario getUsuario(String login, String senha){
        Usuario usuario = new Usuario();
        usuario.setCodigo(10);
        usuario.setLogin("lorem");
        usuario.setNomeCompleto("LoremJ IT");
        usuario.setDepartamento("TI");
        usuario.setEmail("lorem@loremjit.com.br");

        return usuario;
    }
}
