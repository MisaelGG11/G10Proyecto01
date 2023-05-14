package com.example.g10proyecto01;

public class OfertaAcademica {
    private int id_oferta_a;
    private int id_ciclo;
    private int id_materia;
    private int id_docente;

    public OfertaAcademica() {
    }

    public OfertaAcademica(int id_oferta_a, int id_ciclo, int id_materia, int id_docente) {
        this.id_oferta_a = id_oferta_a;
        this.id_ciclo = id_ciclo;
        this.id_materia = id_materia;
        this.id_docente = id_docente;
    }

    public int getId_oferta_a() {
        return id_oferta_a;
    }

    public void setId_oferta_a(int id_oferta_a) {
        this.id_oferta_a = id_oferta_a;
    }

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }
}
