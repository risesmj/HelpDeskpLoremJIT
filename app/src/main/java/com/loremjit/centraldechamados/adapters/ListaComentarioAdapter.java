package com.loremjit.centraldechamados.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.loremjit.centraldechamados.R;
import com.loremjit.centraldechamados.model.Chamado.ChamadoDAO;
import com.loremjit.centraldechamados.model.Chamado.Comentario;
import com.loremjit.centraldechamados.view_holders.ListaComentarioViewHolder;

import java.text.SimpleDateFormat;

public class ListaComentarioAdapter extends RecyclerView.Adapter {
    private int numeroChamado;

    public ListaComentarioAdapter(int numeroChamado){
        this.numeroChamado = numeroChamado;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout itemXML = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_comentario,parent,false);
        ListaComentarioViewHolder viewHolder = new ListaComentarioViewHolder(itemXML);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comentario comentario = ChamadoDAO.getInstance().getAllComentarios(numeroChamado).get(position);
        ListaComentarioViewHolder viewHolder = (ListaComentarioViewHolder) holder;
        viewHolder.tvNome.setText("Richarlisson");
        viewHolder.tvDataHora.setText(SimpleDateFormat.getInstance().format(comentario.getDataDB()));
        viewHolder.tvComentario.setText(comentario.getComentario());
    }

    @Override
    public int getItemCount() {
        return ChamadoDAO.getInstance().getAllComentarios(numeroChamado).size();
    }
}
