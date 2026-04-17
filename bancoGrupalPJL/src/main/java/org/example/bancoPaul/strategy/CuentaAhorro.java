package org.example.bancoPaul.strategy;

public class CuentaAhorro implements TipoCuentaStrategy {
    public boolean puedeTransferir(double saldo, double monto) {
        return saldo >= monto;
    }
}
