package org.example.strategy;

public class CuentaAhorro implements TipoCuentaStrategy {
    public boolean puedeTransferir(double saldo, double monto) {
        return saldo >= monto;
    }
}
