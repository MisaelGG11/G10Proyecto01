package com.example.g10proyecto01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Boolean correct;
    EditText editUsuario;
    EditText editClave;
    String messageError;
    String contrasenia;
    public String usuario;
    public String userPermisos;
    Button login;
    ControlG10Proyecto01 helper;
    List<String> users = new ArrayList<>();
    List<String> passwords = new ArrayList<>();
    List<String> idUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correct = false;

        login = findViewById(R.id.btnLogin);
        editUsuario = findViewById(R.id.edtUsuario);
        editClave = findViewById(R.id.edtPassword);


        helper = new ControlG10Proyecto01(this);
        helper.abrir();
        helper.permisosUsuarios();
        helper.cerrar();

        String sql = "SELECT id_usuario, nom_usuario, clave FROM Usuario";
        Cursor cursor = helper.llenarSpinner(sql);
        while (cursor.moveToNext()) {
            @SuppressLint("Range")
            String idUser= cursor.getString(cursor.getColumnIndex("id_usuario"));
            @SuppressLint("Range")
            String user= cursor.getString(cursor.getColumnIndex("nom_usuario"));
            @SuppressLint("Range")
            String pass = cursor.getString(cursor.getColumnIndex("clave"));
            idUsers.add(idUser);
            users.add(user);
            passwords.add(pass);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usuario = editUsuario.getText().toString();
                usuario = "UserLogueado";
                contrasenia = editClave.getText().toString();
                /*for (int i = 0; i < users.size(); i++){
                    if (contrasenia.equals(passwords.get(i)) && usuario.equals(users.get(i))){
                        correct = true;
                        userPermisos = idUsers.get(i);
                    }
                    else  {messageError = "Usuario o contrasea incorrecto.";}
                }*/
                correct = true;
                userPermisos = "01";
                if (correct){
                    try{
                        Class<?> clase=Class.forName("com.example.g10proyecto01.MenuActivity");
                        Intent inte = new Intent(LoginActivity.this,clase);
                        inte.putExtra("usuarioLogeado", usuario);
                        inte.putExtra("usuarioPermisos", userPermisos);
                        startActivity(inte);
                        finish();

                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, messageError, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}