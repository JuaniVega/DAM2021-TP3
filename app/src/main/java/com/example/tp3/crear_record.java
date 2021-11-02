package com.example.tp3;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class crear_record extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button btnFecha;
    private Button btnHora;
    private Button btnGuardar;
    private TextView tvFecha;
    private TextView tvHora;

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
               // final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
              //  alarm.set(AlarmManager.RTC_WAKEUP, tiempoEnMillis, pendingIntentConActionParaMiBroadcastReceiver);

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
        mes = mes+1;
        String fecha= dia+" / "+mes+" / "+año;
        tvFecha.setText(fecha);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hora, int min) {
        String horario= hora+" : "+min;
        tvHora.setText(horario);
    }
}
