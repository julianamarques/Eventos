package com.app.eventos.model;

public enum TipoAtividade {
    EMDEBITO(0), VENCIDA(1), QUITADA(2);

    private int tipoAtividade;

    TipoAtividade(int i) {
        tipoAtividade = i;
    }

    public int getTipoAtividade() {
        return tipoAtividade;
    }
}
