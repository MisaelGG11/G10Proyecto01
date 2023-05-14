package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgrupo2;
    EditText editofertaa;
    EditText editnumgrup;
    EditText edittipogrupo2;
    EditText editcupo2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_consultar);
        helper = new ControlG10Proyecto01(this);
        editidgrupo2 = (EditText) findViewById(R.id.editidgh2);
        editofertaa = (EditText) findViewById(R.id.editidhorario6);
        editnumgrup = (EditText) findViewById(R.id.editidgrupo6);
        edittipogrupo2 = (EditText) findViewById(R.id.edithorafin2);
        editcupo2 = (EditText) findViewById(R.id.editcupo2);
    }

    public void consultarGrupo(View v) {
        helper.abrir();
        Grupo grupo =
                helper.consultarGrupo(editidgrupo2.getText().toString());
        helper.cerrar();
        if (grupo == null)
            Toast.makeText(this, "Grupo con ID " + editidgrupo2.getText().toString() + " no encontrado.", Toast.LENGTH_LONG).show();
        else {
            editofertaa.setText(String.valueOf(grupo.getId_oferta_a()));
            editnumgrup.setText(String.valueOf(grupo.getNum_grupo()));
            edittipogrupo2.setText(grupo.getTipo_grupo());
            editcupo2.setText(String.valueOf(grupo.getCupo()));
        }
    }

    public void limpiarTexto(View v) {
        editidgrupo2.setText("");
        editofertaa.setText("");
        editnumgrup.setText("");
        edittipogrupo2.setText("");
        editcupo2.setText("");
    }

}
