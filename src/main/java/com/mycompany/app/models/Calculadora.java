package com.mycompany.app.models;

/**
 * Calculadora básica com operações aritméticas fundamentais.
 */
public class Calculadora {

    /**
     * Soma dois inteiros.
     *
     * @param a primeiro operando
     * @param b segundo operando
     * @return soma de {@code a} e {@code b}
     */
    public int soma(int a, int b) {
        return a + b;
    }

    /**
     * Subtrai {@code b} de {@code a}.
     *
     * @param a minuendo
     * @param b subtraendo
     * @return diferença entre {@code a} e {@code b}
     */
    public int subtracao(int a, int b) {
        return a - b;
    }

    /**
     * Multiplica dois inteiros.
     *
     * @param a primeiro fator
     * @param b segundo fator
     * @return produto de {@code a} e {@code b}
     */
    public int multiplicacao(int a, int b) {
        return a * b;
    }

    /**
     * Divide {@code a} por {@code b} com resultado decimal.
     * Retorna {@code 0} se qualquer operando for zero.
     *
     * @param a dividendo
     * @param b divisor
     * @return quociente decimal, ou {@code 0} se {@code a} ou {@code b} for zero
     */
    public double dividir(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a / b;
    }

    /**
     * Divide {@code a} por {@code b} com resultado inteiro (truncado).
     * Retorna {@code 0} se qualquer operando for zero.
     *
     * @param a dividendo
     * @param b divisor
     * @return quociente inteiro, ou {@code 0} se {@code a} ou {@code b} for zero
     */
    public int dividir_inteiro(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return (int) a / b;
    }

    /**
     * Calcula o resto da divisão inteira de {@code a} por {@code b}.
     * Retorna {@code 0} se qualquer operando for zero.
     *
     * @param a dividendo
     * @param b divisor
     * @return resto da divisão, ou {@code 0} se {@code a} ou {@code b} for zero
     */
    public int resto_divisao_inteiro(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a % b;
    }
}
