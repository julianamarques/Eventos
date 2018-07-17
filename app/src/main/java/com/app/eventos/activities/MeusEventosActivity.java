package com.app.eventos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.eventos.R;
import com.app.eventos.adapter.MeusEventosAdapter;
import com.app.eventos.controllers.EventoController;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeusEventosActivity extends AppCompatActivity {
    @BindView(R.id.rv_lista_meus_eventos) protected RecyclerView recyclerMeusEventos;

    private FirebaseAuth auth;
    private EventoController eventoController;
    private MeusEventosAdapter meusEventosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_eventos);
        ButterKnife.bind(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        eventoController = new EventoController();
        meusEventosAdapter = new MeusEventosAdapter(this, eventoController.listarMeusEventos(auth));
        meusEventosAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        recyclerMeusEventos.setAdapter(meusEventosAdapter);
        recyclerMeusEventos.setLayoutManager(new LinearLayoutManager(this));
        recyclerMeusEventos.setHasFixedSize(true);

    }

    @OnClick(R.id.fab_adicionar_eventos)
    public void abrirCadastroEventos() {
        startActivity(new Intent(this, CadastroEventosActivity.class));
    }
}
