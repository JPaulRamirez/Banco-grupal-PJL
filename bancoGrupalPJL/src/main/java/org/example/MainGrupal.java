package org.example;

import org.example.bancoJosue.BancoJosueMediatorAdapter;
import org.example.bancoLourdes.BancoLourdesMediatorAdapter;
import org.example.bancoPaul.BancoPaulMediatorAdapter;

import java.util.Scanner;

public class MainGrupal {
    public static void main(String[] args) {
        MediadorBancario mediador = new MediadorBancario();
        mediador.registrarBanco(new BancoPaulMediatorAdapter());
        mediador.registrarBanco(new BancoJosueMediatorAdapter());
        mediador.registrarBanco(new BancoLourdesMediatorAdapter());

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> mostrarGuiaDePrueba();
                case 2 -> consultarSaldo(scanner, mediador);
                case 3 -> transferir(scanner, mediador);
                case 0 -> System.out.println("Sistema finalizado.");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===============================================");
        System.out.println("              BANCO GRUPAL - MEDIATOR");
        System.out.println("===============================================");
        System.out.println("1. Ver datos de prueba");
        System.out.println("2. Consultar saldo por banco, sucursal y DNI");
        System.out.println("3. Transferencia interbancaria");
        System.out.println("0. Salir");
    }

    private static void mostrarGuiaDePrueba() {
        System.out.println();
        System.out.println("Bancos registrados:");
        System.out.println("- BancoPaul: Centro -> DNI 1, 2 | Norte -> DNI 3, 4");
        System.out.println("- BancoJosue: Centro -> DNI 1001, 1002 | Norte -> DNI 1003 | Sur -> DNI 1004");
        System.out.println("- BancoLourdes: Centro -> DNI 44965002, 12345678 | Norte -> DNI 2001, 2002");
    }

    private static void consultarSaldo(Scanner scanner, MediadorBancario mediador) {
        String banco = leerTexto(scanner, "Banco: ");
        String sucursal = leerTexto(scanner, "Sucursal: ");
        int dni = leerEntero(scanner, "DNI: ");

        try {
            double saldo = mediador.consultarSaldo(banco, sucursal, dni);
            System.out.printf("Saldo disponible: $%.2f%n", saldo);
        } catch (IllegalArgumentException ex) {
            System.out.println("No fue posible consultar el saldo: " + ex.getMessage());
        }
    }

    private static void transferir(Scanner scanner, MediadorBancario mediador) {
        System.out.println();
        System.out.println("Datos del cliente origen");
        String bancoOrigen = leerTexto(scanner, "Banco origen: ");
        String sucursalOrigen = leerTexto(scanner, "Sucursal origen: ");
        int dniOrigen = leerEntero(scanner, "DNI origen: ");

        System.out.println();
        System.out.println("Datos del cliente destino");
        String bancoDestino = leerTexto(scanner, "Banco destino: ");
        String sucursalDestino = leerTexto(scanner, "Sucursal destino: ");
        int dniDestino = leerEntero(scanner, "DNI destino: ");

        double monto = leerDouble(scanner, "Monto: ");

        TransferenciaResultado resultado = mediador.transferir(
                bancoOrigen,
                sucursalOrigen,
                dniOrigen,
                bancoDestino,
                sucursalDestino,
                dniDestino,
                monto
        );

        System.out.println(resultado.getMensaje());
        if (resultado.isExitosa()) {
            System.out.printf("Nuevo saldo origen: $%.2f%n", mediador.consultarSaldo(bancoOrigen, sucursalOrigen, dniOrigen));
            System.out.printf("Nuevo saldo destino: $%.2f%n", mediador.consultarSaldo(bancoDestino, sucursalDestino, dniDestino));
        }
    }

    private static String leerTexto(Scanner scanner, String etiqueta) {
        System.out.print(etiqueta);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(Scanner scanner, String etiqueta) {
        while (true) {
            System.out.print(etiqueta);
            String valor = scanner.nextLine().trim();
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double leerDouble(Scanner scanner, String etiqueta) {
        while (true) {
            System.out.print(etiqueta);
            String valor = scanner.nextLine().trim();
            try {
                return Double.parseDouble(valor);
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un monto valido.");
            }
        }
    }
}
