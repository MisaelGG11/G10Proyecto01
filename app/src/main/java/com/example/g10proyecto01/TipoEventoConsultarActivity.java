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

public class TipoEventoConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editnombreevento;
    List<Integer> ideventos = new ArrayList<>();
    Spinner spinnerEvento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_consultar);

        helper = new ControlG10Proyecto01(this);

        spinnerEvento = findViewById(R.id.spinIdEvento);
        editnombreevento = findViewById(R.id.edittipodeeventoc);

        String sql = "SELECT id_tipo_evento FROM Tipo_evento";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex("id_tipo_evento"));
            ideventos.add(id);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ideventos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvento.setAdapter(adapter);
    }
    public void consultarTipoEvento(View v) {
        String id_Eve = spinnerEvento.getSelectedItem().toString();
        helper.abrir();
        TipoEvento tipoEvento = helper.consultartipoevento(id_Eve);
        helper.cerrar();
        if(tipoEvento == null)
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        else{
            editnombreevento.setText(tipoEvento.getNombre_tipo_evento());
        }
        editnombreevento.setText(tipoEvento.getNombre_tipo_evento());
    }
    public void limpiarTextoEv(View v){
        editnombreevento.setText("");
    }
}
