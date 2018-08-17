package com.app.eventos.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inscricao {

    private String id;
    private double valorTotal;
    private Boolean inscricaoPaga;
    private List<Atividade> atividades;
    private String dataVencimento;
    private String idUser;
    private String idEvento;


    public Inscricao() {}

    public Inscricao(String id, String idEvento, String idUser, List<Atividade> atividades, Boolean inscricaoPaga) {
        this.inscricaoPaga = inscricaoPaga;
        this.atividades = atividades;
        this.id = id;
        this.idEvento = idEvento;
        this.idUser = idUser;
        this.valorTotal = calcularValorTotal();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double calcularValorTotal() {
        for (int i = 0; i < getAtividades().size(); i++) {
            valorTotal += getAtividades().get(i).getValor();
        }

        return valorTotal;
    }

    public Boolean getInscricaoPaga() {
        return inscricaoPaga;
    }

    public void setInscricaoPaga(Boolean inscricaoPaga) {
        this.inscricaoPaga = inscricaoPaga;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("id", id);
        result.put("valor", valorTotal);
        result.put("atividades", atividades);
        result.put("inscricaoPaga", inscricaoPaga);
        result.put("idUser", idUser);
        result.put("idEvento", idEvento);

        return result;
    }
}
