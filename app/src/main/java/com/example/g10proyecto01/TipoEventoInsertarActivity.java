package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TipoEventoInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editidtipoevento, edittipoevento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_insertar);
        helper = new ControlG10Proyecto01(this);

        editidtipoevento = (EditText) findViewById(R.id.editidtipodeevento);
        edittipoevento = (EditText) findViewById(R.id.edittipodeevento);
    }

    public void insertarTipoEvento(View v) {
        String id_tipo_evento = editidtipoevento.getText().toString();
        String nombre_tipo_evento = edittipoevento.getText().toString();
        String regInsertados;

        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setId_tipo_evento(Integer.valueOf(id_tipo_evento));
        tipoEvento.setNombre_tipo_evento(nombre_tipo_evento);

        helper.abrir();

        regInsertados = helper.insertar(tipoEvento);

        helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoTE(View v) {
        editidtipoevento.setText("");
        edittipoevento.setText("");
    }
}