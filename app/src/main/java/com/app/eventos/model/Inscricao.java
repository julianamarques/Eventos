package com.app.eventos.model;

import java.util.List;

public class Inscricao {

    private String id;
    private double valorTotal;
    private Boolean inscricaoPaga;
    private List<Atividade> atividades;
    private Evento evento;
    private String dataVencimento;
    private Usuario usuario;


    public Inscricao() {}

    public Inscricao(String id, Boolean inscricaoPaga, List<Atividade> atividades, double valorTotal) {
        this.inscricaoPaga = inscricaoPaga;
        this.atividades = atividades;
        this.id = id;
        this.valorTotal = valorTotal;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double calcularValorTotal() {
        for (int i = 0; i < getAtividades().size(); i++) {
            double valorDouble = getAtividades().get(i).getValor();
            valorTotal += valorDouble;
        }

        return valorTotal;
    }

    public Boolean getInscricaoPaga() {
        return inscricaoPaga;
    }

    public void setInscricaoPaga(Boolean inscricaoPaga) {
        this.inscricaoPaga = inscricaoPaga;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }
}
