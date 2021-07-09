package com.loremjit.centraldechamados.model.Chamado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ChamadoDAO {
    private Realm db;
    private static ChamadoDAO INSTANCIA;

    public static ChamadoDAO getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new ChamadoDAO();
        }
        return INSTANCIA;
    }

    private ChamadoDAO() {
        db = Realm.getDefaultInstance();
    }

    //Cria um novo chamado
    public boolean abrirNovoChamado(Chamado c) {
        int numeroChamado = getProximaSequenciaChamado();
        boolean sucess = false;
        c.setNumero(numeroChamado);
        db.beginTransaction();
        db.insert(c);
        db.commitTransaction();
        //Se o chamado for inserido, inseri seus comentários
        if(db.where(Chamado.class).equalTo("numero",c.getNumero()).findFirst() != null){
            for (Comentario comentarios : c.getComentarios()){
                comentarios.setNumeroChamado(numeroChamado);
                inserirNovoComentario(comentarios);
            }
            sucess = true;
        }
        return sucess;
    }

    //Cria um novo comentário no chamado especificado
    public boolean inserirNovoComentario(Comentario c) {
        boolean sucess = false;
        c.setId(UUID.randomUUID().hashCode());
        db.beginTransaction();
        db.insert(c);
        db.commitTransaction();

        if(db.where(Comentario.class).equalTo("id",c.getId()).findFirst() != null) {
            sucess = true;
        }

        return sucess;
    }

    //Atualiza os dados de um chamado especificado no objeto
    public void update(Chamado c) {
        db.beginTransaction();
        db.insertOrUpdate(c);
        db.commitTransaction();
    }

    //Encerra um chamado especificado
    public void encerrarChamado(int numeroChamado, Comentario c) {
        //Localiza o chamado
        Chamado chamado = getChamado(numeroChamado);

        //Verifica se existe
        if(chamado != null){
            //Atualiza data de fechamento
            chamado.setDataFechamento(Calendar.getInstance().getTime());
            chamado.setStatus("F");
            db.beginTransaction();
            db.insertOrUpdate(chamado);
            db.commitTransaction();
            inserirNovoComentario(c);
        }
    }

    //Reabri um chamado especificado
    public void reabrirChamado(int numeroChamado, Comentario c) {
        Chamado chamado = getChamado(numeroChamado);

        if(chamado != null){
            chamado.setDataFechamento(null);
            db.beginTransaction();
            db.insertOrUpdate(chamado);
            db.commitTransaction();
            //Resgata o chamado e verifica se foi reaberto
            chamado = getChamado(numeroChamado);
            if(chamado.getDataFechamento() == null && chamado.getStatus() == Chamado.STATUS_AGUARDANDO_ATENDIMENTO){
                inserirNovoComentario(c);
            }
        }
    }

    //Vincula usuario no chamado especifico
    public boolean vincularUsuario(int numeroChamado, int codigoUsuario){
        boolean sucess = false;

        //Vincula o usuário
        Vinculo vinculo = new Vinculo();
        vinculo.setId(UUID.randomUUID().hashCode());
        vinculo.setNumeroChamado(numeroChamado);
        vinculo.setCodigoUsuario(codigoUsuario);
        db.beginTransaction();
        db.insert(vinculo);
        db.commitTransaction();
        //Se estiver vinculado, inseri o comentário informativo
        if(isVinculado(numeroChamado,codigoUsuario)){
            Comentario comentario = new Comentario();
            comentario.setNumeroChamado(numeroChamado);
            comentario.setCodigoUsuario(codigoUsuario);
            comentario.setDataDB(Calendar.getInstance().getTime());
            comentario.setSequencia(getProximaSequenciaComentario(numeroChamado));
            comentario.setEvento(Comentario.EVENTO_VINCULO_USUARIO);
            inserirNovoComentario(comentario);
            sucess = true;
        }

        return sucess;
    }

    //Desvincula usuario no chamado especifico
    public boolean desvincularUsuario(int numeroChamado, int codigoUsuario){
        boolean sucess = false;

        db.beginTransaction();
        db.where(Vinculo.class).equalTo("numeroChamado",numeroChamado)
                .and().equalTo("codigoUsuario",codigoUsuario)
                .findFirst().deleteFromRealm();
        db.commitTransaction();
        //Verifica se não está vinculado
        if(! isVinculado(numeroChamado,codigoUsuario)){
            Comentario comentario = new Comentario();
            comentario.setNumeroChamado(numeroChamado);
            comentario.setCodigoUsuario(codigoUsuario);
            comentario.setDataDB(Calendar.getInstance().getTime());
            comentario.setSequencia(getProximaSequenciaComentario(numeroChamado));
            comentario.setEvento(Comentario.EVENTO_DESVINCULO_USUARIO);
            inserirNovoComentario(comentario);
            sucess = true;
        }
        return sucess;
    }

    //Retorna um chamado de acordo com o número passado
    public Chamado getChamado(int numeroChamado){
        return db.where(Chamado.class).equalTo("numero",numeroChamado).findFirst();
    }

    //Retorna todos os chamados
    public ArrayList<Chamado> getAllChamados(){
        ArrayList<Chamado> chamados = new ArrayList();
        RealmResults results = db.where(Chamado.class).findAll().sort("numero",Sort.DESCENDING);
        chamados.addAll(db.copyFromRealm(results));
        return chamados;
    }

    //Verifica se o usuário é vinculado ao chamado
    public boolean isVinculado(int numeroChamado, int codigoUsuario){
        boolean retorno = false;
        if(db.where(Vinculo.class).equalTo("numeroChamado",numeroChamado)
                .and().equalTo("codigoUsuario",codigoUsuario)
                .findFirst() != null){
            retorno = true;
        }
        return retorno;
    }

    //Resgata a próxima sequência de comentários do chamado
    public int getProximaSequenciaComentario(int numeroChamado){
        return db.where(Comentario.class).equalTo("numeroChamado", numeroChamado).max("sequencia").intValue() + 1;
    }

    //Resgata a próxima sequencia de chamado
    public int getProximaSequenciaChamado(){
        boolean existir = true;
        int numero = 1;
        int incrementa = 1;

        //Verifica se existe registro na tabela
        if(db.where(Chamado.class).findFirst() != null) {
            //Loop até encontrar uma numeração disponível
            while (existir) {
                numero = db.where(Chamado.class).max("numero").intValue() + incrementa;
                //Verifica se já existe a sequencia no banco
                if (db.where(Chamado.class).equalTo("numero", numero).findFirst() == null) {
                    existir = false;
                } else {
                    incrementa++;
                }
            }
        }

        return numero;
    }

    public ArrayList<Comentario> getAllComentarios(int numeroChamado){
        ArrayList<Comentario> comentarios = new ArrayList();
        //Busca em ordem decrescente a sequencia
        RealmResults results = db.where(Comentario.class).equalTo("numeroChamado",numeroChamado).findAll();
        comentarios.addAll(db.copyFromRealm(results.sort("dataDB",Sort.DESCENDING)));
        return comentarios;
    }
}
