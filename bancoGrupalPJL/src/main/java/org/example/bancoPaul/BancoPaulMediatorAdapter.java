package org.example.bancoPaul;

import org.example.IBanco;
import org.example.bancoPaul.Modelo.Cuenta;

public class BancoPaulMediatorAdapter implements IBanco {
    private final Banco banco;

    public BancoPaulMediatorAdapter() {
        this.banco = new Banco();
        banco.inicializarBanco();
        banco.buscarSucursal("Centro").depositar(1, 8000);
        banco.buscarSucursal("Centro").depositar(2, 3500);
        banco.buscarSucursal("Norte").depositar(3, 9100);
        banco.buscarSucursal("Norte").depositar(4, 4200);
    }

    @Override
    public String getNombre() {
        return "BancoPaul";
    }

    @Override
    public boolean existeCliente(String idSucursal, int dni) {
        try {
            return buscarCuenta(idSucursal, dni) != null;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    @Override
    public double consultarSaldo(String idSucursal, int dni) {
        return buscarCuenta(idSucursal, dni).saldo;
    }

    @Override
    public void acreditar(String idSucursal, int dni, double monto) {
        buscarCuenta(idSucursal, dni).acreditar(monto);
    }

    @Override
    public void debitar(String idSucursal, int dni, double monto) {
        buscarCuenta(idSucursal, dni).debitar(monto);
    }

    @Override
    public String describirCliente(String idSucursal, int dni) {
        Cuenta cuenta = buscarCuenta(idSucursal, dni);
        return getNombre() + " / " + idSucursal + " / " + cuenta.nombre + " (DNI " + dni + ")";
    }

    private Cuenta buscarCuenta(String idSucursal, int dni) {
        Sucursal sucursal = banco.buscarSucursal(idSucursal);
        if (sucursal == null) {
            throw new IllegalArgumentException("La sucursal " + idSucursal + " no existe en " + getNombre() + ".");
        }

        Cuenta cuenta = sucursal.buscarPorDni(dni);
        if (cuenta == null) {
            throw new IllegalArgumentException("No existe un cliente con DNI " + dni + " en la sucursal " + idSucursal + ".");
        }
        return cuenta;
    }
}
