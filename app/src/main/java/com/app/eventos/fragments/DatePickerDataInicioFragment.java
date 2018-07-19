package com.app.eventos.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.DatePicker;

import com.app.eventos.R;
import com.app.eventos.utils.FormatacaoData;

import java.util.Calendar;
import java.util.Date;

public class DatePickerDataInicioFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    TextInputEditText editData;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();

        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH);
        int ano = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, dia, mes, ano);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        return datePickerDialog;
    }


    @Override
    public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
        editData = getActivity().findViewById(R.id.edit_data_inicio_evento);

        if (diaSelecionado < 10) {
            editData.setText("0" + diaSelecionado + "/" + (mesSelecionado + 1) + "/" + anoSelecionado);
        }

        else if (mesSelecionado < 10) {
            editData.setText(diaSelecionado + "/" + "0" + (mesSelecionado + 1) + "/" + anoSelecionado);
        }

        else if (anoSelecionado < 10) {
            editData.setText(diaSelecionado + "/" + (mesSelecionado + 1) + "/" + "0" + anoSelecionado);
        }

        else if (diaSelecionado < 10 && mesSelecionado < 10) {
            editData.setText("0" + diaSelecionado + "/" + "0" + (mesSelecionado + 1) + "/" + anoSelecionado);
        }

        else if (mesSelecionado < 10 && anoSelecionado < 10) {
            editData.setText(diaSelecionado + "/" + "0" + (mesSelecionado + 1) + "/" + "0" +anoSelecionado);
        }

        else if (diaSelecionado < 10 && anoSelecionado < 10) {
            editData.setText("0" + diaSelecionado + "/" + (mesSelecionado + 1) + "/" + "0" + anoSelecionado);
        }

        else {
            editData.setText(diaSelecionado + "/" + (mesSelecionado + 1) + "/" + anoSelecionado);
        }
    }
}
