package com.pandragor.tec_iti3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class WELCOMe extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mdraw;
    NavigationView mnavegar;
    Toolbar mtool;

   // AuthProvider mAuthProvider;
   FirebaseAuth mAuthpro;
    DatabaseReference mData;

    ActionBarDrawerToggle mdrawar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_e_l_c_o_me);

        mdraw=findViewById(R.id.drawerlayout);
        mnavegar=findViewById(R.id.navegador);
        mtool=findViewById(R.id.Tolbarwelcome);
       // mAuthProvider=new AuthProvider();

        mAuthpro=FirebaseAuth.getInstance();
        mData= FirebaseDatabase.getInstance().getReference();


        getSupportFragmentManager().beginTransaction().add(R.id.content,new NewsFragment()).commit();
        setTitle("HOME");

        setSupportActionBar(mtool);
        mdrawar= new ActionBarDrawerToggle(this,mdraw,mtool,R.string.opendrawer,R.string.closedrawer);

        mdraw.addDrawerListener(mdrawar);


        mnavegar.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_static,menu);
        return super.onCreateOptionsMenu(menu);


    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        mdrawar.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mdrawar.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        seleciona_item(item);
        return true;
    }




    private void seleciona_item(MenuItem item) {

        FragmentManager mf= getSupportFragmentManager();
        FragmentTransaction ft=mf.beginTransaction();
        switch (item.getItemId()){

            case R.id.news:
                ft.replace(R.id.content,new NewsFragment()).commit();
                break;

            case R.id.biblioteca:
                ft.replace(R.id.content,new BibliotecaFragment() ).commit();
                break;

            case R.id.temario:
                ft.replace(R.id.content,new TemarioFragment()).commit();
                break;

            case R.id.calendario:
                ft.replace(R.id.content,new CalendarioFragment() ).commit();
                break;

            case R.id.perfil:
                ft.replace(R.id.content,new MyperfilFragment() ).commit();
                break;

            case R.id.cursos:
                ft.replace(R.id.content,new CursosFragment()).commit();
                break;

            case R.id.anuncios:
                ft.replace(R.id.content,new AnunciosFragment()).commit();
                break;

            case R.id.soporte:
                ft.replace(R.id.content,new SoporteFragment()).commit();
                break;


        }
        setTitle(item.getTitle());
        mdraw.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mdrawar.onOptionsItemSelected(item)){
          return true;
        }
        else if(item.getItemId()==R.id.cerrar){

            logout();

        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
       mAuthpro.signOut();
        //mAuthProvider.logout();
     Intent intent=new Intent(WELCOMe.this,inicio_App.class);
     startActivity(intent);
     finish();
    }

}