package com.app.eventos.utils;

import android.support.design.widget.TextInputEditText;

public class ValidacaoSenha {
    public static void validarSeSenhasCoincidem(TextInputEditText editSenha, TextInputEditText editRedigiteSenha) {
        if (!editSenha.getText().toString().trim().equals(editRedigiteSenha.getText().toString().trim())) {
            throw new IllegalArgumentException("As senhas não coincidem");
        }
    }

    public static void validarTamanhoMinimo(TextInputEditText editSenha) {
        if(editSenha.getText().toString().trim().length() < 6) {
            throw new IllegalArgumentException("A senha precisa ter no mínimo 6 dígitos");
        }
    }
}
