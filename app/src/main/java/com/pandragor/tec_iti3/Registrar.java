package com.pandragor.tec_iti3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pandragor.tec_iti3.models.Alumno;

public class Registrar extends AppCompatActivity {
    Button mBTNregistrar;
    TextInputEditText mNombre;
    TextInputEditText mTelefono;
    TextInputEditText mCorreo;
    TextInputEditText mPassword;

    FirebaseAuth mAuthpro;
    DatabaseReference mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //instanciamos todos los campos
        mBTNregistrar=findViewById(R.id.BtnSESIONMAES);
        mNombre=findViewById(R.id.NAME);
        mTelefono=findViewById(R.id.TEL);
        mCorreo=findViewById(R.id.correoMaes);
        mPassword=findViewById(R.id.contra);


        //conecta a la base de datos aqui
        mAuthpro=FirebaseAuth.getInstance();
        mData= FirebaseDatabase.getInstance().getReference();

        mBTNregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarAlum();
            }
        });

    }

    private void RegistrarAlum() {
        String name=mNombre.getText().toString();
        String correo=mCorreo.getText().toString();
        String password=mPassword.getText().toString();
        String telefono=mTelefono.getText().toString();
        if (!name.isEmpty() && !correo.isEmpty() && !password.isEmpty() && !telefono.isEmpty()){
            if (password.length() >=6){
                mAuthpro.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            saveAlumno(name,correo,password,telefono);
                            Intent intent=new Intent(Registrar.this,Verificar_telefono.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Registrar.this, "NO SE PUDO REGISTRAR USUARIO", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(this, "LA CONTRASEÑA DEBE TENER POR LO MENOS 6 CARACTERES", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "INGRESE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveAlumno(String name,String email,String pasworrd,String tel) {
        Alumno alumno=new Alumno();
        alumno.setPersona(name);
        alumno.setPassword(pasworrd);
        alumno.setTel(tel);
        alumno.setEmail(email);
        mData.child("USUARIO").child("Alumnos").push().setValue(alumno).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Registrar.this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Registrar.this, "FALLO REGISTRO", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}