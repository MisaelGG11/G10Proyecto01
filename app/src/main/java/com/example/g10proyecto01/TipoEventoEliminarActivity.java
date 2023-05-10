package com.example.g10proyecto01;

import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class TipoEventoEliminarActivity extends Activity {

    ControlG10Proyecto01 helper;
    List<Integer> TESpinner = new ArrayList<>();
    Spinner spinnerIdTE;
    final String ID_TIPO_EVENTO = "id_tipo_evento";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_eliminar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdTE = findViewById(R.id.spinIdEvento);
        String query = "SELECT id_tipo_evento FROM Tipo_evento";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_TIPO_EVENTO));
            TESpinner.add(id);
        }
        ArrayAdapter<Integer> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,TESpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdTE.setAdapter(adapterOpcionesSpinner);
    }

    public void eliminarTipoEvento(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(TipoEventoEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                TipoEvento tipoEvento = new TipoEvento();
                int id_TEv;
                id_TEv =TESpinner.get(spinnerIdTE.getSelectedItemPosition());
                tipoEvento.setId_tipo_evento(id_TEv);
                helper.abrir();
                regEliminadas=helper.eliminar(tipoEvento);
                helper.cerrar();
                Toast.makeText(TipoEventoEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alerta.show();
    }
}