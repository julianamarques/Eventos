package com.app.eventos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eventos.R;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.InscricaoDAO;
import com.app.eventos.model.Inscricao;
import com.app.eventos.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InscricoesPorEventoAdapter extends  RecyclerView.Adapter<InscricoesPorEventoAdapter.ViewHolder> {
    private Context context;
    private List<Inscricao> inscricoesNoEvento;
    private List<Usuario> usuarios;
    private InscricaoDAO incricaoDAO;

    public InscricoesPorEventoAdapter(Context context, String eventoId, List<Usuario> usuarios) {
        this.context = context;
        this.inscricoesNoEvento = listarInscricoesNoEvento(eventoId);
        this.usuarios = usuarios;
        this.incricaoDAO = new InscricaoDAO();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nome_usuario_inscrito) TextView txtNomeUsuarioInscrito;
        @BindView(R.id.txt_valor) TextView txtValor;
        @BindView(R.id.txt_status) TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_inscricao_no_evento, parent, false);
        InscricoesPorEventoAdapter.ViewHolder viewHolder = new InscricoesPorEventoAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InscricoesPorEventoAdapter.ViewHolder holder, int position) {
        final Inscricao inscricao = this.inscricoesNoEvento.get(position);

        configurarClickLongo(holder.itemView, position);
        exibirNomeUsuarioInscrito(inscricao, holder.txtNomeUsuarioInscrito);

        holder.txtValor.setText("Valor: R$" + inscricao.calcularValorTotal());

        if (inscricao.getInscricaoPaga()) {
            holder.txtStatus.setText("Inscrição Confirmada");
        }

        else {
            holder.txtStatus.setText("Inscrição não Confirmada");
        }
    }

    @Override
    public int getItemCount() {
        return inscricoesNoEvento.size();
    }

    private void exibirNomeUsuarioInscrito(Inscricao inscricao, TextView textView) {

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(inscricao.getIdUser())) {
                textView.setText("Nome do Participante: " + usuarios.get(i).getNome());
            }
        }
    }

    private List<Inscricao> listarInscricoesNoEvento(String eventoId) {
        final List<Inscricao> inscricoesNoEvento = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("inscricoes").orderByChild("idEvento").equalTo(eventoId).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                inscricoesNoEvento.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Inscricao inscricao = objSnapshot.getValue(Inscricao.class);
                    inscricoesNoEvento.add(inscricao);
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return inscricoesNoEvento;
    }

    private void configurarClickLongo(View itemView, int position) {
        final Inscricao inscricao = this.inscricoesNoEvento.get(position);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_inscricao, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener((MenuItem item) -> {
                    switch (item.getItemId()) {
                        case R.id.confirmar_inscricao:
                            incricaoDAO.confirmarInscricao(inscricao);
                            break;
                    }

                    return false;
                });

                popupMenu.show();
                return true;
            }
        });
    }
}
