package org.example.bancoJosue;

import org.example.IBanco;

public class BancoJosueMediatorAdapter implements IBanco {
    private final Banco banco;

    public BancoJosueMediatorAdapter() {
        this.banco = new Banco("BancoJosue");
        banco.sucursales.clear();

        Sucursal centro = new Sucursal("Centro");
        Sucursal norte = new Sucursal("Norte");
        Sucursal sur = new Sucursal("Sur");

        centro.cuentas.add(crearCuenta(1001, "Ahorro", centro.nombre, "Josue", "Lopez", "josue@correo.com", "1234", 7600));
        centro.cuentas.add(crearCuenta(1002, "Corriente", centro.nombre, "Nora", "Perez", "nora@correo.com", "1234", 2300));
        norte.cuentas.add(crearCuenta(1003, "Ahorro", norte.nombre, "Mario", "Diaz", "mario@correo.com", "1234", 8900));
        sur.cuentas.add(crearCuenta(1004, "Corriente", sur.nombre, "Elena", "Rios", "elena@correo.com", "1234", 3100));

        banco.sucursales.add(centro);
        banco.sucursales.add(norte);
        banco.sucursales.add(sur);
    }

    @Override
    public String getNombre() {
        return "BancoJosue";
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
        return getNombre() + " / " + idSucursal + " / " + cuenta.titular.nombre + " " + cuenta.titular.apellido + " (DNI " + dni + ")";
    }

    private Cuenta crearCuenta(
            int dni,
            String tipo,
            String sucursal,
            String nombre,
            String apellido,
            String email,
            String contrasena,
            double saldoInicial
    ) {
        Usuario titular = new Usuario(nombre, apellido, email, contrasena);
        Cuenta cuenta = new Cuenta(dni, tipo, sucursal, titular);
        cuenta.acreditar(saldoInicial);
        return cuenta;
    }

    private Cuenta buscarCuenta(String idSucursal, int dni) {
        Sucursal sucursal = banco.buscarSucursal(idSucursal);
        if (sucursal == null) {
            throw new IllegalArgumentException("La sucursal " + idSucursal + " no existe en " + getNombre() + ".");
        }

        Cuenta cuenta = sucursal.buscarCuenta(dni);
        if (cuenta == null) {
            throw new IllegalArgumentException("No existe un cliente con DNI " + dni + " en la sucursal " + idSucursal + ".");
        }
        return cuenta;
    }
}
