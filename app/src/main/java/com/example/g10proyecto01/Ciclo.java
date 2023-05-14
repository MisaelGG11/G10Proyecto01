package com.example.g10proyecto01;

public class Ciclo {
    private int id_ciclo;
    private String ciclo = " ";
    private String año = "    ";

    public Ciclo() {
    }

    public Ciclo(int id_ciclo, String ciclo, String año) {
        this.id_ciclo = id_ciclo;
        this.ciclo = ciclo;
        this.año = año;
    }

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        if (ciclo.length() != 1) {
            throw new IllegalArgumentException("El atributo debe tener una longitud de un caractere");
        }
        this.ciclo = ciclo;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        if (año.length() != 4) {
            throw new IllegalArgumentException("El atributo debe tener una longitud de cuatro caracteres");
        }
        this.año = año;
    }

    @Override
    public String toString() {
        return "Id: " + id_ciclo + " (" + ciclo + "_" + año +")";
    }
}
