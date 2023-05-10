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
    List<Integer> opcionesSpinner = new ArrayList<>();
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

        String queryId = "SELECT id_opcion_crud FROM OpcionCrud";
        Cursor cursorId = helper.llenarSpinner(queryId);
        while(cursorId.moveToNext()){
            @SuppressLint("Range")
            int id = cursorId.getInt(cursorId.getColumnIndex(ID_OPCION_CRUD));
            opcionesSpinner.add(id);
        }
        ArrayAdapter<Integer> adapterOpcionesSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,opcionesSpinner);
        adapterOpcionesSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdOpcionCrud.setAdapter(adapterOpcionesSpinner);
    }

    public void insertarUsuario(View v){
        String idUsuario = editInsertarIdUsuario.getText().toString();
        String nombreUsuario = editInsertarNombreUsuario.getText().toString();
        String contraseñaUsuario = editInsertaContraseñaUsuario.getText().toString();
        String idOpcionCrud = spinnerIdOpcionCrud.getSelectedItem().toString();
        String regInsertados;
        Usuario usuario = new Usuario(idUsuario,nombreUsuario,contraseñaUsuario);

        helper.abrir();
        regInsertados=helper.insertar(usuario,idOpcionCrud);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}