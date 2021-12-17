package com.example.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ListaRecordatorios extends AppCompatActivity {

    private mAdapter adapter;
    private FloatingActionButton volver;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_record);

        volver=findViewById(R.id.floating_action_button);
        RecyclerView recyclerView= findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecordatorioPreferencesDataSource recordatorioPreferencesDataSource= new RecordatorioPreferencesDataSource(ListaRecordatorios.this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<String> myDataSet = new ArrayList<>();
        recordatorioPreferencesDataSource.recuperarRecordatorios(new RecordatorioDataSource.RecuperarRecordatorioCallback() {
            @Override
            public void resultado(boolean exito, List<String> recordatorios) {
                for (int i=0; i<recordatorios.size();i++) {
                        myDataSet.add(recordatorios.get(i));
                }
            }
        });

        adapter = new mAdapter(myDataSet);
        recyclerView.setAdapter(adapter);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaRecordatorios.this, CrearRecordatorios.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(ListaRecordatorios.this, ListaRecordatorios.class);
                startActivity(intent);
                break;
            case R.id.item2:
                Intent intent2 = new Intent(ListaRecordatorios.this, Preferencias.class);
                startActivity(intent2);
                break;
            case R.id.item3:
                RecordatorioPreferencesDataSource recordatorioPreferencesDataSource=new RecordatorioPreferencesDataSource(this);
                recordatorioPreferencesDataSource.eliminarRecordatorio();
                Intent intent3 = new Intent(ListaRecordatorios.this, ListaRecordatorios.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
