package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.g10proyecto01.adaptadores.ListaEscuelaAdapter;

import java.util.ArrayList;
import java.util.List;

public class EscuelaMenuActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView txtBuscar;
    RecyclerView listaEscuelas;
    ArrayList<Escuela> listaArrayEscuelas;
    Button btnInsertar;
    ControlG10Proyecto01 helper;

    ListaEscuelaAdapter adapter;
    String userPermisos;
    List<Integer> permisos = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_menu);

        txtBuscar = findViewById(R.id.editBuscarEscuela);
        listaEscuelas = findViewById(R.id.lvEscuelas);
        btnInsertar = findViewById(R.id.btnInsertarEscuela);

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
            listaEscuelas.setLayoutManager(new LinearLayoutManager(this));

            helper = new ControlG10Proyecto01(this);

            listaArrayEscuelas = new ArrayList<>();

            helper.abrir();

            adapter = new ListaEscuelaAdapter(helper.mostrar());

            helper.cerrar();

            listaEscuelas.setAdapter(adapter);

            txtBuscar.setOnQueryTextListener(this);
        }
    }

    private void insertarRegistro(){
        Intent intent = new Intent(this, EscuelaInsertarActivity.class);
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

                adapter = new ListaEscuelaAdapter(helper.mostrar());

                helper.cerrar();

                listaEscuelas.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        }
    }
}