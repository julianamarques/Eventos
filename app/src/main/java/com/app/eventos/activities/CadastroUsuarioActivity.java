package com.app.eventos.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.eventos.R;
import com.app.eventos.controllers.UsuarioController;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.utils.ValidacaoSenha;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroUsuarioActivity extends AppCompatActivity {
    @BindView(R.id.edit_nome) protected TextInputEditText editNome;
    @BindView(R.id.edit_email) protected TextInputEditText editEmail;
    @BindView(R.id.edit_senha) protected TextInputEditText editSenha;
    @BindView(R.id.edit_redigite_senha) protected TextInputEditText editRedigiteSenha;

    private UsuarioController usuarioController;
    private FirebaseAuth auth;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        ButterKnife.bind(this);

        usuarioController = new UsuarioController();
        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
    }

    @OnClick(R.id.btn_salvar_usuario)
    public void salvarUsuario(final View view) {
        final String nome = editNome.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();
        final String senha = editSenha.getText().toString().trim();

        try {
            ValidacaoSenha.validarSenha(editSenha, editRedigiteSenha);

            auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                idUser = auth.getUid();
                                usuarioController.cadastrarUsuario(nome, email, senha, idUser);
                                finish();
                            }

                            else {
                                Snackbar.make(view, "Não foi possível cadastrar!", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        catch (IllegalArgumentException e) {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }

}
