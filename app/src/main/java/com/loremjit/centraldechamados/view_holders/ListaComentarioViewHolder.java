package com.loremjit.centraldechamados.view_holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loremjit.centraldechamados.R;

public class ListaComentarioViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivPerfil;
    public TextView tvNome;
    public TextView tvDataHora;
    public TextView tvComentario;

    public ListaComentarioViewHolder(@NonNull View itemView) {
        super(itemView);

        tvNome = itemView.findViewById(R.id.tvUsuario);
        tvDataHora = itemView.findViewById(R.id.tvDataHora);
        tvComentario = itemView.findViewById(R.id.tvComentario);
    }
}
