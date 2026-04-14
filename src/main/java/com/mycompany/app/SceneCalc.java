package com.mycompany.app;

import fabiorodrigues.bricks.components.Button;
import fabiorodrigues.bricks.components.Column;
import fabiorodrigues.bricks.components.Row;
import fabiorodrigues.bricks.components.TextField;
import fabiorodrigues.bricks.core.Component;


public class SceneCalc {

    public Component scene() {    
        return new Column().padding(0).gap(2).children(
                new TextField(),
                new Row().padding(0).gap(2).children(
                    new Button("1"),
                    new Button("2"),
                    new Button("3"),
                    new Button("+")),
                new Row().gap(2).children(
                    new Button("4"),
                    new Button("5"),
                    new Button("6"),
                    new Button("-")),
                new Row().gap(2).children(
                    new Button("7"),
                    new Button("8"),
                    new Button("9"),
                    new Button("*")));
    }
}
