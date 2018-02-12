/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmica2epd1;

import java.util.*;

/**
 *
 * @author pablo
 */
public class EPD1EJ1MIO {

    public static void main(String[] args) {

        int max = binaryToDecimal(enfriamientoSimuladoMio());
        System.out.println("Maximo encontrado: " + max);
    }

    private static int[] enfriamientoSimuladoMio() {
        int[] candidato = {0, 0, 0, 0, 0};
        int[] sol = Arrays.copyOf(candidato, candidato.length);
        int[] historico = {0, 0, 0, 0, 0};
        double temp = 300;
        System.out.println("INICIO BUSQUEDA");

        int i = 0;
        int iteraciones = 0;
        while (i < 5 && temp > 10) {
            iteraciones++;
            int[] vecino = new int[5];

            vecino = cambia(candidato, i);
            if (Arrays.equals(vecino, historico) || Arrays.equals(candidato, historico)) {
                pateaCodigo(vecino, candidato);
            }
            System.out.println("nuevo vecino -----  " + Arrays.toString(vecino) + " num "
                    + binaryToDecimal(vecino) + " valor " + valor(vecino));

            double df = valor(candidato) - valor(vecino);

            System.out.println("candidato-vecino ------ " + df);
            
            if (df < 0 || valor(vecino) > (Math.exp(df / temp))) {//nuevo candidato

                System.out.println("nuevo candidato -----> " + binaryToDecimal(vecino));
                historico = candidato;
                candidato = Arrays.copyOf(vecino, vecino.length);
                i = 0;

                if (valor(sol) < valor(candidato)) {
                    sol = candidato;
                }
            } else {
                i++;
            }
            temp = temp * 0.9;
            System.out.println("\n\n\nTEMPERATURA  " + temp);

            //temp = temp * 0.01;
            //System.out.println("\n\n\n" + temp + "\n\n\n");
        }
        System.out.println("ITERACIONES---------" + iteraciones);
        return sol;
    }

    public static int binaryToDecimal(int[] binario) {
        int res = 0;
        int exp = 0;
        for (int i = binario.length - 1; i >= 0; i--) {
            if (binario[i] != 0) {
                res += Math.pow(2.0, exp);
            }
            exp++;
        }
        return res;
    }

    public static int[] cambia(int[] candidato, int index) {
        int[] res = Arrays.copyOf(candidato, candidato.length);
        if (res[index] == 0) {
            res[index] = 1;
        } else {
            res[index] = 0;
        }

        return res;
    }

    public static double valor(int[] sol) {
        int n = binaryToDecimal(sol);

        return Math.pow(n, 3) - 60 * Math.pow(n, 2) + 900 * n + 100;
    }

    public static int[] cambiaAleatorio() {
        int[] res = new int[5];
        for (int i = 0; i < 5; i++) {
            res[i] = (int) (2 * Math.random());

        }

        System.out.println("\t\t\tRAMDOM  " + Arrays.toString(res) + "----" + binaryToDecimal(res));
        return res;
    }

    public static void pateaCodigo(int[] a, int[] b) {
        System.out.println("---------------PEGANDO PATADAS--------------");
        a = cambiaAleatorio();
        b = cambiaAleatorio();
    }
}
