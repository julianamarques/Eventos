package com.app.eventos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.eventos.R;
import com.app.eventos.adapter.EventosAdapter;
import com.app.eventos.controllers.EventoController;
import com.app.eventos.model.Evento;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesEventoActivity extends AppCompatActivity {
    @BindView(R.id.txt_ver_evento) protected TextView txtVerEvento;

    private Evento evento;
    private int positionEvento;
    private EventoController eventoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        ButterKnife.bind(this);

        positionEvento = getIntent().getIntExtra("positionEvento", -1);
        eventoController = new EventoController();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (positionEvento != -1) {
            evento = eventoController.listarEventos().get(positionEvento);
        }

        getSupportActionBar().setTitle(evento.getNome());
        txtVerEvento.setText(evento.getDescricao() + "\n" + evento.getDataInicio() + "\n" + evento.getHoraInicio() + "\n" + evento.getDataFim());

    }
}
