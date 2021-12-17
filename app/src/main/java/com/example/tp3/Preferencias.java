package com.example.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Preferencias extends AppCompatActivity {

    private Switch switch1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mostrar_notif);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        PreferenciaDataSource preferenciaDataSource = new PreferenciaDataSource(Preferencias.this);

        switch1=findViewById(R.id.switch1);
        boolean valorSwitch;

        valorSwitch= preferenciaDataSource.recuperarPreferencia();

        switch1.setChecked(valorSwitch);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                               @Override
                                               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                   preferenciaDataSource.guardarPreferencia(switch1.isChecked());
                                               }
                                           });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Preferencias.this, ListaRecordatorios.class);
                startActivity(intent);
            }
        });

    }

}
