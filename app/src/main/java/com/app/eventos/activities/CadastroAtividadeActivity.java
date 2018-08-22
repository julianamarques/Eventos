package com.app.eventos.activities;

import android.app.DialogFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.eventos.R;
import com.app.eventos.dao.AtividadeDAO;
import com.app.eventos.fragments.DatePickerDataAtividadeFragment;
import com.app.eventos.fragments.TimeAtividadePickerFragment;
import com.app.eventos.model.Atividade;
import com.app.eventos.model.Evento;
import com.app.eventos.model.TipoAtividade;
import com.app.eventos.utils.ValidacaoCadastroEventoCampoVazio;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroAtividadeActivity extends AppCompatActivity {
    @BindView(R.id.edit_nome_atividade) protected EditText editNomeAtividade;
    @BindView(R.id.edit_data_atividade) protected EditText editDataAtividade;
    @BindView(R.id.edit_hora_atividade) protected EditText editHoraAtividade;
    @BindView(R.id.edit_descricao_atividade) protected EditText editDescricaoAtividade;
    @BindView(R.id.edit_valor_atividade) protected EditText editValorAtividade;
    @BindView(R.id.edit_responsavel_atividade) protected EditText editResponsavelAtividade;
    @BindView(R.id.radio_group_atividade) protected RadioGroup radioGroup;

    private AtividadeDAO atividadeDAO;
    private Evento evento;
    private String idEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atividade);
        ButterKnife.bind(this);

        evento = (Evento) getIntent().getSerializableExtra("evento");
        atividadeDAO = new AtividadeDAO();
    }

    @OnClick(R.id.btn_data_inicio_atividade)
    public void abrirDatePickerDataAtividade() {
        DialogFragment dialogFragment = new DatePickerDataAtividadeFragment();
        dialogFragment.show(getFragmentManager(), "datePickerDataAtividade");
    }

    @OnClick(R.id.btn_hora_inicio_atividade)
    public void abrirTimePicker() {
        DialogFragment dialogFragment = new TimeAtividadePickerFragment();
        dialogFragment.show(getFragmentManager(), "timeAtividadePicker");
    }

    @OnClick(R.id.btn_salvar_atividade)
    public void salvarAtividade(View view) {
        String nome = editNomeAtividade.getText().toString().trim();
        String data = editDataAtividade.getText().toString().trim();
        String hora = editHoraAtividade.getText().toString().trim();
        String descricao = editDescricaoAtividade.getText().toString().trim();
        double valor = Double.valueOf(editValorAtividade.getText().toString().trim());
        String responsavel = editResponsavelAtividade.getText().toString().trim();

        int idRadioButtonChecked = radioGroup.getCheckedRadioButtonId();
        TipoAtividade tipoAtividade = null;

        if (idRadioButtonChecked == R.id.radio_mesa_redonda) {
            tipoAtividade = TipoAtividade.MESA_REDONDA;
        }

        else if (idRadioButtonChecked == R.id.radio_palestra) {
            tipoAtividade = TipoAtividade.PALESTRA;
        }

        else if(idRadioButtonChecked == R.id.radio_mini_curso) {
            tipoAtividade = TipoAtividade.MINICURSO;
        }

        idEvento = evento.getId();


        try {
            ValidacaoCadastroEventoCampoVazio.validarCampoVazioAtividade(nome, data, hora, descricao, idRadioButtonChecked, valor, responsavel, idEvento);
            atividadeDAO.cadastrarAtividade(nome, data, hora, descricao, tipoAtividade, valor, responsavel, evento);
            finish();
            Toast.makeText(this,"Atividade Cadastrada!",Toast.LENGTH_SHORT).show();
        }

        catch (IllegalArgumentException e) {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }


}
