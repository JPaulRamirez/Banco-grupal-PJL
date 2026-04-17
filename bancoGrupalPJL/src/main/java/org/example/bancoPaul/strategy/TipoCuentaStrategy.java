package org.example.bancoPaul.strategy;

public interface TipoCuentaStrategy {
    boolean puedeTransferir(double saldo, double monto);
}
