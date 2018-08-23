package com.app.eventos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.adapter.ColaboradorAdapter;
import com.app.eventos.dao.ColaboradorDAO;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdicionarColaboradorActivity extends AppCompatActivity {
    @BindView(R.id.rv_lista_colaboradores) protected RecyclerView recyclerMeusUsuarios;

    private Evento evento;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private ColaboradorAdapter colaboradorAdapter;
    private ColaboradorDAO colaboradorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_colaborador);
        ButterKnife.bind(this);

        evento = (Evento) getIntent().getSerializableExtra("evento");
        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        user = auth.getCurrentUser();
        colaboradorDAO = new ColaboradorDAO();
    }

    @Override
    protected void onResume() {
        super.onResume();

        colaboradorAdapter = new ColaboradorAdapter(this, auth.getUid());
        recyclerMeusUsuarios.setAdapter(colaboradorAdapter);
        recyclerMeusUsuarios.setLayoutManager(new LinearLayoutManager(this));
        recyclerMeusUsuarios.setHasFixedSize(true);

    }

    @OnClick(R.id.btn_salvar_colaborador)
    public void salvarColaborador() {
        if(!colaboradorAdapter.getUsuariosColaboradores().isEmpty()){
            colaboradorDAO.salvarColaborador(auth.getUid(),evento.getId());
            finish();
        }

        else{
            Toast.makeText(this, "Escolha pelo menos um colaborador para contribuir com seu trabalho", Toast.LENGTH_SHORT).show();
        }

    }

}
