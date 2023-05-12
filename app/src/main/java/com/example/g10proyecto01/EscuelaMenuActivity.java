package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.g10proyecto01.adaptadores.ListaEscuelaAdapter;

import java.util.ArrayList;

public class EscuelaMenuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView txtBuscar;
    RecyclerView listaEscuelas;
    ArrayList<Escuela> listaArrayEscuelas;
    Button btnAgregar;
    ControlG10Proyecto01 helper;

    ListaEscuelaAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_menu);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaEscuelas = findViewById(R.id.lvEscuelas);
        btnAgregar = findViewById(R.id.btnAgregar);

        listaEscuelas.setLayoutManager(new LinearLayoutManager(this));

        helper = new ControlG10Proyecto01(this);

        listaArrayEscuelas = new ArrayList<>();

        helper.abrir();

        adapter = new ListaEscuelaAdapter(helper.mostrarEscuelas());

        helper.cerrar();

        listaEscuelas.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    private void insertarRegistro(){
        Intent intent = new Intent(this, EscuelaInsertarActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}