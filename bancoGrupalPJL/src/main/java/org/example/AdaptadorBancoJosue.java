package org.example;

import org.example.bancoJosue.Banco;

public class AdaptadorBancoJosue implements IBanco {
    Banco banco;

    public AdaptadorBancoJosue() {
        banco = new Banco("BancoJosue");
        banco.cargarDatosPrueba();
    }

    public String getNombre() {
        return "BancoJosue";
    }

    public boolean existeCliente(String sucursal, int dni) {
        return banco.existeCliente(sucursal, dni);
    }

    public double consultarSaldo(String sucursal, int dni) {
        return banco.consultarSaldo(sucursal, dni);
    }

    public void acreditar(String sucursal, int dni, double monto) {
        banco.acreditar(sucursal, dni, monto);
    }

    public void debitar(String sucursal, int dni, double monto) {
        banco.debitar(sucursal, dni, monto);
    }
}
