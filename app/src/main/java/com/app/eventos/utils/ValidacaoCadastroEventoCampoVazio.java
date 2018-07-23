package com.app.eventos.utils;


import com.app.eventos.model.Evento;
import com.app.eventos.model.TipoAtividade;

public class ValidacaoCadastroEventoCampoVazio {
    public static void validarCampoVazio(String nome, String descricao, String local, String dataInicio, String horaInicio, String dataFim) {
        if (nome.isEmpty() || descricao.isEmpty() || local.isEmpty() || dataInicio.isEmpty() || horaInicio.isEmpty() || dataFim.isEmpty()) {
            throw new IllegalArgumentException("Há campos vazios!");
        }
    }

    public static void validarCampoVazioAtividade(String nome, String data, String hora, String descricao, String tipoAtividade, String valor, String responsavel, String idUser) {
        if (nome.isEmpty() || descricao.isEmpty() || data.isEmpty() || hora.isEmpty() || tipoAtividade.isEmpty() || valor.isEmpty() || responsavel.isEmpty() || idUser.isEmpty()) {
            throw new IllegalArgumentException("Há campos vazios!");
        }
    }
}
