package com.example.zea_fitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class Entrenamientos extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TableLayout tlEntrenamientos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamientos);
        tlEntrenamientos = findViewById(R.id.tlEntrenamientos);

        db.collection("{id}/usuario/entrenamientos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data =document.getData();
                                TableRow row = new TableRow(getApplicationContext());
                                TableRow rowHead = new TableRow(getApplicationContext());
                                String[] encabezadoTabla = new String[]{"FECHA", "TIPO", "INTENSIDAD", "DURACION"};
                                if(tlEntrenamientos.getChildCount() == 0){
                                for(int i=0; i<encabezadoTabla.length;i++){
                                    TextView cell = new TextView(getApplicationContext());
                                    cell.setText(encabezadoTabla[i]);
                                    cell.setBackgroundResource(R.drawable.table_styles);
                                    cell.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                    cell.setGravity(Gravity.CENTER_HORIZONTAL);
                                    cell.setPadding(8, 8, 8, 8);
                                    rowHead.addView(cell);
                                }}
                                tlEntrenamientos.addView(rowHead);
                                for(Map.Entry<String, Object> entry : data.entrySet()){
                                    //String clave = entry.getKey();
                                    Object valor = entry.getValue();
                                    TextView cell = new TextView(getApplicationContext());
                                    cell.setText(valor.toString());
                                    cell.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                    cell.setBackgroundResource(R.drawable.table_styles);
                                    cell.setGravity(Gravity.CENTER_HORIZONTAL);
                                    cell.setPadding(8, 8, 8, 8);
                                    row.addView(cell);

                                }
                                tlEntrenamientos.addView(row);


                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al conseguir entrenamientos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}