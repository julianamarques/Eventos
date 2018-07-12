package com.app.eventos.model;

class EventoTag {

    private Evento evento;
    private Tag tag;

    public EventoTag(Evento evento, Tag tag) {
        this.evento = evento;
        this.tag = tag;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
