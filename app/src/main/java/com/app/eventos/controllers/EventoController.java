package com.app.eventos.controllers;

import android.support.annotation.NonNull;

import com.app.eventos.activities.MainActivity;
import com.app.eventos.adapter.EventosAdapter;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Evento;
import com.app.eventos.utils.FormatacaoData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoController {
    private Evento evento;

    public EventoController() {}

    public void cadastrarEvento(String nome, String dataInicio, String dataFim, String horaInicio, String descricao, String local, String idUser) {
        evento = new Evento(nome, dataInicio, dataFim, horaInicio, descricao, local);

        //ConfiguracaoFirebase.getDatabaseReference().child("usuarios").child(idUser).child("eventos").push().setValue(evento);
        ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(evento.getId()).setValue(evento, idUser);
    }

    public List<Evento> listarEventos() {
        final List<Evento> eventos = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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

    public List<Evento> listarMeusEventos(FirebaseAuth auth) {
        final List<Evento> meusEventos = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").orderByPriority().equalTo(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = objSnapshot.getValue(Evento.class);
                    meusEventos.add(evento);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return meusEventos;
    }
}
