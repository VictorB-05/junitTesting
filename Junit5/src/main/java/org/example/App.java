package org.example;

import java.util.Scanner;

public class App {

    // Función para validar si el DNI tiene 8 dígitos seguidos de una letra
    public static boolean esDniValido(String dni) {
        if (dni.length() != 9) {
            return false;  // El DNI debe tener exactamente 9 caracteres
        }
        // Comprobar que los primeros 8 caracteres son números
        String numeros = dni.substring(0, 8);
        if (!numeros.matches("[0-9]{8}")) {
            return false;
        }
        // Comprobar que el último carácter es una letra mayúscula
        char letra = dni.charAt(8);
        return Character.isUpperCase(letra);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database bdManager = new Database();
        Gasto gasto = new Gasto();
        Ingreso ingreso = new Ingreso();
        Usuario usuario;

        String dni = null;
        boolean dniValido = false;

        // Solicitar el DNI y validarlo
        while (!dniValido) {
            System.out.print("Usuario, introduzca su DNI (8 dígitos seguidos de una letra): ");
            dni = scanner.nextLine();

            if (esDniValido(dni)) {
                dniValido = true;
            } else {
                System.out.println("DNI no válido. Asegúrese de que el DNI tenga 8 dígitos seguidos de una letra mayúscula.");
            }
        }

        // Verificar si el usuario está registrado en la base de datos
        if (!bdManager.autenticarUsuario(dni)) {
            System.out.println("DNI no registrado. Registrando usuario...");
            bdManager.registrarUsuario(dni);
        }
        usuario = new Usuario(dni);

        // Obtener el saldo inicial desde la base de datos
        double saldo = bdManager.obtenerSaldo(dni);

        String opcion;

        do {
            System.out.println("\n¿Qué desea realizar?");
            System.out.println("1. Gasto");
            System.out.println("2. Ingreso");
            System.out.println("3. Ver saldo y gastos");
            System.out.println("4. Salir");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Seleccione el tipo de gasto (vacaciones, alquiler, irpf, vicios):");
                    String tipoGasto = scanner.nextLine();
                    System.out.print("Ingrese la cantidad: ");
                    double cantidadGasto = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar buffer
                    gasto.agregarGasto(tipoGasto, cantidadGasto);
                    saldo -= cantidadGasto;
                    // Actualizar saldo en la base de datos
                    bdManager.actualizarSaldo(dni, saldo);
                    break;
                case "2":
                    System.out.println("Seleccione el tipo de ingreso (nómina, venta segunda mano):");
                    String tipoIngreso = scanner.nextLine();
                    System.out.print("Ingrese la cantidad: ");
                    double cantidadIngreso = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar buffer
                    ingreso.agregarIngreso(tipoIngreso, cantidadIngreso);
                    saldo += cantidadIngreso;
                    // Actualizar saldo en la base de datos
                    bdManager.actualizarSaldo(dni, saldo);
                    break;
                case "3":
                    System.out.println("Saldo actual: " + saldo);
                    gasto.mostrarGastos();
                    ingreso.mostrarIngresos();
                    break;
                case "4":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (!opcion.equals("4"));

        bdManager.cerrarConexion();
        scanner.close();
    }
}
