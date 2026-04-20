package org.example;

import java.util.Scanner;

import org.example.*;


public class MainGrupal {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // ── Bancos reales ─────────────────────
        var bancoJosue = new org.example.bancoJosue.Banco("Banco Josue");

        var bancoPaul = new org.example.bancoPaul.Banco();
        bancoPaul.inicializarBanco();

        // ── Adapters ──────────────────────────
        BancoAdapter josueAdapter = new BancoJosueAdapter(bancoJosue);
        BancoAdapter paulAdapter  = new BancoPaulAdapter(bancoPaul);

        // ── Mediator ──────────────────────────
        BancoMediator mediator = new BancoMediator();
        mediator.agregarBanco(josueAdapter);
        mediator.agregarBanco(paulAdapter);

        // ── MENÚ ─────────────────────────────
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    mediator.mostrarTodos();
                    break;

                case 2:
                    transferir(mediator);
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
        System.out.println("1. Ver todos los bancos");
        System.out.println("2. Transferir entre bancos");
        System.out.println("0. Salir");
    }

    // ── TRANSFERENCIA INTERACTIVA ──────────
    static void transferir(BancoMediator mediator) {

        System.out.print("DNI origen: ");
        int origen = Integer.parseInt(sc.nextLine());

        System.out.print("DNI destino: ");
        int destino = Integer.parseInt(sc.nextLine());

        System.out.print("Monto: ");
        double monto = Double.parseDouble(sc.nextLine());

        mediator.transferir(origen, destino, monto);
    }
}