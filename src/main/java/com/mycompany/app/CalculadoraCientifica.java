package com.mycompany.app;

public class CalculadoraCientifica extends Calculadora {

    public double raiz_quadrada(int a) {
        if (a < 0) {
            return 0;
        }
        return Math.sqrt(a);
    }

    public double potencia(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return Math.pow(a, b);
    }

    public long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
}
