package org.example.bancoPaul.strategy;

public class CuentaPremium implements TipoCuentaStrategy {
    public boolean puedeTransferir(double saldo, double monto) {
        return saldo + 5000 >= monto;
    }
}
