package com.app.eventos.utils;

import android.support.design.widget.TextInputEditText;

public class ValidacaoCampoVazio {
    public static void validarCampoVazio(TextInputEditText editText) {
        if (editText.getText().toString().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo não pode estar vazio!");
        }
    }
}
