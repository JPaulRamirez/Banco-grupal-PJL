package org.example;

import java.util.Scanner;

public class MainGrupal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MediadorBancario mediador = new MediadorBancario();

        mediador.registrarBanco(new AdaptadorBancoPaul());
        mediador.registrarBanco(new AdaptadorBancoJosue());
        mediador.registrarBanco(new AdaptadorBancoLourdes());

        int op = 0;

        while (op != 4) {
            System.out.println("\n==== BIENVENIDO A RED BANCARIA PJL GRUPAL ====");
            System.out.println("1. Ver datos");
            System.out.println("2. Ver saldo");
            System.out.println("3. Transferir");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");


            if (sc.hasNextInt()) {
                op = sc.nextInt();
                sc.nextLine();
                switch (op) {
                    case 1:
                        System.out.println();
                        System.out.println("Banco registrados:");
                        System.out.println("BancoPaul: Centro -> DNI: 1, 2 | Norte -> DNI: 3, 4");
                        System.out.println("BancoJosue: Centro -> DNI: 1001, 1002 | Norte -> DNI: 1003");
                        System.out.println("BancoLourdes: Centro -> DNI: 2001 | Norte -> DNI: 2002");
                        break;

                    case 2:
                        System.out.print("Banco: ");
                        String banco = sc.nextLine();
                        System.out.print("Sucursal: ");
                        String sucursal = sc.nextLine();
                        System.out.print("DNI: ");
                        int dni = Integer.parseInt(sc.nextLine());

                        System.out.println("Saldo: $" + mediador.consultarSaldo(banco, sucursal, dni));
                        break;

                    case 3:
                        System.out.print("Banco origen: ");
                        String bancoOrigen = sc.nextLine();
                        System.out.print("Sucursal origen: ");
                        String sucursalOrigen = sc.nextLine();
                        System.out.print("DNI origen: ");
                        int dniOrigen = Integer.parseInt(sc.nextLine());

                        System.out.print("Banco destino: ");
                        String bancoDestino = sc.nextLine();
                        System.out.print("Sucursal destino: ");
                        String sucursalDestino = sc.nextLine();
                        System.out.print("DNI destino: ");
                        int dniDestino = Integer.parseInt(sc.nextLine());

                        System.out.print("Monto: ");
                        double monto = Double.parseDouble(sc.nextLine());

                        String r = mediador.transferir(bancoOrigen, sucursalOrigen, dniOrigen, bancoDestino, sucursalDestino, dniDestino, monto);
                        System.out.println(r);

                        if (r.equals("Transferencia realizada")) {
                            System.out.println("Saldo origen: $" + mediador.consultarSaldo(bancoOrigen, sucursalOrigen, dniOrigen));
                            System.out.println("Saldo destino: $" + mediador.consultarSaldo(bancoDestino, sucursalDestino, dniDestino));
                        }
                        break;

                    case 4:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        break;
                }
            }
            else {
                System.out.println("Error: Debes ingresar un numero.");
                sc.next();
            }
        }
    }
}
