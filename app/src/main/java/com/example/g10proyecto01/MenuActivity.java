package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    ListView menuPrincipal;
    TextView usuario;
    private ArrayAdapter<String> lvAdpter;
    String user = "Fabio Flores";
    String[] activities = {"AC17033Activity", "EL19004Activity", "FM19038Activity", "GG20031Activity", "HS19011Activity"};
    ControlG10Proyecto01 BDhelper;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String[] menu = {"AC17033", "EL19004", "FM19038", "GG20031", "HS19011", getResources().getString(R.string.llenadoBaseDatos), getResources().getString(R.string.logout)};

        BDhelper = new ControlG10Proyecto01(this);

        lvAdpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        menuPrincipal = findViewById(R.id.lvMenuPrincipal);
        usuario = findViewById(R.id.tvUsuario);

        menuPrincipal.setAdapter(lvAdpter);
        usuario.setText(user);
        menuPrincipal.setBackgroundColor(Color.parseColor("#C0A9FF"));

        menuPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuPrincipal.getChildAt(position).setBackgroundColor(Color.parseColor("#26979f"));

                if (position < 5) {
                    String nombreValue = activities[position];
                    try {
                        Class<?> clase = Class.forName("com.example.g10proyecto01." + nombreValue);
                        Intent inte = new Intent(MenuActivity.this, clase);
                        MenuActivity.this.startActivity(inte);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (position == 5) {
                    BDhelper.abrir();
                    String tost = BDhelper.llenarBD();
                    BDhelper.cerrar();
                    //Toast.makeText(, tost, Toast.LENGTH_SHORT).show();
                } else if (position == 6) {
                    try {
                        Class<?> clase2 = Class.forName("com.example.g10proyecto01.LoginActivity");
                        Intent inte2 = new Intent(MenuActivity.this, clase2);
                        startActivity(inte2);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        Toast.makeText(this, "Bienvenido " + user, Toast.LENGTH_LONG).show();
    }
}