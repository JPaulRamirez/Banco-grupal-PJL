package org.example;

import java.util.HashMap;
import java.util.Map;

public class MediadorBancario {
    Map<String, IBanco> bancos = new HashMap<>();

    public void registrarBanco(IBanco banco) {
        bancos.put(banco.getNombre().toLowerCase(), banco);
    }

    public String transferir(String bancoOrigen, String sucursalOrigen, int dniOrigen,
                             String bancoDestino, String sucursalDestino, int dniDestino,
                             double monto) {
        if (monto <= 0) {
            return "Monto invalido";
        }

        IBanco origen = bancos.get(bancoOrigen.toLowerCase());
        IBanco destino = bancos.get(bancoDestino.toLowerCase());

        if (origen == null || destino == null) {
            return "Uno de los bancos no existe";
        }

        if (!origen.existeCliente(sucursalOrigen, dniOrigen)) {
            return "No existe el cliente origen";
        }

        if (!destino.existeCliente(sucursalDestino, dniDestino)) {
            return "No existe el cliente destino";
        }
        
        origen.debitar(sucursalOrigen, dniOrigen, monto);
        destino.acreditar(sucursalDestino, dniDestino, monto);


        return "Transferencia realizada";
    }

    public double consultarSaldo(String banco, String sucursal, int dni) {
        IBanco bancoElegido = bancos.get(banco.toLowerCase());

        if(bancoElegido==null)
        {
            System.out.println("El banco no existe.");
            return 0;
        }
        return bancoElegido.consultarSaldo(sucursal, dni);
    }
}
