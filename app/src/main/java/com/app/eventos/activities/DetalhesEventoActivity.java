package com.app.eventos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.adapter.AtividadeAdapter;
import com.app.eventos.dao.AtividadeDAO;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.model.Atividade;
import com.app.eventos.model.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalhesEventoActivity extends AppCompatActivity {

    @BindView(R.id.tv_informacoes_evento) protected TextView tvInformacoesEventos;
    @BindView(R.id.tv_descricao_evento) protected TextView tvDescricaoEventos;
    @BindView(R.id.rv_lista_atividade) protected RecyclerView recyclerMeusEventos;


    private Evento evento;
    private int positionEvento;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private AtividadeDAO atividadeDAO;
    private AtividadeAdapter atividadeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        positionEvento = getIntent().getIntExtra("positionEvento", -1);
        evento = (Evento) getIntent().getSerializableExtra("evento");
        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        atividadeDAO = new AtividadeDAO();
        user = auth.getCurrentUser();
    }

    @Override
    protected void onResume() {
        super.onResume();

        atividadeAdapter = new AtividadeAdapter(this, atividadeDAO.listarAtividades(evento.getId()));
        recyclerMeusEventos.setAdapter(atividadeAdapter);
        recyclerMeusEventos.setLayoutManager(new LinearLayoutManager(this));
        recyclerMeusEventos.setHasFixedSize(true);
        setarTextViews(evento, tvDescricaoEventos, tvInformacoesEventos);
        atividadeAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_inscricao)
    public void abrirTelaDeInscricao() {

        if (user != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("evento", evento);

            startActivity(new Intent(DetalhesEventoActivity.this, RealizarInscricaoActivity.class).putExtras(bundle));
        }

        else{
            Toast.makeText(DetalhesEventoActivity.this, "Faça o login para se efetuar uma inscrição", Toast.LENGTH_SHORT).show();
        }
    }

    private void setarTextViews(Evento evento, TextView tvDescricaoEventos, TextView tvInformacoesEventos) {
        getSupportActionBar().setTitle(evento.getNome());
        tvDescricaoEventos.setText("\n" + evento.getDescricao()+"\n\n");
        tvInformacoesEventos.setText("Local: " + evento.getLocal() +
                "\n\n" +"Status: " + evento.getStatusEvento() + "\n" + "Data de início: " + evento.getDataInicio() + "\n" + "Data de termino: " + evento.getDataFim()+ "\n" + "Hora de realização: " +
                evento.getHoraInicio() + "\n\n\n\n");
    }
}
