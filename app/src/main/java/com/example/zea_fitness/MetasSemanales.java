package com.example.zea_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MetasSemanales extends AppCompatActivity {
    Button btnSaveGoals;
    CheckBox checkBoxLunes,checkBoxMartes,checkboxMiercoles, checkBoxJueves,checkboxViernes,checkboxSabado,chackboxDomingo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas_semanales);
        CheckBox checkboxLunes = findViewById(R.id.checkboxLunes);
        CheckBox checkboxMartes = findViewById(R.id.checkboxMartes);
        CheckBox checkboxMiercoles = findViewById(R.id.checkboxMiercoles);
        CheckBox checkboxJueves = findViewById(R.id.checkboxJueves);
        CheckBox checkboxViernes = findViewById(R.id.checkboxViernes);
        CheckBox checkboxSabado = findViewById(R.id.checkboxSabado);
        CheckBox checkboxDomingo = findViewById(R.id.checkboxDomingo);

        btnSaveGoals = findViewById(R.id.btnSaveGoals);

        btnSaveGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "¡Vamos a entrenar!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void irNotificaciones(View v){
        Intent i = new Intent(MetasSemanales.this, Notificaciones.class);
        startActivity(i);
    }
}