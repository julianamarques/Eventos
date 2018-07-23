package com.app.eventos.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.eventos.R;
import com.app.eventos.model.Evento;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InscricaoActivity extends AppCompatActivity {

    @BindView(R.id.tv_informacoes_evento) protected TextView tvInformacoesEventos;

    private Evento evento;
    private int positionEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        positionEvento = getIntent().getIntExtra("positionEvento", -1);
        evento = (Evento) getIntent().getSerializableExtra("evento");

    }

    @Override
    protected void onResume() {
        super.onResume();

        getSupportActionBar().setTitle(evento.getNome());
        tvInformacoesEventos.setText("\n" + evento.getDescricao() + "\n\n" + "Local: " + evento.getLocal() +
                "\n\n" +"Status: " + evento.getStatusEvento() + "\n" + "Data de início: " + evento.getDataInicio() + "\n" + "Data de termino: " + evento.getDataFim()+ "\n" + "Hora de realização: " +
                evento.getHoraInicio() + "\n\n\n\n");
    }
}
