package com.app.eventos.model;

public enum StatusEvento {
    INSCRICOES_ABERTAS(0), EM_ANDAMENTO(1), REALIZADO(2);

    private int statusEvento;

    StatusEvento(int i) {
        statusEvento = i;
    }

    public int getStatusEvento() {
        return statusEvento;
    }
}
