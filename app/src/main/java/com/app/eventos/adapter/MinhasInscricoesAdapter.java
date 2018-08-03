package com.app.eventos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eventos.R;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Evento;
import com.app.eventos.model.Inscricao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinhasInscricoesAdapter extends RecyclerView.Adapter<MinhasInscricoesAdapter.ViewHolder> {
    private Context context;
    private List<Inscricao> minhasInscricoes;
    private Evento evento;

    public MinhasInscricoesAdapter(Context context, List<Inscricao> minhasInscricoes) {
        this.context = context;
        this.minhasInscricoes = minhasInscricoes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nome_evento_inscrito) TextView txtNomeEventoInscrito;
        @BindView(R.id.txt_valor_inscricao) TextView valorInscricao;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_inscricao, parent, false);
        MinhasInscricoesAdapter.ViewHolder viewHolder = new MinhasInscricoesAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Inscricao inscricao = this.minhasInscricoes.get(position);

        holder.txtNomeEventoInscrito.setText(pegarEventoInscrito(inscricao).getNome());

        holder.valorInscricao.setText("Valor Total: " + inscricao.calcularValorTotal());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public Evento pegarEventoInscrito(Inscricao inscricao) {

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").orderByChild("idEvento").equalTo(inscricao.getIdEvento()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = objSnapshot.getValue(Evento.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return evento;
    }
}
