package com.app.eventos.model;

public enum StatusEvento {
    INSCRICOESABERTAS(0), EMANDAMENTO(1), REALIZADO(2);

    private int statusEvento;

    StatusEvento(int i) {
        statusEvento = i;
    }

    public int getStatusEvento() {
        return statusEvento;
    }
}
