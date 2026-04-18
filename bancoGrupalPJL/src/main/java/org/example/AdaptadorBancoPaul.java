package org.example;

import org.example.bancoPaul.Banco;

public class AdaptadorBancoPaul implements IBanco {
    Banco banco;

    public AdaptadorBancoPaul() {
        banco = new Banco();
        banco.cargarDatosPrueba();
    }

    public String getNombre() {
        return "BancoPaul";
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
