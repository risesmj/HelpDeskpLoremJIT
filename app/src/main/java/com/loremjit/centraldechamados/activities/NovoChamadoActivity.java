package com.loremjit.centraldechamados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.loremjit.centraldechamados.R;
import com.loremjit.centraldechamados.controller.ChamadoController;
import com.loremjit.centraldechamados.model.Chamado.Chamado;
import com.loremjit.centraldechamados.model.Chamado.Comentario;
import com.loremjit.centraldechamados.model.Login.Sessao;

import java.util.Calendar;
import java.util.Date;

public class NovoChamadoActivity extends AppCompatActivity {
    private String areas[];
    //Componentes
    private EditText etAssunto;
    private Spinner spArea;
    private Spinner spPrioridade;
    private EditText etDescricao;
    private Button btSalvar;
    private Button btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);
        getSupportActionBar().setTitle("Novo Chamado");
        setResult(RESULT_CANCELED);

        etAssunto = findViewById(R.id.etAssunto);
        spArea = findViewById(R.id.spArea);
        spPrioridade = findViewById(R.id.spPrioridade);
        etDescricao = findViewById(R.id.etDescricao);
    }

    //Ação do botão de salvar
    public void botaoSalvar(View v){
        Chamado chamado = new Chamado();
        Comentario comentario = new Comentario();
        Date dataAbertura = Calendar.getInstance().getTime();
        ChamadoController controle = new ChamadoController(getApplicationContext());

        //Prepara o comentário
        comentario.setEvento(Comentario.EVENTO_ABERTURA);
        comentario.setComentario(etDescricao.getText().toString().trim());
        comentario.setDataDB(dataAbertura);
        comentario.setSequencia(1);

        //Prepara o chamado
        chamado.setTitulo(etAssunto.getText().toString().trim());
        chamado.addComentario(comentario);
        chamado.setDataAbertura(dataAbertura);
        chamado.setPrioridade(spPrioridade.getSelectedItemPosition());
        //chamado.setCodigoArea(areas[spArea.getSelectedItemPosition()]);
        chamado.setStatus(Chamado.STATUS_AGUARDANDO_ATENDIMENTO);
        //chamado.setCodigoSolicitante(Sessao.getInstance().getUsuario().getCodigo());
        chamado.setCodigoSolicitante(10);
        //chamado.setNomeSolicitante(Sessao.getInstance().getUsuario().getNomeCompleto());

        if(controle.prepararNovoChamado(chamado)){
            setResult(Chamado.CHAMADO_CRIADO);
            finish();
        }
    }

    public void botaoCancelar(View v){
        finish();
    }
}
