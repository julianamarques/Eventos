package com.app.eventos.model;

import java.util.ArrayList;
import java.util.Date;

public class Evento {

    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private String horaInicio;
    private String descricao;
    private ArrayList<Atividade> atividades;
    private ArrayList<Inscricao> inscricoes;
    private ArrayList<Cupom> cupons;
    private ArrayList<Organizacao> organizadores;
    private StatusEvento statusEvento;
    private Local local;

    public Evento(String nome, Date dataInicio, Date dataFim, String horaInicio, String descricao, StatusEvento statusEvento, Local local) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.descricao = descricao;
        this.statusEvento = statusEvento;
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }


}
