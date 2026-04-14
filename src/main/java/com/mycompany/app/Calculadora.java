package com.mycompany.app;

public class Calculadora {

    public int soma(int a, int b) {
        return a + b;
    }

    public int subtracao(int a, int b) {
        return a - b;
    }

    public int multiplicacao(int a, int b) {
        return a * b;
    }

    public double dividir(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a / b;
    }

    public int dividir_inteiro(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return (int) a / b;
    }

    public int resto_divisao_inteiro(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a % b;
    }
}
