package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class GrupoInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgrupo;

    List<Integer> listIdoferta = new ArrayList<>();
    /*EditText editidoferta;*/
    EditText editnumgrupo;
    EditText editcupo;
    EditText edittipogrupo;

    Spinner spinnerOferta;

    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_insertar);
        helper = new ControlG10Proyecto01(this);
        editidgrupo = (EditText) findViewById(R.id.editidgh4);
        spinnerOferta = findViewById(R.id.SpinnerOferta);
        /*editidoferta = (EditText) findViewById(R.id.editidhorario3);*/
        editnumgrupo = (EditText) findViewById(R.id.edithorafin4);
        editcupo = (EditText) findViewById(R.id.edithorafin3);
        edittipogrupo = (EditText) findViewById(R.id.edittipogrupo);

        SpinnerOf();
    }

    public void insertarGrupo(View v) {
        String idgrupo = editidgrupo.getText().toString();
        int idoferta = listIdoferta.get(spinnerOferta.getSelectedItemPosition());
        /*String idoferta = editidoferta.getText().toString();*/
        String numgrupo = editnumgrupo.getText().toString();
        String cupo = editcupo.getText().toString();
        String tipogrupo = edittipogrupo.getText().toString();
        String regInsertados;

        if (idgrupo.isEmpty()||numgrupo.isEmpty()||cupo.isEmpty()||tipogrupo.isEmpty()) {
            Toast.makeText(com.example.g10proyecto01.GrupoInsertarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            Grupo grupo = new Grupo();
            grupo.setId_grupo(Integer.valueOf(idgrupo));
            grupo.setId_oferta_a(Integer.valueOf(idoferta));
            grupo.setNum_grupo(Integer.valueOf(numgrupo));
            grupo.setCupo(Integer.valueOf(cupo));
            grupo.setTipo_grupo(tipogrupo);
            helper.abrir();
            regInsertados = helper.insertar(grupo);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editidgrupo.setText("");
        editnumgrupo.setText("");
        editcupo.setText("");
        edittipogrupo.setText("");
    }

    public void SpinnerOf(){
        String sql = "SELECT id_oferta_a FROM Oferta_Academica";
        Cursor cursorL = helper.llenarSpinner(sql);
        while (cursorL.moveToNext()) {
            @SuppressLint("Range")
            int idOf = cursorL.getInt(cursorL.getColumnIndex("id_oferta_a"));
            listIdoferta.add(idOf);
        }
        ArrayAdapter<Integer> adapterL = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listIdoferta);
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterL.notifyDataSetChanged();
        spinnerOferta.setAdapter(adapterL);
    }
}
