package com.mycompany.app;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;

import java.util.List;

public class SceneCalcCientifica extends SceneCalc {

    private final CalculadoraCientifica calc = new CalculadoraCientifica();

    public SceneCalcCientifica(State<String> display, State<String> prevDisplay, State<String> operator) {
        super(display, prevDisplay, operator);
    }

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
