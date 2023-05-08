
package com.example.g10proyecto01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OpcionCrudInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editIdOpcionCrud;
    EditText editDescripcionOpcionCrud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_crud_insertar);
        helper = new ControlG10Proyecto01(this);
        editIdOpcionCrud = (EditText) findViewById(R.id.editIdOpcionCrud);
        editDescripcionOpcionCrud = (EditText) findViewById(R.id.editDescripcionOpcionCrud);
    }

    public void insertarOpcionCrud(View v){
        int idOpcion = Integer.parseInt(editIdOpcionCrud.getText().toString());
        String descripcionOpcion = editDescripcionOpcionCrud.getText().toString();
        String regInsertados;
        OpcionCrud opcionCrud = new OpcionCrud(idOpcion,descripcionOpcion);

        helper.abrir();
        regInsertados=helper.insertar(opcionCrud);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editIdOpcionCrud.setText("");
        editDescripcionOpcionCrud.setText("");
    }

}