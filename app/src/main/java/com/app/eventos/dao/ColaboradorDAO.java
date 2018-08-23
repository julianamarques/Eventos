package com.app.eventos.dao;

import com.app.eventos.model.Colaborador;

import java.util.HashMap;
import java.util.Map;

public class ColaboradorDAO {
    private Colaborador colaborador;

    public ColaboradorDAO() {}

    public void salvarColaborador(String idUser, String idEvento) {
        String id = ConfiguracaoFirebase.getDatabaseReference().child("colaboradores").push().getKey();
        colaborador = new Colaborador(id, idUser, idEvento);

        Map<String, Object> colaboradorValues = colaborador.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/colaboradores/" + id, colaboradorValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }


    public void recuperarListaColaborador(){

    }

    public void recuperarColaborador(){

    }

    public void deletarColaborador(){

    }
}

