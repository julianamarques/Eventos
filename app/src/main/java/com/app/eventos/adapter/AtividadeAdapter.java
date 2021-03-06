package com.app.eventos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.dao.AtividadeDAO;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Atividade;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.ViewHolder> {
    private Context context;
    private List<Atividade> atividades;

    public AtividadeAdapter (Context context, String eventoId) {
        this.context = context;
        this.atividades = listarAtividades(eventoId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome_atividade) TextView txtNomeAtividade;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public AtividadeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_atividade, parent, false);
        AtividadeAdapter.ViewHolder viewHolder = new AtividadeAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadeAdapter.ViewHolder holder, int position) {
        final Atividade atividade = this.atividades.get(position);

        holder.txtNomeAtividade.setText(atividade.getNome());
        configurarClickCurto(holder.itemView, atividade, position);

    }

    private void configurarClickCurto(View itemView, Atividade atividade, int position) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle(atividade.getNome());
                builder.setMessage("Tipo da Atividade: " + atividade.getTipoAtividade().toString() + "\n " + atividade.getDescricao() + "\n " +
                        "Responsável: " + atividade.getResponsavel() + "\n " + "Data: " + atividade.getData() + "\n " + "Hora:" + atividade.getHora() + "\n " +
                        "Valor: R$ " + atividade.getValor());
                builder.setPositiveButton("OK", (dialog, which) -> {

                });
                builder.create().show();
            }
        });

    }

    private List<Atividade> listarAtividades(String eventoId) {
        final List<Atividade> atividades = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(eventoId).child("atividades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                atividades.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Atividade atividade = objSnapshot.getValue(Atividade.class);
                    atividades.add(atividade);
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return atividades;
    }

    @Override
    public int getItemCount() {
        return this.atividades.size();
    }
}
