package com.mycompany.app.models;

/**
 * Calculadora científica que estende {@link Calculadora} com operações avançadas:
 * raiz quadrada, potência e fatorial.
 */
public class CalculadoraCientifica extends Calculadora {

    /**
     * Calcula a raiz quadrada de {@code a}.
     * Retorna {@code 0} para valores negativos.
     *
     * @param a radicando (deve ser não-negativo)
     * @return raiz quadrada de {@code a}, ou {@code 0} se {@code a < 0}
     */
    public double raiz_quadrada(int a) {
        if (a < 0) {
            return 0;
        }
        return Math.sqrt(a);
    }

    /**
     * Eleva {@code a} à potência {@code b}.
     * Retorna {@code 0} quando o expoente é zero (convenção da aplicação).
     *
     * @param a base
     * @param b expoente
     * @return {@code a} elevado a {@code b}, ou {@code 0} se {@code b == 0}
     */
    public double potencia(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return Math.pow(a, b);
    }

    /**
     * Calcula o fatorial de {@code n} de forma recursiva.
     * Para {@code n <= 1} retorna {@code 1}.
     *
     * @param n número inteiro não-negativo
     * @return fatorial de {@code n}
     */
    public long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}
