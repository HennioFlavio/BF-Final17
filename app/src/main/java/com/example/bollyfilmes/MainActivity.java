package com.example.bollyfilmes;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

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


        //se for diferente de nulo, [e phq ta no layout do tablet
        if(findViewById(R.id.fragment_filme_detalhe)!= null){
            // verificar se o nao esta vazio
            if(savedInstanceState == null) {
                //vai adicioanr um fragment
                // para nao ficar vazio na primeira verificacao
                // nulo quer dizer que nao foi criado nenhum layout ainda
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_filme_detalhe, new FilmeDetalheFragment())
                        .commit();

                //adiconando de forma dinamica o fragment_filme_detalhe
            }
               isTablet = true;

        } else{//se for igual a nulo, [e p ta no layout do celular
            isTablet = false;        }

    }

    @Override
    public void onItemSelected(ItemFilme itemFilme) {

        //Implementar o comportamento pra tablet e para celular
        if (isTablet){
            // Troca os fragments
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // declarar o objeto
            FilmeDetalheFragment detalheFragment = new FilmeDetalheFragment();

            // Bundle para passar parametros entre as activities e os fragments
            Bundle bundle = new Bundle();
            bundle. putSerializable(MainActivity.KEY_FILME, itemFilme);
            detalheFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.fragment_filme_detalhe, detalheFragment);
            fragmentTransaction.commit();

        } else{
            // abre uma outra acitivity
            Intent intent = new Intent(this, FilmeDetalheActivity.class);
            intent.putExtra(MainActivity.KEY_FILME, itemFilme);
            startActivity(intent);


        }

    }
}
