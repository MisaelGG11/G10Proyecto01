package com.example.g10proyecto01;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;

public class HorarioActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgh;
    EditText editidhorario;
    EditText editidgrupo5;
    EditText edithorafin;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_actualizar);
        helper = new ControlG10Proyecto01(this);
        editidgh = (EditText) findViewById(R.id.editidgh);
        editidhorario = (EditText) findViewById(R.id.editidhorario);
        editidgrupo5 = (EditText) findViewById(R.id.editidgrupo5);
        edithorafin = (EditText) findViewById(R.id.edithorafin);
    }

    public void actualizarHorario(View v) {
        Horario horario = new Horario();
        horario.setId_horario(Integer.parseInt(editidgh.getText().toString()));
        horario.setId_evento(Integer.parseInt(editidhorario.getText().toString()));
        horario.setHora_inicio(Timestamp.valueOf(editidgrupo5.getText().toString()));
        horario.setHora_finalizacion(Timestamp.valueOf(edithorafin.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(horario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editidgh.setText("");
        editidhorario.setText("");
        editidgrupo5.setText("");
        edithorafin.setText("");
    }

}
