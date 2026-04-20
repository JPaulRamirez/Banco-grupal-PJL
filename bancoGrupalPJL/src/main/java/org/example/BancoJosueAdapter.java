package org.example;

public class BancoJosueAdapter implements BancoAdapter {

    private org.example.bancoJosue.Banco banco;

    public BancoJosueAdapter(org.example.bancoJosue.Banco banco) {
        this.banco = banco;
    }

    public String getNombre() {
        return "Banco Josue";
    }

    public void mostrarCuentas() {
        banco.mostrarTodo();
    }

    public boolean existeCuenta(int dni) {
        return banco.buscarCuenta(dni) != null;
    }

    public void depositar(int dni, double monto) {
        var c = banco.buscarCuenta(dni);
        if (c != null) c.depositar(monto);
    }

    public boolean retirar(int dni, double monto) {
        var c = banco.buscarCuenta(dni);
        if (c == null) return false;

        if (c.getSaldo() < monto) return false;

        c.retirar(monto);
        return true;
    }
}