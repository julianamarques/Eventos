package com.app.eventos.model;

public class Cupom {

    private Double desconto;
    private String codigo;
    private Evento evento;

    public Cupom(Double desconto, String codigo, Evento evento) {
        this.desconto = desconto;
        this.codigo = codigo;
        this.evento = evento;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
