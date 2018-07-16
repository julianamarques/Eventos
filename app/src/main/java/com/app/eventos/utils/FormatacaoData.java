package com.app.eventos.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatacaoData {

    public FormatacaoData() {}

    public static String formatarData(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }
}
