package org.example.strategy;

public interface TipoCuentaStrategy {
    boolean puedeTransferir(double saldo, double monto);
}
