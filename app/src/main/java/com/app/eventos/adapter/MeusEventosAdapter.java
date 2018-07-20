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
import com.app.eventos.activities.DetalhesMeuEventoActivity;
import com.app.eventos.model.Evento;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeusEventosAdapter extends RecyclerView.Adapter<MeusEventosAdapter.ViewHolder>{
    private Context context;
    private List<Evento> meusEventos;

    public MeusEventosAdapter(Context context, List<Evento> meusEventos) {
        this.context = context;
        this.meusEventos = meusEventos;
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
        MeusEventosAdapter.ViewHolder viewHolder = new MeusEventosAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Evento evento = this.meusEventos.get(position);

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
        return this.meusEventos.size();
    }

    public void configurarClickCurto(final View itemView, Evento evento, final int position) {
        evento = this.meusEventos.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("evento", evento);

        itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, DetalhesMeuEventoActivity.class).putExtras(bundle));
        });
    }
}
