package com.mycompany.app;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.Spacer;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.style.BricksTheme;
import fabiorodrigues.bricks.style.Modifier;

public class App extends BricksApplication {

    // App.java
    private final State<String> display = state("");
    private final State<String> prevDisplay = state("");
    private final State<String> operator = state("");

    private final SceneCalc sceneCalc = new SceneCalc(display, prevDisplay, operator);
    private final SceneCalcCientifica sceneCalcCientifica = new SceneCalcCientifica(
        display,
        prevDisplay,
        operator
    );

    private final State<Boolean> mostrarCientifica = state(false);
    private final State<Component> scenes = state(sceneCalc.scene());

    {
        setTitle("Projeto 6");
        setTheme(BricksTheme.dark());
        setSize(390, 330);
    }

    @Override
    public Component root() {
        return new Column()
            .padding(20)
            .gap(12)
            .modifier(new Modifier().fillMaxWidth())
            .children(
                new Row().children(
                    new Text("Calculadora").fontSize(22.0),
                    new Spacer(),
                    mostrarCientifica.get()
                        ? new Button("Normal").onClick(() -> {
                              scenes.set(sceneCalc.scene());
                              mostrarCientifica.set(false); // volta à normal
                              getStage().setWidth(390);
                              getStage().setHeight(340);
                          })
                        : new Button("Cientifica").onClick(() -> {
                              scenes.set(sceneCalcCientifica.scene());
                              mostrarCientifica.set(true); // vai para científica
                              getStage().setWidth(600);
                              getStage().setHeight(400);
                          })
                ),
                scenes.get()
            );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
