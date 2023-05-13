package com.example.g10proyecto01;

public class Grupo {

    private int id_grupo;
    private int id_oferta_a;
    private int num_grupo;
    private int cupo;
    private String tipo_grupo;

    public Grupo() {
    }

    public Grupo(int id_grupo, int id_oferta_a, int num_grupo, int cupo, String tipo_grupo) {
        this.id_grupo = id_grupo;
        this.id_oferta_a = id_oferta_a;
        this.num_grupo = num_grupo;
        this.cupo = cupo;
        this.tipo_grupo = tipo_grupo;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_oferta_a() {
        return id_oferta_a;
    }

    public void setId_oferta_a(int id_oferta_a) {
        this.id_oferta_a = id_oferta_a;
    }

    public int getNum_grupo() {
        return num_grupo;
    }

    public void setNum_grupo(int num_grupo) {
        this.num_grupo = num_grupo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getTipo_grupo() {
        return tipo_grupo;
    }

    public void setTipo_grupo(String tipo_grupo) {
        this.tipo_grupo = tipo_grupo;
    }
}
