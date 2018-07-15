package com.app.eventos.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.eventos.R;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.edit_login_email) protected TextInputEditText editLoginEmail;
    @BindView(R.id.edit_login_senha) protected TextInputEditText editLoginSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
    }

    @OnClick(R.id.btn_entrar)
    public void entrar(final View view) {
        String email = editLoginEmail.getText().toString().trim();
        String senha = editLoginSenha.getText().toString().trim();

        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                        }

                        else {
                            Snackbar.make(view, "Não foi possível logar!", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick(R.id.btn_abrir_cadastro_usuario)
    public void abrirCadastroUsuario() {
        startActivity(new Intent(this, CadastroUsuarioActivity.class));
    }
}
