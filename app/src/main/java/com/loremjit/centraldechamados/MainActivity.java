package com.loremjit.centraldechamados;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loremjit.centraldechamados.activities.LoginActivity;
import com.loremjit.centraldechamados.activities.NovoChamadoActivity;
import com.loremjit.centraldechamados.activities.VisualizacaoChamadoActivity;
import com.loremjit.centraldechamados.adapters.ListaChamadoAdapter;
import com.loremjit.centraldechamados.model.Chamado.Chamado;
import com.loremjit.centraldechamados.model.Chamado.ChamadoDAO;
import com.loremjit.centraldechamados.model.Login.Sessao;

public class MainActivity extends AppCompatActivity {
    private ListaChamadoAdapter adapter;
    private RecyclerView rvChamados;
    private int posicaoAlterada;
    private FloatingActionButton ftAdicionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Verifica se existe sessão ativa
        //if(Sessao.getInstance().verificarSessaoAtiva()){
         //   prepararTela();
        //}else{
            abrirTelaLogin();
        //}
    }

    //Abre a acitivty de login
    private void abrirTelaLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,1);
    }

    public void botaoAdicionar(View v){
        Intent intent = new Intent(this, NovoChamadoActivity.class);
        startActivityForResult(intent,2);
    }

    public void visualizarChamado(int position,int numeroChamado){
        posicaoAlterada = position;
        Intent intent = new Intent(this,VisualizacaoChamadoActivity.class);
        intent.putExtra("chamado",numeroChamado);
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            //LoginActivity
            case 1:
                //Verifica se está autenticado.
                if(resultCode == RESULT_OK) {
                    prepararTela();
                }else{
                    abrirTelaLogin();
                }
                break;
            //NovoChamadoActivity
            case 2:
                //Novo chamado adicionado
                if(resultCode == Chamado.CHAMADO_CRIADO){
                    adapter.notifyItemInserted(0);
                }
                break;
             //VisualizacaoChamadoActivity
            case 3:
                if(resultCode == Chamado.CHAMADO_ALTERADO){
                    adapter.notifyItemChanged(posicaoAlterada);
                }
                break;
        }
    }

    private void prepararTela(){
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Lista de Chamados");
        adapter = new ListaChamadoAdapter();
        rvChamados = findViewById(R.id.rvChamados);
        rvChamados.setLayoutManager(new LinearLayoutManager(this));
        rvChamados.setAdapter(adapter);
    }
}
