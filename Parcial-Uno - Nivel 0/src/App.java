import java.util.List;
import java.util.Scanner;

import domain.Ahorro;
import domain.Corriente;
import domain.Cuenta;
import service.ServiceCuenta;

public class App {
    public static void main(String[] args) {
        ServiceCuenta service = new ServiceCuenta();
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        
        System.out.println("Total cuentas cargadas: " + service.obtenerCuentas().size());

        do {
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Listar cuentas Ahorro");
            System.out.println("2. Listar cuentas Corriente");
            System.out.println("3. Crear cuenta Ahorro");
            System.out.println("4. Crear cuenta Corriente");
            System.out.println("5. Buscar cuenta por número");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada no válida. Ingrese un número.");
                sc.nextLine();
                continue;
            }
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    List<Ahorro> ahorros = service.listarAhorros();
                    System.out.println("Cuentas Ahorro (total: " + ahorros.size() + "):");
                    if (ahorros.isEmpty()) System.out.println("No hay cuentas Ahorro.");
                    else for (Ahorro a : ahorros) System.out.println(a);
                    break;

                case 2:
                    List<Corriente> corrientes = service.listarCorrientes();
                    System.out.println("Cuentas Corriente (total: " + corrientes.size() + "):");
                    if (corrientes.isEmpty()) System.out.println("No hay cuentas Corriente.");
                    else for (Corriente c : corrientes) System.out.println(c);
                    break;

                case 3:
                    System.out.print("Número de cuenta: ");
                    String numA = sc.nextLine();
                    System.out.print("DNI: ");
                    long dniA = Long.parseLong(sc.nextLine());
                    System.out.print("Saldo: ");
                    double saldoA = Double.parseDouble(sc.nextLine());
                    System.out.print("Fecha creación: ");
                    String fecha = sc.nextLine();
                    service.crearAhorro(new Ahorro(numA, dniA, saldoA, fecha));
                    System.out.println("Cuenta Ahorro creada.");
                    break;

                case 4:
                    System.out.print("Número de cuenta: ");
                    String numC = sc.nextLine();
                    System.out.print("DNI: ");
                    long dniC = Long.parseLong(sc.nextLine());
                    System.out.print("Saldo: ");
                    double saldoC = Double.parseDouble(sc.nextLine());
                    System.out.print("Impuesto (ej. 0.3): ");
                    double imp = Double.parseDouble(sc.nextLine());
                    service.crearCorriente(new Corriente(numC, dniC, saldoC, imp));
                    System.out.println("Cuenta Corriente creada.");
                    break;

                case 5:
                    System.out.print("Ingrese número de cuenta a buscar: ");
                    String buscar = sc.nextLine().trim();
                    Cuenta encontrada = service.buscarPorNumeroCuenta(buscar);
                    if (encontrada != null) {
                        System.out.println("Cuenta encontrada: " + encontrada);
                    } else {
                        System.out.println("No existe cuenta con número: " + buscar);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}