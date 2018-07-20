package com.app.eventos.model;

import java.util.List;

class Inscricao {

    private Double valorTotal;
    private Boolean inscricaoPaga;
    private List<Atividade> atividades;
    private Evento evento;
    private String dataVencimento;
    private Usuario usuario;

    public Inscricao(Double valorTotal, Boolean inscricaoPaga, Evento evento, String dataVencimento, Usuario usuario) {
        this.valorTotal = valorTotal;
        this.inscricaoPaga = inscricaoPaga;
        this.evento = evento;
        this.dataVencimento = dataVencimento;
        this.usuario = usuario;
    }

    public Double calcularValorTotal() {
        for (int i = 0; i < atividades.size(); i++) {
            valorTotal += this.atividades.get(i).getValor();
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
