package com.pandragor.tec_iti3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class Sesion extends AppCompatActivity {


    TextInputEditText mcorreo;
    TextInputEditText mpassword;
    Button msesion;

    //implementacion de la autenticacion y base de datos
    FirebaseAuth mAuth;
    DatabaseReference mData;

    // DIALOGO DE ALERTA QUE DEBE DE ESPERAR
    AlertDialog mdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);


        mcorreo=findViewById(R.id.correo);
        mpassword=findViewById(R.id.clave);
        msesion=findViewById(R.id.BtnSESION);

        mAuth= FirebaseAuth.getInstance();
        mData= FirebaseDatabase.getInstance().getReference();


        mdialog= new SpotsDialog.Builder().setContext(Sesion.this).setMessage("ESPERE UN MOMENTO").build();

        msesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();

            }
        });

    }


    private void goToHome() {
        String email=mcorreo.getText().toString();
        String clave=mpassword.getText().toString();
        if (!email.isEmpty() && !clave.isEmpty()){

            if (clave.length() >=6){
                //aqui se va a mostrar el mensaje
                mdialog.show();
                mAuth.signInWithEmailAndPassword(email,clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent =new Intent(Sesion.this, WELCOMe.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            //Toast.makeText(Sesion.this, "CONEXION EXITOSA", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Sesion.this, "VERIFIQUE SUS DATOS ESTAN INCORRECTOS", Toast.LENGTH_SHORT).show();
                        }
                        // y aqui va a terminar mensaje
                        mdialog.dismiss();
                    }
                });
            }
            else if (clave.length() >=5){
                Toast.makeText(this, "CONTRASEÑA INCOMPLETA", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "DEBE DE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
        }

    }
}