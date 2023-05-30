package com.example.g10proyecto01;

import androidx.annotation.NonNull;

public class OpcionCrud {

    private int id_opcion_crud;
    private String des_opcion;

    public OpcionCrud() {
    }

    public OpcionCrud(int id_opcion_crud, String des_opcion) {
        this.id_opcion_crud = id_opcion_crud;
        this.des_opcion = des_opcion;
    }

    public OpcionCrud(int id_opcion_crud) {
        this.id_opcion_crud = id_opcion_crud;
    }

    public int getId_opcion_crud() {
        return id_opcion_crud;
    }

    public void setId_opcion_crud(int id_opcion_crud) {
        this.id_opcion_crud = id_opcion_crud;
    }

    public String getDes_opcion() {
        return des_opcion;
    }

    public void setDes_opcion(String des_opcion) {
        this.des_opcion = des_opcion;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
