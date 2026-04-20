package org.example;

import java.util.Scanner;

public class MainGrupal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MediadorBancario mediador = new MediadorBancario();

        IBanco bancoPaul = new AdaptadorBancoPaul();
        IBanco bancoJosue = new AdaptadorBancoJosue();
        IBanco bancoLourdes = new AdaptadorBancoLourdes();

        mediador.registrarBanco(bancoPaul);
        mediador.registrarBanco(bancoJosue);
        mediador.registrarBanco(bancoLourdes);
        int op = 0;

        while (op != 4) {
            System.out.println("\n==== BIENVENIDO A RED BANCARIA PJL GRUPAL ====");
            System.out.println("1. Ver datos de los bancos");
            System.out.println("2. Ver saldo de cliente por banco ,sucursal y Dni");
            System.out.println("3. Transferencia interbancaria");
            System.out.println("4. Salir");

            op = leerEntero(sc, "Opcion: ");

            switch (op) {
                case 1:
                    mostrarDatos(bancoPaul,bancoJosue,bancoLourdes);
                    break;

                case 2:
                    consultarSaldo(sc, mediador);
                    break;

                case 3:
                    transferir(sc, mediador);
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }

        sc.close();
    }

    private static void mostrarDatos(IBanco bancoPaul,IBanco bancoJosue, IBanco bancoLourdes) {
        System.out.println("\n=== BANCOS REGISTRADOS ===");
        System.out.println("1. "+ bancoPaul.getDatos());
        System.out.println("2. "+ bancoJosue.getDatos());
        System.out.println("3. "+ bancoLourdes.getDatos());
    }

    private static void consultarSaldo(Scanner sc, MediadorBancario mediador) {
        System.out.println("\n=== CONSULTAR SALDO ===");

        String banco = seleccionarBanco(sc);
        String sucursal = seleccionarSucursal(sc);

        int dni = leerEntero(sc, "DNI: ");

        double saldo = mediador.consultarSaldo(banco, sucursal, dni);
        System.out.println("Saldo: $" + saldo);
    }

    private static void transferir(Scanner sc, MediadorBancario mediador) {
        System.out.println("\n=== TRANSFERENCIA INTERBANCARIA ===");

        System.out.println("\n--- DATOS ORIGEN ---");
        String bancoOrigen = seleccionarBanco(sc);
        String sucursalOrigen = seleccionarSucursal(sc);
        int dniOrigen = leerEntero(sc, "DNI origen: ");

        System.out.println("\n--- DATOS DESTINO ---");
        String bancoDestino = seleccionarBanco(sc);
        String sucursalDestino = seleccionarSucursal(sc);
        int dniDestino = leerEntero(sc, "DNI destino: ");

        double monto = leerDouble(sc);

        String resultado = mediador.transferir(bancoOrigen, sucursalOrigen, dniOrigen, bancoDestino, sucursalDestino, dniDestino, monto);

        System.out.println(resultado);

        if (resultado.equals("Transferencia realizada")) {
            System.out.println("Saldo origen: $" +
                    mediador.consultarSaldo(bancoOrigen, sucursalOrigen, dniOrigen));

            System.out.println("Saldo destino: $" +
                    mediador.consultarSaldo(bancoDestino, sucursalDestino, dniDestino));
        }
    }

    private static String seleccionarBanco(Scanner sc) {
        int op;
        do {
            System.out.println("\nSeleccione banco:");
            System.out.println("1. BancoPaul");
            System.out.println("2. BancoJosue");
            System.out.println("3. BancoLourdes");

            op = leerEntero(sc, "Opcion: ");

            switch (op) {
                case 1:
                    return "BancoPaul";
                case 2:
                    return "BancoJosue";
                case 3:
                    return "BancoLourdes";
                default:
                    System.out.println("Banco invalido.");
            }
        } while (true);
    }

    private static String seleccionarSucursal(Scanner sc) {
        int op;

        do {
            System.out.println("\nSeleccione sucursal:");
            System.out.println("1. Centro");
            System.out.println("2. Norte");

            op = leerEntero(sc, "Opcion: ");

            switch (op) {
                case 1:
                    return "Centro";
                case 2:
                    return "Norte";
                default:
                    System.out.println("Sucursal invalida.");
            }
        } while (true);
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);

            if (sc.hasNextInt()) {
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;
            } else {
                System.out.println("Error: debe ingresar un numero entero.");
                sc.next();
            }
        }
    }

    private static double leerDouble(Scanner sc) {
        while (true) {
            System.out.print("Monto: ");
            if (sc.hasNextDouble()) {
                double numero = sc.nextDouble();
                sc.nextLine();
                return numero;
            } else {
                System.out.println("Error: debe ingresar un monto valido.");
                sc.next();
            }
        }
    }
}
