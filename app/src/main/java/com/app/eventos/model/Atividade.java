package com.app.eventos.model;

public class Atividade {

    private String id;
    private String nome;
    private String data;
    private String hora;
    private String descricao;
    private TipoAtividade tipoAtividade;
    private String valor;
    private String responsavel;

    public Atividade() {}

    public Atividade(String id, String nome, String data, String hora, String descricao, TipoAtividade tipoAtividade, String valor, String responsavel) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.valor = valor;
        this.responsavel = responsavel;
        this.id = id;
        this.tipoAtividade = tipoAtividade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
