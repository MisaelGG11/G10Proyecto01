package com.example.g10proyecto01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ControlG10Proyecto01 {

    private static final String[] camposCiclo = new String[]{"id_ciclo", "ciclo", "año"};
    private static final String[] camposDocente = new String[]{"id_docente", "id_empleado", "nip_docente", "categoria_docente"};
    private static final String[] camposEmpleado = new String[]{"id_empleado", "id_tipo_empleado", "nombre_empleado", "apellido_empleado", "email_empleado", "telefono_empleado"};
    private static final String[] camposEscuela = new String[]{"id_escuela", "acronimo", "nombre"};
    private static final String[] camposEventoEspecial = new String[]{"id_evento", "id_tipo_evento", "organizador", "nombre_evento", "fecha"};
    private static final String[] camposGrupo = new String[]{"id_grupo", "id_oferta_a", "num_grupo", "tipo_grupo", "cupo"};
    private static final String[] camposGrupoHorario = new String[]{"id_gh", "id_horario", "id_grupo"};
    private static final String[] camposHorario = new String[]{"id_horario", "id_evento", "hora_inicio", "hora_finalizacion"};
    private static final String[] camposLocalAdministrado = new String[]{"id_local_admin", "id_localidad", "id_empleado"};
    private static final String[] camposLocalidad = new String[]{"id_localidad", "id_evento", "edificio_localidad", "nombre_localidad", "capacidad_localidad"};
    private static final String[] camposMateria = new String[]{"id_materia", "id_escuela", "cod_materia", "ciclo_materia", "nombre_materia"};
    private static final String[] camposOfertaAcademica = new String[]{"id_oferta_a", "id_ciclo", "id_docente", "id_materia"};
    private static final String[] camposOpcionCrud = new String[]{"id-opcion", "des_opcion", "NumCrud"};
    private static final String[] camposPropuestaEspecifica = new String[]{"id_especifica", "id_propuesta", "id_gh", "id_localidad", "estado_especifica"};
    private static final String[] camposPropuestaGeneral = new String[]{"id_propuesta", "estado_propuesta"};
    private static final String[] camposTipoEmpleado = new String[]{"id_tipo_empleado", "ocupacion"};
    private static final String[] camposTipoEvento = new String[]{"id_tipo_evento", "nombre_tipo_evento"};
    private static final String[] camposUsuario = new String[]{"id_usuario", "nom_usuario", "clave"};
    private static final String[] camposAcessoUsuario = new String[]{"id_usuario", "id-opcion"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlG10Proyecto01(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "TBD"; // Aquí se añade el nombre de la base de datos
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE Ciclo(id_ciclo NUMBER(6) NOT NULL PRIMARY KEY, ciclo CHAR(1) NOT NULL, año CHAR(4) NOT NULL);");
                db.execSQL("CREATE TABLE Docente(id_docente INTEGER NOT NULL PRIMARY KEY, id_empleado INTEGER NOT NULL, nip_docente INTEGER NOT NULL, categoria_docente VARCHAR2(10) NOT NULL);");
                db.execSQL("CREATE TABLE Empleado_UES(id_empleado INTEGER NOT NULL PRIMARY KEY, id_tipo_empleado INTEGER NOT NULL, nombre_empleado VARCHAR2(30) NOT NULL, apellido_empleado VARCHAR2(30) NOT NULL, email_empleado VARCHAR2(50) NOT NULL, telefono_empleado INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Escuela(id_escuela INTEGER NOT NULL PRIMARY KEY, acronimo VARCHAR2(10) NOT NULL, nombre VARCHAR2(30) NOT NULL);");
                db.execSQL("CREATE TABLE Evento_Especial(id_evento INTEGER NOT NULL PRIMARY KEY, id_tipo_evento INTEGER NOT NULL, organizador VARCHAR2(50) NOT NULL, nombre_evento VARCHAR2(50) NOT NULL, fecha DATE NOT NULL);");
                db.execSQL("CREATE TABLE Grupo(id_grupo INTEGER NOT NULL PRIMARY KEY, id_oferta_a INTEGER NOT NULL, num_grupo INTEGER NOT NULL, tipo_grupo VARCHAR2(11) NOT NULL, cupo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Grupo_Horario(id_gh INTEGER NOT NULL PRIMARY KEY, id_horario INTEGER NOT NULL, id_grupo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Grupo_Horario (id_gh INTEGER NOT NULL PRIMARY KEY, id_horario INTEGER NOT NULL, id_grupo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Local_Administrado(id_local_admin INTEGER NOT NULL PRIMARY KEY, id_localidad INTEGER NOT NULL, id_empleado INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Localidad(id_localidad INTEGER NOT NULL, id_evento INTEGER NOT NULL, edificio_localidad VARCHAR2(60) NOT NULL, nombre_localidad VARCHAR2(30) NOT NULL, capacidad_localidad INTEGER NOT NULL, CONSTRAINT PK_LOCALIDAD PRIMARY KEY (id_localidad));");
                db.execSQL("CREATE TABLE Materia(id_materia INTEGER NOT NULL PRIMARY KEY, id_escuela INTEGER NOT NULL, cod_materia VARCHAR2(6) NOT NULL, ciclo_materia VARCHAR2(50) NOT NULL, nombre_materia VARCHAR2(30) NOT NULL);");
                db.execSQL("CREATE TABLE Oferta_Academica(id_oferta_a INTEGER NOT NULL PRIMARY KEY, id_ciclo NUMBER(6) NOT NULL, id_docente INTEGER NOT NULL, id_materia INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE OpcionCrud(id_opcion CHAR(3) NOT NULL PRIMARY KEY, des_opcion VARCHAR2(30) NOT NULL, NumCrud INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Propuesta_Especifica(id_especifica INTEGER NOT NULL, id_propuesta INTEGER NOT NULL, id_gh INTEGER NOT NULL, id_localidad INTEGER NOT NULL, estado_especifica VARCHAR2(1) NOT NULL, CONSTRAINT PK_PROPUESTA_ESPECIFICA PRIMARY KEY (id_especifica));");
                db.execSQL("CREATE TABLE Propuesta_General(id_propuesta INTEGER NOT NULL, estado_propuesta VARCHAR2(1) NOT NULL, CONSTRAINT PK_PROPUESTA_GENERAL PRIMARY KEY (id_propuesta));");
                db.execSQL("CREATE TABLE Tipo_de_Empleado(id_tipo_empleado INTEGER NOT NULL, ocupacion VARCHAR2(50) NOT NULL, CONSTRAINT PK_TIPO_DE_EMPLEADO PRIMARY KEY (id_tipo_empleado));");
                db.execSQL("CREATE TABLE Tipo_evento (id_tipo_evento INTEGER NOT NULL, nombre_tipo_evento VARCHAR2(50) NOT NULL, CONSTRAINT PK_TIPO_EVENTO PRIMARY KEY (id_tipo_evento));");
                db.execSQL("CREATE TABLE Usuario (id_usuario CHAR(2) NOT NULL, nom_usuario VARCHAR2(30) NOT NULL, clave CHAR(5) NOT NULL, CONSTRAINT PK_USUARIO PRIMARY KEY (id_usuario));");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    // Verificar integridad
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {

        return false;
    }

    // Datos para llenar base de datos
    public String llenarBD(){

        return null;
    }


}
