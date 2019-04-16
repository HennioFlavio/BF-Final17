package com.example.bollyfilmes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    private int posicaoItem = ListView.INVALID_POSITION;
    private static final String KEY_POSICAO = "SELECIONADO";
    // ListView colocado no corpo de classe para ser acessado pelo metodo onViewStateRestored
    private ListView list;
    // o mesmo que estava em FilmesAdapter
    private boolean userFilmeDestaque = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // acrecentou esse inflate tirou do return e colocou no return somente a view
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // ListView colocado no corpo de classe para ser acessado pelo metodo onViewStateRestored
        // colou ess codigo do MainActivity
        //ListView list = (ListView) view.findViewById(R.id.list_filmes);
        list = (ListView) view.findViewById(R.id.list_filmes);

        final ArrayList<ItemFilme> arrayList = new ArrayList<>();
        arrayList.add(new ItemFilme("Homem Aranha", "Filme de heroi picado por uma aranha", "10/04/2016", 4));
        arrayList.add(new ItemFilme("Homem Cobra", "Filme de heroi picado por uma cobra", "06/01/2016", 2));
        arrayList.add(new ItemFilme("Homem Javali", "Filme de heroi levou chifrada de um javali", "30/06/2016", 3));
        arrayList.add(new ItemFilme("Homem Passaro", "Filme de heroi bicado por um passaro", "23/05/2016", 5));
        arrayList.add(new ItemFilme("Homem Cachorro", "Filme de heroi mordido por um cachorro", "14/02/2016", 3.5f));
        arrayList.add(new ItemFilme("Homem Gato", "Filme de heroi arranhado por um gato", "10/04/2016", 2.5f));

        FilmesAdapter adapter = new FilmesAdapter(getContext(), arrayList);
        // fez alteraçoes aqui para que o filme destaque nao apareca no tablet
        // usou a flag craiada:userFilmeDestaque
        adapter.setUserFilmeDestaque(userFilmeDestaque);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                ItemFilme itemFilme = arrayList.get(position);

                //Pega a referencia da nossa activity que esta usando esse fragment
                Callback callback = (Callback) getActivity();
                callback.onItemSelected((itemFilme));
                posicaoItem = position;
            }
        });

        // se o estado nao for nule e se existir  a chave
        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_POSICAO)){
            // se tem alguma posicao guardada
            posicaoItem = savedInstanceState.getInt(KEY_POSICAO);
        }

        return view;
    }




    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Aqui vai salvar a posicao no cash do fragment :
        if(posicaoItem != ListView.INVALID_POSITION ){
            outState.putInt(KEY_POSICAO, posicaoItem);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(posicaoItem != ListView.INVALID_POSITION && list != null ){
            // Restaurando e aproveitando a posicao se for != de invalido
            // vai pegar a posicao e setar a list
            // e levar a posicao que queremos
            list.smoothScrollByOffset(posicaoItem);
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_atualizar:
                Toast.makeText(getContext(), "Atualizando os filmes...", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    // Mesmo metodo usado em FilmesAdapter
    public void setUserFilmeDestaque(boolean userFilmeDestaque) {
        this.userFilmeDestaque = userFilmeDestaque;
    }

    // Criacao de callback para nao abrir putra activity no tablet
    public interface Callback{

        void onItemSelected(ItemFilme itemFilme);
    }

}
