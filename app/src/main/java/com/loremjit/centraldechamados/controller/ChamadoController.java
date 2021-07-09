package com.loremjit.centraldechamados.controller;

import android.content.Context;
import android.widget.Toast;

import com.loremjit.centraldechamados.R;
import com.loremjit.centraldechamados.model.Chamado.Chamado;
import com.loremjit.centraldechamados.model.Chamado.ChamadoDAO;
import com.loremjit.centraldechamados.model.Chamado.Comentario;

public class ChamadoController {
    private ChamadoDAO chamado = ChamadoDAO.getInstance();
    private Context contexto;

    public ChamadoController(){

    }

    public ChamadoController(Context contexto){
        this.contexto = contexto;
    }

    //Valida inclusão de novo chamado
    public boolean prepararNovoChamado(Chamado c){
        boolean sucess = true;

        if(c.getTitulo().isEmpty()){
            Toast.makeText(contexto,"Preencha o campo assunto.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(c.getComentarios().isEmpty()){
            Toast.makeText(contexto,"Preencha o campo descrição.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(c.getPrioridade() < 0 || c.getPrioridade() > 5){
            Toast.makeText(contexto,"Preencha o campo prioridade.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(c.getCodigoSolicitante() == 0){
            Toast.makeText(contexto,contexto.getString(R.string.login_nao_autenticado),Toast.LENGTH_SHORT).show();
            return false;
        }

        return chamado.abrirNovoChamado(c);
    }

    //Valida a inclusão de um novo comentário
    public boolean prepararNovoComentario(Comentario c){
        boolean sucess = true;

        if(c.getComentario().isEmpty()){
            return false;
        }

        if(c.getEvento().isEmpty()){
            return false;
        }

        if(c.getCodigoUsuario() == 0){
            return false;
        }

        return chamado.inserirNovoComentario(c);
    }
}
