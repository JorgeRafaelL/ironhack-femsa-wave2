package com.jorge.lab7;

import java.util.ArrayList;
import java.util.List;

/*
 * State Change Notification Across System Components: Ensure components are notified about changes in the state of other parts without creating tight coupling.
 * Se optó por el patrón Observador. Este patrón permite que un objeto notifique a otros objetos interesados  sobre cambios en su estado
 * sin que el sujeto conozca directamente a esos observadores. Así, los componentes permanecen desacoplados y pueden reaccionar a los eventos de cambio de estado de manera autónoma.
 * */

// 1. Interfaz Observer (Observador)
interface Observer {

    void update(String estado);

}

// 2. Clase Subject que mantiene la lista de observadores
class OrderManager {

    private final List<Observer> observers = new ArrayList<>();

    private String state;

    // Método para agregar observadores
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Método para eliminar observadores
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Método para notificar cambios a todos los observadores
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    // Método que cambia el estado y notifica a los observadores
    public void changeState(String newState) {
        this.state = newState;
        notifyObserver();
    }

}

// 3. Observadores concretos
class UserInterface implements Observer {

    @Override
    public void update(String estado) {
        System.out.println("Interfaz de Usuario: El estado ha cambiado a " + estado);
    }

}

class InventorySystem implements Observer {

    @Override
    public void update(String estado) {
        System.out.println("Sistema de Inventario: El estado ha cambiado a " + estado);
    }

}

public class StateChangeNotificationAcrossSystemComponents {

    public static void main(String[] args) {
        // Creamos el gestor de pedidos (sujeto)
        OrderManager gestor = new OrderManager();

        // Creamos observadores y los agregamos al gestor de pedidos
        UserInterface interfazUsuario = new UserInterface();
        InventorySystem sistemaInventario = new InventorySystem();

        gestor.addObserver(interfazUsuario);
        gestor.addObserver(sistemaInventario);

        // Cambiamos el estado, lo que notifica automáticamente a los observadores
        gestor.changeState("En Proceso");
        gestor.changeState("Completado");
    }

}
