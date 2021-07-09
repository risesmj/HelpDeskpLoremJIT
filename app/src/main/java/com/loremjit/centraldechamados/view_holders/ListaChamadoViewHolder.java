package com.loremjit.centraldechamados.view_holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.loremjit.centraldechamados.MainActivity;
import com.loremjit.centraldechamados.R;

public class ListaChamadoViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNumero;
    public TextView tvTitulo;
    public TextView tvData;
    public ConstraintLayout ciPai;
    private int posicao;
    private int numeroChamado;

    public ListaChamadoViewHolder(@NonNull View itemView) {
        super(itemView);

        tvNumero = itemView.findViewById(R.id.tvNumeroChamado);
        tvTitulo = itemView.findViewById(R.id.tvTituloChamado);
        tvData = itemView.findViewById(R.id.tvDataChamado);
        ciPai = itemView.findViewById(R.id.icChamado);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) v.getContext()).visualizarChamado(posicao,numeroChamado);
            }
        });
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int Posicao) {
        this.posicao = posicao;
    }

    public int getNumeroChamado() {
        return numeroChamado;
    }

    public void setNumeroChamado(int numeroChamado) {
        this.numeroChamado = numeroChamado;
    }
}
