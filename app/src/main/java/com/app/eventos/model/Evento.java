package com.app.eventos.model;

import com.app.eventos.dao.ColaboradorDAO;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Evento implements Serializable {
    private String id;
    private String nome;
    private String dataInicio;
    private String dataFim;
    private String horaInicio;
    private String descricao;
    private List<Atividade> atividades = new ArrayList<>();
    private List<Inscricao> inscricoes;
    private List<Cupom> cupons;
    private Organizacao organizacao;
    private StatusEvento statusEvento;
    private String local;
    private String idUser;

    public Evento() {}


    public Evento(String id, String idUser, String nome, String dataInicio, String dataFim, String horaInicio, String descricao, String local) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.descricao = descricao;
        this.local = local;
        this.statusEvento = StatusEvento.CRIADO;
        this.id = id;
        this.idUser = idUser;
    }

    public Evento(String id, String idUser, String nome, String dataInicio, String dataFim, String horaInicio, String descricao, String local, StatusEvento statusEvento, List<Atividade> atividades) {
        this.id = id;
        this.idUser = idUser;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.descricao = descricao;
        this.local = local;
        this.statusEvento = statusEvento;
        this.atividades = atividades;

    }

    public String getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    public void setOrganizacao(Organizacao organizacao) {
        this.organizacao = organizacao;
    }

    public Organizacao getOrganizacao() {
        return organizacao;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", id);
        result.put("nome", nome);
        result.put("descricao", descricao);
        result.put("local", local);
        result.put("dataInicio", dataInicio);
        result.put("horaInicio", horaInicio);
        result.put("dataFim", dataFim);
        result.put("statusEvento", statusEvento);
        result.put("atividades", atividades);
        result.put("idUser", idUser);

        return  result;
    }
}
