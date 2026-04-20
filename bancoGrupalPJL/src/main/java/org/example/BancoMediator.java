package org.example;

import java.util.ArrayList;
import java.util.List;

public class BancoMediator {

    private List<BancoAdapter> bancos = new ArrayList<>();

    public void agregarBanco(BancoAdapter banco) {
        bancos.add(banco);
    }

    public void mostrarTodos() {
        for (BancoAdapter b : bancos) {
            System.out.println("\n=== " + b.getNombre() + " ===");
            b.mostrarCuentas();
        }
    }

    public BancoAdapter buscarBancoPorCuenta(int dni) {
        for (BancoAdapter b : bancos) {
            if (b.existeCuenta(dni)) return b;
        }
        return null;
    }

    public void transferir(int dniOrigen, int dniDestino, double monto) {

        BancoAdapter origen  = buscarBancoPorCuenta(dniOrigen);
        BancoAdapter destino = buscarBancoPorCuenta(dniDestino);

        if (origen == null || destino == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        if (!origen.retirar(dniOrigen, monto)) {
            System.out.println("No se pudo retirar el dinero");
            return;
        }

        destino.depositar(dniDestino, monto);

        System.out.println("Transferencia realizada entre bancos");
    }
}