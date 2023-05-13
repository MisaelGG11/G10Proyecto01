package com.example.g10proyecto01;

public class GrupoHorario
{
    //db.execSQL("CREATE TABLE Grupo_Horario(id_gh INTEGER NOT NULL PRIMARY KEY, id_horario INTEGER NOT NULL, id_grupo INTEGER NOT NULL);");
    private int id_gh;
    private int id_horario;
    private int id_grupo;

    public GrupoHorario() {
    }

    public GrupoHorario(int id_gh, int id_horario, int id_grupo) {
        this.id_gh = id_gh;
        this.id_horario = id_horario;
        this.id_grupo = id_grupo;
    }

    public int getId_gh() {
        return id_gh;
    }

    public void setId_gh(int id_gh) {
        this.id_gh = id_gh;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }
}
