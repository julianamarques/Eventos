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
    private InscricaoDAO inscricaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_inscricoes);
        ButterKnife.bind(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        inscricaoDAO = new InscricaoDAO();
    }

    @Override
    protected void onResume() {
        super.onResume();

        minhasInscricoesAdapter = new MinhasInscricoesAdapter(this, listarEventos(), listarMinhasInscricoes(auth));
        reciclerMinhasInscricoes.setAdapter(minhasInscricoesAdapter);
        reciclerMinhasInscricoes.setLayoutManager(new LinearLayoutManager(this));
        reciclerMinhasInscricoes.setHasFixedSize(true);
    }

    public List<Inscricao> listarMinhasInscricoes(FirebaseAuth auth) {
        final List<Inscricao> minhasInscricoes = new ArrayList<>();
        String usuarioId = auth.getUid();

        ConfiguracaoFirebase.getDatabaseReference().child("inscricoes").orderByChild("idUser").equalTo(usuarioId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                minhasInscricoes.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Inscricao inscricao = objSnapshot.getValue(Inscricao.class);
                    minhasInscricoes.add(inscricao);
                }

                minhasInscricoesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return minhasInscricoes;
    }

    public List<Evento> listarEventos() {
        final List<Evento> eventos = new ArrayList<>();

        ConfiguracaoFirebase.getDatabaseReference().child("eventos").orderByChild("nome").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventos.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Evento evento = objSnapshot.getValue(Evento.class);
                    eventos.add(evento);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return eventos;
    }
 }