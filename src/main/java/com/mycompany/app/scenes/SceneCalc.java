package com.mycompany.app.scenes;

import com.mycompany.app.models.Calculadora;
import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.TextField;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.style.Modifier;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyCode;

/**
 * Scene da calculadora básica.
 * Apresenta um campo de entrada, botões numéricos e operadores aritméticos fundamentais.
 * Pode ser estendida para adicionar linhas extra de botões via {@link #extraRows()}.
 */
public class SceneCalc {

    /** Valor actualmente visível no display. */
    protected final State<String> display;
    /** Valor do operando anterior (antes de seleccionar um operador). */
    protected final State<String> prevDisplay;
    /** Operador aritmético pendente (ex.: "+", "-", "*", "/"). */
    protected final State<String> operator;

    Modifier maxWidth = new Modifier().fillMaxWidth();

    /**
     * Cria uma scene de calculadora partilhando os estados fornecidos.
     *
     * @param display     estado do valor actual no display
     * @param prevDisplay estado do valor anterior
     * @param operator    estado do operador pendente
     */
    public SceneCalc(State<String> display, State<String> prevDisplay, State<String> operator) {
        this.display = display;
        this.prevDisplay = prevDisplay;
        this.operator = operator;
    }

    /**
     * Devolve linhas de botões adicionais a inserir acima dos botões numéricos.
     * Por omissão, não adiciona linhas. As subclasses sobrepõem este método
     * para incluir operações extra (ex.: científicas).
     *
     * @return lista de componentes de linha extra
     */
    protected List<Component> extraRows() {
        return List.of();
    }

    /**
     * Converte uma string para {@code double}, devolvendo {@code 0} em caso de erro.
     *
     * @param s string a converter
     * @return valor numérico, ou {@code 0} se inválido
     */
    private double parseVal(String s) {
        if (s == null || s.isEmpty() || s.equals(".")) return 0;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Formata um resultado numérico: exibe como inteiro quando não há parte decimal.
     *
     * @param result valor a formatar
     * @return representação textual do resultado
     */
    private String formatResult(double result) {
        // Mostra inteiro se nao tiver parte decimal
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }

    /**
     * Executa o cálculo pendente com base nos valores de {@code prevDisplay},
     * {@code display} e {@code operator}.
     *
     * @return resultado da operação, ou {@code 0} se o operador for desconhecido
     */
    protected double calculate() {
        Calculadora calc = new Calculadora();
        double a = parseVal(prevDisplay.get());
        double b = parseVal(display.get());
        return switch (operator.get()) {
            case "+" -> calc.soma((int) a, (int) b);
            case "-" -> calc.subtracao((int) a, (int) b);
            case "*" -> calc.multiplicacao((int) a, (int) b);
            case "/" -> calc.dividir((int) a, (int) b);
            case "//" -> calc.dividir_inteiro((int) a, (int) b);
            case "%" -> calc.resto_divisao_inteiro((int) a, (int) b);
            default -> 0;
        };
    }

    /**
     * Regista um operador e, se já existir uma operação pendente, calcula-a primeiro.
     * Move o valor actual para {@code prevDisplay} e limpa o display.
     *
     * @param op operador a aplicar (ex.: "+", "-", "*", "/", "//", "%")
     */
    protected void handleOperator(String op) {
        // Se ja temos operador e valor anterior e valor atual, calcula antes
        if (!operator.get().isEmpty() && !display.get().isEmpty()) {
            double result = calculate();
            prevDisplay.set(formatResult(result));
        } else {
            prevDisplay.set(display.get());
        }
        display.set("");
        operator.set(op);
    }

    /**
     * Finaliza a operação pendente, exibe o resultado no display e limpa o estado.
     * Não faz nada se não houver operador ou valor introduzido.
     */
    protected void handleEquals() {
        if (!operator.get().isEmpty() && !display.get().isEmpty()) {
            double result = calculate();
            display.set(formatResult(result));
            prevDisplay.set("");
            operator.set("");
        }
    }

    /**
     * Constrói e devolve o componente visual da cena da calculadora básica.
     * Inclui o campo de display, as linhas extra de {@link #extraRows()} e
     * os botões numéricos e de operadores.
     *
     * @return componente raiz da cena
     */
    public Component scene() {
        List<Component> children = new ArrayList<>();

        children.add(
            new TextField()
                .bindTo(display)
                .modifier(maxWidth)
                .inputFilter("[0-9.]")
                .autoFocus()
                .hideCursor()
                .onKeyPressed(e -> {
                    switch (e.getText()) {
                        case "+" -> handleOperator("+");
                        case "-" -> handleOperator("-");
                        case "*" -> handleOperator("*");
                        case "/" -> handleOperator("/");
                        case "%" -> handleOperator("%");
                    }
                    if (e.getCode() == KeyCode.ENTER || e.getText().equals("=")) {
                        handleEquals();
                    }
                    if (e.getCode() == KeyCode.BACK_SPACE && !display.get().isEmpty()) {
                        display.set(display.get().substring(0, display.get().length() - 1));
                    }
                })
        );

        children.addAll(extraRows());

        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("Resto divisão")
                        .modifier(maxWidth)
                        .onClick(() -> handleOperator("%")),
                    new Button("Divisão inteira")
                        .modifier(maxWidth)
                        .onClick(() -> handleOperator("//")),
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
                        .onClick(() -> display.set(display.get() + "1")),
                    new Button("2")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "2")),
                    new Button("3")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "3")),
                    new Button("+").modifier(maxWidth).onClick(() -> handleOperator("+"))
                )
        );
        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("4")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "4")),
                    new Button("5")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "5")),
                    new Button("6")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "6")),
                    new Button("-").modifier(maxWidth).onClick(() -> handleOperator("-"))
                )
        );
        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("7")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "7")),
                    new Button("8")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "8")),
                    new Button("9")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "9")),
                    new Button("*").modifier(maxWidth).onClick(() -> handleOperator("*"))
                )
        );
        children.add(
            new Row()
                .gap(2)
                .modifier(maxWidth)
                .children(
                    new Button("0")
                        .modifier(maxWidth)
                        .onClick(() -> display.set(display.get() + "0")),
                    new Button(".")
                        .modifier(maxWidth)
                        .onClick(() -> {
                            if (!display.get().contains(".")) display.set(display.get() + ".");
                        }),
                    new Button("=").modifier(maxWidth).onClick(this::handleEquals),
                    new Button("/").modifier(maxWidth).onClick(() -> handleOperator("/"))
                )
        );

        return new Column()
            .padding(0)
            .gap(2)
            .modifier(maxWidth)
            .children(children.toArray(new Component[0]));
    }
}
