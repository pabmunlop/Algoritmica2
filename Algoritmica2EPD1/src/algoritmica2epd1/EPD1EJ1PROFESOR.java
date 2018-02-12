/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmica2epd1;



 import java.util.Arrays;

public class EPD1EJ1PROFESOR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int max = (int) binaryToDecimal(enfriamientoSimulado());
        System.out.println("Maximo encontrado: " + max);
    }

    private static int[] enfriamientoSimulado() {
        int[] bestSolution = {0, 0, 0, 0, 0};
        int[] solutions = {0, 0, 0, 0, 0};
        double mejorValor = Integer.MAX_VALUE;
        double temp = 300;
        while (temp >= 10) {
            int nuevo = (int) (Math.random() * solutions.length);
            permuta(solutions, nuevo);
            int[] vecino = Arrays.copyOf(solutions, solutions.length);
            permuta(vecino, (int) (Math.random() * solutions.length));
            double valorFuncion = funcionCalc(vecino);
            if (aceptarProbabilidad(valorFuncion, mejorValor, temp) > Math.random()) {
                mejorValor = valorFuncion;
            }
            if (mejorValor > funcionCalc(bestSolution)) {
                bestSolution = Arrays.copyOf(vecino, vecino.length);
            }
            temp -= 0.1 * temp;
        }
        return bestSolution;
    }

    private static void permuta(int[] sVecino, int n) {
        if (sVecino[n] == 1) {
            sVecino[n] = 0;
        } else {
            sVecino[n] = 1;
        }
    }

    private static double funcionCalc(int[] array) {
        int n = binaryToDecimal(array);
        return Math.pow(n, 3) - 60 * Math.pow(n, 2) + 900 * n + 100;
    }

    private static double aceptarProbabilidad(double valorFuncion, double mejorValor, double temperatura) {
        if (valorFuncion > mejorValor) {
            return 1.0;
        }
        return Math.exp((mejorValor - valorFuncion) / temperatura);
    }

    private static int binaryToDecimal(int[] array) {
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                n += Math.pow(2, i);
            }
        }
        return n;
    }

}

