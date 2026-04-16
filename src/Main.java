import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Administradora admin = new Administradora();

        Persona p1 = new Persona.Builder().setNombre("Lourdes").setDni("44965002").setDomicilio("Av. Bartolomé Mitre").build();
        admin.registrarCuenta(p1, "Ahorro", "001");

        Persona p2 = new Persona.Builder().setNombre("Edward").setDni("12345678").setDomicilio("Calle 555").build();
        admin.registrarCuenta(p2, "Corriente", "002");

        int op;
        do {
            System.out.println("\n==== BANCO ====");
            System.out.println("1. Nueva Cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Transferir");
            System.out.println("4. Balance");
            System.out.println("5. Retirar");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            op = entrada.nextInt(); entrada.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nombre: "); String n = entrada.nextLine();
                    System.out.print("DNI: "); String d = entrada.nextLine();
                    System.out.print("Calle: "); String dom = entrada.nextLine();
                    System.out.print("Tipo: "); String t = entrada.nextLine();
                    System.out.print("CBU: "); String c = entrada.nextLine();

                    Persona nuevaP = new Persona.Builder().setNombre(n).setDni(d).setDomicilio(dom).build();

                    admin.registrarCuenta(nuevaP, t,c);
                    break;
                case 2:
                    System.out.print("CBU: "); String cb = entrada.nextLine();
                    Cuenta cu = admin.buscar(cb);
                    if (cu != null) {
                        System.out.print("Monto: ");
                        cu.depositar(entrada.nextDouble());
                    }
                    break;
                case 3:
                    System.out.print("CBU Origen: "); String ori = entrada.next();
                    System.out.print("CBU Destino: "); String des = entrada.next();
                    System.out.print("Monto: ");
                    admin.transferir(ori, des, entrada.nextDouble());
                    break;
                case 4:
                    admin.balance();
                    break;
                case 5: // Opción de Retirar.4
                    System.out.print("CBU: ");
                    String cbuBusqueda = entrada.nextLine();

                    // El HashMap busca el objeto. Si no existe, 'cuentaExtraccion' será null.
                    Cuenta cuentaExtraccion = admin.buscar(cbuBusqueda);

                    if (cuentaExtraccion != null) {
                        // SI EXISTE: Pedimos el monto
                        System.out.print("Monto: ");
                        double monto = entrada.nextDouble();
                        entrada.nextLine(); // Limpieza de buffer

                        cuentaExtraccion.retirar(monto); // La clase Cuenta valida si tiene saldo
                    } else {
                        // NO EXISTE: Acá manejamos el error que pediste
                        System.out.println(">>> Error: El CBU [" + cbuBusqueda + "] no existe en el sistema.");
                    }
                    break;

            }
        } while (op != 0);
    }
}