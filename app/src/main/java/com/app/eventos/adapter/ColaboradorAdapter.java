package com.app.eventos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColaboradorAdapter  extends RecyclerView.Adapter<ColaboradorAdapter.ViewHolder>{
    @BindView(R.id.btn_salvar_inscricao) protected Button btnSalvarInscricao;

    private Context context;
    private List<Usuario> usuarios;
    private ArrayList<Usuario> usuariosColaboradores = new ArrayList<>();

    public ColaboradorAdapter (Context context, String usuarioId) {
        this.context = context;
        this.usuarios = listarUsuarios(usuarioId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nome_usuario_colaborador) TextView tvNomeUsuarioColaborador;
        @BindView(R.id.tv_email_usuario_colaborador) TextView tvEmailUsuarioColaborador;
        @BindView(R.id.chk_colaborador)
        CheckBox chkEscolhido;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ColaboradorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_colaborador, parent, false);
        ColaboradorAdapter.ViewHolder viewHolder = new ColaboradorAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColaboradorAdapter.ViewHolder holder, int position) {
        final Usuario usuario = this.usuarios.get(position);

        holder.tvNomeUsuarioColaborador.setText(usuario.getNome());
        holder.tvEmailUsuarioColaborador.setText(usuario.getEmail());

        holder.chkEscolhido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox checkBox = (CheckBox) buttonView;

                if (checkBox.isChecked()){
                    usuariosColaboradores.add(usuarios.get(position));
                }

                else if(!checkBox.isChecked()){
                    usuariosColaboradores.remove(usuarios.get(position));
                }
            }
        });

    }

    public List<Usuario> getUsuariosColaboradores() {
        return usuariosColaboradores;
    }

    private List<Usuario> listarUsuarios(String eventoId) {
        final List<Usuario> usuarios = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarios.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = objSnapshot.getValue(Usuario.class);
                    usuarios.add(usuario);
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return usuarios;
    }


    @Override
    public int getItemCount() {
        return this.usuarios.size();
    }

}
