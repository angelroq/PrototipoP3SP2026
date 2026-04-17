/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author Angel R
 */
public class clsFacultades {
    String codigo_facultad, nombre_facultad, status_facultad;

    @Override
    public String toString() {
        return "clsFacultades{" + "codigo_facultad=" + codigo_facultad + ", nombre_facultad=" + nombre_facultad + ", status_facultad=" + status_facultad + '}';
    }

    public String getCodigo_facultad() {
        return codigo_facultad;
    }

    public void setCodigo_facultad(String codigo_facultad) {
        this.codigo_facultad = codigo_facultad;
    }

    public String getNombre_facultad() {
        return nombre_facultad;
    }

    public void setNombre_facultad(String nombre_facultad) {
        this.nombre_facultad = nombre_facultad;
    }

    public String getStatus_facultad() {
        return status_facultad;
    }

    public void setStatus_facultad(String status_facultad) {
        this.status_facultad = status_facultad;
    }

    public clsFacultades(){
        
    }
    public clsFacultades(String codigo_facultad, String nombre_facultad, String status_facultad) {
        this.codigo_facultad = codigo_facultad;
        this.nombre_facultad = nombre_facultad;
        this.status_facultad = status_facultad;
    }
    
}
