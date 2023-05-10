package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UsuarioConsultarActivity extends Activity {

    ControlG10Proyecto01 helper;
    EditText editNombre;
    EditText editContraseña;
    EditText editPermisos;
    Spinner spinnerIdUsuario;
    List<String> opcionesSpinner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_consultar);
        helper = new ControlG10Proyecto01(this);
        editContraseña = findViewById(R.id.editConsultarContraseñaUsuario);
        editNombre = findViewById(R.id.editConsultarNombreUsuario);
        editPermisos = findViewById(R.id.editConsultarPermisosUsuario);
        spinnerIdUsuario = findViewById(R.id.spinConsultarIdUsuario);
        String query = "SELECT id_usuario FROM Usuario";
        Cursor cursor = helper.llenarSpinner(query);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            String idUsuario = cursor.getString(cursor.getColumnIndex("id_usuario"));
            opcionesSpinner.add(idUsuario);
        }
        ArrayAdapter<String> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdUsuario.setAdapter(adapterOpcionesSpinner);
    }

    public void consultarUsuario(View v){

        String idOpcionSeleccionada = spinnerIdUsuario.getSelectedItem().toString();
        helper.abrir();
        Usuario usuario = helper.consultarUsuario(idOpcionSeleccionada);
        String accesoUsuario = helper.consultarAccesoUsuario(idOpcionSeleccionada);
        helper.cerrar();
        if(usuario == null){
            Toast.makeText(this, R.string.registro_no_encontrado, Toast.LENGTH_LONG).show();
        }
        editNombre.setText(usuario.getNom_usuario());
        editContraseña.setText(usuario.getClave());
        editPermisos.setText(accesoUsuario);
    }
    public void limpiarTexto(View v){
        editNombre.setText("");
        editContraseña.setText("");
        editPermisos.setText("");
    }
}