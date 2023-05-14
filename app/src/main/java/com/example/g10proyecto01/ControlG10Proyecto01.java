package com.example.g10proyecto01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ControlG10Proyecto01 {

    private static final String[] camposCiclo = new String[]{"id_ciclo", "ciclo", "año"};
    private static final String[] camposDocente = new String[]{"id_docente", "id_empleado", "nip_docente", "categoria_docente"};
    private static final String[] camposEmpleado = new String[]{"id_empleado", "id_tipo_empleado", "nombre_empleado", "apellido_empleado", "email_empleado", "telefono_empleado"};
    private static final String[] camposEscuela = new String[]{"id_escuela", "acronimo", "nombre"};
    private static final String[] camposEventoEspecial = new String[]{"id_evento", "id_tipo_evento", "nombre_evento", "organizador", "fecha", "id_horario", "id_localidad"};
    private static final String[] camposGrupo = new String[]{"id_grupo", "id_oferta_a", "num_grupo", "tipo_grupo", "cupo"};
    private static final String[] camposGrupoHorario = new String[]{"id_gh", "id_horario", "id_grupo"};
    private static final String[] camposHorario = new String[]{"id_horario", "id_evento", "hora_inicio", "hora_finalizacion"};
    private static final String[] camposLocalAdministrado = new String[]{"id_local_admin", "id_localidad", "id_empleado"};
    private static final String[] camposLocalidad = new String[]{"id_localidad", "edificio_localidad", "nombre_localidad", "capacidad_localidad"};
    private static final String[] camposMateria = new String[]{"id_materia", "cod_materia", "nombre_materia", "id_escuela"};
    private static final String[] camposOfertaAcademica = new String[]{"id_oferta_a", "id_ciclo", "id_docente", "id_materia"};
    private static final String[] camposOpcionCrud = new String[]{"id_opcion_crud", "des_opcion"};
    private static final String[] camposPropuestaEspecifica = new String[]{"id_especifica", "id_propuesta", "id_gh", "id_localidad", "estado_especifica"};
    private static final String[] camposPropuestaGeneral = new String[]{"id_propuesta", "estado_propuesta"};
    private static final String[] camposTipoEmpleado = new String[]{"id_tipo_empleado", "ocupacion"};
    private static final String[] camposTipoEvento = new String[]{"id_tipo_evento", "nombre_tipo_evento"};
    private static final String[] camposUsuario = new String[]{"id_usuario", "nom_usuario", "clave"};
    private static final String[] camposAccesoUsuario = new String[]{"id_acceso", "id_usuario", "id-opcion"};


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlG10Proyecto01(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "AsignacionHLBD.s3db"; // Aquí se añade el nombre de la base de datos
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
                db.execSQL("CREATE TABLE Evento_Especial(id_evento INTEGER NOT NULL PRIMARY KEY, id_tipo_evento INTEGER NOT NULL, nombre_evento VARCHAR2(100) NOT NULL, organizador INTEGER NOT NULL, fecha VARCHAR2(8) NOT NULL, id_horario INTEGER NOT NULL, id_localidad INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Grupo(id_grupo INTEGER NOT NULL PRIMARY KEY, id_oferta_a INTEGER NOT NULL, num_grupo INTEGER NOT NULL, tipo_grupo VARCHAR2(11) NOT NULL, cupo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Horario(id_horario INTEGER NOT NULL PRIMARY KEY, id_evento INTEGER NOT NULL, hora_inicio TIMESTAMP NOT NULL, hora_finalizacion TIMESTAMP NOT NULL);");
                db.execSQL("CREATE TABLE Grupo_Horario(id_gh INTEGER NOT NULL PRIMARY KEY, id_horario INTEGER NOT NULL, id_grupo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Local_Administrado(id_local_admin INTEGER NOT NULL PRIMARY KEY, id_localidad INTEGER NOT NULL, id_empleado INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Localidad(id_localidad INTEGER NOT NULL, edificio_localidad VARCHAR2(60) NOT NULL, nombre_localidad VARCHAR2(30) NOT NULL, capacidad_localidad INTEGER NOT NULL, CONSTRAINT PK_LOCALIDAD PRIMARY KEY (id_localidad));");
                db.execSQL("CREATE TABLE Materia(id_materia INTEGER NOT NULL PRIMARY KEY, cod_materia VARCHAR2(6) NOT NULL, nombre_materia VARCHAR2(30) NOT NULL, id_escuela INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Oferta_Academica(id_oferta_a INTEGER NOT NULL PRIMARY KEY, id_ciclo NUMBER(6) NOT NULL, id_docente INTEGER NOT NULL, id_materia INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE OpcionCrud(id_opcion_crud INTEGER NOT NULL PRIMARY KEY, des_opcion VARCHAR2(30) NOT NULL);");
                db.execSQL("CREATE TABLE Propuesta_Especifica(id_especifica INTEGER NOT NULL, id_propuesta INTEGER NOT NULL, id_gh INTEGER NOT NULL, id_localidad INTEGER NOT NULL, estado_especifica VARCHAR2(1) NOT NULL, CONSTRAINT PK_PROPUESTA_ESPECIFICA PRIMARY KEY (id_especifica));");
                db.execSQL("CREATE TABLE Propuesta_General(id_propuesta INTEGER NOT NULL, estado_propuesta VARCHAR2(1) NOT NULL, CONSTRAINT PK_PROPUESTA_GENERAL PRIMARY KEY (id_propuesta));");
                db.execSQL("CREATE TABLE Tipo_de_Empleado(id_tipo_empleado INTEGER NOT NULL, ocupacion VARCHAR2(50) NOT NULL, CONSTRAINT PK_TIPO_DE_EMPLEADO PRIMARY KEY (id_tipo_empleado));");
                db.execSQL("CREATE TABLE Tipo_evento (id_tipo_evento INTEGER NOT NULL, nombre_tipo_evento VARCHAR2(50) NOT NULL, CONSTRAINT PK_TIPO_EVENTO PRIMARY KEY (id_tipo_evento));");
                db.execSQL("CREATE TABLE Usuario (id_usuario CHAR(2) NOT NULL, nom_usuario VARCHAR2(30) NOT NULL, clave VARCHAR(10) NOT NULL, CONSTRAINT PK_USUARIO PRIMARY KEY (id_usuario));");
                db.execSQL("CREATE TABLE AccesoUsuario (id_acceso INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, id_usuario CHAR(2) NOT NULL, id_opcion_crud INTEGER NOT NULL, FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario), FOREIGN KEY(id_opcion_crud) REFERENCES OpcionCrud(id_opcion_crud))");
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

    /******************************************** Tabla Grupo ********************************************/
    public String insertar(Grupo grupo) {
        String regInsertados = "Registro insertado # ";
        long contador = 0;
        ContentValues grup = new ContentValues();
        grup.put("id_grupo", grupo.getId_grupo());
        grup.put("id_oferta_a", grupo.getId_oferta_a());
        grup.put("num_grupo", grupo.getNum_grupo());
        grup.put("cupo", grupo.getCupo());
        grup.put("tipo_grupo", grupo.getTipo_grupo());
        contador = db.insert("grupo", null, grup);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al insertar el registro: Registro duplicado, verifique la inserción.";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Grupo grupo) {
        if (verificarIntegridad(grupo, 666)) {
            String[] id = {String.valueOf(grupo.getId_grupo())};
            ContentValues cv = new ContentValues();
            cv.put("id_oferta_a", grupo.getId_oferta_a());
            cv.put("num_grupo", grupo.getNum_grupo());
            cv.put("cupo", grupo.getCupo());
            cv.put("tipo_grupo", grupo.getTipo_grupo());
            db.update("grupo", cv, "id_grupo = ?", id);
            return "Registro actualizado correctamente";
        } else {
            return "Registro con ID " + grupo.getId_grupo() + " no existe.";
        }

    }

    public String eliminar(Grupo grupo) {
        String regAfectados = "Fila afectada #";
        int contador = 0;
        if (verificarIntegridad(grupo, 666)) {
            contador += db.delete("Grupo_Horario", "id_grupo='" + grupo.getId_grupo() + "'", null);
        }
        contador += db.delete("Grupo", "id_grupo='" + grupo.getId_grupo() + "'", null);
        regAfectados += contador;
        return regAfectados;
    }

    //db.execSQL("CREATE TABLE Grupo(id_grupo INTEGER NOT NULL PRIMARY KEY, id_oferta_a INTEGER NOT NULL, num_grupo INTEGER NOT NULL, tipo_grupo VARCHAR2(11) NOT NULL, cupo INTEGER NOT NULL);");
    public Grupo consultarGrupo(String id_grupo) {
        String[] id = {id_grupo};
        Cursor cursor = db.query("grupo", camposGrupo, "id_grupo = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            Grupo grupo = new Grupo();
            grupo.setId_grupo(cursor.getInt(0));
            grupo.setId_oferta_a(cursor.getInt(1));
            grupo.setNum_grupo(cursor.getInt(2));
            grupo.setTipo_grupo(cursor.getString(3));
            grupo.setCupo(cursor.getInt(4));
            return grupo;
        } else {
            return null;
        }
    }

    /******************************************** Tabla Horario ********************************************/
    public String insertar(Horario horario) {
        return null;
    }

    public String actualizar(Horario horario) {
        return null;
    }

    public String eliminar(Horario horario) {
        return null;
    }

    //db.execSQL("CREATE TABLE Horario(id_horario INTEGER NOT NULL PRIMARY KEY, id_evento INTEGER NOT NULL, hora_inicio TIMESTAMP NOT NULL, hora_finalizacion TIMESTAMP NOT NULL);");

    public Grupo consultarHorario(int id_horario, int id_evento, String hora_inicio, String hora_finalizacion) {
        return null;
    }

    /******************************************** Tabla Grupo Horario ********************************************/
    public String insertar(GrupoHorario grupohorario) {
        return null;
    }

    public String actualizar(GrupoHorario grupohorario) {
        return null;
    }

    public String eliminar(GrupoHorario grupohorario) {
        return null;
    }

    //db.execSQL("CREATE TABLE Grupo_Horario(id_gh INTEGER NOT NULL PRIMARY KEY, id_horario INTEGER NOT NULL, id_grupo INTEGER NOT NULL);");
    public Grupo consultarGrupoHorario(int id_gh, int id_horario, int num_grupo, int id_grupo) {
        return null;
    }


    /******************************************** Tabla Usuario ********************************************/
    // CAMPOS: {"id_usuario", "nom_usuario", "clave"}
    /* Insertar Usuario*/
    public String insertar(Usuario usuario) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues user = new ContentValues();
        user.put("ID del usuario:", usuario.getId_usuario());
        user.put("Nombre del usuario:", usuario.getNom_usuario());
        user.put("Clave:", usuario.getClave());

        contador = db.insert("Usuario", null, user);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Usuario usuario, String opcionCrud) {
        String regInsertados = "Registro Insertado tabla usuario Nº= ";
        long contador = 0, contador2 = 0;

        ContentValues user = new ContentValues();
        user.put("id_usuario", usuario.getId_usuario());
        user.put("nom_usuario", usuario.getNom_usuario());
        user.put("clave", usuario.getClave());

        ContentValues accesoUsuario = new ContentValues();
        accesoUsuario.put("id_usuario", usuario.getId_usuario());
        accesoUsuario.put("id_opcion_crud", opcionCrud);
        contador = db.insert("Usuario", null, user);
        contador2 = db.insert("AccesoUsuario", null, accesoUsuario);

        if ((contador == -1 || contador == 0) && (contador2 == -1 || contador2 == 0)) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + (contador);
            regInsertados = regInsertados + "\nRegistro Insertado tabla acceso usuario Nº= " + contador2;
        }

        return regInsertados;
    }


    public Usuario consultarUsuario(String id_usuario) {
        String[] id = {id_usuario};
        Cursor cursor1 = db.query("Usuario", camposUsuario, "id_usuario = ?", id, null, null, null);

        if (cursor1.moveToFirst()) {
            Usuario usuario = new Usuario(cursor1.getString(0), cursor1.getString(1), cursor1.getString(2));
            return usuario;
        } else {
            return null;
        }
    }

    public String actualizar(Usuario usuario) {
        String[] id = {String.valueOf(usuario.getId_usuario())};
        ContentValues contentValues = new ContentValues();
        if (usuario.getNom_usuario() != null) {
            contentValues.put("nom_usuario", usuario.getNom_usuario());
        }
        if (usuario.getClave() != null) {
            contentValues.put("clave", usuario.getClave());
        }
        db.update("Usuario", contentValues, "id_usuario = ?", id);
        return "Registro Actualizado Correctamente";
    }

    /******************************************** Tabla AccesoUsuario ********************************************/
    // CAMPOS: {"id_acceso", "id_usuario","id_opcion_crud"}
    public String insertar(AccesoUsuario accesoUsuario) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues accUsuario = new ContentValues();
        accUsuario.put("id_acceso", accesoUsuario.getId_acceso());
        accUsuario.put("id_usuario", accesoUsuario.getId_usuario());
        accUsuario.put("id_opcion_crud", accesoUsuario.getId_opcion_crud());

        ;
        contador = db.insert("AccesoUsuario", null, accUsuario);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String consultarAccesoUsuario(String id_usuario) {
        String[] id = {id_usuario};
        String consulta = "SELECT opc.des_opcion FROM AccesoUsuario acc INNER JOIN OpcionCrud opc ON opc.id_opcion_crud = acc.id_opcion_crud WHERE acc.id_usuario = ?;";
        Cursor cursor1 = DBHelper.getReadableDatabase().rawQuery(consulta, id);
        String accesosUsuario = "";
        while (cursor1.moveToNext()) {
            accesosUsuario += cursor1.getString(0) + "\n";
        }
        if (accesosUsuario == "") {
            return null;
        }
        return accesosUsuario;
    }

    /******************************************** Tabla EmpleadoUES ********************************************/
    // CAMPOS: {"id_empleado", "id_tipo_empleado", "nombre_empleado", "apellido_empleado", "email_empleado", "telefono_empleado"}

    /*  Insertar EmpleadoUES  */
    public String insertar(EmpleadoUES empleadoUES) {
        String regInsertados = context.getResources().getString(R.string.regInsertado);
        long contador = 0;
        if (verificarIntegridad(empleadoUES, 1)) {
            ContentValues empleado = new ContentValues();
            empleado.put("id_empleado", empleadoUES.getId_empleado());
            empleado.put("id_tipo_empleado", empleadoUES.getId_tipo_empleado());
            empleado.put("nombre_empleado", empleadoUES.getNombre_empleado());
            empleado.put("apellido_empleado", empleadoUES.getApellido_empleado());
            empleado.put("email_empleado", empleadoUES.getEmail_empleado());
            empleado.put("telefono_empleado", empleadoUES.getTelefono_empleado());
            contador = db.insert("Empleado_UES", null, empleado);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = context.getResources().getString(R.string.error);
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    /*  Consultar EmpleadoUES  */
    public EmpleadoUES consultarEmpleadoUES(String id_empleado) {
        String[] id = {id_empleado};
        Cursor cursor = db.query("Empleado_UES", camposEmpleado, "id_empleado = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            EmpleadoUES empleado = new EmpleadoUES();
            empleado.setId_empleado(cursor.getInt(0));
            empleado.setId_tipo_empleado(cursor.getInt(1));
            empleado.setNombre_empleado(cursor.getString(2));
            empleado.setApellido_empleado(cursor.getString(3));
            empleado.setEmail_empleado(cursor.getString(4));
            empleado.setTelefono_empleado(cursor.getInt(5));
            return empleado;
        } else {
            return null;
        }

    }

    /*  Actualizar EmpleadoUES  */
    public String actualizar(EmpleadoUES empleadoUES) {
        String[] id = {String.valueOf(empleadoUES.getId_empleado())};
        ContentValues empleado = new ContentValues();
        empleado.put("id_tipo_empleado", empleadoUES.getId_tipo_empleado());
        empleado.put("nombre_empleado", empleadoUES.getNombre_empleado());
        empleado.put("apellido_empleado", empleadoUES.getApellido_empleado());
        empleado.put("email_empleado", empleadoUES.getEmail_empleado());
        empleado.put("telefono_empleado", empleadoUES.getTelefono_empleado());
        db.update("Empleado_UES", empleado, "id_empleado = ?", id);
        return context.getResources().getString(R.string.regActualizado);
    }

    /*  Eliminar EmpleadoUES  */
    public String eliminar(EmpleadoUES empleadoUES) {
        String regAfectados = context.getResources().getString(R.string.regEliminados);
        int contador = 0;
        if (verificarIntegridad(empleadoUES, 5)) {
            String where = "id_empleado=" + empleadoUES.getId_empleado();
            contador += db.delete("Empleado_UES", where, null);
            regAfectados += contador;
            return regAfectados;
        } else return context.getResources().getString(R.string.referencia) + " " +
                context.getResources().getString(R.string.IdEmpleado) + " " +
                empleadoUES.getId_empleado();
    }

    /******************************************** Tabla Docente ********************************************/
    // CAMPOS: {"id_docente", "id_empleado", "nip_docente", "categoria_docente"}

    /*  Insertar Docente  */
    public String insertar(Docente docente) {
        String regInsertados = context.getResources().getString(R.string.regInsertado);
        long contador = 0;
        if (verificarIntegridad(docente, 3)) {
            ContentValues docent = new ContentValues();
            docent.put("id_docente", docente.getId_docente());
            docent.put("id_empleado", docente.getId_empleado());
            docent.put("nip_docente", docente.getNip_docente());
            docent.put("categoria_docente", docente.getCategoria_docente());
            contador = db.insert("Docente", null, docent);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = context.getResources().getString(R.string.error);
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    /*  Consultar Docente  */
    public Docente consultarDocente(String id_Docente) {
        String[] id = {id_Docente};
        Cursor cursor = db.query("Docente", camposDocente, "id_Docente = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            Docente docente = new Docente();
            docente.setId_docente(cursor.getInt(0));
            docente.setId_empleado(cursor.getInt(1));
            docente.setNip_docente(cursor.getInt(2));
            docente.setCategoria_docente(cursor.getString(3));
            return docente;
        } else {
            return null;
        }
    }

    /*  Actualizar Docente  */
    public String actualizar(Docente docente) {
        String[] id = {String.valueOf(docente.getId_docente())};
        ContentValues docent = new ContentValues();
        docent.put("id_empleado", docente.getId_empleado());
        docent.put("nip_docente", docente.getNip_docente());
        docent.put("categoria_docente", docente.getCategoria_docente());
        db.update("Docente", docent, "id_docente= ?", id);
        return context.getResources().getString(R.string.regActualizado);
    }


    /*  Eliminar Docente  */
    public String eliminar(Docente docente) {
        String regAfectados = context.getResources().getString(R.string.regEliminados);
        int contador = 0;
        if (verificarIntegridad(docente, 4)) {
            String where = "id_Docente=" + docente.getId_docente();
            contador += db.delete("Docente", where, null);
            regAfectados += contador;
            return regAfectados;
        } else return context.getResources().getString(R.string.referencia) + " " +
                context.getResources().getString(R.string.IdDocente) + " " +
                docente.getId_docente();
    }

    /******************************************** Tabla TipoEmpleado ********************************************/
    // CAMPOS: {"id_tipo_empleado", "ocupacion"}

    /*  Insertar EmpleadoUES  */
    public String insertar(TipoEmpleado tipoEmpleado) {
        String regInsertados = context.getResources().getString(R.string.regInsertado);
        long contador = 0;
        ContentValues tEmpleado = new ContentValues();
        tEmpleado.put("id_tipo_empleado", tipoEmpleado.getId_tipo_empleado());
        tEmpleado.put("ocupacion", tipoEmpleado.getOcupacion());

        contador = db.insert("Tipo_de_Empleado", null, tEmpleado);
        if (contador == -1 || contador == 0) {
            regInsertados = context.getResources().getString(R.string.error);
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    /*  Consultar Tipo de Empleado  */
    public TipoEmpleado consultarTipoEmpleado(String id_tipo_empleado) {
        String[] id = {id_tipo_empleado};
        Cursor cursor = db.query("Tipo_de_Empleado", camposTipoEmpleado, "id_tipo_empleado = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            TipoEmpleado tipoEmpleado = new TipoEmpleado();
            tipoEmpleado.setId_tipo_empleado(cursor.getInt(0));
            tipoEmpleado.setOcupacion(cursor.getString(1));
            return tipoEmpleado;
        } else {
            return null;
        }
    }

    /*  Actualizar Tipo de Empleado  */
    public String actualizar(TipoEmpleado tipoEmpleado) {
        String[] id = {String.valueOf(tipoEmpleado.getId_tipo_empleado())};
        ContentValues cv = new ContentValues();
        cv.put("ocupacion", tipoEmpleado.getOcupacion());
        db.update("Tipo_de_Empleado", cv, "id_tipo_empleado = ?", id);
        return context.getResources().getString(R.string.regActualizado);
    }

    /*  Eliminar tipoEmpleado  */
    public String eliminar(TipoEmpleado tipoEmpleado) {
        String regAfectados = context.getResources().getString(R.string.regEliminados);
        int contador = 0;
        if (verificarIntegridad(tipoEmpleado, 2)) {
            String where = "id_tipo_empleado=" + tipoEmpleado.getId_tipo_empleado();
            contador += db.delete("Tipo_de_Empleado", where, null);
            regAfectados += contador;
            return regAfectados;
        } else return context.getResources().getString(R.string.referencia) + " " +
                context.getResources().getString(R.string.tvIdTipoEmpleado) + " " +
                tipoEmpleado.getId_tipo_empleado();
    }

    /******************************************** Tabla OpcionCrud ********************************************/
    // CAMPOS: {"id_opcion_crud", "des_opcion"}
    public String insertar(OpcionCrud opcionCrud) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues opcCrud = new ContentValues();
        opcCrud.put("id_opcion_crud", opcionCrud.getId_opcion_crud());
        opcCrud.put("des_opcion", opcionCrud.getDes_opcion());

        ;
        contador = db.insert("OpcionCrud", null, opcCrud);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public OpcionCrud consultarOpcionCrud(String id_opcion_crud) {
        String[] id = {id_opcion_crud};
        Cursor cursor1 = db.query("OpcionCrud", camposOpcionCrud, "id_opcion_crud = ?", id, null, null, null);
        System.out.println(cursor1);
        if (cursor1.moveToFirst()) {
            OpcionCrud opcionCrud = new OpcionCrud(cursor1.getInt(0), cursor1.getString(1));
            return opcionCrud;
        } else {
            return null;
        }
    }

    public String actualizar(OpcionCrud opcionCrud) {
        String[] id = {String.valueOf(opcionCrud.getId_opcion_crud())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("des_opcion", opcionCrud.getDes_opcion());
        db.update("OpcionCrud", contentValues, "id_opcion_crud = ?", id);
        return "Registro Actualizado Correctamente";
    }

    public String eliminar(OpcionCrud opcionCrud) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        String where = "id_opcion_crud=" + opcionCrud.getId_opcion_crud();
        contador += db.delete("OpcionCrud", where, null);
        regAfectados += contador;
        return regAfectados;
    }

    /*********************************** Tabla Escuela ***********************************/

    /* Insertar escuela */
    public String insertar(Escuela escuela) {
        String regInsertados = "";

        long contador = 0;

        ContentValues escu = new ContentValues();

        escu.put("id_escuela", escuela.getId_escuela());
        escu.put("acronimo", escuela.getAcronimo());
        escu.put("nombre", escuela.getNombre());

        contador = db.insert("escuela", null, escu);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    /*  Consultar escuela  */
    public Escuela consultarEscuela(String id_escuela) {
        String[] id = {id_escuela};

        Cursor cursor = db.query("escuela", camposEscuela, "id_escuela = ?", id, null, null, null);

        if (cursor.moveToFirst()) {
            Escuela escuela = new Escuela();

            escuela.setId_escuela(cursor.getInt(0));
            escuela.setAcronimo(cursor.getString(1));
            escuela.setNombre(cursor.getString(2));

            return escuela;
        } else {
            return null;
        }
    }

    /*  Actualizar escuela  */

    public String actualizar(Escuela escuela) {

        if (true) {
            String[] id = {String.valueOf(escuela.getId_escuela())};

            ContentValues cv = new ContentValues();

            cv.put("Acronimo", escuela.getAcronimo());
            cv.put("Nombre", escuela.getNombre());

            db.update("escuela", cv, "id_escuela = ?", id);

            return "Correcto";
        } else {
            return "";
        }
    }

    /*  Eliminar escuela  */

    public String eliminar(Escuela escuela) {
        String regAfectados = "";

        int contador = 0;

        /*
        if (verificarIntegridad(alumno,3)) {
            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
        }
        */

        contador += db.delete("escuela", "id_escuela='" + escuela.getId_escuela() + "'", null);

        regAfectados += contador;

        return regAfectados;
    }

    /*  muestra todas las escuelas  */

    public ArrayList<Escuela> mostrarEscuelas() {

        ArrayList<Escuela> listaEscuelas = new ArrayList<>();
        Escuela escuela;
        Cursor cursorEscuelas;

        cursorEscuelas = db.rawQuery("SELECT * FROM " + "escuela" + " ORDER BY id_escuela ASC", null);

        if (cursorEscuelas.moveToFirst()) {
            do {
                escuela = new Escuela();
                escuela.setId_escuela(cursorEscuelas.getInt(0));
                escuela.setAcronimo(cursorEscuelas.getString(1));
                escuela.setNombre(cursorEscuelas.getString(2));

                listaEscuelas.add(escuela);
            } while (cursorEscuelas.moveToNext());
        }

        return listaEscuelas;
    }


    /*********************************** Tabla Ciclo ***********************************/

    /* Insertar ciclo */
    public String insertar(Ciclo ciclo) {
        String regInsertados = "";

        long contador = 0;

        ContentValues cic = new ContentValues();

        cic.put("id_ciclo", ciclo.getId_ciclo());
        cic.put("ciclo", ciclo.getCiclo());
        cic.put("año", ciclo.getAño());

        contador = db.insert("ciclo", null, cic);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    /*  Consultar ciclo  */
    public Ciclo consultarCiclo(String id_ciclo) {
        String[] id = {id_ciclo};

        Cursor cursor = db.query("ciclo", camposCiclo, "id_ciclo = ?", id, null, null, null);

        if (cursor.moveToFirst()) {
            Ciclo ciclo = new Ciclo();

            ciclo.setId_ciclo(cursor.getInt(0));
            ciclo.setCiclo(cursor.getString(1));
            ciclo.setAño(cursor.getString(2));

            return ciclo;
        } else {
            return null;
        }
    }

    /*  Actualizar ciclo  */

    public String actualizar(Ciclo ciclo) {

        if (true) {
            String[] id = {String.valueOf(ciclo.getId_ciclo())};

            ContentValues cv = new ContentValues();

            cv.put("ciclo", ciclo.getCiclo());
            cv.put("año", ciclo.getAño());

            db.update("ciclo", cv, "id_ciclo = ?", id);

            return "Correcto";
        } else {
            return "";
        }
    }

    /*  Eliminar escuela  */

    public String eliminar(Ciclo ciclo) {
        String regAfectados = "";

        int contador = 0;

        /*
        if (verificarIntegridad(alumno,3)) {
            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
        }
        */

        contador += db.delete("ciclo", "id_ciclo='" + ciclo.getId_ciclo() + "'", null);

        regAfectados += contador;

        return regAfectados;
    }

    /*  muestra todas las escuelas  */

    public ArrayList<Ciclo> mostrarCiclos() {

        ArrayList<Ciclo> listaCiclos = new ArrayList<>();
        Ciclo ciclo;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + "ciclo" + " ORDER BY id_ciclo ASC", null);

        if (cursor.moveToFirst()) {
            do {
                ciclo = new Ciclo();
                ciclo.setId_ciclo(cursor.getInt(0));
                ciclo.setCiclo(cursor.getString(1));
                ciclo.setAño(cursor.getString(2));

                listaCiclos.add(ciclo);
            } while (cursor.moveToNext());
        }

        return listaCiclos;
    }


    /*********************************** Tabla Materia ***********************************/

    /* Insertar materia */
    public String insertar(Materia materia) {
        String regInsertados = "";

        long contador = 0;

        ContentValues mat = new ContentValues();

        mat.put("id_materia", materia.getId_materia());
        mat.put("cod_materia", materia.getCod_materia());
        mat.put("nombre_materia", materia.getNom_materia());
        mat.put("id_escuela", materia.getId_escuela());

        contador = db.insert("materia", null, mat);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    /*  Consultar materia  */
    public Materia consultarMateria(String id_materia) {
        String[] id = {id_materia};

        Cursor cursor = db.query("materia", camposMateria, "id_materia = ?", id, null, null, null);

        if (cursor.moveToFirst()) {
            Materia materia = new Materia();

            materia.setId_materia(cursor.getInt(0));
            materia.setCod_materia(cursor.getString(1));
            materia.setNom_materia(cursor.getString(2));
            materia.setId_escuela(cursor.getInt(3));

            return materia;
        } else {
            return null;
        }
    }

    /*  Actualizar ciclo  */

    public String actualizar(Materia materia) {

        if (true) {
            String[] id = {String.valueOf(materia.getId_materia())};

            ContentValues cv = new ContentValues();

            cv.put("cod_materia", materia.getCod_materia());
            cv.put("nombre_materia", materia.getNom_materia());
            cv.put("id_escuela", materia.getId_escuela());

            db.update("materia", cv, "id_materia = ?", id);

            return "Correcto";
        } else {
            return "";
        }
    }

    /*  Eliminar materia  */

    public String eliminar(Materia materia) {
        String regAfectados = "";

        int contador = 0;

        /*
        if (verificarIntegridad(alumno,3)) {
            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
        }
        */

        contador += db.delete("materia", "id_materia='" + materia.getId_materia() + "'", null);

        regAfectados += contador;

        return regAfectados;
    }

    /*  muestra todas las materias  */

    public ArrayList<Materia> mostrarMaterias() {

        ArrayList<Materia> listaMaterias = new ArrayList<>();
        Materia materia;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + "materia" + " ORDER BY id_materia ASC", null);

        if (cursor.moveToFirst()) {
            do {
                materia = new Materia();
                materia.setId_materia(cursor.getInt(0));
                materia.setCod_materia(cursor.getString(1));
                materia.setNom_materia(cursor.getString(2));
                materia.setId_escuela(cursor.getInt(3));

                listaMaterias.add(materia);
            } while (cursor.moveToNext());
        }

        return listaMaterias;
    }


    /*********************************** Tabla Local ***********************************/
    // CAMPOS: {"id_localidad", "edificio_localidad", "nombre_localidad", "capacidad_localidad"}

    /*  Insertar Localidad  */
    public String insertar(Localidad localidad) {
        String regInsertados = context.getResources().getString(R.string.regInsertado);

        long contador = 0;

        ContentValues loc = new ContentValues();

        loc.put("id_localidad", localidad.getId_localidad());
        loc.put("edificio_localidad", localidad.getEdificio_localidad());
        loc.put("nombre_localidad", localidad.getNombre_localidad());
        loc.put("capacidad_localidad", localidad.getCapacidad_localidad());

        contador = db.insert("localidad", null, loc);

        if (contador == -1 || contador == 0) {
            regInsertados = context.getResources().getString(R.string.error);
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    /*  Consultar Localidad  */
    public Localidad consultarlocalidad(String id_localidad) {
        String[] id = {id_localidad};
        Cursor cursorLoc = db.query("localidad", camposLocalidad, "id_localidad = ?", id, null, null, null);

        if (cursorLoc.moveToFirst()) {
            Localidad localidad = new Localidad(cursorLoc.getInt(0), cursorLoc.getString(1), cursorLoc.getString(2), cursorLoc.getInt(3));
            return localidad;
        } else {
            return null;
        }
    }

    /*  Actualizar Localidad  */
    public String actualizar(Localidad localidad) {
        String[] id = {String.valueOf(localidad.getId_localidad())};
        ContentValues loc = new ContentValues();
        loc.put("id_localidad", localidad.getId_localidad());
        loc.put("edificio_localidad", localidad.getEdificio_localidad());
        loc.put("nombre_localidad", localidad.getNombre_localidad());
        loc.put("capacidad_localidad", localidad.getCapacidad_localidad());

        db.update("localidad", loc, "id_localidad = ?", id);
        return context.getResources().getString(R.string.regActualizado);
    }

    /*  Eliminar Localidad  */
    public String eliminar(Localidad localidad) {
        String regAfectados = context.getResources().getString(R.string.regEliminados);

        int contador = 0;
        if (verificarIntegridad(localidad, 8)) {
            String where = "id_localidad=" + localidad.getId_localidad();
            contador += db.delete("Localidad", where, null);
            regAfectados += contador;
            return regAfectados;
        } else return context.getResources().getString(R.string.referencia) + " " +
                context.getResources().getString(R.string.txtViewIdLocal) + " " +
                localidad.getId_localidad();
    }

    /*********************************** Tabla Local Administrado ***********************************/
    // CAMPOS: {"id_local_admin", "id_localidad", "id_empleado"}

    /*  Insertar Local Administrado */
    public String insertar(LocalAdministrado localAdministrado) {
        String regInsertados = context.getResources().getString(R.string.regInsertado);

        long contador = 0;
        if (verificarIntegridad(localAdministrado, 6) || verificarIntegridad(localAdministrado, 7)) {
            ContentValues localAdmin = new ContentValues();
            localAdmin.put("id_local_admin", localAdministrado.getId_local_admin());
            localAdmin.put("id_localidad", localAdministrado.getId_local());
            localAdmin.put("id_empleado", localAdministrado.getId_empleadoadministrador());
            contador = db.insert("Local_Administrado", null, localAdmin);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = context.getResources().getString(R.string.error);
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    /*  Consultar Local Administrado*/
    public LocalAdministrado consultarlocalAdmin(String id_local_admin) {
        String[] id = {id_local_admin};
        Cursor cursorLoc = db.query("Local_Administrado", camposLocalAdministrado, "id_local_admin = ?", id, null, null, null);
        if (cursorLoc.moveToFirst()) {
            LocalAdministrado localAdministrado = new LocalAdministrado(cursorLoc.getInt(0), cursorLoc.getInt(1), cursorLoc.getInt(2));
            return localAdministrado;
        } else {
            return null;
        }
    }

    /*  Actualizar Local Administrado */
    public String actualizar(LocalAdministrado localAdministrado) {
        String[] id = {String.valueOf(localAdministrado.getId_local())};
        ContentValues locAd = new ContentValues();
        locAd.put("id_localidad", localAdministrado.getId_local());
        locAd.put("id_empleado", localAdministrado.getId_empleadoadministrador());

        db.update("Local_Administrado", locAd, "id_local_admin = ?", id);
        return context.getResources().getString(R.string.regActualizado);
    }

    /*  Eliminar Local Administrado*/
    public String eliminar(LocalAdministrado localAdministrado) {
        String regAfectados = context.getResources().getString(R.string.regEliminados);
        int contador = 0;
        String where = "id_local_admin=" + localAdministrado.getId_local_admin();
        contador += db.delete("Local_Administrado", where, null);
        regAfectados += contador;
        return regAfectados;
    }

    /*********************************** Tabla Tipo de Evento ***********************************/
    // CAMPOS: {"id_tipo_evento", "nombre_tipo_evento"}

    /*  Insertar Tipo de Evento  */
    public String insertar(TipoEvento tipoEvento) {
        String regInsertados = context.getResources().getString(R.string.regInsertado);

        long contador = 0;

        ContentValues teven = new ContentValues();

        teven.put("id_tipo_evento", tipoEvento.getId_tipo_evento());
        teven.put("nombre_tipo_evento", tipoEvento.getNombre_tipo_evento());

        contador = db.insert("Tipo_evento", null, teven);

        if (contador == -1 || contador == 0) {
            regInsertados = context.getResources().getString(R.string.error);
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    /*  Consultar Tipo de Evento  */
    public TipoEvento consultartipoevento(String id_tipo_evento) {
        String[] id = {id_tipo_evento};
        Cursor cursortv = db.query("Tipo_evento", camposTipoEvento, "id_tipo_evento = ?", id, null, null, null);
        if (cursortv.moveToFirst()) {
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setId_tipo_evento(cursortv.getInt(0));
            tipoEvento.setNombre_tipo_evento(cursortv.getString(1));
            return tipoEvento;
        } else {
            return null;
        }
    }

    /*  Actualizar Tipo de Evento  */
    public String actualizar(TipoEvento tipoEvento) {
        String[] id = {String.valueOf(tipoEvento.getId_tipo_evento())};
        ContentValues cv = new ContentValues();
        cv.put("nombre_tipo_evento", tipoEvento.getNombre_tipo_evento());

        db.update("Tipo_evento", cv, "id_tipo_evento = ?", id);
        return context.getResources().getString(R.string.regActualizado);
    }

    /*  Eliminar Tipo de Evento  */

    public String eliminar(TipoEvento tipoEvento) {
        String regAfectados = context.getResources().getString(R.string.regEliminados);
        int contador = 0;
        if (verificarIntegridad(tipoEvento, 10)) {
            String where = "id_tipo_evento=" + tipoEvento.getId_tipo_evento();
            contador += db.delete("Tipo_evento", where, null);
            regAfectados += contador;
            return regAfectados;
        } else return context.getResources().getString(R.string.referencia) + " " +
                context.getResources().getString(R.string.txtViewIdLocal) + " " +
                tipoEvento.getId_tipo_evento();
    }

    /*********************************** Tabla Evento Especial ***********************************/
    // CAMPOS: {"id_evento", "id_tipo_evento", "nombre_evento", "organizador", "fecha", "id_horario", "id_localidad"}

    /*  Insertar Evento Especial  */
    public String insertar(EventoEspecial eventoEspecial) {
        String regInsertados = context.getResources().getString(R.string.regInsertado);

        long contador = 0;
        if (verificarIntegridad(eventoEspecial, 9)) {
            ContentValues eventoE = new ContentValues();
            eventoE.put("id_evento", eventoEspecial.getId_evento());
            eventoE.put("id_tipo_evento", eventoEspecial.getId_tipo_evento());
            eventoE.put("nombre_evento", eventoEspecial.getNombre_evento());
            eventoE.put("organizador", eventoEspecial.getId_organizador());
            eventoE.put("fecha", eventoEspecial.getFecha_evento());
            eventoE.put("id_horario", eventoEspecial.getHorario());
            eventoE.put("id_localidad", eventoEspecial.getId_localidad());
            contador = db.insert("Evento_Especial", null, eventoE);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = context.getResources().getString(R.string.error);
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    /*  Consultar Evento Especial  */
    /*  Actualizar Evento Especial  */
    /*  Eliminar Evento Especial  */
    /*public String eliminar(EventoEspecial eventoEspecial){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="id_local_admin=" + eventoEspecial.getId_evento();
        contador += db.delete("Evento_Especial", where, null);
        regAfectados+=contador;
        return regAfectados;
    }*/

    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1: {
                //  VERIFICA QUE EXISTA TIPO DE EMPLEADO AL INSERTAR UN EMPLEADO UES
                EmpleadoUES empleado = (EmpleadoUES) dato;
                String[] id = {String.valueOf(empleado.getId_tipo_empleado())};

                Cursor cursor = db.query("Tipo_de_Empleado", null, "id_tipo_empleado = ?", id, null, null, null);

                if (cursor.moveToFirst()) {
                    //SE ENCUENTRAN DATOS
                    return true;
                }
                return false;
            }
            case 2: {
                //VERIFICA QUE NO SE ELIMINE UN TIPO EMPLEADO MIENTRA EXISTA UNA REFERENCIA DE EL EN LA TABLA EMPLEADO UES
                TipoEmpleado tipoEmpleado = (TipoEmpleado) dato;
                Cursor cursor = db.query(true, "Empleado_UES", new String[]{"id_tipo_empleado"}, "id_tipo_empleado ='" + tipoEmpleado.getId_tipo_empleado() + "'", null, null, null, null, null);
                if (cursor.moveToFirst())
                    return false;
                else
                    return true;
            }
            case 3: {
                //VERIFICA QUE EXISTA EMPLEADO UES ANTES DE INSERTAR UN DOCENTE
                Docente docente = (Docente) dato;
                String[] id = {String.valueOf(docente.getId_empleado())};

                Cursor cursor = db.query("Empleado_UES", null, "id_empleado = ?", id, null, null, null);

                if (cursor.moveToFirst()) {
                    //SE ENCUENTRAN DATOS
                    return true;
                }
                return false;
            }
            case 4: {
                //VERIFICA QUE NO SE ELIMINE UN DOCENTE MIENTRA EXISTA UNA REFERENCIA DE EL EN LA TABLA OFERTA ACADEMICA
                Docente docente = (Docente) dato;
                Cursor cursor = db.query(true, "Oferta_Academica", new String[]{"id_docente"}, "id_docente ='" + docente.getId_docente() + "'", null, null, null, null, null);
                if (cursor.moveToFirst())
                    return false;
                else
                    return true;
            }
            case 5: {
                //VERIFICA QUE NO SE ELIMINE UN EMPLEADO UES MIENTRA EXISTA UNA REFERENCIA DE EL EN LAS TABLAS DOCENTE Y LOCAL ADMINISTRADO
                EmpleadoUES empleado = (EmpleadoUES) dato;
                Cursor cursor1 = db.query(true, "Docente", new String[]{"id_empleado"}, "id_empleado ='" + empleado.getId_empleado() + "'", null, null, null, null, null);
                Cursor cursor2 = db.query(true, "Local_Administrado", new String[]{"id_empleado"}, "id_empleado ='" + empleado.getId_empleado() + "'", null, null, null, null, null);
                if (cursor1.moveToFirst() || cursor2.moveToFirst())
                    return false;
                else
                    return true;
            }
            case 6: {
                //  VERIFICA QUE EXISTA UNA LOCALIDAD AL INSERTAR UN LOCAL ADMINISTRADO

                LocalAdministrado localidadadmin = (LocalAdministrado) dato;
                String[] idLocal = {String.valueOf(localidadadmin.getId_local())};

                Cursor cursorL = db.query("localidad", null, "id_localidad = ?", idLocal, null, null, null);

                if (cursorL.moveToFirst()) {
                    //SE ENCUENTRAN DATOS
                    return true;
                }
                return false;
            }

            case 7: {
                //  VERIFICA QUE EXISTA UN EMPLEADO UES AL INSERTAR UN LOCAL ADMINISTRADO
                LocalAdministrado locadmin = (LocalAdministrado) dato;
                String[] idEmpleado = {String.valueOf(locadmin.getId_empleadoadministrador())};

                Cursor cursorE = db.query("Empleado_UES", null, "id_empleado = ?", idEmpleado, null, null, null);

                if (cursorE.moveToFirst()) {
                    //SE ENCUENTRAN DATOS
                    return true;
                }
                return false;
            }

            case 8: {
                //VERIFICA QUE NO SE ELIMINE UNA LOCALIDAD MIENTRA EXISTA UNA REFERENCIA EN LAS TABLAS: LOCAL ADMINISTRADO, EVENTO ESPECIAL y PROPUESTA ESPECIFICA
                Localidad localidad = (Localidad) dato;
                Cursor cursorL1 = db.query(true, "Local_Administrado", new String[]{"id_localidad"}, "id_localidad ='" + localidad.getId_localidad() + "'", null, null, null, null, null);
                Cursor cursorL2 = db.query(true, "Evento_Especial", new String[]{"id_localidad"}, "id_localidad ='" + localidad.getId_localidad() + "'", null, null, null, null, null);
                //Cursor cursorL3 = db.query(true, "Propuesta_Especifica", new String[]{"id_localidad"}, "id_localidad ='" + localidad.getId_localidad() + "'", null, null, null, null, null);

                if (cursorL1.moveToFirst() || cursorL2.moveToFirst())
                    return false;
                else
                    return true;
            }

            case 9: {
                //  VERIFICA QUE EXISTA UN TIPO DE EVENTO, EMPLEADO, LOCALIDAD y HORARIO AL INSERTAR UN EVENTO ESPECIAL
                EventoEspecial evento = (EventoEspecial) dato;
                String[] idTipoE = {String.valueOf(evento.getId_tipo_evento())};
                String[] idEmpleado = {String.valueOf(evento.getId_organizador())};
                String[] idLocal = {String.valueOf(evento.getId_localidad())};
                String[] idHorario = {String.valueOf(evento.getHorario())};

                Cursor cursorT = db.query("Tipo_evento", null, "id_tipo_evento = ?", idTipoE, null, null, null);
                Cursor cursorE = db.query("Empleado_UES", null, "id_empleado = ?", idEmpleado, null, null, null);
                Cursor cursorL = db.query("Localidad", null, "id_localidad = ?", idLocal, null, null, null);
                //Cursor cursorH = db.query("Horario", null, "id_Horario = ?", idHorario , null, null,null);

                if (cursorT.moveToFirst() || cursorE.moveToFirst() || cursorL.moveToFirst()) {
                    //SE ENCUENTRAN DATOS
                    return true;
                }
                return false;
            }

            case 10: {
                //VERIFICA QUE NO SE ELIMINE UNA TIPO DE EVENTO MIENTRA EXISTA UNA REFERENCIA EN EVENTO ESPECIAL
                TipoEvento tipoE = (TipoEvento) dato;
                Cursor cursor = db.query(true, "Evento_Especial", new String[]{"id_tipo_evento"}, "id_tipo_evento ='" + tipoE.getId_tipo_evento() + "'", null, null, null, null, null);

                if (cursor.moveToFirst())

                    return false;
                else
                    return true;
            }
            case 666: {
                //Verificar que exista grupo
                Grupo grupo2 = (Grupo) dato;
                String[] id = {String.valueOf(grupo2.getId_grupo())};
                abrir();
                Cursor c2 = db.query("Grupo", null, "id_grupo = ?", id, null, null, null);
                if (c2.moveToFirst()) {
                    //Se encontro Grupo
                    return true;
                }
                return false;
            }
            default:
                return false;
        }


    }


    //Usuarios iniciales
    public void permisosUsuarios() {

        db.execSQL("DELETE FROM Usuario");
        db.execSQL("DELETE FROM OpcionCrud");
        db.execSQL("DELETE FROM AccesoUsuario");

        //USUARIOS

        final String[] IDusuario = {"01", "02", "03", "04", "05"};
        final String[] nomUsuario = {"Misael", "Fabio", "Claudia", "Leonardo", "Alexander"};
        final String[] clave = {"GG20031", "FM19038", "AC17033", "EL19004", "HS19011"};


        Usuario user = new Usuario();

        for (int i = 0; i < 5; i++) {
            user.setId_usuario(IDusuario[i]);
            user.setNom_usuario(nomUsuario[i]);
            user.setClave(clave[i]);
            insertar(user);
        }

        //ACCESOUSUARIO

        final int[] idsAccesoUsuario = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final String[] IDusuarios = {"01", "02", "03", "04", "01", "01", "01", "05", "05", "05"};
        final int[] idOpcionCrud_Access = {1, 2, 3, 4, 2, 3, 4, 4, 2, 3};
        for (int i = 0; i < idsAccesoUsuario.length; i++) {
            AccesoUsuario accesoUsuario = new AccesoUsuario(idsAccesoUsuario[i], IDusuarios[i], idOpcionCrud_Access[i]);
            insertar(accesoUsuario);
        }

        //OPCIONCRUD

        final int[] idOpcionCrud = {1, 2, 3, 4};
        final String[] opcionCrud = {"Insertar", "Actualizar", "Eliminar", "Consultar"};
        for (int i = 0; i < opcionCrud.length; i++) {
            OpcionCrud opcion = new OpcionCrud(idOpcionCrud[i], opcionCrud[i]);
            insertar(opcion);
        }

    }

    // Método para rescatar datos de de la base para el Spinner
    public Cursor llenarSpinner(String sql) throws SQLException {
        Cursor cursor = DBHelper.getReadableDatabase().rawQuery(sql, null);
        return cursor;
    }

    // Datos para llenar base de datos
    public String llenarBD() {
        abrir();

        //Limpia Base
        db.execSQL("DELETE FROM Escuela");
        db.execSQL("DELETE FROM Ciclo");
        db.execSQL("DELETE FROM Materia");

        db.execSQL("DELETE FROM Tipo_de_Empleado");
        db.execSQL("DELETE FROM Empleado_UES");
        db.execSQL("DELETE FROM Docente");

        db.execSQL("DELETE FROM OpcionCrud");
        db.execSQL("DELETE FROM AccesoUsuario");

        db.execSQL("DELETE FROM Localidad");
        db.execSQL("DELETE FROM Local_Administrado");
        db.execSQL("DELETE FROM Tipo_evento");
        db.execSQL("DELETE FROM Evento_Especial");

        //CICLO
        final int[] CicloId = {1, 2, 3, 4};
        final String[] CicloCiclo = {"1", "1", "2", "2"};
        final String[] CicloAño = {"2021", "2022", "2021", "2022"};

        Ciclo ciclo = new Ciclo();
        for (int i = 0; i < 4; i++) {
            ciclo.setId_ciclo(CicloId[i]);
            ciclo.setCiclo(CicloCiclo[i]);
            ciclo.setAño(CicloAño[i]);
            insertar(ciclo);
        }

        //ESCUELA
        final int[] EscuelaId = {1, 2, 3, 4};
        final String[] EscuelaAcronimo = {"EISI", "UCB", "EII", "EIE"};
        final String[] EscuelaNombre = {"Escuela de Ingeniería en Sistemas Informaticos", "Unidad de Ciencias Básicas", "Escuela de Ingeniería Industrial", "Escuela de Ingeniería Eléctrica"};

        Escuela escuela = new Escuela();
        for (int i = 0; i < 4; i++) {
            escuela.setId_escuela(EscuelaId[i]);
            escuela.setAcronimo(EscuelaAcronimo[i]);
            escuela.setNombre(EscuelaNombre[i]);
            insertar(escuela);
        }

        //GRUPO
        //GRUPOHORARIO
        //HORARIO

        //MATERIA

        final int[] MateraiaId = {1, 2, 3, 4};
        final String[] MateriaCod = {"MAT115", "MAT215", "MAT315", "MAT415"};
        final String[] MateriaNombre = {"Matematica I", "Matematica II", "Matematica III", "Matematica IV"};
        final int[] MateriaIdEscuela = {1, 1, 2, 2};

        Materia materia = new Materia();
        for (int i = 0; i < 4; i++) {
            materia.setId_materia(MateraiaId[i]);
            materia.setCod_materia(MateriaCod[i]);
            materia.setNom_materia(MateriaNombre[i]);
            materia.setId_escuela(MateriaIdEscuela[i]);
            insertar(materia);
        }


        //OFERTAACADEMICA

        //OPCIONCRUD
        final int[] idOpcionCrud = {1, 2, 3, 4};
        final String[] opcionCrud = {"Insertar", "Actualizar", "Eliminar", "Consultar"};
        for (int i = 0; i < opcionCrud.length; i++) {
            OpcionCrud opcion = new OpcionCrud(idOpcionCrud[i], opcionCrud[i]);
            insertar(opcion);
        }

        //PROPUESTAESPECIFICA
        //PROPUESTAGENERAL

        //TIPO EMPLEADO
        final int[] idTipoEmpleado = {1, 2, 3, 4};
        final String[] ocupacion = {"Secretario", "Administrador", "Profesor", "Encargado de Laboratorios"};

        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        for (int i = 0; i < 4; i++) {
            tipoEmpleado.setId_tipo_empleado(idTipoEmpleado[i]);
            tipoEmpleado.setOcupacion(ocupacion[i]);
            insertar(tipoEmpleado);
        }

        //EMPLEADO UES
        final int[] idEmpleado = {1, 2, 3, 4};
        final int[] idTipoEmpleado_EmpleadoUES = {4, 3, 2, 1};
        final String[] nombres = {"Juan", "Luis", "Laura", "Julia"};
        final String[] apellidos = {"Perez", "Gómez", "Rodriguez", "López"};
        final String[] emails = {"juan12@gmail.com", "LuisGG@gmail.com", "Laurita96gmail.com", "JuliaL17@gmail.com"};
        final int[] telefonos = {77889945, 75489561, 71724598, 76611225};

        EmpleadoUES empleado = new EmpleadoUES();
        for (int i = 0; i < 4; i++) {
            empleado.setId_empleado(idEmpleado[i]);
            empleado.setId_tipo_empleado(idTipoEmpleado_EmpleadoUES[i]);
            empleado.setNombre_empleado(nombres[i]);
            empleado.setApellido_empleado(apellidos[i]);
            empleado.setEmail_empleado(emails[i]);
            empleado.setTelefono_empleado(telefonos[i]);
            insertar(empleado);
        }

        //DOCENTE
        final int[] idDocente = {1, 2, 3, 4};
        final int[] idEmpleado_Docente = {4, 2, 1, 3};
        final int[] nip = {2233445, 5656897, 4561237, 1234567};
        final String[] categoria = {"Categoria1", "Categoria2", "Categoria3", "Categoria2"};

        Docente docente = new Docente();
        for (int i = 0; i < 4; i++) {
            docente.setId_docente(idDocente[i]);
            docente.setId_empleado(idEmpleado_Docente[i]);
            docente.setNip_docente(nip[i]);
            docente.setCategoria_docente(categoria[i]);
            insertar(docente);
        }

        //ACCESO USUARIO
        final int[] idsAccesoUsuario = {1, 2, 3, 4, 5, 6, 7, 8};
        final String[] IDusuarios = {"01", "02", "03", "04", "05", "01", "01", "01"};
        final int[] idOpcionCrud_Access = {1, 2, 3, 4, 1, 2, 3, 4};
        for (int i = 0; i < idsAccesoUsuario.length; i++) {
            AccesoUsuario accesoUsuario = new AccesoUsuario(idsAccesoUsuario[i], IDusuarios[i], idOpcionCrud_Access[i]);
            insertar(accesoUsuario);
        }

        //LOCALIDAD
        final int[] idlocal = {1, 2, 3, 4, 5, 6, 7};
        final String[] edificio = {"Auditorio Miguel Mármol", "Biblioteca FIA", "Edificio B", "Edificio B", "Edificio C", "Labcomp EISI", "Labcomp EISI"};
        final String[] localnom = {"Auditorio Miguel Mármol", "BIB-201", "B11", "B32", "C12", "LCOMP1", "LCOMP3"};
        final int[] cupo = {100, 50, 100, 100, 100, 20, 20};

        Localidad localidad = new Localidad();
        for (int i = 0; i < 7; i++) {
            localidad.setId_localidad(idlocal[i]);
            localidad.setEdificio_localidad(edificio[i]);
            localidad.setNombre_localidad(localnom[i]);
            localidad.setCapacidad_localidad(cupo[i]);
            insertar(localidad);
        }

        //LOCALADMINISTRADO

        final int[] idLocaladmin = {1, 2, 3, 4};
        final int[] idLocal = {4, 2, 1, 3};
        final int[] idEncargado = {2, 2, 1, 4};

        LocalAdministrado locAdmin = new LocalAdministrado();
        for (int i = 0; i < 4; i++) {
            locAdmin.setId_local_admin(idLocaladmin[i]);
            locAdmin.setId_local(idLocal[i]);
            locAdmin.setId_empleadoadministrador(idEncargado[i]);

            insertar(locAdmin);
        }

        //TIPO DE EVENTO
        final int[] idTipoEvento = {1, 2, 3, 4};
        final String[] nombre = {"Foro", "Conferencia", "Examen Parcial", "Capacitacion"};

        TipoEvento tipoEvento = new TipoEvento();
        for (int i = 0; i < 4; i++) {
            tipoEvento.setId_tipo_evento(idTipoEvento[i]);
            tipoEvento.setNombre_tipo_evento(nombre[i]);
            insertar(tipoEvento);
        }

        //EVENTOESPECIAL
        final int[] idevento = {1, 2, 3, 4};
        final int[] idTipooEvento = {3, 3, 1, 4};
        final String[] nombreEvento = {"Examen Parcial 1 MAT115", "Examen Parcial 1 MAT3115", "Japon en El Salvador", "Capacitacion de Salud Mental"};
        final int[] organizador = {1, 2, 3, 4};
        final String[] fecha = {"03/03/23", "15/03/23", "12/05/23", "12/06/23"};
        final int[] horario = {1, 2, 3, 4};
        final int[] local = {1, 3, 2, 1};

        EventoEspecial eventoEspecial = new EventoEspecial();
        for (int i = 0; i < 4; i++) {
            eventoEspecial.setId_evento(idevento[i]);
            eventoEspecial.setId_tipo_evento(idTipooEvento[i]);
            eventoEspecial.setNombre_evento(nombreEvento[i]);
            eventoEspecial.setId_organizador(organizador[i]);
            eventoEspecial.setFecha_evento(fecha[i]);
            eventoEspecial.setHorario(horario[i]);
            eventoEspecial.setId_localidad(local[i]);
            insertar(eventoEspecial);
        }
        cerrar();

        return context.getResources().getString(R.string.llenadoBD);
    }
}


