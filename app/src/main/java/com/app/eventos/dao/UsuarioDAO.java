package com.app.eventos.dao;

import com.app.eventos.activities.LoginActivity;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class UsuarioDAO {
    private Usuario usuario;

    public UsuarioDAO() {}

    public void cadastrarUsuario(String nome, String email, String senha, String idUser) {
        usuario = new Usuario(idUser, nome, email, senha);

        Map<String, Object> usuarioValues = usuario.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/usuarios/" + idUser, usuarioValues);
        ConfiguracaoFirebase.getDatabaseReference().updateChildren(childUpdates);
    }
}
