package com.example.zea_fitness;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText etNombre, etApellido,etEmail;
    Button btnBrowse, btnSave;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        etNombre = findViewById(R.id.etNombre);
        btnBrowse = findViewById(R.id.btnBrowse);
        imgView = findViewById(R.id.imgView);
        btnSave = findViewById(R.id.btnSave);
        etApellido = findViewById(R.id.etApellido);
        etEmail = findViewById(R.id.etEmail);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker.launch(new Intent(MediaStore.ACTION_PICK_IMAGES));

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String correo = etEmail.getText().toString();
                guardar(nombre, apellido, correo);

            }
        });
    }
    public void guardar(String nombre, String apellido, String correo){
        Map<String ,Object> user = new HashMap<>();
        user.put("nombre",nombre);
        user.put("apellido",apellido);
        user.put("correo",correo);
        db.collection("usuario").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                Toast.makeText(getApplicationContext(),"Guardado exitoso",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(@NonNull Exception e) {
                //Log.w(TAG, "Error adding document", e);
                Toast.makeText(getApplicationContext(), "Guardado fallido", Toast.LENGTH_SHORT).show();
            }
        }); }
    public void irNotificaciones(View v){
        Intent i = new Intent(Perfil.this, Notificaciones.class);
        startActivity(i);
    }
    ActivityResultLauncher<Intent> imagePicker = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Uri selectedImgUri = result.getData().getData();
                imgView.setImageURI(selectedImgUri);
            }
        }
    });



}
