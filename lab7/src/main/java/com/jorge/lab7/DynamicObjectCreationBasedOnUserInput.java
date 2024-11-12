package com.jorge.lab7;

/*
 * Dynamic Object Creation Based on User Input: Implement a system to dynamically create various types of user interface elements based on user actions.
 * Se optó por el patrón de diseño Facotry ya que que se usa para manejar y centralizar la creación de objetos.
 * */

// Interfaz base
interface UIElement {

    void render();

}

// Clases concretas de UI Elements
class Button implements UIElement {

    @Override
    public void render() {
        System.out.println("Rendering Button");
    }

}

class TextField implements UIElement {

    @Override
    public void render() {
        System.out.println("Rendering TextField");
    }

}

// Factory para crear elementos de UI basados en el tipo
class UIElementFactory {

    public static UIElement createElement(String elementType) {
        return switch (elementType.toLowerCase()) {
            case "button" -> new Button();
            case "textfield" -> new TextField();
            default -> throw new IllegalArgumentException("Unknown element type");
        };
    }

}

// Uso
public class DynamicObjectCreationBasedOnUserInput {

    public static void main(String[] args) {
        UIElement element = UIElementFactory.createElement("button");
        element.render();
    }

}