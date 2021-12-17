package com.example.tp3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.*;

public class CrearRecordatorios extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button btnFecha;
    private Button btnHora;
    private Button btnGuardar;
    private TextView tvFecha;
    private TextView tvHora;
    private android.support.design.widget.TextInputEditText tfNota;
    private Toolbar toolbar;

    int diaCal;
    int mesCal;
    int añoCal;
    int horaCal;
    int minCal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_record);

        btnFecha=findViewById(R.id.btnSelFecha);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnHora=findViewById(R.id.btnSelHora);
        tvFecha=findViewById(R.id.tvFecha);
        tvHora=findViewById(R.id.tvHora);
        tfNota=findViewById(R.id.tfNota);
        RecordatorioReceiver rec = new RecordatorioReceiver();
        RecordatorioPreferencesDataSource recordatorioPreferencesDataSource= new RecordatorioPreferencesDataSource(CrearRecordatorios.this);
        PreferenciaDataSource preferenciaDataSource= new PreferenciaDataSource(this);
        List<RecordatorioModel> listaRM= new ArrayList<RecordatorioModel>();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dia= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                int mes= Calendar.getInstance().get(Calendar.MONTH);
                int año= Calendar.getInstance().get(Calendar.YEAR);
                mostrarPickerFecha(dia, mes, año);
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hora= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int min= Calendar.getInstance().get(Calendar.MINUTE);
                mostrarPickerHora(hora, min);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long fechaMs= fechaEnMillis(añoCal,mesCal,diaCal,horaCal,minCal, 0);
                final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent intent= new Intent(CrearRecordatorios.this, RecordatorioReceiver.class);
                intent.putExtra("TEXTO", tfNota.getText().toString());
                intent.setAction(rec.RECORDATORIO);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent,0);

                if(preferenciaDataSource.recuperarPreferencia()) {
                    alarm.set(AlarmManager.RTC_WAKEUP, fechaMs, pendingIntent);

                    Date date = new Date(añoCal, mesCal, diaCal, horaCal, minCal);

                    RecordatorioModel recordatorioModel = new RecordatorioModel(tfNota.getText().toString(), date);
                    listaRM.add(recordatorioModel);

                    recordatorioPreferencesDataSource.guardarRecordatorio(recordatorioModel, new RecordatorioDataSource.GuardarRecordatorioCallback() {
                        @Override
                        public void resultado(boolean exito) {
                            if (exito)
                                Toast.makeText(getApplicationContext(), "RECORDATORIO PROGRAMADO", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "RECORDATORIO NO PROGRAMADO - DESACTIVADO", Toast.LENGTH_LONG).show();
                }
                restaurarPantalla();

                Intent inte = new Intent(CrearRecordatorios.this, ListaRecordatorios.class);
                startActivity(inte);
            }

        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrearRecordatorios.this, ListaRecordatorios.class);
                startActivity(intent);
            }
        });
    }

    private long fechaEnMillis(int año, int mes, int dia, int hora, int min, int seg){
        Calendar cal=Calendar.getInstance();
        cal.set(año,mes,dia,hora,min, seg);
        long fechaAlarma=cal.getTimeInMillis();

        return fechaAlarma;
    }

    private void mostrarPickerFecha(int dia, int mes, int año) {
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, this, año, mes, dia);
        datePickerDialog.show();
    }

    private void mostrarPickerHora(int hora, int min) {
        TimePickerDialog timePickerDialog= new TimePickerDialog(this, this,hora, min, false);
        timePickerDialog.show();
    }

    private void restaurarPantalla(){
        tvFecha.setText("Fecha seleccionada");
        tvHora.setText("Horario seleccionado");
        tfNota.setText("");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
        diaCal=dia;
        mesCal=mes;
        añoCal=año;

        mes = mes+1;
        String fecha= dia+" / "+mes+" / "+año;
        tvFecha.setText(fecha);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hora, int min) {
        horaCal=hora;
        minCal=min;

        String horario= hora+" : "+min;

        if((min>10)&&(hora<10)){
            horario= "0"+hora+" : "+min;
        }
        if((min<10)&&(hora>10)){
             horario= hora+" : "+"0"+min;
        }
        if((min<10)&&(hora<10)){
             horario= "0"+hora+" : "+"0"+min;
        }
        tvHora.setText(horario);
    }

}
