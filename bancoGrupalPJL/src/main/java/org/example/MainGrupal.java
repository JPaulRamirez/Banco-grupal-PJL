package org.example;

import java.util.Scanner;

public class MainGrupal {

    static Scanner sc = new Scanner(System.in);

    // ── Bancos ─────────────────────────────
    static org.example.bancoJosue.Banco bancoJosue =
            new org.example.bancoJosue.Banco("Banco Josue");

    static org.example.bancoPaul.Banco bancoPaul =
            new org.example.bancoPaul.Banco();

    // ── MAIN ───────────────────────────────
    public static void main(String[] args) {

        bancoPaul.inicializarBanco();

        int opcion;

        do {
            mostrarMenu();
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    System.out.println("\n=== BANCO JOSUE ===");
                    bancoJosue.mostrarTodo();
                    break;

                case 2:
                    System.out.println("\n=== BANCO PAUL ===");
                    mostrarBancoPaulCompleto();
                    break;

                case 3:
                    transferir();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    // ── MENU ───────────────────────────────
    static void mostrarMenu() {
        System.out.println("\n===== BANCO GRUPAL =====");
        System.out.println("1. Ver Banco Josue");
        System.out.println("2. Ver Banco Paul");
        System.out.println("3. Transferir entre bancos");
        System.out.println("0. Salir");
    }

    // ── MOSTRAR BANCO PAUL COMPLETO ────────
    static void mostrarBancoPaulCompleto() {
        for (org.example.bancoPaul.Sucursal s : bancoPaul.getSucursales()) {
            s.mostrarCuentas();
        }
    }

    // ── TRANSFERENCIA INTERACTIVA ──────────
    static void transferir() {

        System.out.println("Banco origen:");
        System.out.println("1. Banco Josue");
        System.out.println("2. Banco Paul");
        int origenBanco = Integer.parseInt(sc.nextLine());

        System.out.print("DNI origen: ");
        int dniOrigen = Integer.parseInt(sc.nextLine());

        System.out.println("Banco destino:");
        System.out.println("1. Banco Josue");
        System.out.println("2. Banco Paul");
        int destinoBanco = Integer.parseInt(sc.nextLine());

        System.out.print("DNI destino: ");
        int dniDestino = Integer.parseInt(sc.nextLine());

        System.out.print("Monto: ");
        double monto = Double.parseDouble(sc.nextLine());

        ejecutarTransferencia(origenBanco, destinoBanco, dniOrigen, dniDestino, monto);
    }

    // ── LOGICA DE TRANSFERENCIA ────────────
    static void ejecutarTransferencia(int bancoOrigen, int bancoDestino,
                                      int dniOrigen, int dniDestino, double monto) {

        // ── ORIGEN: JOSUE ───────────────────
        if (bancoOrigen == 1) {

            var origen = bancoJosue.buscarCuenta(dniOrigen);

            if (origen == null) {
                System.out.println("Cuenta origen no encontrada en Banco Josue");
                return;
            }

            if (bancoDestino == 1) {
                // Josue → Josue
                var destino = bancoJosue.buscarCuenta(dniDestino);

                if (destino == null) {
                    System.out.println("Cuenta destino no encontrada");
                    return;
                }

                origen.transferir(destino, monto);

            } else {
                // Josue → Paul
                var destino = buscarCuentaPaul(dniDestino);

                if (destino == null) {
                    System.out.println("Cuenta destino no encontrada en Banco Paul");
                    return;
                }

                origen.retirar(monto);
                destino.depositar(monto);
            }

        } else {
            // ── ORIGEN: PAUL ─────────────────

            var origen = buscarCuentaPaul(dniOrigen);

            if (origen == null) {
                System.out.println("Cuenta origen no encontrada en Banco Paul");
                return;
            }

            if (bancoDestino == 2) {
                // Paul → Paul
                var destino = buscarCuentaPaul(dniDestino);

                if (destino == null) {
                    System.out.println("Cuenta destino no encontrada");
                    return;
                }

                origen.transferirCuenta(destino, monto);

            } else {
                // Paul → Josue
                var destino = bancoJosue.buscarCuenta(dniDestino);

                if (destino == null) {
                    System.out.println("Cuenta destino no encontrada en Banco Josue");
                    return;
                }

                origen.extraerSaldo(monto);
                destino.depositar(monto);
            }
        }

        System.out.println("Transferencia realizada con éxito");
    }

    // ── BUSCAR CUENTA EN BANCO PAUL ────────
    static org.example.bancoPaul.Modelo.Cuenta buscarCuentaPaul(int dni) {

        for (var s : bancoPaul.getSucursales()) {
            var c = s.buscarPorDni(dni);
            if (c != null) return c;
        }

        return null;
    }
}