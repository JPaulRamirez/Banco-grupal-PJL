package org.example;

public interface IBanco {
    String getNombre();
    boolean existeCliente(String sucursal, int dni);
    double consultarSaldo(String sucursal, int dni);
    void acreditar(String sucursal, int dni, double monto);
    void debitar(String sucursal, int dni, double monto);
}
