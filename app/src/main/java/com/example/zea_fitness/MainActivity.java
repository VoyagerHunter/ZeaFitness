package com.example.zea_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.zea_fitness.MetasLargoPlazo;
import com.example.zea_fitness.MetasSemanales;
import com.example.zea_fitness.Notificaciones;
import com.example.zea_fitness.Perfil;
import com.example.zea_fitness.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void irNotificaciones(View v){
        Intent i = new Intent(MainActivity.this, Notificaciones.class);
        startActivity(i);
    }
    public void irPerfil(View view){
        Intent i = new Intent(MainActivity.this, Perfil.class);
        startActivity(i);
    }
    public void irMetasSemanales(View view){
        Intent i = new Intent(MainActivity.this, MetasSemanales.class);
        startActivity(i);
    }
    public void irMetasLargoPlazo(View view){
        Intent i = new Intent(MainActivity.this, MetasLargoPlazo.class);
        startActivity(i);
    }
    public void irRegistroEntrenamientos(View v){
        Intent i = new Intent(MainActivity.this, RegistroEntrenamientos.class);
        startActivity(i);
    }
    public void irEntrenamientos(View v){
        Intent i = new Intent(MainActivity.this, Entrenamientos.class);
        startActivity(i);
    }
}