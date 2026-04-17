package org.example.bancoLourdes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class
Administradora {
    Map<String, Cuenta> mapaCuentas = new HashMap<>();
    ArrayList<String> historial = new ArrayList<>();

    public void registrarCuenta(Persona p, String tipo, String cbu) {
        Cuenta nueva = new Cuenta(p, tipo, cbu);
        mapaCuentas.put(cbu, nueva);
        System.out.println(">>> Cuenta dada de alta para: " + p.nombre);
    }

    public void transferir(String cbuOrigen, String cbuDestino, double monto) {
        Cuenta origen = buscar(cbuOrigen);
        Cuenta destino = buscar(cbuDestino);

        if (origen != null && destino != null) {
            if (origen.saldo >= monto) {
                origen.saldo -= monto;
                destino.saldo += monto;
                historial.add("Transferencia: $" + monto + " de " + origen.titular.nombre + " a " + destino.titular.nombre);
                System.out.println(">>> Transferencia exitosa.");
            } else {
                System.out.println("No hay fondos suficientes");
            }
        } else {
            System.out.println("Error: Uno de los CBUs no existe.");
        }
    }

    public void balance() {
        System.out.println("\n--- BALANCE GENERAL DE CUENTAS ---");
        for (Cuenta c : mapaCuentas.values()) {
            System.out.println(c);
        }
        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
        if (historial.isEmpty()) {
            System.out.println("- No hay transferencias registradas.");
        } else {
            for (String h : historial) {
                System.out.println("- " + h);
            }
        }
    }

    public Cuenta buscar(String cbu) {

        return mapaCuentas.get(cbu);
    }
}