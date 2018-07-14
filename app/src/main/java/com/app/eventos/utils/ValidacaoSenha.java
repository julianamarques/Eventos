package com.app.eventos.utils;

import android.support.design.widget.TextInputEditText;

public class ValidacaoSenha {
    public static void validarSenha(TextInputEditText editSenha, TextInputEditText editRedigiteSenha) {
        if (!editSenha.getText().toString().trim().equals(editRedigiteSenha.getText().toString().trim())) {
            throw new IllegalArgumentException("As senhas não coincidem");
        }
    }
}
