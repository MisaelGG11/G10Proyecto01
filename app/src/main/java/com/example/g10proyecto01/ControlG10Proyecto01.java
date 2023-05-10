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
    private static final String[] camposAcessoUsuario = new String[]{"id_usuario", "id-opcion"};

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
                db.execSQL("CREATE TABLE Usuario (id_usuario CHAR(2) NOT NULL, nom_usuario VARCHAR2(30) NOT NULL, clave CHAR(7) NOT NULL, CONSTRAINT PK_USUARIO PRIMARY KEY (id_usuario));");
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

    /******************************************** Tabla EmpleadoUES ********************************************/
    // CAMPOS: {"id_empleado", "id_tipo_empleado", "nombre_empleado", "apellido_empleado", "email_empleado", "telefono_empleado"}
    /*  Insertar EmpleadoUES  */
    public String insertar(EmpleadoUES empleadoUES){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues empleado = new ContentValues();
        empleado.put("id_empleado", empleadoUES.getId_empleado());
        empleado.put("id_tipo_empleado", empleadoUES.getId_tipo_empleado());
        empleado.put("nombre_empleado", empleadoUES.getNombre_empleado());
        empleado.put("apellido_empleado", empleadoUES.getApellido_empleado());
        empleado.put("email_empleado", empleadoUES.getEmail_empleado());
        empleado.put("telefono_empleado", empleadoUES.getTelefono_empleado());

        contador = db.insert("Empleado_UES", null, empleado);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    /*  Consultar EmpleadoUES  */

    /*  Actualizar EmpleadoUES  */

    /*  Inserta EmpleadoUES  */


    /******************************************** Tabla Docente ********************************************/
    // CAMPOS: {"id_docente", "id_empleado", "nip_docente", "categoria_docente"}

    /*  Insertar Docente  */
    public String insertar(Docente docente){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues docent = new ContentValues();
        docent.put("id_docente", docente.getId_docente());
        docent.put("id_empleado", docente.getId_empleado());
        docent.put("nip_docente", docente.getNip_docente());
        docent.put("categoria_docente", docente.getCategoria_docente());
;
        contador = db.insert("Docente", null, docent);
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

    /*  Actualizar Docente  */

    /*  Eliminar Docente  */


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
    public TipoEmpleado consultar(String id_tipo_empleado){
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
        String where="id_tipo_empleado=" + tipoEmpleado.getId_tipo_empleado();
        contador += db.delete("Tipo_de_Empleado", where, null);
        regAfectados+=contador;
        return regAfectados;
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
        System.out.println("Llego acá");
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

    /* Metodos Insertar aqui */

    public String insertar(Escuela escuela) {
        String regInsertados = "Registro Insertado Nº= ";

        long contador = 0;

        ContentValues escu = new ContentValues();

        escu.put("id_escuela", escuela.getId_escuela());
        escu.put("acronimo", escuela.getAcronimo());
        escu.put("nombre",escuela.getNombre());

        contador = db.insert("escuela", null, escu);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, RegistroDuplicado.Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }
    /*********************************** Tabla Local ***********************************/
    // CAMPOS: {"id_localidad", "edificio_localidad", "nombre_localidad", "capacidad_localidad"}
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
        Cursor cursor = db.query("localidad", camposLocalidad, "id_localidad = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Localidad localidad = new Localidad();
            localidad.setId_localidad(cursor.getInt(0));
            localidad.setEdificio_localidad(cursor.getString(1));
            localidad.setNombre_localidad(cursor.getString(2));
            localidad.setCapacidad_localidad(cursor.getInt(3));
            return localidad;
        }else{
            return null;
        }
    }
    /*  Actualizar Localidad  */

    /*  Eliminar Localidad  */

    /*********************************** Tabla Localidad Administrado ***********************************/
    // CAMPOS: {"id_local_admin", "id_localidad", "id_empleado"}

    /*  Insertar Localidad Administrado */

    /*  Consultar Localidad Administrado*/

    /*  Actualizar Localidad Administrado */

    /*  Eliminar Localidad Administrado */


    /*********************************** Tabla Evento Especial ***********************************/
    // CAMPOS: {"id_evento", "id_tipo_evento", "organizador", "nombre_evento", "fecha"}

    /*  Insertar Evento Especial  */
    /*  Consultar Evento Especial  */
    /*  Actualizar Evento Especial  */
    /*  Eliminar Evento Especial  */

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
        Cursor cursor = db.query("Tipo_evento", camposTipoEvento, "id_tipo_evento = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            TipoEvento tipoEvento = new TipoEvento();
            tipoEvento.setId_tipo_evento(cursor.getInt(0));
            tipoEvento.setNombre_tipo_evento(cursor.getString(1));
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






    // Verificar integridad
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {

        return false;
    }





    //Usuarios iniciales
    public void permisosUsuarios(){
        final String[] IDusuario = {"U1", "U2", "U3", "U4"};
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
        //ESCUELA
        final int[] EscuelaId = {1};
        final String[] EscuelaAcronimo = {"EISI"};
        final String[] EscuelaNombre = {"Escuela de Ingenieria en sistemas informaticos"};

        abrir();
        //Limpia Base
        db.execSQL("DELETE FROM Escuela");

        db.execSQL("DELETE FROM Tipo_de_Empleado");
        db.execSQL("DELETE FROM Empleado_UES");
        db.execSQL("DELETE FROM Docente");

        db.execSQL("DELETE FROM OpcionCrud");

        db.execSQL("DELETE FROM Localidad");
        db.execSQL("DELETE FROM Local_Administrado");
        db.execSQL("DELETE FROM Tipo_evento");
        db.execSQL("DELETE FROM Evento_Especial");


        Escuela escuela = new Escuela();
        for (int i = 0; i < 1; i++) {
            escuela.setId_escuela(EscuelaId[i]);
            escuela.setAcronimo(EscuelaAcronimo[i]);
            escuela.setNombre(EscuelaNombre[i]);
            insertar(escuela);
        }

        //TIPO EMPLEADO
        final int[] idTipoEmpleado = {1,2,3,4};
        final String[] ocupacion = {"Secretario", "Administrador", "Profesor", "Encargado de Laboratorios"};

        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        for (int i = 0; i < 4; i++){
            tipoEmpleado.setId_tipo_empleado(idTipoEmpleado[i]);
            tipoEmpleado.setOcupacion(ocupacion[i]);
            insertar(tipoEmpleado);
        }

        final int[] idOpcionCrud= {1,2,3,4};
        final String[] opcionCrud = {"insertar","actualizar","eliminar","ver detalle"};
        for (int i = 0; i < opcionCrud.length; i++) {
            OpcionCrud opcion = new OpcionCrud(idOpcionCrud[i],opcionCrud[i]);
            insertar(opcion);
        }

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

        //TIPO DE EVENTO
        final int[] idTipoEvento = {1,2,3,4};
        final String[] nombre = {"Foro", "Conferencia", "Examen Parcial", "Capacitacion"};

        TipoEvento tipoEvento = new TipoEvento();
        for (int i = 0; i < 4; i++){
            tipoEvento.setId_tipo_evento(idTipoEvento[i]);
            tipoEvento.setNombre_tipo_evento(nombre[i]);
            insertar(tipoEvento);
        }

        cerrar();

        return "Guardo Correctamente";
    }
}
