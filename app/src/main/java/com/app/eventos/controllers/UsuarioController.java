package com.app.eventos.controllers;

import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.model.Usuario;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioController {
    private Usuario usuario;

    public UsuarioController() {}

    public void cadastrarUsuario(String nome, String email, String senha, String idUser) {
        usuario = new Usuario(nome, email, senha);
        ConfiguracaoFirebase.getDatabaseReference().child("usuarios").child(idUser).setValue(usuario);
    }
}
