package com.example.tp3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource{
    private final SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    List<String> recordatorios= new ArrayList<String>();

    RecordatorioPreferencesDataSource(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio, GuardarRecordatorioCallback callback) {
        String record= recordatorio.getTexto()+", "+recordatorio.getFecha();
        editor.putString("recordatorio"+recordatorio.getid(), record);
        callback.resultado(editor.commit());
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {
        String defaultValue = "NO";
        Map<String, ?> rec = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : rec.entrySet()) {
            if (entry.getKey().startsWith("recordatorio")) {
                recordatorios.add(sharedPreferences.getString(entry.getKey(), defaultValue));
            }
        }
        callback.resultado(true, recordatorios);
    }

    public void eliminarRecordatorio(){
        editor.clear().commit();
    }
}

