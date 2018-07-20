package com.app.eventos.controllers;

import android.support.annotation.NonNull;

import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventoController {
    private Evento evento;

    public EventoController() {}

    public void cadastrarEvento(String nome, String dataInicio, String dataFim, String horaInicio, String descricao, String local, String idUser) {
        evento = new Evento(nome, dataInicio, dataFim, horaInicio, descricao, local);

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(evento.getId()).setValue(evento, idUser);
    }
}
