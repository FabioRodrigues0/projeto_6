package com.mycompany.app;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.TextField;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.style.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SceneCalc {

    protected final State<String> display;
    protected final State<String> prevDisplay;
    protected final State<String> operator;

    Modifier maxWidth = new Modifier().fillMaxWidth();

    public SceneCalc(State<String> display, State<String> prevDisplay, State<String> operator) {
        this.display = display;
        this.prevDisplay = prevDisplay;
        this.operator = operator;
    }

    protected List<Component> extraRows() {
        return List.of();
    }

    protected double calculate() {
        Calculadora calc = new Calculadora();
        return switch (operator.get()) {
            case "+" -> calc.soma(
                Integer.parseInt(prevDisplay.get()),
                Integer.parseInt(display.get())
            );
            case "-" -> calc.subtracao(
                Integer.parseInt(prevDisplay.get()),
                Integer.parseInt(display.get())
            );
            case "*" -> calc.multiplicacao(
                Integer.parseInt(prevDisplay.get()),
                Integer.parseInt(display.get())
            );
            case "/" -> calc.dividir(
                Integer.parseInt(prevDisplay.get()),
                Integer.parseInt(display.get())
            );
            case "//" -> calc.dividir_inteiro(
                Integer.parseInt(prevDisplay.get()),
                Integer.parseInt(display.get())
            );
            case "%" -> calc.resto_divisao_inteiro(
                Integer.parseInt(prevDisplay.get()),
                Integer.parseInt(display.get())
            );
            default -> 0;
        };
    }

    public Component scene() {
        List<Component> children = new ArrayList<>();
        children.add(new TextField().bindTo(display).modifier(maxWidth));
        children.addAll(extraRows());
        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("Resto divisão")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            prevDisplay.set(display.get());
                            display.set("");
                            if (!operator.get().isEmpty()) {
                                calculate();
                            }
                            operator.set("%");
                        }),
                    new Button("Divisão inteira")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            prevDisplay.set(display.get());
                            display.set("");
                            if (!operator.get().isEmpty()) {
                                calculate();
                            }
                            operator.set("//");
                        }),
                    new Button("AC")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set("");
                            prevDisplay.set("");
                            operator.set("");
                        })
                )
        );
        children.add(
            new Row()
                .padding(0)
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("1")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "1");
                        }),
                    new Button("2")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "2");
                        }),
                    new Button("3")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "3");
                        }),
                    new Button("+")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            prevDisplay.set(display.get());
                            display.set("");
                            if (!operator.get().isEmpty()) {
                                calculate();
                            }
                            operator.set("+");
                        })
                )
        );
        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("4")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "4");
                        }),
                    new Button("5")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "5");
                        }),
                    new Button("6")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "6");
                        }),
                    new Button("-")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            prevDisplay.set(display.get());
                            display.set("");
                            if (!operator.get().isEmpty()) {
                                calculate();
                            }
                            operator.set("-");
                        })
                )
        );
        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("7")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "7");
                        }),
                    new Button("8")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "8");
                        }),
                    new Button("9")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "9");
                        }),
                    new Button("*")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            prevDisplay.set(display.get());
                            display.set("");
                            if (!operator.get().isEmpty()) {
                                calculate();
                            }
                            operator.set("*");
                        })
                )
        );
        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("0")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            display.set(display.get() + "0");
                        }),
                    new Button(".")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            if (!display.get().contains(".")) {
                                display.set(display.get() + ".");
                            }
                        }),
                    new Button("=")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            if (!operator.get().isEmpty()) {
                                double result = calculate();
                                display.set(String.valueOf(result));
                                operator.set("");
                            }
                        }),
                    new Button("/")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            prevDisplay.set(display.get());
                            display.set("");
                            if (!operator.get().isEmpty()) {
                                calculate();
                            }
                            operator.set("/");
                        })
                )
        );

        return new Column()
            .padding(0)
            .gap(2)
            .modifier(maxWidth)
            .children(children.toArray(new Component[0]));
    }
}
