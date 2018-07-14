package com.app.eventos.model;

public class ApoioRealizacao {

    private Organizacao organizacao;
    private Evento evento;

    public ApoioRealizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
    }

    public void setOrganizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Evento getEvento() {
        return evento;
    }
}
