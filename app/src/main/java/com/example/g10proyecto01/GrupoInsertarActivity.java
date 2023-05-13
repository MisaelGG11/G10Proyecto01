package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgrupo;
    EditText editidoferta;
    EditText editnumgrupo;
    EditText editcupo;
    EditText edittipogrupo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_insertar);
        helper = new ControlG10Proyecto01(this);
        editidgrupo = (EditText) findViewById(R.id.editidgrupo);
        editidoferta = (EditText) findViewById(R.id.editidoferta);
        editnumgrupo = (EditText) findViewById(R.id.editnumgrupo);
        editcupo = (EditText) findViewById(R.id.editcupo);
        edittipogrupo = (EditText) findViewById(R.id.edittipogrupo);
    }

    public void insertarGrupo(View v) {
        String idgrupo = editidgrupo.getText().toString();
        String idoferta = editidoferta.getText().toString();
        String numgrupo = editnumgrupo.getText().toString();
        String cupo = editcupo.getText().toString();
        String tipogrupo = edittipogrupo.getText().toString();
        String regInsertados;
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

    public void limpiarTexto(View v) {
        editidgrupo.setText("");
        editidoferta.setText("");
        editnumgrupo.setText("");
        editcupo.setText("");
        edittipogrupo.setText("");
    }
}
