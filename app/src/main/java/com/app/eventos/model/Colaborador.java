package com.app.eventos.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Colaborador {

    private String id;
    private List<Usuario> colaboradores;
    private String idEvento;

    public Colaborador(String id, List<Usuario> colaboradores, String idEvento) {
        this.id = id;
        this.colaboradores = colaboradores;
        this.idEvento = idEvento;
    }

    public List<Usuario> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Usuario> colaboradores) {
        this.colaboradores = colaboradores;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", id);
        result.put("usuariosColaboradores", colaboradores);
        result.put("idEvento", idEvento);

        return  result;
    }


}

