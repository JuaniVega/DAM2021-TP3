package com.example.tp3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.*;

public class crear_record extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button btnFecha;
    private Button btnHora;
    private Button btnGuardar;
    private TextView tvFecha;
    private TextView tvHora;
    private android.support.design.widget.TextInputEditText tfNota;

    int diaCal;
    int mesCal;
    int añoCal;
    int horaCal;
    int minCal;
    int cont=0;

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
                final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent= new Intent(crear_record.this, RecordatorioReceiver.class);
                intent.putExtra("TEXTO", tfNota.getText().toString());
                intent.setAction(rec.RECORDATORIO);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(), cont, intent,0);
                alarm.set(AlarmManager.RTC_WAKEUP, (fechaEnMillis(añoCal,mesCal,diaCal,horaCal,minCal, 0)),  pendingIntent);

                System.out.println(cont);

                Toast.makeText(getApplicationContext(), "RECORDATORIO PROGRAMADO", Toast.LENGTH_LONG).show();

                restaurarPantalla();

                cont++;
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
