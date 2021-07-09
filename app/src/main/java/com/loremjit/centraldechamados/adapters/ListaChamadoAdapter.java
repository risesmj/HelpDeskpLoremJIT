package com.loremjit.centraldechamados.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.loremjit.centraldechamados.R;
import com.loremjit.centraldechamados.model.Chamado.Chamado;
import com.loremjit.centraldechamados.model.Chamado.ChamadoDAO;
import com.loremjit.centraldechamados.view_holders.ListaChamadoViewHolder;

import java.text.SimpleDateFormat;

public class ListaChamadoAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout itemXML = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_chamado,parent,false);
        ListaChamadoViewHolder viewHolder = new ListaChamadoViewHolder(itemXML);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chamado chamado = ChamadoDAO.getInstance().getAllChamados().get(position);
        ListaChamadoViewHolder viewHolder = (ListaChamadoViewHolder) holder;
        viewHolder.tvNumero.setText(String.valueOf(chamado.getNumero()));
        viewHolder.tvTitulo.setText(chamado.getTitulo());
        viewHolder.tvData.setText(SimpleDateFormat.getInstance().format(chamado.getDataAbertura()));
        viewHolder.setPosicao(position);
        viewHolder.setNumeroChamado(chamado.getNumero());

        /*
        switch (chamado.getPrioridade()){
            case 0:
                viewHolder.ciPai.setBackgroundResource(R.color.colorPrimary);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
        }
         */
    }

    @Override
    public int getItemCount() {
        return ChamadoDAO.getInstance().getAllChamados().size();
    }
}
