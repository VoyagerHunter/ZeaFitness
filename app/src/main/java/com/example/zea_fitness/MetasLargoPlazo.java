package com.example.zea_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MetasLargoPlazo extends AppCompatActivity {
    Spinner spinner;
    EditText etFecha;
    Button btnSelectDate, btnSaveGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas_largo_plazo);
        spinner = findViewById(R.id.spinner);
        etFecha = findViewById(R.id.etFecha);
        btnSelectDate = findViewById(R.id.btnSelectDate);
        btnSaveGoal = findViewById(R.id.btnSaveGoal);

        String [] opcionesIntensidad ={"Seleccionar","Bajar de peso","Subir de peso", "Aumentar Resistencia","Aumentar fuerza"};
        ArrayAdapter<String> intensidadAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesIntensidad);
        spinner.setAdapter(intensidadAdapter);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });
        btnSaveGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "¡Semana de entreno!", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void irNotificaciones(View v){
        Intent i = new Intent(MetasLargoPlazo.this, Notificaciones.class);
        startActivity(i);
    }
    public void openDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                etFecha.setText(String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(day));

            }
        },2023,10,13);
        dialog.show();
    }


}
