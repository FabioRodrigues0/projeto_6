package com.mycompany.app.scenes;

import com.mycompany.app.models.CalculadoraCientifica;
import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;
import java.util.List;

/**
 * Cena da calculadora científica.
 * Estende {@link SceneCalc} com botões de raiz quadrada (√x), potência (x^y) e fatorial (n!).
 */
public class SceneCalcCientifica extends SceneCalc {

    private final CalculadoraCientifica calc = new CalculadoraCientifica();

    /**
     * Cria a cena científica partilhando os estados fornecidos.
     *
     * @param display     estado do valor actual no display
     * @param prevDisplay estado do valor anterior
     * @param operator    estado do operador pendente
     */
    public SceneCalcCientifica(
        State<String> display,
        State<String> prevDisplay,
        State<String> operator
    ) {
        super(display, prevDisplay, operator);
    }

    /**
     * Sobrepõe o cálculo para tratar o operador de potência ({@code ^}).
     * Os restantes operadores são delegados ao pai.
     *
     * @return resultado do cálculo
     */
    @Override
    protected double calculate() {
        if (operator.get().equals("^")) {
            return calc.potencia(
                Integer.parseInt(prevDisplay.get()),
                Integer.parseInt(display.get())
            );
        }
        return super.calculate();
    }

    /**
     * Devolve a linha extra com os botões científicos: √x, x^y e n!.
     *
     * @return lista com a linha de botões científicos
     */
    @Override
    protected List<Component> extraRows() {
        return List.of(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("√x")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            if (!display.get().isEmpty()) {
                                int n = (int) Double.parseDouble(display.get());
                                display.set(String.valueOf(calc.raiz_quadrada(n)));
                            }
                        }),
                    new Button("x^y")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            prevDisplay.set(display.get());
                            display.set("");
                            if (!operator.get().isEmpty()) {
                                calculate();
                            }
                            operator.set("^");
                        }),
                    new Button("n!")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            if (!display.get().isEmpty()) {
                                int n = (int) Double.parseDouble(display.get());
                                display.set(String.valueOf(calc.factorial(n)));
                            }
                        })
                )
        );
    }
}
