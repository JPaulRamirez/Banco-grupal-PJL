package org.example.Modelo;

import org.example.Rol;

public class Admin extends Usuario {

    public String correo;
    public String username;
    public String password;

    public Admin(String nombre, int dni, Rol rol , String correo, String username, String password) {
        super(nombre, dni,rol);
        this.correo = correo;
        this.username = username;
        this.password = password;
    }
    public boolean login(String user, String pass) {

        if(this.username.equals(user) && this.password.equals(pass))
        {
            return true;
        } else {
            return false;
        }
    }


}
