package com.app.eventos.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.eventos.R;
import com.app.eventos.adapter.MeusEventosAdapter;
import com.app.eventos.dao.EventoDAO;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeusEventosActivity extends AppCompatActivity {
    @BindView(R.id.rv_lista_meus_eventos) protected RecyclerView recyclerMeusEventos;

    private FirebaseAuth auth;
    private MeusEventosAdapter meusEventosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_eventos);
        ButterKnife.bind(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
    }

    @Override
    protected void onResume() {
        super.onResume();

        meusEventosAdapter = new MeusEventosAdapter(this, auth);
        recyclerMeusEventos.setAdapter(meusEventosAdapter);
        recyclerMeusEventos.setLayoutManager(new LinearLayoutManager(this));
        recyclerMeusEventos.setHasFixedSize(true);
    }

    @OnClick(R.id.fab_adicionar_eventos)
    public void abrirCadastroEventos() {
        startActivity(new Intent(this, CadastroEventosActivity.class));
    }
}
