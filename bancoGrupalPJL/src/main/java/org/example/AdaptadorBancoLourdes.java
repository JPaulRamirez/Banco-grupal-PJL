package org.example;

import org.example.bancoLourdes.Administradora;

public class AdaptadorBancoLourdes implements IBanco {
    Administradora admin;

    public AdaptadorBancoLourdes() {
        admin = new Administradora();
        admin.cargarDatosPrueba();
    }

    public String getNombre() {
        return "BancoLourdes";
    }

    public boolean existeCliente(String sucursal, int dni) {
        return admin.existeCliente(sucursal, dni);
    }

    public double consultarSaldo(String sucursal, int dni) {
        return admin.consultarSaldo(sucursal, dni);
    }

    public void acreditar(String sucursal, int dni, double monto) {
        admin.acreditar(sucursal, dni, monto);
    }

    public void debitar(String sucursal, int dni, double monto) {
        admin.debitar(sucursal, dni, monto);
    }
}
