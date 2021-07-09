package com.loremjit.centraldechamados.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.loremjit.centraldechamados.R;
import com.loremjit.centraldechamados.adapters.ListaChamadoAdapter;
import com.loremjit.centraldechamados.adapters.ListaComentarioAdapter;
import com.loremjit.centraldechamados.model.Chamado.Chamado;
import com.loremjit.centraldechamados.model.Chamado.ChamadoDAO;
import com.loremjit.centraldechamados.model.Chamado.Comentario;

public class VisualizacaoChamadoActivity extends AppCompatActivity {
    private int numeroChamado;
    private Chamado chamado;
    private ListaComentarioAdapter adapter;
    private RecyclerView rvComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_chamado);

        numeroChamado = getIntent().getIntExtra("chamado",-1);
        getSupportActionBar().setTitle("Chamado: " + String.valueOf(numeroChamado));

        adapter = new ListaComentarioAdapter(numeroChamado);
        rvComentarios = findViewById(R.id.rvComentarios);
        rvComentarios.setLayoutManager(new LinearLayoutManager(this));
        rvComentarios.setAdapter(adapter);

        if(numeroChamado != -1){
            ChamadoDAO chamadoDAO = ChamadoDAO.getInstance();
            chamado = chamadoDAO.getChamado(numeroChamado);
        }
    }

    public void botaoComentar(View v){
        Intent intent = new Intent(this,ComentarioChamadoActivity.class);
        intent.putExtra("chamado",numeroChamado);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            //ComentarioChamadoActivity
            case 1:
                if(requestCode == Comentario.COMENTARIO_INSERIDO){
                    setResult(Chamado.CHAMADO_ALTERADO);
                    adapter.notifyItemChanged(0);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visualizacao_chamado_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
