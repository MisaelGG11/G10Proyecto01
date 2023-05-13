package com.example.g10proyecto01;


public class LocalAdministrado {
    private int id_local_admin;
    private int id_local;
    private int id_empleadoadministrador;

    public LocalAdministrado(){
    }
    //Constructor
    public LocalAdministrado(int id_local_admin, int id_local, int id_empleadoadministrador) {
        this.id_local_admin = id_local_admin;
        this.id_local = id_local;
        this.id_empleadoadministrador = id_empleadoadministrador;
    }

    // Getter y Setter

    public int getId_local_admin() {
        return id_local_admin;
    }

    public void setId_local_admin(int id_local_admin) {
        this.id_local_admin = id_local_admin;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_empleadoadministrador() {
        return id_empleadoadministrador;
    }

    public void setId_empleadoadministrador(int id_empleadoadministrador) {
        this.id_empleadoadministrador = id_empleadoadministrador;
    }
}