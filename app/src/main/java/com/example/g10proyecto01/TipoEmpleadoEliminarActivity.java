package com.example.g10proyecto01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class TipoEmpleadoEliminarActivity extends Activity {
    EditText editTipoEmpleado;
    //ControlBDCarnet controlhelper;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_empleado_eliminar);
        //controlhelper=new ControlBDCarnet (this);
        editTipoEmpleado=(EditText)findViewById(R.id.editIdEmpleado);
    }

    public void eliminarTipoEmpleado(View v){
        AlertDialog.Builder alerta = new AlertDialog.Builder(TipoEmpleadoEliminarActivity.this);
        alerta.setCancelable(false);
        alerta.setMessage("¿Desea eliminar este registro?");
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI RESPONDE QUE SI A LA ALERTA
                String regEliminadas;
                TipoEmpleado tipoEmpleado=new TipoEmpleado();
                tipoEmpleado.setId_tipo_empleado(editTipoEmpleado.getText().toString());
                //controlhelper.abrir();
                //regEliminadas=controlhelper.eliminar(alumno);
                regEliminadas = "Se elimino registros";
                //controlhelper.cerrar();
                Toast.makeText(TipoEmpleadoEliminarActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ACCIONES SI PONE NO
                dialog.cancel();
            }
        });
        //MUESTRA ALERTA PARA EVENTO ONCLICK DEL BOTON
        alerta.show();

    }
}
