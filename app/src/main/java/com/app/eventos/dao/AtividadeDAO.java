package com.app.eventos.dao;

import android.support.annotation.NonNull;

import com.app.eventos.model.Atividade;
import com.app.eventos.model.Evento;
import com.app.eventos.model.TipoAtividade;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtividadeDAO {

    private Atividade atividade;

    public AtividadeDAO() {}

    public void cadastrarAtividade(String nome, String data, String hora, String descricao, TipoAtividade tipoAtividade, double valor, String responsavel, Evento evento) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("atividades").push().getKey();
        atividade = new Atividade(id, nome, data, hora, descricao, tipoAtividade, valor, responsavel);
        evento.getAtividades().add(atividade);

        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + evento.getId(), eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }

    public List<Atividade> listarAtividades(String eventoId) {
        final List<Atividade> atividades = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(eventoId).child("atividades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                atividades.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Atividade atividade = objSnapshot.getValue(Atividade.class);
                    atividades.add(atividade);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return atividades;
    }
}
