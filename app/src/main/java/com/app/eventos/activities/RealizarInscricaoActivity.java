package com.app.eventos.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.adapter.AtividadeAdapter;
import com.app.eventos.adapter.InscricaoEventoAdapter;
import com.app.eventos.dao.AtividadeDAO;
import com.app.eventos.dao.ConfiguracaoFirebase;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.dao.InscricaoDAO;
import com.app.eventos.model.Atividade;
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

public class RealizarInscricaoActivity extends AppCompatActivity {
    @BindView(R.id.rv_lista_atividades_inscricao) protected RecyclerView recyclerInscricao;
    @BindView(R.id.tv_valor_total) protected TextView tvValorTotal;


    private Evento evento;
    private int positionEvento;
    private FirebaseAuth auth;
    private AtividadeDAO atividadeDAO;
    private InscricaoEventoAdapter inscricaoEventoAdapter;
    private List<Atividade> atividades;
    public InscricaoDAO inscricaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao);
        ButterKnife.bind(this);

        positionEvento = getIntent().getIntExtra("positionEvento", -1);
        evento = (Evento) getIntent().getSerializableExtra("evento");
        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        atividadeDAO = new AtividadeDAO();
        inscricaoDAO = new InscricaoDAO();
    }

    public List<Atividade> listarAtividades(String eventoId) {
        final List<Atividade> atividades = new ArrayList<>();

    ConfiguracaoFirebase.getDatabaseReference().child("eventos").child(eventoId).child("atividades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                atividades.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Atividade atividade = objSnapshot.getValue(Atividade.class);
                    atividades.add(atividade);
                }

                inscricaoEventoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return atividades;
    }


    @Override
    protected void onResume() {
        super.onResume();

        inscricaoEventoAdapter = new InscricaoEventoAdapter(this, listarAtividades(evento.getId()));
        recyclerInscricao.setAdapter(inscricaoEventoAdapter);
        recyclerInscricao.setLayoutManager(new LinearLayoutManager(this));
        recyclerInscricao.setHasFixedSize(true);
        tvValorTotal.setText("Valor: R$" + inscricaoEventoAdapter.obterValorTotalInscricao());
    }

    @OnClick(R.id.btn_salvar_inscricao)
    public void salvarInscricao() {
        inscricaoDAO.cadastrarInscricao(evento, inscricaoEventoAdapter.getAtividadesInscricao(), auth.getUid());
        finish();
    }
}
