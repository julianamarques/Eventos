package com.app.eventos.model;

public enum TipoAtividade {
    PALESTRA(0), MINICURSO(1), MESAREDONDA(2);

    private int tipoAtividade;

    TipoAtividade(int i) {
        tipoAtividade = i;
    }

    public int getTipoAtividade() {
        return tipoAtividade;
    }
}
