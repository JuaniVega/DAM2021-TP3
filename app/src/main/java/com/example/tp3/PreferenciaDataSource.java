package com.example.tp3;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PreferenciaDataSource {

    private final SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public PreferenciaDataSource(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void guardarPreferencia(boolean valor) {
        editor.putBoolean("preferencia", valor);
        editor.commit();
    }

    public boolean recuperarPreferencia() {
        Boolean defaultValue = false;
        boolean valor = sharedPreferences.getBoolean("preferencia", defaultValue);
        return valor;
    }
}
