package com.example.g10proyecto01;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;

public class GrupoHorarioActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgh;
    EditText editidhorario;
    EditText editidgrupo5;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_horario_actualizar);
        helper = new ControlG10Proyecto01(this);
        editidgh = (EditText) findViewById(R.id.editidgh);
        editidhorario = (EditText) findViewById(R.id.editidhorario);
        editidgrupo5 = (EditText) findViewById(R.id.editidgrupo5);
    }

    public void actualizarGrupoHorario(View v) {
        GrupoHorario grupohorario = new GrupoHorario();
        grupohorario.setId_gh(Integer.parseInt(editidgh.getText().toString()));
        grupohorario.setId_horario(Integer.parseInt(editidhorario.getText().toString()));
        grupohorario.setId_grupo(Integer.parseInt(editidgrupo5.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(grupohorario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editidgh.setText("");
        editidhorario.setText("");
        editidgrupo5.setText("");
    }
}
