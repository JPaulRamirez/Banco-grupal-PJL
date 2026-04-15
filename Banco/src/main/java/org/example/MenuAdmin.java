package org.example;
import org.example.Modelo.Cuenta;
import org.example.strategy.CuentaAhorro;
import org.example.strategy.CuentaCorriente;
import org.example.strategy.CuentaPremium;
import org.example.strategy.TipoCuentaStrategy;

import java.util.Scanner;
public class MenuAdmin {
    private Banco banco;
    private Scanner sc;

    public MenuAdmin(Banco banco, Scanner sc) {
        this.banco = banco;
        this.sc = sc;
    }

    public void iniciar() {
        banco.mostrarSucursales();
        System.out.print("Ingrese sucursal: ");
        String nombre = sc.next();

        Sucursal sucursal = banco.buscarSucursal(nombre);

        if (sucursal == null) {
            System.out.println("Sucursal no existe");
            return;
        }

        // 🔐 LOGIN
        System.out.print("Usuario: ");
        String user = sc.next();

        System.out.print("Password: ");
        String pass = sc.next();

        if (!sucursal.login(user, pass)) {
            System.out.println("Acceso denegado");
            return;
        }

        int opcion = 0;

        while (opcion != 8) {

            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Eliminar cuenta");
            System.out.println("3. Depositar");
            System.out.println("4. Transferir");
            System.out.println("5. Mostrar cuentas");
            System.out.println("6. Saldo total sucursal");
            System.out.println("7. Salir");

            System.out.print("\nSeleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombreC = sc.nextLine();

                    System.out.print("Direccion: ");
                    String dir = sc.nextLine();

                    System.out.print("DNI: ");
                    int dni = sc.nextInt();

                    System.out.println("Tipo de cuenta:");
                    System.out.println("1. Ahorro");
                    System.out.println("2. Corriente");
                    System.out.println("3. Premium");

                    int tipo = sc.nextInt();

                    TipoCuenta tipoCuenta;
                    TipoCuentaStrategy estrategia;

                    switch (tipo) {
                        case 1:
                            tipoCuenta = TipoCuenta.AHORRO;
                            estrategia = new CuentaAhorro();
                            break;
                        case 2:
                            tipoCuenta = TipoCuenta.CORRIENTE;
                            estrategia = new CuentaCorriente();
                            break;
                        case 3:
                            tipoCuenta = TipoCuenta.PREMIUM;
                            estrategia = new CuentaPremium();
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            return; // 🔥 clave
                    }
                    Cuenta c = new Cuenta(nombreC, dir, tipoCuenta, dni, estrategia);
                    sucursal.registrarCuenta(c);
                    break;

                case 2:
                    System.out.print("DNI: ");
                    int dniElimina = sc.nextInt();

                    Cuenta cuenta = sucursal.buscarPorDni(dniElimina);
                    if (cuenta != null) {
                        sucursal.eliminarCuenta(cuenta);
                    }
                    break;

                case 3:
                    System.out.print("DNI: ");
                    int dniDep = sc.nextInt();

                    System.out.print("Monto: ");
                    double monto = sc.nextDouble();

                    sucursal.depositar(dniDep, monto);
                    break;

                case 4:
                    System.out.print("DNI origen: ");
                    int o = sc.nextInt();

                    System.out.print("DNI destino: ");
                    int d = sc.nextInt();

                    System.out.print("Monto: ");
                    double m = sc.nextDouble();

                    sucursal.transferir(o, d, m);
                    break;

                case 5:
                    sucursal.mostrarCuentas();
                    break;

                case 6:
                    System.out.println("Saldo total: $" + sucursal.consultarSaldoTotal());
                    break;

            }
        }
    }
}
