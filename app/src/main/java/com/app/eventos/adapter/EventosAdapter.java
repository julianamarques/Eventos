package com.app.eventos.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eventos.R;
import com.app.eventos.activities.DetalhesEventoActivity;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {
    private Context context;
    private List<Evento> eventos;

    public EventosAdapter(Context context) {
        this.context = context;
        this.eventos = listarEventos();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nome_evento) TextView txtNomeEvento;
        @BindView(R.id.txt_descricao_evento) TextView txtDescricaoEvento;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_evento, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Evento evento = this.eventos.get(position);

        holder.txtNomeEvento.setText(evento.getNome());
        holder.txtDescricaoEvento.setText(evento.getDescricao());

        configurarClickCurto(holder.itemView, evento, position);
    }

    @Override
    public int getItemCount() {
        return this.eventos.size();
    }

    public void configurarClickCurto(final View itemView, Evento evento, final int position) {
        evento = this.eventos.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("evento", evento);

        itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, DetalhesEventoActivity.class).putExtras(bundle));
        });
    }

    private List<Evento> listarEventos() {
        final List<Evento> eventos = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").orderByChild("nome").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventos.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = objSnapshot.getValue(Evento.class);
                    eventos.add(evento);
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return eventos;
    }
}
