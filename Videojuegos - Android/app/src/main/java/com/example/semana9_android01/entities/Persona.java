package com.example.semana9_android01.entities;


public class Persona {
    public String Nombre;
    public String Telefono;
    public String linkImagen;


    public Persona() {
    }

    public Persona(String nombre, String telefono, String linkImagen) {
        this.Nombre = nombre;
        this.Telefono = telefono;
        this.linkImagen = linkImagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }
}
