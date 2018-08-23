package com.app.eventos.dao;

import android.support.annotation.NonNull;

import com.app.eventos.adapter.MinhasInscricoesAdapter;
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

public class InscricaoDAO {
    private Inscricao inscricao;

    public InscricaoDAO() {}

    public void cadastrarInscricao(Evento evento, List<Atividade> atividades, String idUser) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("inscricoes").push().getKey();
        inscricao = new Inscricao(id, evento.getId(), idUser, atividades, false);

        Map<String, Object> inscricaoValues = inscricao.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/inscricoes/" + id, inscricaoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }


    public void confirmarInscricao(Inscricao inscricao) {
        inscricao.setInscricaoPaga(true);

        Map<String, Object> inscricaoValues = inscricao.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/inscricoes/" + inscricao.getId(), inscricaoValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }
}
