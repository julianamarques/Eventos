package com.app.eventos.dao;

import com.app.eventos.model.Atividade;
import com.app.eventos.model.Evento;
import com.app.eventos.model.Inscricao;

import java.util.List;

public class InscricaoDAO {
    private Inscricao inscricao;

    public InscricaoDAO() {}

    public void cadastrarInscricao(Evento evento, List<Atividade> atividadesInscricao, String idUser, double valor) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("inscricoes").push().getKey();
        String priority = idUser + evento.getId();
        inscricao = new Inscricao(id, false, atividadesInscricao, valor);

        ConfiguracaoFirebase.getDatabaseReference().child("inscricoes").child(inscricao.getId()).setValue(inscricao, priority);
    }
}
