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

public class UsuarioActualizarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editNombre;
    EditText editContraseña;
    Spinner spinnerIdUsuario;
    List<String> opcionesSpinner = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actualizar);
        helper = new ControlG10Proyecto01(this);
        editContraseña = findViewById(R.id.editActualizarContraseñaUsuario);
        editNombre = findViewById(R.id.editActualizarNombreUsuario);
        spinnerIdUsuario = findViewById(R.id.spinActualizarIdUsuario);
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

    public void ActualizarUsuario(View v){
        String idOpcionSeleccionada = spinnerIdUsuario.getSelectedItem().toString();
        String nombre = editNombre.getText().toString();
        String password = editContraseña.getText().toString();
        Usuario usuario = new Usuario(idOpcionSeleccionada);
        if(nombre.isEmpty() && password.isEmpty()){
            Toast.makeText(this,R.string.alertNoData,Toast.LENGTH_LONG).show();
            return;
        }
        if(!nombre.isEmpty()){
            usuario.setNom_usuario(nombre);
        }
        if(!password.isEmpty()){
            usuario.setClave(password);
        }
        helper.abrir();
        String estado = helper.actualizar(usuario);
        helper.cerrar();
        Toast.makeText(this,estado,Toast.LENGTH_LONG).show();
    }

    public void limpiarTexto(){
        editContraseña.setText("");
        editNombre.setText("");
    }
}