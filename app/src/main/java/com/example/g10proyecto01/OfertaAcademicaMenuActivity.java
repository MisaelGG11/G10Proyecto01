package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.g10proyecto01.adaptadores.ListaMateriaAdapter;
import com.example.g10proyecto01.adaptadores.ListaOfertaAdapter;

import java.util.ArrayList;
import java.util.List;

public class OfertaAcademicaMenuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView txtBuscar;
    RecyclerView listaOfertas;
    Button btnInsertar;
    ControlG10Proyecto01 helper;

    ListaOfertaAdapter adapter;
    String userPermisos;
    List<Integer> permisos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta_academica_menu);

        txtBuscar = findViewById(R.id.editBuscarOferta);
        listaOfertas = findViewById(R.id.lvOfertas);
        btnInsertar = findViewById(R.id.btnInsertarOferta);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarRegistro();
            }
        });

        //Pregunta por los permisos
        AppGlobal global = (AppGlobal) getApplicationContext();
        userPermisos = global.getUserPermisos();

        helper = new ControlG10Proyecto01(this);

        String sql = "SELECT id_opcion_crud FROM AccesoUsuario WHERE id_usuario = '"+ userPermisos+"'";

        Cursor cursor = helper.llenarSpinner(sql);

        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int permiso = cursor.getInt(cursor.getColumnIndex("id_opcion_crud"));
            permisos.add(permiso);
        }

        if (!permisos.contains(1)){
            btnInsertar.setEnabled(false);
        }

        if (permisos.contains(4)){
            listaOfertas.setLayoutManager(new LinearLayoutManager(this));

            helper = new ControlG10Proyecto01(this);

            helper.abrir();

            adapter = new ListaOfertaAdapter(helper.mostrarOfertas());

            helper.cerrar();

            listaOfertas.setAdapter(adapter);

            txtBuscar.setOnQueryTextListener(this);
        }
    }

    private void insertarRegistro(){
        Intent intent = new Intent(this, OfertaAcademicaInsertarActivity.class);
        startActivityForResult(intent,1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            if (permisos.contains(4)){
                helper.abrir();

                adapter = new ListaOfertaAdapter(helper.mostrarOfertas());

                helper.cerrar();

                listaOfertas.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        }
    }
}