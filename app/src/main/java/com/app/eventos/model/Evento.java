package com.app.eventos.model;

import com.app.eventos.dao.ConfiguracaoFirebase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Evento implements Serializable {
    private String id;
    private String nome;
    private String dataInicio;
    private String dataFim;
    private String horaInicio;
    private String descricao;
    private ArrayList<Atividade> atividades;
    private ArrayList<Inscricao> inscricoes;
    private ArrayList<Cupom> cupons;
    private ArrayList<ApoioRealizacao> apoioRealizacoes;
    private StatusEvento statusEvento;
    private String local;

    public Evento() {}

    public Evento(String nome, String dataInicio, String dataFim, String horaInicio, String descricao, String local) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.descricao = descricao;
        this.local = local;
        this.id = ConfiguracaoFirebase.getDatabaseReference().child("eventos").push().getKey();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusEvento getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(StatusEvento statusEvento) {
        this.statusEvento = statusEvento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
