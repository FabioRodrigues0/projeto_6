package com.mycompany.app;

import com.mycompany.app.scenes.SceneCalc;
import com.mycompany.app.scenes.SceneCalcCientifica;
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

// prompt: passa pelo projeto e adiciona o javadoc
/**
 * Ponto de entrada da aplicação de calculadora.
 * Gere a troca entre a cena normal ({@link SceneCalc}) e a científica ({@link SceneCalcCientifica}).
 */
public class App extends BricksApplication {

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

    /**
     * Constrói a interface raiz com o cabeçalho e a cena activa.
     * O botão de alternância redimensiona a janela conforme o modo seleccionado.
     *
     * @return componente raiz da aplicação
     */
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

    /**
     * Inicia a aplicação JavaFX.
     *
     * @param args argumentos da linha de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}
