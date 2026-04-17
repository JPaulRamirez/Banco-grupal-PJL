package org.example.bancoLourdes;

import org.example.IBanco;

import java.util.ArrayList;
import java.util.List;

public class BancoLourdesMediatorAdapter implements IBanco {
    private final List<Sucursal> sucursales = new ArrayList<>();

    public BancoLourdesMediatorAdapter() {
        Sucursal centro = new Sucursal("Centro");
        Sucursal norte = new Sucursal("Norte");

        centro.registrarCuenta(crearCuenta("Lourdes", "44965002", "Av. Bartolome Mitre", "Ahorro", "001", 5400));
        centro.registrarCuenta(crearCuenta("Edward", "12345678", "Calle 555", "Corriente", "002", 7600));
        norte.registrarCuenta(crearCuenta("Camila", "2001", "Diagonal 77", "Ahorro", "003", 6100));
        norte.registrarCuenta(crearCuenta("Bruno", "2002", "Avenida Siempre Viva", "Corriente", "004", 4800));

        sucursales.add(centro);
        sucursales.add(norte);
    }

    @Override
    public String getNombre() {
        return "BancoLourdes";
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
        return getNombre() + " / " + idSucursal + " / " + cuenta.titular.nombre + " (DNI " + dni + ")";
    }

    private Cuenta crearCuenta(
            String nombre,
            String dni,
            String domicilio,
            String tipo,
            String cbu,
            double saldoInicial
    ) {
        Persona persona = new Persona.Builder()
                .setNombre(nombre)
                .setDni(dni)
                .setDomicilio(domicilio)
                .build();

        Cuenta cuenta = new Cuenta(persona, tipo, cbu);
        cuenta.acreditar(saldoInicial);
        return cuenta;
    }

    private Cuenta buscarCuenta(String idSucursal, int dni) {
        Sucursal sucursal = buscarSucursal(idSucursal);
        if (sucursal == null) {
            throw new IllegalArgumentException("La sucursal " + idSucursal + " no existe en " + getNombre() + ".");
        }

        Cuenta cuenta = sucursal.buscarPorDni(dni);
        if (cuenta == null) {
            throw new IllegalArgumentException("No existe un cliente con DNI " + dni + " en la sucursal " + idSucursal + ".");
        }
        return cuenta;
    }

    private Sucursal buscarSucursal(String idSucursal) {
        for (Sucursal sucursal : sucursales) {
            if (sucursal.nombre.equalsIgnoreCase(idSucursal)) {
                return sucursal;
            }
        }
        return null;
    }
}
