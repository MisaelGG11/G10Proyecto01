package com.example.g10proyecto01;

import java.sql.Time;
import java.sql.Timestamp;

public class Horario {
    // CREATE TABLE Horario (id_horario INTEGER NOT NULL PRIMARY KEY, id_evento INTEGER NOT NULL, hora_inicio TIMESTAMP NOT NULL, hora_finalizacion TIMESTAMP NOT NULL);
    private int id_horario;
    private int id_evento;
    private Timestamp hora_inicio;
    private Timestamp hora_finalizacion;
    private String dia;

    public Horario() {
    }

    public Horario(int id_horario, Timestamp hora_inicio, Timestamp hora_finalizacion, String dia) {
        this.id_horario = id_horario;
        this.hora_inicio = hora_inicio;
        this.hora_finalizacion = hora_finalizacion;
        this.dia = dia;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public Timestamp getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Timestamp hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Timestamp getHora_finalizacion() {
        return hora_finalizacion;
    }

    public void setHora_finalizacion(Timestamp hora_finalizacion) {
        this.hora_finalizacion = hora_finalizacion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
