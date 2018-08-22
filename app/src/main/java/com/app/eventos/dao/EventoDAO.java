package com.app.eventos.dao;

import android.support.annotation.NonNull;

import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Atividade;
import com.app.eventos.model.Evento;
import com.app.eventos.model.Inscricao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventoDAO {
    private Evento evento;

    public EventoDAO() {}

    public void cadastrarEvento(String nome, String dataInicio, String dataFim, String horaInicio, String descricao, String local, String idUser) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("eventos").push().getKey();
        evento = new Evento(id, idUser, nome, dataInicio, dataFim, horaInicio, descricao, local);

        Map<String, Object> eventoValues = evento.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/eventos/" + id, eventoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }

    public void deletarEvento(String eventoId){
        ConfiguracaoFirebase.getDatabaseReference().child("atividades").orderByPriority().equalTo(eventoId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    objSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(eventoId).removeValue();
    }

    public List<Evento> listarEventos() {
        final List<Evento> eventos = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").orderByChild("nome").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventos.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = objSnapshot.getValue(Evento.class);
                    eventos.add(evento);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return eventos;
    }
}
