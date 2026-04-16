import java.util.ArrayList;
import java.util.List;

public class Banco {



    String nombre;
    List<Sucursal> sucursales;


    public Banco(String nombre) {
        this.nombre     = nombre;
        this.sucursales = new ArrayList<Sucursal>();

        // Las sucursales vienen predefinidas — no se crean desde el menú
        sucursales.add(new Sucursal("Sucursal 1"));
        sucursales.add(new Sucursal("Sucursal 2"));
        sucursales.add(new Sucursal("Sucursal 3"));
    }


    // Busca una sucursal por nombre dentro del banco
    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }

    // Busca una cuenta por DNI en todas las sucursales
    public Cuenta buscarCuenta(String dni) {
        for (Sucursal s : sucursales) {
            Cuenta c = s.buscarCuenta(dni);   // Le pregunta a cada sucursal
            if (c != null) return c;           // Si la encontró, la devuelve
        }
        return null;   // No se encontró en ninguna sucursal
    }



    // Muestra solo los nombres de las sucursales, sin info de cuentas
    public void mostrarSoloSucursales() {
        System.out.println("===== " + nombre + " =====");
        System.out.println("Sucursales disponibles:");
        for (Sucursal s : sucursales) {
            System.out.println("  • " + s.nombre);
        }
    }

    // Muestra toda la estructura del banco: sucursales y sus cuentas
    public void mostrarTodo() {
        System.out.println("===== " + nombre + " =====");

        if (sucursales.isEmpty()) {
            System.out.println("Sin sucursales registradas.");
            return;
        }

        // Delega la impresión a cada sucursal que a su vez delega a cada cuenta
        for (Sucursal s : sucursales) {
            s.mostrar();
        }
    }
}