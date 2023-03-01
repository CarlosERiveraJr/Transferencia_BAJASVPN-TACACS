package com.telcel.sir.pojos;

public class WorkOrder
{
    private String NumEmp;
    private String Usuario;
    private String Nombre;
    private String Des;
    private String IdWO;
    
    public String getNumEmp() {
        return this.NumEmp;
    }
    
    public void setNumEmp(final String NumEmp) {
        this.NumEmp = NumEmp;
    }
    
    public String getUsuario() {
        return this.Usuario;
    }
    
    public void setUsuario(final String Usuario) {
        this.Usuario = Usuario;
    }
    
    public String getNombre() {
        return this.Nombre;
    }
    
    public void setNombre(final String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getDes() {
        return this.Des;
    }
    
    public void setDes(final String Des) {
        this.Des = Des;
    }
    
    public String getIdWO() {
        return this.IdWO;
    }
    
    public void setIdWO(final String IdWO) {
        this.IdWO = IdWO;
    }
    
    @Override
    public String toString() {
        return "WorkOrder{NumEmp=" + this.NumEmp + ", Usuario=" + this.Usuario + ", Nombre=" + this.Nombre + ", Des=" + this.Des + '}';
    }
}