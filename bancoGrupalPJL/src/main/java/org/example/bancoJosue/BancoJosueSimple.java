package org.example.bancoJosue;

import org.example.IBanco;

public class BancoJosueSimple implements IBanco {
    Banco banco;

    public BancoJosueSimple() {
        banco = new Banco("BancoJosue");
        banco.sucursales.clear();

        Sucursal centro = new Sucursal("Centro");
        Sucursal norte = new Sucursal("Norte");

        Usuario u1 = new Usuario("Josue", "Lopez", "a", "1");
        Usuario u2 = new Usuario("Mia", "Perez", "b", "1");
        Usuario u3 = new Usuario("Leo", "Gomez", "c", "1");

        Cuenta c1 = new Cuenta(1001, "Ahorro", "Centro", u1);
        Cuenta c2 = new Cuenta(1002, "Corriente", "Centro", u2);
        Cuenta c3 = new Cuenta(1003, "Ahorro", "Norte", u3);

        c1.saldo = 6000;
        c2.saldo = 3000;
        c3.saldo = 8000;

        centro.cuentas.add(c1);
        centro.cuentas.add(c2);
        norte.cuentas.add(c3);

        banco.sucursales.add(centro);
        banco.sucursales.add(norte);
    }

    public String getNombre() {
        return "BancoJosue";
    }

    public boolean existeCliente(String sucursal, int dni) {
        Sucursal s = banco.buscarSucursal(sucursal);
        if (s == null) return false;
        return s.buscarCuenta(dni) != null;
    }

    public double consultarSaldo(String sucursal, int dni) {
        Sucursal s = banco.buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarCuenta(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        return c.saldo;
    }

    public void acreditar(String sucursal, int dni, double monto) {
        Sucursal s = banco.buscarSucursal(sucursal);
        Cuenta c = s.buscarCuenta(dni);
        c.saldo += monto;
    }

    public void debitar(String sucursal, int dni, double monto) {
        Sucursal s = banco.buscarSucursal(sucursal);
        Cuenta c = s.buscarCuenta(dni);
        if (c.saldo < monto) throw new IllegalArgumentException("Saldo insuficiente");
        c.saldo -= monto;
    }
}
