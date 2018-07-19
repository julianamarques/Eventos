package com.app.eventos.model;

import java.util.List;

public class Organizacao {
    private String nome;
    private String descricao;
    private List<Evento> eventos;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
