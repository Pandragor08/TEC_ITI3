package com.pandragor.tec_iti3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class carreras extends AppCompatActivity {
    Button mcivil;
    Button mgestion;
    Button minfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreras);
        mcivil=findViewById(R.id.civill);
        mgestion=findViewById(R.id.gestiones);
        minfo=findViewById(R.id.informa);


        minfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://iztapalapa3.tecnm.mx/ofertaedu/informatica.html"));
                startActivity(intent);
            }
        });
        mgestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://iztapalapa3.tecnm.mx/ofertaedu/gestion.html"));
                startActivity(intent);
            }
        });
        mcivil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://iztapalapa3.tecnm.mx/ofertaedu/civil.html"));
                startActivity(intent);
            }
        });

    }
}