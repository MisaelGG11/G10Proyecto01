package com.example.g10proyecto01;

public class EmpleadoUES {
    private String id_empleado;
    private String id_tipo_empleado;
    private String nombre_empleado;
    private String apellido_empleado;
    private String email_empleado;
    private String telefono_empleado;

    //Constructor
    public EmpleadoUES() {
    }

    public EmpleadoUES(String id_empleado, String id_tipo_empleado, String nombre_empleado, String apellido_empleado, String email_empleado, String telefono_empleado) {
        this.id_empleado = id_empleado;
        this.id_tipo_empleado = id_tipo_empleado;
        this.nombre_empleado = nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.email_empleado = email_empleado;
        this.telefono_empleado = telefono_empleado;
    }

    public String getId_empleado(){
        return id_empleado;
    }
    public void setId_empleado(String id_empleado){
        this.id_empleado = id_empleado;
    }
    public String getId_tipo_empleado(){
        return id_tipo_empleado;
    }
    public void setId_tipo_empleado(String id_tipo_empleado){
        this.id_tipo_empleado = id_tipo_empleado;
    }
    public String getNombre_empleado(){
        return nombre_empleado;
    }
    public void setNombre_empleado(String nombre_empleado){
        this.nombre_empleado = nombre_empleado;
    }
    public String getApellido_empleado(){
        return this.apellido_empleado;
    }
    public void setApellido_empleado(String apellido_empleado){
        this.apellido_empleado = apellido_empleado;
    }
    public String getEmail_empleado(){
        return email_empleado;
    }
    public void setEmail_empleado(String email_empleado){
        this.email_empleado = email_empleado;
    }
    public String getTelefono_empleado(){
        return  telefono_empleado;
    }
    public void setTelefono_empleado(String telefono_empleado){
        this.telefono_empleado = telefono_empleado;
    }
}



