package com.example.zea_fitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zea_fitness.Notificaciones;
import com.example.zea_fitness.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegistroEntrenamientos extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Spinner sp1TipoEntrenamiento, sp2Intensidad;
    TextView tvContador;
    EditText etFecha;
    TableLayout tlEntrenamientoGuardado;
    SeekBar skDuracion;
    TableRow trTableRow;
    Button btnGuardar, btnCalendario;
    private String minutosEntrenados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_entrenamientos);
        sp1TipoEntrenamiento = findViewById(R.id.sp1TipoEntrenamiento);
        sp2Intensidad = findViewById(R.id.intensidadEntrenamiento);
        etFecha = findViewById(R.id.etDate);
        btnCalendario = findViewById(R.id.btnCalendario);
        skDuracion = findViewById(R.id.duracionEntrenamiento);
        btnGuardar = findViewById(R.id.btnSaveGoal);
        tvContador = findViewById(R.id.contador);
        tlEntrenamientoGuardado = findViewById(R.id.entrenamientoGuardado);

        String [] opcionesIntensidad ={"Seleccionar","Baja", "Moderada","Alta"};
        String [] opcionesTipoEntrenamiento = {"Seleccionar","Cardiovascular", "Muscular", "Flexibilidad", "Alta Intensidad (HIIT)", "Resistencia", "Fuerza", "Pilates", "Intervalos", "Funcional", "Velocidad", "Agilidad", "Equilibrio", "Boxeo", "Natación", "Ciclismo", "Core", "CrossFit", "Bodyweight", "Levantamiento de Pesas"};

        ArrayAdapter <String> intensidadAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesIntensidad);
        sp2Intensidad.setAdapter(intensidadAdapter);

        ArrayAdapter <String> tipoEntrenamientoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesTipoEntrenamiento);
        sp1TipoEntrenamiento.setAdapter(tipoEntrenamientoAdapter);



        skDuracion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvContador.setText(i + " mins.");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int minutos = seekBar.getProgress();
                minutosEntrenados = String.valueOf(minutos);

            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            //Se guardan datos recopilados en una lista
            @Override
            public void onClick(View view) {
                String tipoEntrenamiento = sp1TipoEntrenamiento.getSelectedItem().toString();
                String fecha = etFecha.getText().toString();
                String duracion = minutosEntrenados;
                String intensidad = sp2Intensidad.getSelectedItem().toString();
                guardar(tipoEntrenamiento, intensidad, duracion, fecha);
                String[] encabezadoTabla = new String[]{"TIPO", "INTENSIDAD", "DURACIÓN", "FECHA"};


                String[] entrenamientoSummary = new String[]{tipoEntrenamiento, intensidad, duracion, fecha};


                TableRow row = new TableRow(view.getContext());
                TableRow rowHead = new TableRow(view.getContext());

                if(tlEntrenamientoGuardado.getChildCount()== 0){
                for(int i=0; i<encabezadoTabla.length;i++){
                    TextView cell = new TextView(view.getContext());
                    cell.setText(encabezadoTabla[i]);
                    cell.setBackgroundResource(R.drawable.table_styles);
                    cell.setGravity(Gravity.CENTER_HORIZONTAL);
                    cell.setPadding(8, 8, 8, 8);
                    rowHead.addView(cell);
                }
                    tlEntrenamientoGuardado.addView(rowHead);

                }

                for(int i=0;i<entrenamientoSummary.length;i++){
                    TextView cell = new TextView(view.getContext());
                    cell.setText(entrenamientoSummary[i]);
                    cell.setBackgroundResource(R.drawable.table_styles);
                    cell.setGravity(Gravity.CENTER_HORIZONTAL);
                    cell.setPadding(8, 8, 8, 8);
                    row.addView(cell);
                }
                tlEntrenamientoGuardado.addView(row);

            }



        });

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();

            }
        });


    }
    public void guardar(String tipo, String intensidad, String duracion, String fecha){
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("tipo", tipo);
        user.put("intensidad", intensidad);
        user.put("duracion", duracion);
        user.put("fecha", fecha);

        // Add a new document with a generated ID
        db.collection("entrenamientos")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(),"Guardado exitoso",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        Toast.makeText(getApplicationContext(), "Guardado fallido", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void irNotificaciones(View v){
        Intent i = new Intent(RegistroEntrenamientos.this, Notificaciones.class);
        startActivity(i);
    }
    //Metodo devuelve calendario
    public void openDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                etFecha.setText(String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(day));

            }
        },2023,10,13);
        dialog.show();
    }

};