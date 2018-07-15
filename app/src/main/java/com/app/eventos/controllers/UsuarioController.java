package com.app.eventos.controllers;

import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Usuario;

public class UsuarioController {
    private Usuario usuario;
    private static int idUsuario = 0;


    public void cadastrarUsuario(String nome, String email, String senha) {
        String idUsuario = String.valueOf(this.idUsuario);

        usuario = new Usuario(nome, email, senha);

        ConfiguracaoFirebase.getDatabaseReference().child("usuarios").child(idUsuario).push().setValue(usuario);
        this.idUsuario += 1;
    }
}
