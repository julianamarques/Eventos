package com.app.eventos.utils;


public class ValidacaoCadastroEventoCampoVazio {
    public static void validarCampoVazio(String nome, String descricao, String local, String dataInicio, String horaInicio, String dataFim) {
        if (nome.isEmpty() || descricao.isEmpty() || local.isEmpty() || dataInicio.isEmpty() || horaInicio.isEmpty() || dataFim.isEmpty()) {
            throw new IllegalArgumentException("Há campos vazios!");
        }
    }
}
