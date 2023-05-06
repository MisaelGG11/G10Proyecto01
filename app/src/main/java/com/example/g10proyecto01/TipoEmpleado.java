package com.example.g10proyecto01;

public class TipoEmpleado {
    private String id_tipo_empleado;
    private String ocupacion;

    //Constructor
    public TipoEmpleado() {
    }

    public TipoEmpleado(String id_tipo_empleado,String ocupacion){
        this.id_tipo_empleado = id_tipo_empleado;
        this.ocupacion = ocupacion;
    }

    public String getId_tipo_empleado(){
        return id_tipo_empleado;
    }
    public void setId_tipo_empleado(String id_tipo_empleado){
        this.id_tipo_empleado = id_tipo_empleado;
    }
    public String getOcupacion(){
        return ocupacion;
    }
    public void setOcupacion(String ocupacion){
        this.ocupacion = ocupacion;
    }
}
