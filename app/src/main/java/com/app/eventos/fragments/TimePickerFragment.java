package com.app.eventos.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.app.eventos.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private TextInputEditText editHora;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, minuto, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int horaSelecionada, int minutoSelecionado) {
        String hora = String.valueOf(horaSelecionada);
        String minuto = String.valueOf(minutoSelecionado);

        editHora = getActivity().findViewById(R.id.edit_hora_inicio_evento);

        if(minutoSelecionado < 10) {
            editHora.setText(hora + ":0" + minuto);
        }

        else {
            editHora.setText(hora + ":" + minuto);
        }

    }
}
