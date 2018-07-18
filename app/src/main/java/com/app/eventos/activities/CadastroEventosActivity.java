package com.app.eventos.activities;

import android.app.DialogFragment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.eventos.R;
import com.app.eventos.controllers.EventoController;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.fragments.DatePickerDataFimFragment;
import com.app.eventos.fragments.DatePickerDataInicioFragment;
import com.app.eventos.fragments.TimePickerFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroEventosActivity extends AppCompatActivity {
    @BindView(R.id.edit_nome_evento) protected TextInputEditText editNomeEvento;
    @BindView(R.id.edit_descricao_do_evento) protected TextInputEditText editDescricaoEvento;
    @BindView(R.id.edit_local_evento) protected TextInputEditText editLocalEvento;
    @BindView(R.id.edit_data_inicio_evento) protected TextInputEditText editDataInicioEvento;
    @BindView(R.id.edit_hora_inicio_evento) protected TextInputEditText editHoraInicioEvento;
    @BindView(R.id.edit_data_fim_evento) protected TextInputEditText editDataFimEvento;

    private FirebaseAuth auth;
    private String idUser;
    private EventoController eventoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_eventos);
        ButterKnife.bind(this);

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        eventoController = new EventoController();
    }

    @OnClick(R.id.btn_data_inicio_evento)
    public void abrirDatePickerDataInicio() {
        DialogFragment dialogFragment = new DatePickerDataInicioFragment();
        dialogFragment.show(getFragmentManager(), "datePickerDataInicio");
    }

    @OnClick(R.id.btn_hora_inicio_evento)
    public void abrirTimePicker() {
        DialogFragment dialogFragment = new TimePickerFragment();
        dialogFragment.show(getFragmentManager(), "timePicker");
    }

    @OnClick(R.id.btn_data_fim_evento)
    public void abrirDatePickerDataFim() {
        DialogFragment dialogFragment = new DatePickerDataFimFragment();
        dialogFragment.show(getFragmentManager(), "datePickerDataFim");
    }

    @OnClick(R.id.btn_salvar_evento)
    public void salvarEvento() {
        String nome = editNomeEvento.getText().toString().trim();
        String descricao = editDescricaoEvento.getText().toString().trim();
        String local = editLocalEvento.getText().toString().trim();
        String dataInicio = editDataInicioEvento.getText().toString().trim();
        String horaInicio = editHoraInicioEvento.getText().toString().trim();
        String dataFim = editDataFimEvento.getText().toString().trim();
        idUser = auth.getUid();

        eventoController.cadastrarEvento(nome, dataInicio, dataFim, horaInicio, descricao, local, idUser);
        finish();
    }
}
