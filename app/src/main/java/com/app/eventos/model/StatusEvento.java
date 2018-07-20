package com.app.eventos.model;

public enum StatusEvento {
    CRIADO(0), INSCRICOES_ABERTAS(1), EM_ANDAMENTO(2), REALIZADO(3);

    private int statusEvento;

    StatusEvento(int i) {
        statusEvento = i;
    }

    public int getStatusEvento() {
        return statusEvento;
    }
}
