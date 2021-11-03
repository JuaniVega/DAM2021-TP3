package com.example.tp3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class crear_record extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button btnFecha;
    private Button btnHora;
    private Button btnGuardar;
    private TextView tvFecha;
    private TextView tvHora;
    private int alarmID = 1;

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
        int dia= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mes= Calendar.getInstance().get(Calendar.MONTH);
        int año= Calendar.getInstance().get(Calendar.YEAR);
        int hora= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min= Calendar.getInstance().get(Calendar.MINUTE);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPickerFecha(dia, mes, año);
            }
        });

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPickerHora(hora, min);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(crear_record.this, getString(R.string.changed_to, horaCal + ":" + minCal), Toast.LENGTH_LONG).show();
                setAlarm(alarmID, fechaEnMillis(añoCal,mesCal,diaCal,horaCal,minCal), crear_record.this);
            }
        });
    }

    private void mostrarPickerFecha(int dia, int mes, int año) {
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, this, año, mes, dia);
        datePickerDialog.show();
    }

    private void mostrarPickerHora(int hora, int min) {
        TimePickerDialog timePickerDialog= new TimePickerDialog(this, this,hora, min, false);
        timePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
        diaCal = dia;
        mesCal = mes;
        añoCal = año;
        mes = mes+1;
        String fecha= dia+" / "+mes+" / "+año;
        tvFecha.setText(fecha);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hora, int min) {
        horaCal = hora;
        minCal = min;
        String horario= hora+" : "+min;
        tvHora.setText(horario);
    }

    private long fechaEnMillis(int año, int mes, int dia, int hora, int min){
        Calendar today =Calendar.getInstance();
        today.set(año,mes,dia,hora,min);
        long fechaFinAlarma= today.getTimeInMillis();
        //long fechaFinAlarma=6000;
        return fechaFinAlarma;
    }

    public static void setAlarm(int i, Long timestamp, Context ctx) {
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(ctx, recordatorioReceiver.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(ctx, i, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);
    }
}


