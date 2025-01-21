package org.example;

public class Gasto {
    private double vacaciones;
    private double alquiler;
    private double irpf;
    private double vicios;

    public Gasto() {
        this.vacaciones = 0.0;
        this.alquiler = 0.0;
        this.irpf = 0.0;
        this.vicios = 0.0;
    }

    public void agregarGasto(String tipo, double cantidad) {
        switch (tipo.toLowerCase()) {
            case "vacaciones":
                vacaciones += cantidad;
                break;
            case "alquiler":
                alquiler += cantidad;
                break;
            case "irpf":
                irpf += cantidad;
                break;
            case "vicios":
                vicios += cantidad;
                break;
            default:
                System.out.println("Tipo de gasto no reconocido.");
        }
    }

    public double calcularTotalGastos() {
        return vacaciones + alquiler + irpf + vicios;
    }

    public void mostrarGastos() {
        System.out.println("Gastos Totales:");
        System.out.println("Vacaciones: " + vacaciones);
        System.out.println("Alquiler: " + alquiler);
        System.out.println("IRPF: " + irpf);
        System.out.println("Vicios: " + vicios);
    }
}

