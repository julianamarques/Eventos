package com.app.eventos.activities;

import android.app.DialogFragment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.eventos.R;
import com.app.eventos.dao.ConfiguracaoFirebaseAuth;
import com.app.eventos.dao.EventoDAO;
import com.app.eventos.fragments.DatePickerDataFimFragment;
import com.app.eventos.fragments.DatePickerDataInicioFragment;
import com.app.eventos.fragments.TimePickerFragment;
import com.app.eventos.model.Evento;
import com.app.eventos.model.StatusEvento;
import com.app.eventos.utils.ValidacaoCadastroEvento;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditarEventoActivity extends AppCompatActivity {

    @BindView(R.id.edit_nome_evento) protected TextInputEditText editNomeEvento;
    @BindView(R.id.edit_descricao_do_evento) protected TextInputEditText editDescricaoEvento;
    @BindView(R.id.edit_local_evento) protected TextInputEditText editLocalEvento;
    @BindView(R.id.edit_data_inicio_evento) protected TextInputEditText editDataInicioEvento;
    @BindView(R.id.edit_hora_inicio_evento) protected TextInputEditText editHoraInicioEvento;
    @BindView(R.id.edit_data_fim_evento) protected TextInputEditText editDataFimEvento;
    @BindView(R.id.radio_group_status_evento) protected RadioGroup radioGroup;
    @BindView(R.id.radio_b_criado) protected RadioButton radioButtonCriado;
    @BindView(R.id.radio_b_insc_aberta) protected RadioButton radioButtonInscricaoAberta;
    @BindView(R.id.radio_b_em_andamento) protected RadioButton radioButtonEmAndamento;
    @BindView(R.id.radio_b_realizado) protected RadioButton radioButtonRealizado;

    private Evento evento;

    private FirebaseAuth auth;
    private String idUser;
    private EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        ButterKnife.bind(this);

        evento = (Evento) getIntent().getSerializableExtra("evento");

        auth = ConfiguracaoFirebaseAuth.getFirebaseAuth();
        eventoDAO = new EventoDAO();

        setandoTextoEdit(evento);

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
    public void salvarEvento(View view) {
        String nome = editNomeEvento.getText().toString().trim();
        String descricao = editDescricaoEvento.getText().toString().trim();
        String local = editLocalEvento.getText().toString().trim();
        String dataInicio = editDataInicioEvento.getText().toString().trim();
        String horaInicio = editHoraInicioEvento.getText().toString().trim();
        String dataFim = editDataFimEvento.getText().toString().trim();
        StatusEvento status = setandoStatusEvento(evento);

        idUser = auth.getUid();

        try {
            ValidacaoCadastroEvento.validarCampoVazio(nome, descricao, local, dataInicio, horaInicio, dataFim);
            eventoDAO.editarEvento(nome, dataInicio, dataFim, horaInicio, descricao, local, idUser, evento.getId(), status, evento.getAtividades());

            Snackbar.make(view, "Conclua o cadastro em Meus eventos", Snackbar.LENGTH_SHORT).show();

            finish();
        }

        catch (IllegalArgumentException e) {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }

    private StatusEvento setandoStatusEvento(Evento evento) {
        int idRadioButtonChecked = radioGroup.getCheckedRadioButtonId();
        StatusEvento statusEvento = null;

        if (idRadioButtonChecked == R.id.radio_b_criado) {
            statusEvento = StatusEvento.CRIADO;
        }

        else if (idRadioButtonChecked == R.id.radio_b_insc_aberta) {
            statusEvento = StatusEvento.INSCRICOES_ABERTAS;
        }

        else if(idRadioButtonChecked == R.id.radio_b_em_andamento) {
            statusEvento = StatusEvento.EM_ANDAMENTO;
        }

        else if(idRadioButtonChecked == R.id.radio_b_realizado) {
            statusEvento = StatusEvento.REALIZADO;
        }

        return statusEvento;

    }

    private void setandoTextoEdit(Evento evento) {
        editNomeEvento.setText(evento.getNome());
        editDescricaoEvento.setText(evento.getDescricao());
        editLocalEvento.setText(evento.getLocal());
        editDataInicioEvento.setText(evento.getDataInicio());
        editDataFimEvento.setText(evento.getDataFim());
        editHoraInicioEvento.setText(evento.getHoraInicio());
        StatusEvento status = evento.getStatusEvento();


        switch (status.getStatusEvento()){
            case(0):
                radioButtonCriado.setChecked(true);
                break;

            case(1):
                radioButtonInscricaoAberta.setChecked(true);
                break;

            case(2):
                radioButtonEmAndamento.setChecked(true);
                break;

            case(3):
                radioButtonRealizado.setChecked(true);
                break;
        }



    }




}
