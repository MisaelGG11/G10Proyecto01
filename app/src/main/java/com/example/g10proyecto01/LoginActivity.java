package com.example.g10proyecto01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Boolean correct = true;
    Button login;
    String categoria = "Administrador";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Class<?> clase=Class.forName("com.example.g10proyecto01.MenuActivity");
                    Intent inte = new Intent(LoginActivity.this,clase);
                    startActivity(inte);

                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
    }
}