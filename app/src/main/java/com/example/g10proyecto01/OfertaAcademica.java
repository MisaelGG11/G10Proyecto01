package com.example.g10proyecto01;

public class OfertaAcademica {
    private int id_ofertaAcedemica;
    private Ciclo ciclo;
    private Materia materia;
    //private Docente docente;

    public OfertaAcademica(int id_ofertaAcedemica, Ciclo ciclo, Materia materia) {
        this.id_ofertaAcedemica = id_ofertaAcedemica;
        this.ciclo = ciclo;
        this.materia = materia;
    }

    public int getId_ofertaAcedemica() {
        return id_ofertaAcedemica;
    }

    public void setId_ofertaAcedemica(int id_ofertaAcedemica) {
        this.id_ofertaAcedemica = id_ofertaAcedemica;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
