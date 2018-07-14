package com.app.eventos.model;

import java.util.ArrayList;

public class Organizacao {
    private String nome;
    private String descricao;
    private ArrayList<Evento> eventos;

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

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
}
