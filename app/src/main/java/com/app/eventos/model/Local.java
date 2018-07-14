package com.app.eventos.model;

public class Local {

    private String rua;
    private Integer num;
    private String bairro;

    public Local(String rua, Integer num, String bairro) {
        this.rua = rua;
        this.num = num;
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
