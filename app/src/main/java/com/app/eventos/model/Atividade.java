package com.app.eventos.model;

public class Atividade {

    private String nome;
    private String hora;
    private String descricao;
    private TipoAtividade tipoAtividade;
    private Double valor;
    private String Responsavel;

    public Atividade(String nome, String hora, String descricao, TipoAtividade tipoAtividade, Double valor, String responsavel) {
        this.nome = nome;
        this.hora = hora;
        this.descricao = descricao;
        this.tipoAtividade = tipoAtividade;
        this.valor = valor;
        Responsavel = responsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String responsavel) {
        Responsavel = responsavel;
    }
}
