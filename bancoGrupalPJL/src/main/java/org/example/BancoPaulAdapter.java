package org.example;

public class BancoPaulAdapter implements BancoAdapter {

    private org.example.bancoPaul.Banco banco;

    public BancoPaulAdapter(org.example.bancoPaul.Banco banco) {
        this.banco = banco;
    }

    public String getNombre() {
        return "Banco Paul";
    }

    public void mostrarCuentas() {
        for (var s : banco.getSucursales()) {
            s.mostrarCuentas();
        }
    }

    public boolean existeCuenta(int dni) {
        return buscarCuenta(dni) != null;
    }

    private org.example.bancoPaul.Modelo.Cuenta buscarCuenta(int dni) {
        for (var s : banco.getSucursales()) {
            var c = s.buscarPorDni(dni);
            if (c != null) return c;
        }
        return null;
    }

    public void depositar(int dni, double monto) {
        var c = buscarCuenta(dni);
        if (c != null) c.depositar(monto);
    }

    public boolean retirar(int dni, double monto) {
        var c = buscarCuenta(dni);
        if (c == null) return false;

        double saldoAntes = c.saldo;
        c.extraerSaldo(monto);

        return c.saldo < saldoAntes;
    }
}