package com.app.eventos.dao;

import com.app.eventos.model.Atividade;
import com.app.eventos.model.Evento;
import com.app.eventos.model.Inscricao;

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
}
