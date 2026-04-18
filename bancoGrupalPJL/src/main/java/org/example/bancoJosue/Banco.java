package org.example.bancoJosue;
// ============================================================
//  CLASE: Banco
//  Es la raíz del patrón Composite. Contiene sucursales,
//  que a su vez contienen cuentas.
//
//  También actúa como "Repositorio" central: es el único
//  punto desde donde se puede buscar cualquier sucursal
//  o cuenta de todo el sistema.
//
//  Estructura del árbol:
//    Banco
//    ├── Sucursal A
//    │     ├── Cuenta (DNI: 11111111)
//    │     └── Cuenta (DNI: 22222222)
//    └── Sucursal B
//          └── Cuenta (DNI: 33333333)
// ============================================================

import java.util.ArrayList;
import java.util.List;

public class Banco {

    // ----------------------------------------------------------
    //  ATRIBUTOS
    // ----------------------------------------------------------

    String nombre;                      // Nombre del banco (ej: "Banco DINO")
    public List<Sucursal> sucursales;          // Lista de todas las sucursales

    // ----------------------------------------------------------
    //  CONSTRUCTOR
    // ----------------------------------------------------------

    public Banco(String nombre) {
        this.nombre     = nombre;
        this.sucursales = new ArrayList<Sucursal>();

        // Las sucursales vienen predefinidas — no se crean desde el menú
        sucursales.add(new Sucursal("Sucursal 1"));
        sucursales.add(new Sucursal("Sucursal 2"));
        sucursales.add(new Sucursal("Sucursal 3"));
    }

    public void cargarDatosPrueba() {
        sucursales.clear();

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

        sucursales.add(centro);
        sucursales.add(norte);
    }

    // ----------------------------------------------------------
    //  BÚSQUEDAS (actúa como repositorio central)
    // ----------------------------------------------------------

    // Busca una sucursal por nombre dentro del banco
    // Devuelve la sucursal o null si no existe
    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }

    public boolean existeCliente(String sucursal, int dni) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) return false;
        return s.buscarCuenta(dni) != null;
    }

    public double consultarSaldo(String sucursal, int dni) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarCuenta(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        return c.saldo;
    }

    public void acreditar(String sucursal, int dni, double monto) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarCuenta(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        c.saldo += monto;
    }

    public void debitar(String sucursal, int dni, double monto) {
        Sucursal s = buscarSucursal(sucursal);
        if (s == null) throw new IllegalArgumentException("Sucursal no encontrada");
        Cuenta c = s.buscarCuenta(dni);
        if (c == null) throw new IllegalArgumentException("Cliente no encontrado");
        if (c.saldo < monto) throw new IllegalArgumentException("Saldo insuficiente");
        c.saldo -= monto;
    }

    // Busca una cuenta por DNI en TODAS las sucursales
    // Esto permite transferencias entre sucursales distintas
    public Cuenta buscarCuenta(int dni) {
        for (Sucursal s : sucursales) {
            for (Cuenta c : s.cuentas) {
                if (c.dni == dni) {
                    return c;
                }
            }
        }
        return null;
    }
    public String getDatos() {
        String texto = nombre + ": ";

        for (int i = 0; i < sucursales.size(); i++) {
            Sucursal sucursal = sucursales.get(i);
            texto += sucursal.nombre + " -> DNI ";

            for (int j = 0; j < sucursal.cuentas.size(); j++) {
                texto += sucursal.cuentas.get(j).dni;
                if (j < sucursal.cuentas.size() - 1) {
                    texto += ", ";
                }
            }

            if (i < sucursales.size() - 1) {
                texto += " | ";
            }
        }

        return texto;
    }


    // Muestra solo los nombres de las sucursales, sin info de cuentas
    // Se usa en el menú público para no exponer datos privados
    public void mostrarSoloSucursales () {
        System.out.println("===== " + nombre + " =====");
        System.out.println("Sucursales disponibles:");
        for (Sucursal s : sucursales) {
            System.out.println("  • " + s.nombre);
        }
    }

    // Muestra toda la estructura del banco: sucursales y sus cuentas
    // Gracias al Composite, con un solo llamado se recorre todo el árbol
    public void mostrarTodo () {
        System.out.println("===== " + nombre + " =====");

        if (sucursales.isEmpty()) {
            System.out.println("Sin sucursales registradas.");
            return;
        }

        // Delega la impresión a cada sucursal,
        // que a su vez delega a cada cuenta
        for (Sucursal s : sucursales) {
            s.mostrar();
        }
    }
}
