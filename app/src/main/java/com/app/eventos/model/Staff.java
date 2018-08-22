package com.app.eventos.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Staff {

    private String id;
    private String idEvento;
    private String idUser;

    public Staff(String id, String idEvento, String idUser) {
        this.id = id;
        this.idEvento = idEvento;
        this.idUser = idUser;
    }

    public String getEvento() {
        return idEvento;
    }

    public void setEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", id);
        result.put("idEvento", idEvento);
        result.put("idUser", idUser);

        return  result;
    }


}

