package com.app.eventos.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.adapter.MinhasInscricoesAdapter;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.dao.EventoDAO;
import com.app.eventos.dao.InscricaoDAO;
import com.app.eventos.model.Evento;
import com.app.eventos.model.Inscricao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MinhasInscricoesActivity extends AppCompatActivity {
    @BindView(R.id.rv_lista_minhas_inscricoes) RecyclerView reciclerMinhasInscricoes;

    private FirebaseAuth auth;
    private MinhasInscricoesAdapter minhasInscricoesAdapter;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_inscricoes);
        ButterKnife.bind(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        eventoDAO = new EventoDAO();
    }

    @Override
    protected void onResume() {
        super.onResume();

        minhasInscricoesAdapter = new MinhasInscricoesAdapter(this, eventoDAO.listarEventos(), auth);
        reciclerMinhasInscricoes.setAdapter(minhasInscricoesAdapter);
        reciclerMinhasInscricoes.setLayoutManager(new LinearLayoutManager(this));
        reciclerMinhasInscricoes.setHasFixedSize(true);
    }
 }