package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GrupoHorarioConsultarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgh2;
    EditText editidhorario6;
    EditText editidgrupo6;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_horario_consultar);
        helper = new ControlG10Proyecto01(this);
        editidgh2 = (EditText) findViewById(R.id.editidgh2);
        editidhorario6 = (EditText) findViewById(R.id.editidhorario6);
        editidgrupo6 = (EditText) findViewById(R.id.editidgrupo6);
    }

    public void consultarGrupoHorario(View v) {
        helper.abrir();
        GrupoHorario grupohorario = helper.consultarGrupoHorario(editidgh2.getText().toString());
        helper.cerrar();
        if (grupohorario == null)
            Toast.makeText(this, "Grupo Horario con ID " + editidgh2.getText().toString() + " no encontrado.", Toast.LENGTH_LONG).show();
        else {
            editidgh2.setText(String.valueOf(grupohorario.getId_gh()));
            editidhorario6.setText(String.valueOf(grupohorario.getId_horario()));
            editidgrupo6.setText(String.valueOf(grupohorario.getId_grupo()));
        }
    }

    public void limpiarTexto(View v) {
        editidgh2.setText("");
        editidhorario6.setText("");
        editidgrupo6.setText("");
    }
}
