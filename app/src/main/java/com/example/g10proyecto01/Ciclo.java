package com.example.g10proyecto01;

public class Ciclo {
    private int id_ciclo;
    private char ciclo;
    private short año;

    public Ciclo(int id_ciclo, char ciclo, short año) {
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

    public char getCiclo() {
        return ciclo;
    }

    public void setCiclo(char ciclo) {
        this.ciclo = ciclo;
    }

    public short getAño() {
        return año;
    }

    public void setAño(short año) {
        this.año = año;
    }
}
