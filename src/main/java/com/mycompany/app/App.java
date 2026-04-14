package com.mycompany.app;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.Text;
import fabiorodrigues.bricks.components.Spacer;
import fabiorodrigues.bricks.core.BricksApplication;
import fabiorodrigues.bricks.core.Component;
import fabiorodrigues.bricks.core.State;
import fabiorodrigues.bricks.style.BricksTheme;

public class App extends BricksApplication {
    {
        setTitle("Projeto 6");
        setTheme(BricksTheme.dark());
    }
    private final SceneCalc sceneCalc = new SceneCalc();
    private final SceneCalcCientifica sceneCalcCientifica = new SceneCalcCientifica();

    private final State<Boolean> mostrarCientifica = state(false);
    private final State<Component> scenes = state(sceneCalc.scene());


    @Override
    public Component root() {
        return new Column().padding(20).gap(12).children(
                new Row().children(
                    new Text("Calculadora").fontSize(22.0),
                    new Spacer(),
                    mostrarCientifica.get() 
                        ? new Button("Normal").onClick(() -> {scenes.set(sceneCalc.scene()); mostrarCientifica.set(true);}) 
                        : new Button("Cientifica").onClick(() -> {scenes.set(sceneCalcCientifica.scene()); mostrarCientifica.set(false);})
                ),
                scenes.get()
        );
    }

    public static void main(String[] args) { launch(args); }
}
