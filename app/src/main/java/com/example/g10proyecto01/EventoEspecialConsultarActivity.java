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

public class EventoEspecialConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText edittNom,edittTipo,edittIdOrganizador,edittLocal,edittFecha,edittIdHora;
    List<Integer> idEve = new ArrayList<>();
    Spinner spinnerEvenEs;
    final String ID_EVENTO_E = "id_evento";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_especial_consultar);

        helper = new ControlG10Proyecto01(this);

        spinnerEvenEs = findViewById(R.id.spinIdEvento);
        edittNom=findViewById(R.id.ENom);
        edittTipo=findViewById(R.id.eidTipoEve);
        edittIdOrganizador=findViewById(R.id.eOrganizador);
        edittFecha=findViewById(R.id.efecha);
        edittIdHora=findViewById(R.id.eHorario);
        edittLocal=findViewById(R.id.elocal);


        String query = "SELECT id_evento FROM Evento_Especial";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_EVENTO_E));
            idEve.add(id);
        }
        ArrayAdapter<Integer> adapterEVE = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,idEve);
        adapterEVE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEvenEs.setAdapter(adapterEVE);
    }

    public void consultarEventoE(View v) {
        String id_Lo = spinnerEvenEs.getSelectedItem().toString();
        helper.abrir();
        EventoEspecial eventoEspecial = helper.consultarEventoEspecial(id_Lo);
        helper.cerrar();
        if(eventoEspecial == null) {
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_LONG).show();
        }
        edittNom.setText(eventoEspecial.getNombre_evento());
        edittTipo.setText(String.valueOf(eventoEspecial.getId_tipo_evento()));
        edittIdOrganizador.setText(String.valueOf(eventoEspecial.getId_organizador()));
        edittFecha.setText(eventoEspecial.getFecha_evento());
        edittIdHora.setText(String.valueOf(eventoEspecial.getHorario()));
        edittLocal.setText(String.valueOf(eventoEspecial.getId_localidad()));

    }
    public void limpiarTextoEVE(View v){
        edittLocal.setText("");
        edittNom.setText("");
        edittTipo.setText("");
        edittIdOrganizador.setText("");
        edittFecha.setText("");
        edittIdHora.setText("");
    }
}
