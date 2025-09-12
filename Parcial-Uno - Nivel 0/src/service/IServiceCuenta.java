package service;

import java.util.List;
import domain.Ahorro;
import domain.Corriente;
import domain.Cuenta;

public interface IServiceCuenta {
    List<Cuenta> obtenerCuentas();
    List<Ahorro> listarAhorros();
    List<Corriente> listarCorrientes();
    void crearAhorro(Ahorro ahorro);
    void crearCorriente(Corriente corriente);
    Cuenta buscarPorNumeroCuenta(String numeroCuenta);
}
