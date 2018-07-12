package com.app.eventos.model;

public class Tag {

    private String nome;
    private EventoTag eventoTag;

    public Tag(String nome, EventoTag eventoTag) {
        this.nome = nome;
        this.eventoTag = eventoTag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EventoTag getEventoTag() {
        return eventoTag;
    }

    public void setEventoTag(EventoTag eventoTag) {
        this.eventoTag = eventoTag;
    }
}
