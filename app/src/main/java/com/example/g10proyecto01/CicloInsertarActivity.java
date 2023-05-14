package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CicloInsertarActivity extends AppCompatActivity {
    ControlG10Proyecto01 helper;
    EditText editIdCiclo;
    Spinner spinnerCiclo;
    EditText editAño;
    List<Integer> valores = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar);

        helper = new ControlG10Proyecto01(this);
        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
        spinnerCiclo = findViewById(R.id.spinnerCiclo);
        editAño = (EditText) findViewById(R.id.editAño);

        //llenamos la lista
        valores.add(1);
        valores.add(2);

        //establece valores al spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiclo.setAdapter(adapter);
    }

    public void insertarCiclo(View v) {
        String regex = "^(2000|2[0-9]{3}|3000)$";

        if (editIdCiclo.getText().toString().isEmpty() ||
                editAño.getText().toString().isEmpty()) {

            Toast.makeText(this, getResources().getString(R.string.vacio), Toast.LENGTH_SHORT).show();
        } else if (editAño.getText().toString().matches(regex)){
            String id_ciclo = editIdCiclo.getText().toString();
            String ciclo = String.valueOf(valores.get(spinnerCiclo.getSelectedItemPosition()));
            String año = editAño.getText().toString();

            String regInsertados;

            Ciclo cicloOb = new Ciclo();
            cicloOb.setId_ciclo(Integer.valueOf(id_ciclo));
            cicloOb.setCiclo(ciclo);
            cicloOb.setAño(año);

            helper.abrir();

            regInsertados = helper.insertar(cicloOb);

            helper.cerrar();

            if (regInsertados.contains("Err")) {
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }  else {
            Toast.makeText(this, getResources().getString(R.string.anioValido), Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editIdCiclo.setText("");
        editAño.setText("");
    }
}