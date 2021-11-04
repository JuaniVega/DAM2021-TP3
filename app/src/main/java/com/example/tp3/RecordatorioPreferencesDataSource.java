package com.example.tp3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class RecordatorioPreferencesDataSource {

    private final SharedPreferences sharedPreferences;
    RecordatorioPreferencesDataSource(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    // Implementación de los metodos de la interface
}
