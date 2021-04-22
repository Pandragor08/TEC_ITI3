package com.pandragor.tec_iti3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class inicio_App extends AppCompatActivity {

        Button Registro;
        Button Sesiones;
        Button visit;
        Button face;
        Button insta;
       Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Registro=findViewById(R.id.Register);
        Sesiones=findViewById(R.id.Sesion);
        visit=findViewById(R.id.Visit);
        face=findViewById(R.id.facebook);
        insta=findViewById(R.id.intagram);

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(inicio_App.this,Registrar.class);
                startActivity(intent);
            }
        });


        Sesiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(inicio_App.this,Sesion.class);
                startActivity(intent);
            }
        });

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(inicio_App.this,visitante.class);
                startActivity(intent);
            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/itiztapalapa3"));
                startActivity(intent);
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/itiztapalapa3/?fbclid=IwAR3lEyYI74Q7X3ViWtWo5iPGFOU8GUz5BWtEQ8t2RiMR-IYzE76QvmjH3zc"));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() !=null){
            Intent intent=new Intent(inicio_App.this, WELCOMe.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }}
}