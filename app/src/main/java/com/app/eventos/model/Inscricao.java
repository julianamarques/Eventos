package com.app.eventos.model;

import java.util.ArrayList;
import java.util.Date;

class Inscricao {

    private Double valorTotal;
    private ArrayList<Atividade> atividades;
    private Boolean inscricaoPaga;
    private Evento evento;
    private Date dataVencimento;
    private Usuario usuario;
    private static int qtdPalestra;
    private static int qtdMiniCurso;
    private static int qtdMesaRedonda;
    private static Double valorPalestra = 15.00;
    private static Double valorMiniCurso = 20.00;
    private static Double valorMesaRedonda = 30.00;

    public Inscricao(Double valorTotal, ArrayList<Atividade> atividades, Boolean inscricaoPaga, Evento evento, Date dataVencimento, Usuario usuario) {
        this.valorTotal = valorTotal;
        this.atividades = atividades;
        this.inscricaoPaga = inscricaoPaga;
        this.evento = evento;
        this.dataVencimento = dataVencimento;
        this.usuario = usuario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(ArrayList<Atividade> atividades) {
        this.atividades = atividades;
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

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void encontraQuantidadeTiposAtividade(ArrayList<Atividade> atividades){
        qtdPalestra = 0;
        qtdMiniCurso = 0;
        qtdMesaRedonda = 0;

        for (int i = 0; i < atividades.size(); i++){
            if (atividades.get(i).getTipoAtividade().equals(TipoAtividade.PALESTRA)){
                qtdPalestra ++;

            }else if (atividades.get(i).getTipoAtividade().equals(TipoAtividade.MINICURSO)){
                qtdMiniCurso ++;

            }else if (atividades.get(i).getTipoAtividade().equals(TipoAtividade.MINICURSO)){
                qtdMesaRedonda ++;
            }
        }

    }
}
