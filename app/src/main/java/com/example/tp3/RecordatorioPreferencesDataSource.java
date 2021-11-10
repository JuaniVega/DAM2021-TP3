package com.example.tp3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource {

    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor miEditor;

    RecordatorioPreferencesDataSource(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        miEditor = sharedPreferences.edit();
    }

    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio, GuardarRecordatorioCallback callback) {
        String recordatorioString = recordatorio.getTexto()+" , "+recordatorio.getFecha();
        miEditor.putString("Nuevo_Recordatorio",recordatorioString);
        miEditor.commit(); //finalizo el proceso de crear el archivo y almacenar un nuevo recordatorio
        callback.resultado(true);
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {

    }

    public String recuperarDatos(){
        String recuperarString = sharedPreferences.getString("Nuevo_Recordatorio", "No existe la informacion");
        return recuperarString;
    }

}
