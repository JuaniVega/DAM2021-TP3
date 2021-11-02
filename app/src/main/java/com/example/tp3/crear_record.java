package com.example.tp3;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class crear_record extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button btnFecha;
    private TextView tvFecha;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_record);

        btnFecha=findViewById(R.id.btnSelFecha);
        tvFecha=findViewById(R.id.tvFecha);
        int dia= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mes= Calendar.getInstance().get(Calendar.MONTH);
        int año= Calendar.getInstance().get(Calendar.YEAR);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPicker(dia, mes, año);
            }
        });
    }

    private void mostrarPicker(int dia, int mes, int año) {
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, this, año, mes, dia);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int año, int mes, int dia) {
        mes = mes+1;
        String fecha= dia+" / "+mes+" / "+año;
        tvFecha.setText(fecha);
    }
}
