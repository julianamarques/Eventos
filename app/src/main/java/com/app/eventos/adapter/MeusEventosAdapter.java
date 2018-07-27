package com.app.eventos.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.activities.CadastroAtividadeActivity;
import com.app.eventos.activities.DetalhesMeuEventoActivity;
import com.app.eventos.dao.EventoDAO;
import com.app.eventos.model.Evento;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeusEventosAdapter extends RecyclerView.Adapter<MeusEventosAdapter.ViewHolder>{
    private Context context;
    private List<Evento> meusEventos;
    private EventoDAO eventoDAO;


    public MeusEventosAdapter(Context context, List<Evento> meusEventos) {
        this.context = context;
        this.meusEventos = meusEventos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nome_evento) TextView txtNomeEvento;


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


        configurarClickCurto(holder.itemView, evento, position);
        configurarClickLongo(holder.itemView, position);

    }

    private void configurarClickLongo(View itemView, int position) {
        final Evento evento = this.meusEventos.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("evento", evento);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_evento, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener((MenuItem item) -> {
                    switch (item.getItemId()) {
                        case R.id.btn_adicionar_atividade:
                            adicionarAtividade(v, evento, position);
                            break;

                        case R.id.btn_editar_evento:
                            editarEvento(v, evento, position);
                            break;

                        case R.id.btn_excluir_evento:
                            excluirEvento(v, evento, position);
                            break;
                    }
                    return false;
                });

                popupMenu.show();
                return true;
            }
        });

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

    public void adicionarAtividade(View v, Evento evento, final int position){
        evento = this.meusEventos.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("evento", evento);

       context.startActivity(new Intent(context, CadastroAtividadeActivity.class).putExtras(bundle));


    }

    public void editarEvento(View v, Evento evento, final int position){
        Toast.makeText(context, "MT hora nessa calma...", Toast.LENGTH_SHORT).show();
    }

    public void excluirEvento(View v, Evento evento, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle("EventosAPP");
        builder.setMessage("Deseja remover " + evento.getNome()+ " permanentemente?");
        builder.setPositiveButton("SIM", (dialog, which) -> {
            eventoDAO = new EventoDAO();
            eventoDAO.deletarEvento(evento.getId());
            this.meusEventos.remove(evento);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            Snackbar.make(v, "Evento " + evento.getNome() + " removido",
                    Snackbar.LENGTH_LONG).show();

        });
        builder.setNegativeButton("NÃO", (dialog, which) -> {
            Snackbar.make(v, "Evento não removido", Snackbar.LENGTH_LONG).show();
        });

        builder.create().show();
    }

}
