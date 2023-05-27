package com.example.g10proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ServiciosWebActivity extends ListActivity {

    String[] activities={"ServicioWeb1Activity","ServicioWeb2Activity","ServicioWeb3Activity", "ServicioWeb4Activity", "ServicioWeb5Activity",  "ServicioWeb6Activity",  "ServicioWeb7Activity",  "ServicioWeb8Activity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] menu={ getResources().getString(R.string.servicioWeb1),
                getResources().getString(R.string.servicioWeb2),
                getResources().getString(R.string.servicioWeb3),
                getResources().getString(R.string.servicioWeb4),
                getResources().getString(R.string.servicioWeb5),
                getResources().getString(R.string.servicioWeb6),
                getResources().getString(R.string.servicioWeb7),
                getResources().getString(R.string.servicioWeb8) };

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));

        ListView listView = getListView();
        listView.setBackgroundColor(Color.parseColor("#F9E79F"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];

        l.getChildAt(position).setBackgroundColor(Color.parseColor("#E5E7E9"));

        try{
            Class<?> clase=Class.forName("com.example.g10proyecto01." + nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}