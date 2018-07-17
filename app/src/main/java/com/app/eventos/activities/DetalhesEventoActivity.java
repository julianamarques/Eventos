package com.app.eventos.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.adapter.EventosAdapter;
import com.app.eventos.controllers.EventoController;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.model.Evento;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesEventoActivity extends AppCompatActivity {
    @BindView(R.id.txt_ver_evento) protected TextView txtVerEvento;

    private Evento evento;
    private int positionEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        ButterKnife.bind(this);

        positionEvento = getIntent().getIntExtra("positionEvento", -1);
        evento = (Evento) getIntent().getSerializableExtra("evento");
    }

    @Override
    protected void onResume() {
        super.onResume();

        getSupportActionBar().setTitle(evento.getNome());
        txtVerEvento.setText(evento.getDescricao() + "\n" + evento.getDataInicio() + "\n" + evento.getHoraInicio() + "\n" + evento.getDataFim());
    }
}
