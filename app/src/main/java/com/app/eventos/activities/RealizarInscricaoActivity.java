package com.app.eventos.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventos.R;
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
    private FirebaseAuth auth;
    private InscricaoEventoAdapter inscricaoEventoAdapter;
    public InscricaoDAO inscricaoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_inscricao);
        ButterKnife.bind(this);

        evento = (Evento) getIntent().getSerializableExtra("evento");
        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        inscricaoDAO = new InscricaoDAO();
    }

    @Override
    protected void onResume() {
        super.onResume();

        inscricaoEventoAdapter = new InscricaoEventoAdapter(this, evento.getId(), tvValorTotal);
        recyclerInscricao.setAdapter(inscricaoEventoAdapter);
        recyclerInscricao.setLayoutManager(new LinearLayoutManager(this));
        recyclerInscricao.setHasFixedSize(true);
    }

    @OnClick(R.id.btn_salvar_inscricao)
    public void salvarInscricao() {
        if(!inscricaoEventoAdapter.getAtividadesInscricao().isEmpty()){
            inscricaoDAO.cadastrarInscricao(evento, inscricaoEventoAdapter.getAtividadesInscricao(), auth.getUid());
            finish();
        }

        else{
            Toast.makeText(this, "Escolha pelo menos uma atividade para se inscrever no evento", Toast.LENGTH_SHORT).show();
        }

    }
}
