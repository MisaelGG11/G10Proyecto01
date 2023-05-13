package com.example.g10proyecto01;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgrupo3;
    EditText editofertaa3;
    EditText editnumgrupo3;
    EditText edittipogrupo3;
    EditText editcupo3;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_actualizar);
        helper = new ControlG10Proyecto01(this);
        editidgrupo3 = (EditText) findViewById(R.id.editidgrupo3);
        editofertaa3 = (EditText) findViewById(R.id.editofertaa3);
        editnumgrupo3 = (EditText) findViewById(R.id.editnumgrupo3);
        edittipogrupo3 = (EditText) findViewById(R.id.edittipogrupo3);
        editcupo3 = (EditText) findViewById(R.id.editcupo3);
    }

    public void actualizarGrupo(View v) {
        Grupo grupo = new Grupo();
        grupo.setId_grupo(Integer.parseInt(editidgrupo3.getText().toString()));
        grupo.setId_oferta_a(Integer.parseInt(editofertaa3.getText().toString()));
        grupo.setNum_grupo(Integer.parseInt(editnumgrupo3.getText().toString()));
        grupo.setTipo_grupo(edittipogrupo3.getText().toString());
        grupo.setCupo(Integer.parseInt(editcupo3.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(grupo);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editidgrupo3.setText("");
        editofertaa3.setText("");
        editnumgrupo3.setText("");
        edittipogrupo3.setText("");
        editcupo3.setText("");
    }

}
