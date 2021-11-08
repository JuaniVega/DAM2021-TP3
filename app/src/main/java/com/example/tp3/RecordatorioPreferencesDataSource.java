package com.example.tp3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.List;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource{
    private final SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    RecordatorioPreferencesDataSource(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio/*, GuardarRecordatorioCallback callback*/) {
        String record= recordatorio.getTexto()+", "+recordatorio.getFecha();
        editor.putString("recordatorio", record);
        editor.commit();
    }

    @Override
    public String recuperarRecordatorios(/*RecuperarRecordatorioCallback callback*/) {
        String defaultValue = "NO";
        String recordatorios = sharedPreferences.getString("recordatorio", defaultValue);
        return recordatorios;
    }
}
