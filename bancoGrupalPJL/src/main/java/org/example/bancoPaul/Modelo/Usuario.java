package org.example.bancoPaul.Modelo;

import org.example.bancoPaul.enums.Rol;

public class Usuario {

    public String nombre;
    public int dni;
    public Rol rol;

    public Usuario(String nombre, int dni, Rol rol)
    {
        this.nombre=nombre;
        this.dni = dni ;
        this.rol =rol;
    }
}
