package com.pandragor.tec_iti3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class visitante extends AppCompatActivity {

    Button mconvocatoria;
    Button mCarrera;
    Button mUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitante);
        mconvocatoria=findViewById(R.id.convoca);
        mCarrera=findViewById(R.id.carrera);
        mUbicacion=findViewById(R.id.ubicacion);

        mUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/TecNM+Campus+Iztapalapa+III/@19.3366535,-98.9858642,15z/data=!4m5!3m4!1s0x0:0x511207a1a7a61918!8m2!3d19.3366535!4d-98.9858642"));
                startActivity(intent);
            }
        });
        mCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(visitante.this,carreras.class);
                startActivity(intent);
                
            }
        });
        mconvocatoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(visitante.this,pdf_lector.class);
                startActivity(intent);
            }
        });


    }
}