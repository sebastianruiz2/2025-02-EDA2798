import java.util.Scanner;

import model.Account;
import services.AccountService;

public class App {
    private static AccountService accountService = new AccountService();

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runCrudMenu(sc, "Account");
                        break;
                    case "2":
                        runCrudMenu(sc, "Balance");
                        break;
                    case "3":
                        runCrudMenu(sc, "Loans");
                        break;
                    case "4":
                        runCrudMenu(sc, "Cards");
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Intente de nuevo.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void runCrudMenu(Scanner sc, String entityName) {
        boolean back = false;
        while (!back) {
            printCrudMenu(entityName);
            String opt = sc.nextLine().trim();

            switch (opt) {
                case "1": // CREATE
                    System.out.println("[" + entityName + "] Crear nueva cuenta");
                    System.out.print("Número de cuenta: ");
                    String accountNumber = sc.nextLine().trim();
                    System.out.print("Nombre del titular: ");
                    String holderName = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Teléfono: ");
                    String phone = sc.nextLine().trim();
                    System.out.print("Tipo de cuenta (Savings/Checking): ");
                    String accountType = sc.nextLine().trim();
                    System.out.print("Dirección: ");
                    String address = sc.nextLine().trim();

                    Account newAccount = new Account(accountNumber, holderName, email, phone, accountType, address);
                    accountService.save(newAccount);
                    System.out.println("✅ Cuenta creada exitosamente.");
                    break;

                case "2": // READ BY ID
                    System.out.print("Ingrese el número de cuenta a buscar: ");
                    String id = sc.nextLine().trim();
                    accountService.findById(id).ifPresentOrElse(
                        acc -> System.out.println("✅ Encontrado: " + acc),
                        () -> System.out.println("❌ No se encontró ninguna cuenta con id=" + id)
                    );
                    break;

                case "3": // LIST ALL
                    System.out.println("\n📋 Listado de cuentas registradas:");
                    accountService.findAll().forEach(System.out::println);
                    break;

                case "4": // UPDATE
                    System.out.print("Ingrese el número de cuenta que desea actualizar: ");
                    String idUp = sc.nextLine().trim();
                    accountService.findById(idUp).ifPresentOrElse(existing -> {
                        System.out.println("Cuenta actual: " + existing);
                        System.out.print("Nuevo nombre del titular: ");
                        String newHolder = sc.nextLine().trim();
                        System.out.print("Nuevo email: ");
                        String newEmail = sc.nextLine().trim();
                        System.out.print("Nuevo teléfono: ");
                        String newPhone = sc.nextLine().trim();
                        System.out.print("Nuevo tipo de cuenta: ");
                        String newType = sc.nextLine().trim();
                        System.out.print("Nueva dirección: ");
                        String newAddress = sc.nextLine().trim();

                        Account updated = new Account(idUp, newHolder, newEmail, newPhone, newType, newAddress);
                        accountService.save(updated);
                        System.out.println("✅ Cuenta actualizada correctamente.");
                    }, () -> System.out.println("❌ No se encontró la cuenta con número " + idUp));
                    break;

                case "5": // DELETE
                    System.out.print("Ingrese el número de cuenta que desea eliminar: ");
                    String idDel = sc.nextLine().trim();
                    boolean deleted = accountService.deleteById(idDel);
                    if (deleted)
                        System.out.println("✅ Cuenta eliminada correctamente.");
                    else
                        System.out.println("❌ No se encontró una cuenta con ese número.");
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }
}