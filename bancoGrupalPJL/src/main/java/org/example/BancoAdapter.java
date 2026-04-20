package org.example;

public interface BancoAdapter {

    String getNombre();

    void mostrarCuentas();

    boolean existeCuenta(int dni);

    void depositar(int dni, double monto);

    boolean retirar(int dni, double monto);
}