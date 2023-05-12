package com.example.g10proyecto01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import java.util.ArrayList;
import java.util.List;


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
    private static final String[] camposMateria = new String[]{"id_materia", "id_escuela", "cod_materia", "ciclo_materia", "nombre_materia"};
    private static final String[] camposOfertaAcademica = new String[]{"id_oferta_a", "id_ciclo", "id_docente", "id_materia"};
    private static final String[] camposOpcionCrud = new String[]{"id_opcion_crud", "des_opcion"};
    private static final String[] camposPropuestaEspecifica = new String[]{"id_especifica", "id_propuesta", "id_gh", "id_localidad", "estado_especifica"};
    private static final String[] camposPropuestaGeneral = new String[]{"id_propuesta", "estado_propuesta"};
    private static final String[] camposTipoEmpleado = new String[]{"id_tipo_empleado", "ocupacion"};
    private static final String[] camposTipoEvento = new String[]{"id_tipo_evento", "nombre_tipo_evento"};
    private static final String[] camposUsuario = new String[]{"id_usuario", "nom_usuario", "clave"};
    private static final String[] camposAccesoUsuario = new String[]{"id_acceso","id_usuario", "id-opcion"};


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
                db.execSQL("CREATE TABLE Evento_Especial(id_evento INTEGER NOT NULL PRIMARY KEY, id_tipo_evento INTEGER NOT NULL, nombre_evento VARCHAR2(50) NOT NULL, organizador VARCHAR2(50) NOT NULL, fecha DATE NOT NULL, id_horario INTEGER NOT NULL, id_localidad INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Grupo(id_grupo INTEGER NOT NULL PRIMARY KEY, id_oferta_a INTEGER NOT NULL, num_grupo INTEGER NOT NULL, tipo_grupo VARCHAR2(11) NOT NULL, cupo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Grupo_Horario(id_gh INTEGER NOT NULL PRIMARY KEY, id_horario INTEGER NOT NULL, id_grupo INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Local_Administrado(id_local_admin INTEGER NOT NULL PRIMARY KEY, id_localidad INTEGER NOT NULL, id_empleado INTEGER NOT NULL);");
                db.execSQL("CREATE TABLE Localidad(id_localidad INTEGER NOT NULL, edificio_localidad VARCHAR2(60) NOT NULL, nombre_localidad VARCHAR2(30) NOT NULL, capacidad_localidad INTEGER NOT NULL, CONSTRAINT PK_LOCALIDAD PRIMARY KEY (id_localidad));");
                db.execSQL("CREATE TABLE Materia(id_materia INTEGER NOT NULL PRIMARY KEY, id_escuela INTEGER NOT NULL, cod_materia VARCHAR2(6) NOT NULL, ciclo_materia VARCHAR2(50) NOT NULL, nombre_materia VARCHAR2(30) NOT NULL);");
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

    /******************************************** Tabla Usuario ********************************************/
    // CAMPOS: {"id_usuario", "nom_usuario", "clave"}
    /* Insertar Usuario*/
    public String insertar (Usuario usuario){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues user = new ContentValues();
        user.put("id_usuario", usuario.getId_usuario());
        user.put("nom_usuario", usuario.getNom_usuario());
        user.put("clave", usuario.getClave());

        contador = db.insert("Usuario", null, user);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String insertar(Usuario usuario, String opcionCrud){
        String regInsertados="Registro Insertado tabla usuario Nº= ";
        long contador=0, contador2 = 0;

        ContentValues user = new ContentValues();
        user.put("id_usuario", usuario.getId_usuario());
        user.put("nom_usuario", usuario.getNom_usuario());
        user.put("clave", usuario.getClave());

        ContentValues accesoUsuario = new ContentValues();
        accesoUsuario.put("id_usuario", usuario.getId_usuario());
        accesoUsuario.put("id_opcion_crud",opcionCrud);
        contador = db.insert("Usuario", null, user);
        contador2 = db.insert("AccesoUsuario",null,accesoUsuario);

        if((contador==-1 || contador==0) && (contador2==-1 || contador2==0))
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+(contador);
            regInsertados=regInsertados+"\nRegistro Insertado tabla acceso usuario Nº= " + contador2;
        }

        return regInsertados;
    }


    public Usuario consultarUsuario(String id_usuario){
        String[] id = {id_usuario};
        Cursor cursor1 = db.query("Usuario", camposUsuario, "id_usuario = ?", id, null, null, null);

        if(cursor1.moveToFirst()){
            Usuario usuario = new Usuario(cursor1.getString(0),cursor1.getString(1), cursor1.getString(2));
            return usuario;
        }else{
            return null;
        }
    }

    public String actualizar(Usuario usuario){
        String[] id = {String.valueOf(usuario.getId_usuario())};
        ContentValues contentValues = new ContentValues();
        if(usuario.getNom_usuario() != null){
            contentValues.put("nom_usuario",usuario.getNom_usuario());
        }
        if(usuario.getClave() != null){
            contentValues.put("clave",usuario.getClave());
        }
        db.update("Usuario",contentValues,"id_usuario = ?", id);
        return "Registro Actualizado Correctamente";
    }
    /******************************************** Tabla AccesoUsuario ********************************************/
    // CAMPOS: {"id_acceso", "id_usuario","id_opcion_crud"}

    public String insertar(AccesoUsuario accesoUsuario){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues accUsuario = new ContentValues();
        accUsuario.put("id_acceso", accesoUsuario.getId_acceso());
        accUsuario.put("id_usuario",accesoUsuario.getId_usuario());
        accUsuario.put("id_opcion_crud", accesoUsuario.getId_opcion_crud());

        ;
        contador = db.insert("AccesoUsuario", null, accUsuario);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String consultarAccesoUsuario(String id_usuario){
        String[] id = {id_usuario};
        String consulta = "SELECT opc.des_opcion FROM AccesoUsuario acc INNER JOIN OpcionCrud opc ON opc.id_opcion_crud = acc.id_opcion_crud WHERE acc.id_usuario = ?;";
        Cursor cursor1 = DBHelper.getReadableDatabase().rawQuery(consulta,id);
        String accesosUsuario = "";
        while(cursor1.moveToNext()){
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
    public String insertar(EmpleadoUES empleadoUES){
        String regInsertados="Registro Insertado Nº= ";
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
        if(contador == -1 || contador == 0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    /*  Consultar EmpleadoUES  */
    public EmpleadoUES consultarEmpleadoUES(String id_empleado){
        String[] id = {id_empleado};
        Cursor cursor = db.query("Empleado_UES", camposEmpleado, "id_empleado = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            EmpleadoUES empleado = new EmpleadoUES();
            empleado.setId_empleado(cursor.getInt(0));
            empleado.setId_tipo_empleado(cursor.getInt(1));
            empleado.setNombre_empleado(cursor.getString(2));
            empleado.setApellido_empleado(cursor.getString(3));
            empleado.setEmail_empleado(cursor.getString(4));
            empleado.setTelefono_empleado(cursor.getInt(5));
            return empleado;
        }else{
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
        return "Registro Actualizado Correctamente";
    }

    /*  Inserta EmpleadoUES  */
    public String eliminar(EmpleadoUES empleadoUES){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(tipoEmpleado, 2)){
            String where="id_empleado=" + empleadoUES.getId_empleado();
            contador += db.delete("Empleado_UES", where, null);
            regAfectados+=contador;
            return regAfectados;
        //}
        //else return "Hay una referencia en Empleado UES de id tipo de empleado " + tipoEmpleado.getId_tipo_empleado();
    }

    /******************************************** Tabla Docente ********************************************/
    // CAMPOS: {"id_docente", "id_empleado", "nip_docente", "categoria_docente"}

    /*  Insertar Docente  */
    public String insertar(Docente docente){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if (verificarIntegridad(docente, 3)){
            ContentValues docent = new ContentValues();
            docent.put("id_docente", docente.getId_docente());
            docent.put("id_empleado", docente.getId_empleado());
            docent.put("nip_docente", docente.getNip_docente());
            docent.put("categoria_docente", docente.getCategoria_docente());
            contador = db.insert("Docente", null, docent);
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    /*  Consultar Docente  */
    public Docente consultarDocente(String id_Docente){
        String[] id = {id_Docente};
        Cursor cursor = db.query("Docente", camposDocente, "id_Docente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Docente docente = new Docente();
            docente.setId_docente(cursor.getInt(0));
            docente.setId_empleado(cursor.getInt(1));
            docente.setNip_docente(cursor.getInt(2));
            docente.setCategoria_docente(cursor.getString(3));
            return docente;
        }else{
            return null;
        }
    }

    /*  Actualizar Docente  */
    public String actualizar(Docente docente){
        String[] id = {String.valueOf(docente.getId_docente())};
        ContentValues docent = new ContentValues();
        docent.put("id_empleado", docente.getId_empleado());
        docent.put("nip_docente", docente.getNip_docente());
        docent.put("categoria_docente", docente.getCategoria_docente());
        db.update("Docente",docent,"id_docente= ?", id);
        return "Registro Actualizado Correctamente";
    }


    /*  Eliminar Docente  */
    public String eliminar(Docente docente){
        String regAfectados="filas afectadas= ";
        int contador=0;
        //if (verificarIntegridad(docente, 4)) {
            String where = "id_Docente=" + docente.getId_docente();
            contador += db.delete("Docente", where, null);
            regAfectados += contador;
            return regAfectados;
        //}
        //else return "Hay una referencia en Empleado UES de id tipo de empleado " + docente.getId_docente();
    }

    /******************************************** Tabla TipoEmpleado ********************************************/
    // CAMPOS: {"id_tipo_empleado", "ocupacion"}

    /*  Insertar EmpleadoUES  */
    public String insertar(TipoEmpleado tipoEmpleado){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues tEmpleado = new ContentValues();
        tEmpleado.put("id_tipo_empleado", tipoEmpleado.getId_tipo_empleado());
        tEmpleado.put("ocupacion", tipoEmpleado.getOcupacion());

        ;
        contador = db.insert("Tipo_de_Empleado", null, tEmpleado);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    /*  Consultar Tipo de Empleado  */
    public TipoEmpleado consultarTipoEmpleado(String id_tipo_empleado){
        String[] id = {id_tipo_empleado};
        Cursor cursor = db.query("Tipo_de_Empleado", camposTipoEmpleado, "id_tipo_empleado = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoEmpleado tipoEmpleado = new TipoEmpleado();
            tipoEmpleado.setId_tipo_empleado(cursor.getInt(0));
            tipoEmpleado.setOcupacion(cursor.getString(1));
            return tipoEmpleado;
        }else{
            return null;
        }
    }

    /*  Actualizar Tipo de Empleado  */
    public String actualizar(TipoEmpleado tipoEmpleado){
        String[] id = {String.valueOf(tipoEmpleado.getId_tipo_empleado())};
        ContentValues cv = new ContentValues();
        cv.put("ocupacion", tipoEmpleado.getOcupacion());
        db.update("Tipo_de_Empleado",cv,"id_tipo_empleado = ?", id);
        return "Registro Actualizado Correctamente";
    }

    /*  Eliminar tipoEmpleado  */
    public String eliminar(TipoEmpleado tipoEmpleado){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(tipoEmpleado, 2)){
            String where="id_tipo_empleado=" + tipoEmpleado.getId_tipo_empleado();
            contador += db.delete("Tipo_de_Empleado", where, null);
            regAfectados+=contador;
            return regAfectados;
        }
        else return "Hay una referencia en Empleado UES de id tipo de empleado " + tipoEmpleado.getId_tipo_empleado();
    }
    /******************************************** Tabla OpcionCrud ********************************************/
    // CAMPOS: {"id_opcion_crud", "des_opcion"}

    public String insertar(OpcionCrud opcionCrud){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues opcCrud = new ContentValues();
        opcCrud.put("id_opcion_crud", opcionCrud.getId_opcion_crud());
        opcCrud.put("des_opcion", opcionCrud.getDes_opcion());

        ;
        contador = db.insert("OpcionCrud", null, opcCrud);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public OpcionCrud consultarOpcionCrud(String id_opcion_crud){
        String[] id = {id_opcion_crud};
        Cursor cursor1 = db.query("OpcionCrud", camposOpcionCrud, "id_opcion_crud = ?", id, null, null, null);
        System.out.println(cursor1);
        if(cursor1.moveToFirst()){
            OpcionCrud opcionCrud = new OpcionCrud(cursor1.getInt(0),cursor1.getString(1));
            return opcionCrud;
        }else{
            return null;
        }
    }
    public String actualizar(OpcionCrud opcionCrud){
        String[] id = {String.valueOf(opcionCrud.getId_opcion_crud())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("des_opcion",opcionCrud.getDes_opcion());
        db.update("OpcionCrud",contentValues,"id_opcion_crud = ?", id);
        return "Registro Actualizado Correctamente";
    }
    public String eliminar(OpcionCrud opcionCrud){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="id_opcion_crud=" + opcionCrud.getId_opcion_crud();
        contador += db.delete("OpcionCrud", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    /*********************************** Tabla Escuela ***********************************/

    /* Insertar escuela */
    public String insertar(Escuela escuela) {
        String regInsertados = "Registro Insertado Nº= ";

        long contador = 0;

        ContentValues escu = new ContentValues();

        escu.put("id_escuela", escuela.getId_escuela());
        escu.put("acronimo", escuela.getAcronimo());
        escu.put("nombre", escuela.getNombre());

        contador = db.insert("escuela", null, escu);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, RegistroDuplicado.Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    /*  Consultar escuela  */
    public Escuela consultar(String id_escuela) {
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

            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con carnet " + escuela.getId_escuela() + " no existe en la DB";
        }
    }

    /*  Actualizar escuela  */

    public String eliminar(Escuela escuela) {
        String regAfectados = "filas afectadas= ";

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

    public ArrayList<Escuela> mostrar() {

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

    /*********************************** Tabla Local ***********************************/
    // CAMPOS: {"id_localidad", "edificio_localidad", "nombre_localidad", "capacidad_localidad"}

    /*  Insertar Localidad  */
    public String insertar(Localidad localidad) {
        String regInsertados = "Registro Insertado Nº= ";

        long contador = 0;

        ContentValues loc = new ContentValues();

        loc.put("id_localidad", localidad.getId_localidad());
        loc.put("edificio_localidad", localidad.getEdificio_localidad());
        loc.put("nombre_localidad", localidad.getNombre_localidad());
        loc.put("capacidad_localidad", localidad.getCapacidad_localidad());

        contador = db.insert("localidad", null, loc);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, RegistroDuplicado.Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    /*  Consultar Localidad  */
    public Localidad consultarlocalidad(String id_localidad){
        String[] id = {id_localidad};
        Cursor cursorLoc = db.query("localidad", camposLocalidad, "id_localidad = ?", id, null, null, null);
        //System.out.println(cursorLoc);
        if(cursorLoc.moveToFirst()){
            Localidad localidad = new Localidad(cursorLoc.getInt(0),cursorLoc.getString(1),cursorLoc.getString(2),cursorLoc.getInt(3));
            return localidad;
        }else{
            return null;
        }
    }

    /*  Actualizar Localidad  */
    public String actualizar(Localidad localidad){
        String[] id = {String.valueOf(localidad.getId_localidad())};
        ContentValues loc = new ContentValues();
        loc.put("id_localidad", localidad.getId_localidad());
        loc.put("edificio_localidad", localidad.getEdificio_localidad());
        loc.put("nombre_localidad", localidad.getNombre_localidad());
        loc.put("capacidad_localidad", localidad.getCapacidad_localidad());

        db.update("localidad",loc,"id_localidad = ?", id);
        return "Registro Actualizado Correctamente";
    }

    /*  Eliminar Localidad  */
    public String eliminar(Localidad localidad){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="id_localidad=" + localidad.getId_localidad();
        contador += db.delete("Localidad", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    /*********************************** Tabla Localidad Administrado ***********************************/
    // CAMPOS: {"id_local_admin", "id_localidad", "id_empleado"}

    /*  Insertar Localidad Administrado */

    /*  Consultar Localidad Administrado*/

    /*  Actualizar Localidad Administrado */

    /*  Eliminar Localidad Administrado */
    public String eliminar(LocalAdministrado localAdministrado){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="id_local_admin=" + localAdministrado.getId_local_admin();
        contador += db.delete("Local_Administrado", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    /*********************************** Tabla Evento Especial ***********************************/
    // CAMPOS: {"id_evento", "id_tipo_evento", "organizador", "nombre_evento", "fecha"}

    /*  Insertar Evento Especial  */
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

    /*********************************** Tabla Tipo de Evento ***********************************/
    // CAMPOS: {"id_tipo_evento", "nombre_tipo_evento"}

    /*  Insertar Tipo de Evento  */
    public String insertar(TipoEvento tipoEvento) {
        String regInsertados = "Registro Insertado Nº= ";

        long contador = 0;

        ContentValues teven = new ContentValues();

        teven.put("id_tipo_evento", tipoEvento.getId_tipo_evento());
        teven.put("nombre_tipo_evento", tipoEvento.getNombre_tipo_evento());

        contador = db.insert("Tipo_evento", null, teven);

        if (contador == -1 || contador == 0) {
            regInsertados = "ERROR al Insertar el registro, Registro Duplicado.Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    /*  Consultar Tipo de Evento  */
    public TipoEvento consultartipoevento(String id_tipo_evento){
        String[] id = {id_tipo_evento};
        Cursor cursortv = db.query("Tipo_evento", camposTipoEvento, "id_tipo_evento = ?", id, null, null, null);
        if(cursortv.moveToFirst()){
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setId_tipo_evento(cursortv.getInt(0));
            tipoEvento.setNombre_tipo_evento(cursortv.getString(1));
            return tipoEvento;
        }else{
            return null;
        }
    }

    /*  Actualizar Tipo de Evento  */
    public String actualizar(TipoEvento tipoEvento){
        String[] id = {String.valueOf(tipoEvento.getId_tipo_evento())};
        ContentValues cv = new ContentValues();
        cv.put("nombre_tipo_evento",tipoEvento.getNombre_tipo_evento());

        db.update("Tipo_evento",cv,"id_tipo_evento = ?", id);
        return "Registro Actualizado Correctamente";
    }

    /*  Eliminar Tipo de Evento  */

    public String eliminar(TipoEvento tipoEvento){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="id_tipo_evento=" + tipoEvento.getId_tipo_evento();
        contador += db.delete("Tipo_evento", where, null);
        regAfectados+=contador;
        return regAfectados;
    }






    /********************************************************* Verificar integridad *******************************************************/
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion){
            case 1: {
                //  VERIFICA QUE EXISTA TIPO DE EMPLEADO AL INSERTAR UN EMPLEADO UES
                EmpleadoUES empleado = (EmpleadoUES) dato;
                String[] id = {String.valueOf(empleado.getId_tipo_empleado())};

                Cursor cursor = db.query("Tipo_de_Empleado", null, "id_tipo_empleado = ?", id , null, null,null);

                if (cursor.moveToFirst()){
                    //SE ENCUENTRAN DATOS
                    return true;
                }
                return false;
            }
            case 2: {
                //VERIFICA QUE NO SE ELIMINE UN TIPO EMPLEADO MIENTRA EXiISTA UNA REFERENCIA DE EL EN LA TABLA EMPLEADO UES
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

                Cursor cursor = db.query("Empleado_UES", null, "id_empleado = ?", id , null, null,null);

                if (cursor.moveToFirst()){
                    //SE ENCUENTRAN DATOS
                    return true;
                }
                return false;
            }
            default: return false;
        }
    }





    //Usuarios iniciales
    public void permisosUsuarios(){

        final String[] IDusuario = {"01", "02", "03", "04", "05"};
        final String[] nomUsuario = {"Misael", "Fabio", "Claudia", "Leonardo", "Alexander"};
        final String[] clave = {"GG20031", "FM19038", "AC17033", "EL19004", "HS19011"};


        db.execSQL("DELETE FROM Usuario");

        Usuario user = new Usuario();

        for (int i = 0; i < 4; i++){
            user.setId_usuario(IDusuario[i]);
            user.setNom_usuario(nomUsuario[i]);
            user.setClave(clave[i]);
            insertar(user);
        }
    }

    // Método para rescatar datos de de la base para el Spinner
    public Cursor llenarSpinner(String sql) throws SQLException{
        Cursor cursor = DBHelper.getReadableDatabase().rawQuery(sql, null);
        return cursor;
    }
    // Datos para llenar base de datos
    public String llenarBD() {
        abrir();

        //Limpia Base
        db.execSQL("DELETE FROM Escuela");

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
        //DOCENTE
        //EMPLEADO_UES
        //ESCUELA
        final int[] EscuelaId = {1,2,3,4};
        final String[] EscuelaAcronimo = {"EISI","UCB","EII","EIE"};
        final String[] EscuelaNombre = {"Escuela de Ingeniería en Sistemas Informaticos","Unidad de Ciencias Básicas","Escuela de Ingeniería Industrial","Escuela de Ingeniería Eléctrica"};

        Escuela escuela = new Escuela();
        for (int i = 0; i < 4; i++){
            escuela.setId_escuela(EscuelaId[i]);
            escuela.setAcronimo(EscuelaAcronimo[i]);
            escuela.setNombre(EscuelaNombre[i]);
            insertar(escuela);
        }
        //EVENTOESPECIAL
        //GRUPO
        //GRUPOHORARIO
        //HORARIO
        //LOCALADMINISTRADO
        //LOCALIDAD
        final int[] idlocal = {1,2,3,4,5,6,7};
        final String[] edificio= {"Auditorio Miguel Mármol", "Biblioteca FIA", "Edificio B", "Edificio B", "Edificio C","Labcomp EISI", "Labcomp EISI"};
        final String[] localnom = {"Auditorio Miguel Mármol", "BIB-201", "B11", "B32", "C12", "LCOMP1", "LCOMP3"};
        final int[] cupo = {100,50,100,100,100,20,20};

        Localidad localidad = new Localidad();
        for (int i = 0; i < 7; i++){
            localidad.setId_localidad(idlocal[i]);
            localidad.setEdificio_localidad(edificio[i]);
            localidad.setNombre_localidad(localnom[i]);
            localidad.setCapacidad_localidad(cupo[i]);
            insertar(localidad);
        }

        //MATERIA

        //OFERTAACADEMICA

        //OPCIONCRUD
        final int[] idOpcionCrud= {1,2,3,4};
        final String[] opcionCrud = {"insertar","actualizar","eliminar","ver detalle"};
        for (int i = 0; i < opcionCrud.length; i++) {
            OpcionCrud opcion = new OpcionCrud(idOpcionCrud[i],opcionCrud[i]);
            insertar(opcion);
        }

        //PROPUESTAESPECIFICA
        //PROPUESTAGENERAL

        //TIPO EMPLEADO
        final int[] idTipoEmpleado = {1,2,3,4};
        final String[] ocupacion = {"Secretario", "Administrador", "Profesor", "Encargado de Laboratorios"};

        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        for (int i = 0; i < 4; i++){
            tipoEmpleado.setId_tipo_empleado(idTipoEmpleado[i]);
            tipoEmpleado.setOcupacion(ocupacion[i]);
            insertar(tipoEmpleado);
        }

        //EMPLEADO UES
        final  int [] idEmpleado = {1,2,3,4};
        final int [] idTipoEmpleado_EmpleadoUES = {4,3,2,1};
        final String [] nombres = {"Juan", "Luis", "Laura", "Julia"};
        final String [] apellidos = {"Perez", "Gómez", "Rodriguez", "López"};
        final String [] emails = {"juan12@gmail.com", "LuisGG@gmail.com", "Laurita96gmail.com", "JuliaL17@gmail.com"};
        final int [] telefonos = {77889945, 75489561, 71724598, 76611225};

        EmpleadoUES empleado = new EmpleadoUES();
        for (int i = 0; i < 4; i++){
            empleado.setId_empleado(idEmpleado[i]);
            empleado.setId_tipo_empleado(idTipoEmpleado_EmpleadoUES[i]);
            empleado.setNombre_empleado(nombres[i]);
            empleado.setApellido_empleado(apellidos[i]);
            empleado.setEmail_empleado(emails[i]);
            empleado.setTelefono_empleado(telefonos[i]);
            insertar(empleado);
        }

        //DOCENTE
        final int[] idDocente = {1,2,3,4};
        final int [] idEmpleado_Docente = {4,2,1,3};
        final int [] nip = {2233445, 5656897, 4561237, 1234567};
        final String [] categoria = {"Categoria1", "Categoria2", "Categoria3", "Categoria2"};

        Docente docente = new Docente();
        for (int i = 0; i < 4; i++){
            docente.setId_docente(idDocente[i]);
            docente.setId_empleado(idEmpleado_Docente[i]);
            docente.setNip_docente(nip[i]);
            docente.setCategoria_docente(categoria[i]);
            insertar(docente);
        }

        //ACCESO USUARIO
        final int[] idsAccesoUsuario= {1,2,3,4};
        final String[] IDusuarios = {"01", "02", "03", "04","05"};
        for (int i = 0; i < idsAccesoUsuario.length; i++) {
            AccesoUsuario accesoUsuario = new AccesoUsuario(idsAccesoUsuario[i],IDusuarios[i],idsAccesoUsuario[i]);
            insertar(accesoUsuario);
        }

        //TIPO DE EVENTO
        final int[] idTipoEvento = {1,2,3,4};
        final String[] nombre = {"Foro", "Conferencia", "Examen Parcial", "Capacitacion"};

        TipoEvento tipoEvento = new TipoEvento();
        for (int i = 0; i < 4; i++){
            tipoEvento.setId_tipo_evento(idTipoEvento[i]);
            tipoEvento.setNombre_tipo_evento(nombre[i]);
            //insertar(tipoEvento);
        }

        cerrar();

        return "Guardo Correctamente";
    }
}

