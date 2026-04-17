package org.example;
public interface IBanco {
    String getNombre();
    void recibirTransferencia(String idSucursal, String cbu, double monto);
    double consultarSaldo(String idSucursal, String cbu);
}
