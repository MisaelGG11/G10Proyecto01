package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TipoEventoActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText edittipodeevento;
    List<Integer> TESpinner = new ArrayList<>();
    Spinner spinnerIdTE;
    final String ID_TIPO_EVENTO = "id_tipo_evento";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_actualizar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdTE = findViewById(R.id.spinIdEvento);
        edittipodeevento = findViewById(R.id.edittipodeeventoa
        );
        String query = "SELECT id_tipo_evento FROM Tipo_evento";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_TIPO_EVENTO));
            TESpinner.add(id);
        }
        ArrayAdapter<Integer> adapterTESpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,TESpinner);
        adapterTESpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdTE.setAdapter(adapterTESpinner);
    }

    public void ActualizarTEV(View v){
        String idEvento = spinnerIdTE.getSelectedItem().toString();
        String NomEvento = edittipodeevento.getText().toString();

        if (NomEvento.isEmpty()) {
            Toast.makeText(TipoEventoActualizarActivity.this, "Ingresar datos obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            TipoEvento tipoEvento = new TipoEvento(Integer.parseInt(idEvento),NomEvento);
            System.out.println(tipoEvento.toString());
            helper.abrir();
            String estado = helper.actualizar(tipoEvento);
            helper.cerrar();

            Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
        }

    }

    public void limpiarTextoTEV(View v){
        edittipodeevento.setText("");
    }
}

