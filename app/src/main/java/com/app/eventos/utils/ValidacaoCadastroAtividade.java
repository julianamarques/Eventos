package com.app.eventos.utils;

public class ValidacaoCadastroAtividade {
    public static void validarCampoVazio(String nome, String data, String hora, String descricao, int idRadioButtonChecked, double valor, String responsavel) {
        if (nome.isEmpty() || descricao.isEmpty() || data.isEmpty() || hora.isEmpty() || (idRadioButtonChecked == -1) || String.valueOf(valor).isEmpty() || responsavel.isEmpty()) {
            throw new IllegalArgumentException("Há campos vazios!");
        }
    }
}
