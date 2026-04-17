package org.example.bancoJosue;

public class Autenticacion {

    private Banco banco;

    public Autenticacion(Banco banco) {
        this.banco = banco;
    }

    public Cuenta loginUsuario(int dni, String pass) {
        Cuenta cuenta = banco.buscarCuenta(dni);

        if (cuenta == null || !cuenta.activa) return null;
        if (!cuenta.titular.contrasena.equals(pass)) return null;

        return cuenta;
    }

    public boolean loginAdmin(String user, String pass) {
        return user.equals("admin") && pass.equals("1234");
    }
}
