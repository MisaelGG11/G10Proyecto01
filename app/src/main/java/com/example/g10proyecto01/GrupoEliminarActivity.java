package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class GrupoEliminarActivity extends Activity {
    EditText editidgrupo4;
    ControlG10Proyecto01 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_eliminar);
        controlhelper = new ControlG10Proyecto01(this);
        editidgrupo4 = (EditText) findViewById(R.id.editidgh3);
    }

    public void eliminarGrupo(View v) {
        String regEliminadas;
        Grupo grupo = new Grupo();
        grupo.setId_grupo(Integer.parseInt(editidgrupo4.getText().toString()));
        controlhelper.abrir();
        regEliminadas = controlhelper.eliminar(grupo);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}
