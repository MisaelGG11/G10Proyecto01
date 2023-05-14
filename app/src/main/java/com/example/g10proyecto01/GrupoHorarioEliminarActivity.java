package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoHorarioEliminarActivity extends Activity {

    EditText editidgh3;
    ControlG10Proyecto01 controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_horario_eliminar);
        controlhelper = new ControlG10Proyecto01(this);
        editidgh3 = (EditText) findViewById(R.id.editidgh3);
    }

    public void eliminarGrupoHorario(View v) {
        String regEliminadas;
        GrupoHorario grupohorario = new GrupoHorario();
        grupohorario.setId_gh(Integer.parseInt(editidgh3.getText().toString()));
        controlhelper.abrir();
        regEliminadas = controlhelper.eliminar(grupohorario);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
