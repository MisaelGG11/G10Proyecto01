package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;

public class HorarioInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editidgh4;
    EditText editidhorario3;
    EditText edithorafin4;
    EditText edithorafin3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_insertar);
        helper = new ControlG10Proyecto01(this);
        editidgh4 = (EditText) findViewById(R.id.editidgh4);
        editidhorario3 = (EditText) findViewById(R.id.editidhorario3);
        edithorafin4 = (EditText) findViewById(R.id.edithorafin4);
        edithorafin3 = (EditText) findViewById(R.id.edithorafin3);
    }

    public void insertarHorario(View v) {
        String idhorario = editidgh4.getText().toString();
        String idevento = editidhorario3.getText().toString();
        String horainicio = edithorafin4.getText().toString();
        String horafinalizacion = edithorafin3.getText().toString();
        String regInsertados;
        Horario horario = new Horario();
        horario.setId_horario(Integer.valueOf(idhorario));
        horario.setId_evento(Integer.valueOf(idevento));
        horario.setHora_inicio(Timestamp.valueOf(horainicio));
        horario.setHora_finalizacion(Timestamp.valueOf(horafinalizacion));
        helper.abrir();
        regInsertados = helper.insertar(horario);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editidgh4.setText("");
        editidhorario3.setText("");
        edithorafin4.setText("");
        edithorafin3.setText("");
    }
}
