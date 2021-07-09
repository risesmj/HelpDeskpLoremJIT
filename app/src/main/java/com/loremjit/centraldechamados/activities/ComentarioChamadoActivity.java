package com.loremjit.centraldechamados.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.loremjit.centraldechamados.R;
import com.loremjit.centraldechamados.controller.ChamadoController;
import com.loremjit.centraldechamados.model.Chamado.Comentario;
import com.loremjit.centraldechamados.model.Login.Sessao;

import java.util.Calendar;

public class ComentarioChamadoActivity extends AppCompatActivity {
    private String eventos[];
    private int numeroChamado;
    //Componentes
    private EditText etComentario;
    private Spinner spEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_chamado);
        getSupportActionBar().setTitle("Novo comentário");
        setResult(RESULT_CANCELED);
        numeroChamado = getIntent().getIntExtra("chamado",0);
        etComentario = findViewById(R.id.etComentario);
    }

    public void botaoSalvar(){
        Comentario comentario = new Comentario();
        ChamadoController controle = new ChamadoController();

        comentario.setNumeroChamado(numeroChamado);
        comentario.setComentario(etComentario.getText().toString().trim());
        comentario.setCodigoUsuario(10);
        //comentario.setNomeUsuario(Sessao.getInstance().getUsuario().getNomeCompleto());
        comentario.setDataDB(Calendar.getInstance().getTime());
        comentario.setEvento("teste");
        if(controle.prepararNovoComentario(comentario)){
            setResult(Comentario.COMENTARIO_INSERIDO);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comentario_chamado_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menu_comentario_salvar:
                botaoSalvar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
