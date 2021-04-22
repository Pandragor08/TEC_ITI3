package com.pandragor.tec_iti3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class pdf_lector extends AppCompatActivity {
    TextView textView;
    PDFView pdfView;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference nref=database.getReference("url");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_lector);
        pdfView=(PDFView)findViewById(R.id.pdfView);
        textView=(TextView)findViewById(R.id.text1);
       nref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
             String value= snapshot.getValue(String.class);
             textView.setText(value);
             Toast.makeText(pdf_lector.this, "Descargando", Toast.LENGTH_SHORT).show();
             String url=textView.getText().toString();
             new RetrivePdfString().execute(url);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               Toast.makeText(pdf_lector.this, "fallo descarga", Toast.LENGTH_SHORT).show();
           }
       });

    }

     class RetrivePdfString extends AsyncTask<String,Void, InputStream> {

         @Override
         protected InputStream doInBackground(String... strings) {
             InputStream inputStream=null;
             try {
                 URL url=new URL(strings[0]);
                 HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                 if (urlConnection.getResponseCode()==200){
                     inputStream=new BufferedInputStream(urlConnection.getInputStream());
                 }
             }catch (IOException e){
                 return  null;
             }
             return inputStream;
         }

         @Override
         protected void onPostExecute(InputStream inputStream) {
             pdfView.fromStream(inputStream).load();
         }
     }
}