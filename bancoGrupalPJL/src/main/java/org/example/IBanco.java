package org.example;

import org.example.bancoPaul.Modelo.Cuenta;

public interface IBanco {
    boolean transferir(int dniOrigen, int dniDestino, double monto);
    void depositar(int dni, double monto);
    Cuenta buscarCuenta(int dni);
}
