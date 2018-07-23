package com.app.eventos.dao;

import com.app.eventos.model.Atividade;
import com.app.eventos.model.Evento;
import com.app.eventos.model.TipoAtividade;

public class AtividadeDAO {

    private Atividade atividade;

    public AtividadeDAO() {}

    public void cadastrarAtividade(String nome, String data, String hora, String descricao, String tipoAtividade, String valor, String responsavel, String idUser) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("atividades").push().getKey();
        atividade = new Atividade(id, nome, data, hora, descricao, tipoAtividade, valor, responsavel);

        ConfiguracaoFirebase.getDatabaseReference().child("atividades").child(atividade.getId()).setValue(atividade, id);
    }
}
