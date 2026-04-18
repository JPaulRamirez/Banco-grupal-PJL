package org.example;

public interface IBanco {
    String getNombre();
    boolean existeCliente(String idSucursal, int dni);
    double consultarSaldo(String idSucursal, int dni);
    void acreditar(String idSucursal, int dni, double monto);
    void debitar(String idSucursal, int dni, double monto);
    String describirCliente(String idSucursal, int dni);
}
