package com.app.eventos.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.eventos.R;
import com.app.eventos.adapter.InscricoesPorEventoAdapter;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.UsuarioDAO;
import com.app.eventos.model.Evento;
import com.app.eventos.model.Inscricao;
import com.app.eventos.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesMeuEventoActivity extends AppCompatActivity {
    @BindView(R.id.txt_info_meu_evento) protected TextView txtInfoMeuEvento;
    @BindView(R.id.rv_lista_inscricoes_no_evento) protected RecyclerView recyclerInscricoesNoEvento;

    private Evento evento;
    private int positionEvento;
    private InscricoesPorEventoAdapter inscricoesPorEventoAdapter;
    private UsuarioDAO usuarioDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_meu_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        positionEvento = getIntent().getIntExtra("positionEvento", -1);
        evento = (Evento) getIntent().getSerializableExtra("evento");
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getSupportActionBar().setTitle(evento.getNome());
        txtInfoMeuEvento.setText(evento.getDescricao() + "\n\n" + "Local: " + evento.getLocal()
                + "\n" + "Data de início: " + evento.getDataInicio() + "\n" + "Hora de realização: " + evento.getHoraInicio()
                + "\n" + "Data de término: " + evento.getDataFim() + "\n" + "Status: " + evento.getStatusEvento());

        inscricoesPorEventoAdapter = new InscricoesPorEventoAdapter(this, evento.getId(), usuarioDAO.listarUsuarios());
        recyclerInscricoesNoEvento.setAdapter(inscricoesPorEventoAdapter);
        recyclerInscricoesNoEvento.setLayoutManager(new LinearLayoutManager(this));
        recyclerInscricoesNoEvento.setHasFixedSize(true);
    }
}
