package com.app.eventos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class InscricaoEventoAdapter extends RecyclerView.Adapter<InscricaoEventoAdapter.ViewHolder> {
    @BindView(R.id.btn_salvar_inscricao) protected Button btnSalvarInscricao;

    private Context context;
    private List<Atividade> atividades;
    private ArrayList<Atividade> atividadesInscricao = new ArrayList<>();
    private TextView txtValorInscricao;

    public InscricaoEventoAdapter (Context context, String eventoId, TextView txtValorInscricao) {
        this.context = context;
        this.atividades = listarAtividades(eventoId);
        this.txtValorInscricao = txtValorInscricao;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome_atividade_inscricao) TextView tvNomeAtividadeInscricao;
        @BindView(R.id.tv_descricao_atividade_inscricao) TextView tvDescricaoAtividadeInscricao;
        @BindView(R.id.tv_data_atividade_inscricao)TextView tvDataAtividadeInscricao;
        @BindView(R.id.tv_hora_atividade_inscricao)TextView tvHoraAtividadeInscricao;
        @BindView(R.id.tv_valor_atividade_inscricao)TextView tvValorAtividadeInscricao;
        @BindView(R.id.chk_atividade) CheckBox chkEscolhido;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public InscricaoEventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_atividade_inscricao, parent, false);
        InscricaoEventoAdapter.ViewHolder viewHolder = new InscricaoEventoAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InscricaoEventoAdapter.ViewHolder holder, int position) {
        final Atividade atividade = this.atividades.get(position);

        holder.tvNomeAtividadeInscricao.setText(atividade.getNome());
        holder.tvDescricaoAtividadeInscricao.setText(atividade.getDescricao());
        holder.tvDataAtividadeInscricao.setText("Data: " + atividade.getData());
        holder.tvHoraAtividadeInscricao.setText("Hora: " + atividade.getHora());
        holder.tvValorAtividadeInscricao.setText("Valor: R$" + atividade.getValor());

        holder.chkEscolhido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox checkBox = (CheckBox) buttonView;

                if (checkBox.isChecked()){
                    atividadesInscricao.add(atividades.get(position));
                    Toast.makeText(context, String.valueOf(obterValorTotalInscricao()), Toast.LENGTH_SHORT).show();
                    txtValorInscricao.setText("Valor: R$" + obterValorTotalInscricao());
                }

                else if(!checkBox.isChecked()){
                    atividadesInscricao.remove(atividades.get(position));
                }
            }
        });

        configurarClickCurto(holder.itemView, atividade, position);
    }

    public double obterValorTotalInscricao(){
        double valor = 0;

        if (!atividadesInscricao.isEmpty()){
            for (int i = 0; i< atividadesInscricao.size(); i++){
                valor += atividadesInscricao.get(i).getValor();
            }
        }

        return valor;
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

    public List<Atividade> getAtividadesInscricao() {
        return atividadesInscricao;
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

