package org.example.bancoJosue;

public class Sesion {


    static final String ROL_ADMIN   = "admin";
    static final String ROL_USUARIO = "usuario";

    String  rol;
    Cuenta  cuentaActiva;
    boolean logueado;



    public Sesion() {
        logueado     = false;
        rol          = null;
        cuentaActiva = null;
    }



    public void iniciarComoAdmin() {
        logueado     = true;
        rol          = ROL_ADMIN;
        cuentaActiva = null;
    }


    public void iniciarComoUsuario(Cuenta cuenta) {
        logueado     = true;
        rol          = ROL_USUARIO;
        cuentaActiva = cuenta;
    }


    public void cerrar() {
        logueado     = false;
        rol          = null;
        cuentaActiva = null;
        System.out.println("Sesión cerrada.");
    }


    public boolean esAdmin() {
        return logueado && rol.equals(ROL_ADMIN);
    }

    public boolean esUsuario() {
        return logueado && rol.equals(ROL_USUARIO);
    }
}