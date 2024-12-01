package com.edutecno.modelo;
import java.util.Date;

public class Usuario {

    private int id;
    private String nombre;
    private String username;
    private String clave;
    private String correo;
    private Date fechaNacimiento;
    private String animal;

    public Usuario(int id, String nombre, String username, String clave, String correo, Date fechaNacimiento, String animal) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.clave = clave;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.animal = animal;
    }

    public Usuario(String nombre, String username, String clave, String correo) {
        this.nombre = nombre;
        this.username = username;
        this.clave = clave;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", clave='" + clave + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", animal='" + animal + '\'' +
                '}';
    }
}