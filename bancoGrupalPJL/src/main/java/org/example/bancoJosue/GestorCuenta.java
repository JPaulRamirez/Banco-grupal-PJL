package org.example.bancoJosue;

public class GestorCuenta {

    private Banco banco;

    public GestorCuenta(Banco banco) {
        this.banco = banco;
    }

    public boolean crearCuenta(String sucursalNombre, int dni, Usuario usuario) {

        Sucursal suc = banco.buscarSucursal(sucursalNombre);

        if (suc == null) return false;
        if (banco.buscarCuenta(dni) != null) return false;

        Cuenta cuenta = new Cuenta(dni, "Ahorro", suc.nombre, usuario);
        suc.cuentas.add(cuenta);

        return true;
    }

    public boolean darDeBaja(int dni) {
        Cuenta cuenta = banco.buscarCuenta(dni);

        if (cuenta == null) return false;

        cuenta.darDeBaja();
        return true;
    }

    public Cuenta buscarCuenta(int dni) {
        return banco.buscarCuenta(dni);
    }
}