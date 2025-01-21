package org.example;

public class Ingreso {
    private double nomina;
    private double ventaSegundaMano;

    public Ingreso() {
        this.nomina = 0.0;
        this.ventaSegundaMano = 0.0;
    }

    public void agregarIngreso(String tipo, double cantidad) {
        switch (tipo.toLowerCase()) {
            case "nomina":
                nomina += cantidad;
                break;
            case "venta segunda mano":
                ventaSegundaMano += cantidad;
                break;
            default:
                System.out.println("Tipo de ingreso no reconocido.");
        }
    }

    public double calcularTotalIngresos() {
        return nomina + ventaSegundaMano;
    }

    public void mostrarIngresos() {
        System.out.println("Ingresos Totales:");
        System.out.println("Nómina: " + nomina);
        System.out.println("Venta en páginas de segunda mano: " + ventaSegundaMano);
    }
}

