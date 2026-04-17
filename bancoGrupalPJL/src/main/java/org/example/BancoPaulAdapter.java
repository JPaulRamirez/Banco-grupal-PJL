package org.example;

import org.example.bancoPaul.Banco;
import org.example.bancoPaul.Modelo.Cuenta;
import org.example.bancoPaul.Sucursal;


public class BancoPaulAdapter implements IBanco {

    private Banco banco;

    public BancoPaulAdapter(Banco banco) {
        this.banco = banco;
    }

    public Cuenta buscarCuenta(int dni) {
        return banco.buscarCuentaGlobal(dni);
    }

    public void depositar(int dni, double monto) {
        Cuenta c = buscarCuenta(dni);
        if (c != null) c.depositar(monto);
    }

    public boolean transferir(int dniOrigen, int dniDestino, double monto) {

        Cuenta origen = buscarCuenta(dniOrigen);

        if (origen == null) return false;

        origen.extraerSaldo(monto);

        return true;
    }
}
