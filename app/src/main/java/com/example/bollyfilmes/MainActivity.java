package com.example.bollyfilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_FILME = "FILME";

    // para verificar se e´tablet ou nao. Atributo sw claas
    private boolean isTablet = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //como verificar é tablet ou nao
        // verificar se vai usar:
        // o res/layout/activity_main
        // ou o activity_main.xml


        if(findViewById(R.id.fragment_filme_detalhe)!= null){
            // verificar se o nao esta vazio


            if(savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_filme_detalhe, new FilmeDetalheFragment())
                        .commit();

            }
               isTablet = true;

        } else{
            isTablet = false;        }

    }
}
