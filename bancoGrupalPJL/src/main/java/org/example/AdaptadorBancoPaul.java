package org.example;

import org.example.bancoPaul.Banco;
import org.example.bancoPaul.Sucursal;
import org.example.bancoPaul.Modelo.Cuenta;

public class AdaptadorBancoPaul implements IBanco {
    Banco banco;

    public AdaptadorBancoPaul() {
        banco = new Banco();
        banco.inicializarBanco();
        banco.buscarSucursal("Centro").depositar(1, 7000);
        banco.buscarSucursal("Centro").depositar(2, 5000);
        banco.buscarSucursal("Norte").depositar(3, 9000);
        banco.buscarSucursal("Norte").depositar(4, 2000);
    }

    public String getNombre() {
        return "BancoPaul";
    }

    public boolean existeCliente(String sucursal, int dni) {
        Sucursal s = banco.buscarSucursal(sucursal);
        if (s == null) return false;
        return s.buscarPorDni(dni) != null;
    }

    public double consultarSaldo(String sucursal, int dni) {
        Sucursal s = banco.buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarPorDni(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        return c.saldo;
    }

    public void acreditar(String sucursal, int dni, double monto) {
        Sucursal s = banco.buscarSucursal(sucursal);
        Cuenta c = s.buscarPorDni(dni);
        c.saldo += monto;
    }

    public void debitar(String sucursal, int dni, double monto) {
        Sucursal s = banco.buscarSucursal(sucursal);
        Cuenta c = s.buscarPorDni(dni);
        if (c.saldo < monto) throw new IllegalArgumentException("Saldo insuficiente");
        c.saldo -= monto;
    }
}
