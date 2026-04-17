package org.example;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MediadorBancario mediador = new MediadorBancario();

        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n===============================================");
            System.out.println("       BIENVENIDO A RED BANCARIA PJL");
            System.out.println("===============================================");
            System.out.println("1. BALANCE (Saldo)");
            System.out.println("2. TRANSFERENCIA INTERBANCARIA");
            System.out.println("3. SALIR");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            if (opcion == 1) {
                System.out.println("\n[ ESTADO DE CUENTA ]");
                System.out.print("Ingrese CBU: ");
                String cbu = sc.next();
                System.out.println("Saldo disponible: $XXXX.XX (Consultando...)");
            }
            else if (opcion == 2) {
                System.out.println("\n[ NUEVA TRANSFERENCIA ]");
                System.out.println("Elija Banco: 1. Josué | 2. Lourdes | 3. Paul");
                int b = sc.nextInt();
                String nombreB = (b == 1) ? "bancojosue" : (b == 2) ? "bancolourdes" : "bancopaul";

                System.out.println("Elija Sucursal: 1. Centro | 2. Norte");
                int s = sc.nextInt();
                String idSuc = (s == 1) ? "Centro" : "Norte";

                System.out.print("CBU Destino: ");
                String cbuD = sc.next();
                System.out.print("Monto: $");
                double monto = sc.nextDouble();

                mediador.enviarDinero(nombreB, idSuc, cbuD, monto);
                System.out.println("✔ Operación enviada al Mediador.");
            }
        }
    }
}
