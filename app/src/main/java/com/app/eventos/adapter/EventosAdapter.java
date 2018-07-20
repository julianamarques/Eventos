package com.app.eventos.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eventos.R;
import com.app.eventos.activities.InscricaoActivity;
import com.app.eventos.model.Evento;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {
    private Context context;
    private List<Evento> eventos;

    public EventosAdapter(Context context, List<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nome_evento) TextView txtNomeEvento;
        @BindView(R.id.txt_descricao_evento) TextView txtDescricaoEvento;
        @BindView(R.id.txt_local_evento) TextView txtLocalEvento;
        @BindView(R.id.txt_data_inicio_evento) TextView txtDataInicioEvento;
        @BindView(R.id.txt_hora_inicio_evento) TextView txtHoraInicioEvento;
        @BindView(R.id.txt_data_fim_evento) TextView txtDataFimEvento;

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
        holder.txtLocalEvento.setText(evento.getLocal());
        holder.txtDataInicioEvento.setText(evento.getDataInicio());
        holder.txtHoraInicioEvento.setText(evento.getHoraInicio());
        holder.txtDataFimEvento.setText(evento.getDataFim());

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
            context.startActivity(new Intent(context, InscricaoActivity.class).putExtras(bundle));
        });
    }
}
