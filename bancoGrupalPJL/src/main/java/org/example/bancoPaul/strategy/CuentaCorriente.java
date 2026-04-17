package org.example.bancoPaul.strategy;

public class CuentaCorriente implements TipoCuentaStrategy {
    public boolean puedeTransferir(double saldo, double monto) {
        return saldo + 1000 >= monto;
    }
}
