package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;

public class GrupoHorarioInsertarActivity extends Activity{
    ControlG10Proyecto01 helper;
    EditText editidgh4;
    EditText editidhorario3;
    EditText edithorafin4;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_horario_insertar);
        helper = new ControlG10Proyecto01(this);
        editidgh4 = (EditText) findViewById(R.id.editidgh4);
        editidhorario3 = (EditText) findViewById(R.id.editidhorario3);
        edithorafin4 = (EditText) findViewById(R.id.edithorafin4);
    }

    public void insertarGrupoHorario(View v) {
        String idgh = editidgh4.getText().toString();
        String idhorario = editidhorario3.getText().toString();
        String idgrupo = edithorafin4.getText().toString();
        String regInsertados;
        GrupoHorario grupohorario = new GrupoHorario();
        grupohorario.setId_gh(Integer.valueOf(idgh));
        grupohorario.setId_horario(Integer.valueOf(idhorario));
        grupohorario.setId_grupo(Integer.valueOf(idgrupo));
        helper.abrir();
        regInsertados = helper.insertar(grupohorario);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editidgh4.setText("");
        editidhorario3.setText("");
        edithorafin4.setText("");
    }
}
