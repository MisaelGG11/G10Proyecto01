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

public class UsuarioInsertarActivity extends Activity {
    ControlG10Proyecto01 helper;
    EditText editInsertarIdUsuario;
    EditText editInsertarNombreUsuario;
    EditText editInsertaContraseñaUsuario;
    List<String> opcionesSpinner = new ArrayList<>();
    Spinner spinnerIdOpcionCrud;
    final String ID_OPCION_CRUD = "id_opcion_crud";
    final String DES_OPTION = "desc_option";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_insertar);
        helper = new ControlG10Proyecto01(this);
        spinnerIdOpcionCrud = findViewById(R.id.spinOpcionCrudUsuario);
        editInsertarIdUsuario = findViewById(R.id.editInsertarIdUsuario);
        editInsertarNombreUsuario = findViewById(R.id.editInsertarNombreUsuario);
        editInsertaContraseñaUsuario = findViewById(R.id.editInsertaContraseñaUsuario);

        String queryId = "SELECT id_opcion_crud, des_opcion FROM OpcionCrud";
        Cursor cursor = helper.llenarSpinner(queryId);
        while(cursor.moveToNext()){
            @SuppressLint("Range")
            String id = cursor.getString(0);
            String descripcion = cursor.getString(1);
            opcionesSpinner.add(id + ": " + descripcion);
        }
        ArrayAdapter<String> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdOpcionCrud.setAdapter(adapterOpcionesSpinner);
    }

    public void insertarUsuario(View v){
        String idUsuario = editInsertarIdUsuario.getText().toString();
        String nombreUsuario = editInsertarNombreUsuario.getText().toString();
        String contraseñaUsuario = editInsertaContraseñaUsuario.getText().toString();
        String idOpcionCrud = spinnerIdOpcionCrud.getSelectedItem().toString().substring(0,2);
        String regInsertados;
        Usuario usuario = new Usuario(idUsuario,nombreUsuario,contraseñaUsuario);

        helper.abrir();
        regInsertados=helper.insertar(usuario,idOpcionCrud);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editInsertarIdUsuario.setText("");
        editInsertarNombreUsuario.setText("");
        editInsertaContraseñaUsuario.setText("");
    }
}