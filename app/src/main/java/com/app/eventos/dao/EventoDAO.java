package com.app.eventos.dao;

import android.support.annotation.NonNull;

import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private Evento evento;

    public EventoDAO() {}

    public void cadastrarEvento(String nome, String dataInicio, String dataFim, String horaInicio, String descricao, String local, String idUser) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("eventos").push().getKey();
        evento = new Evento(id, nome, dataInicio, dataFim, horaInicio, descricao, local);

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(evento.getId()).setValue(evento, idUser);
    }

    public void deletarEvento(String eventoId){
        ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(eventoId).removeValue();
    }
}
