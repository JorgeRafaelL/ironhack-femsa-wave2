package com.jorge.lab7;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
 * Efficient Management of Asynchronous Operations: Manage multiple asynchronous operations like API calls which need to be coordinated without blocking the main application workflow.
 * Se optó por el patrón Promise. Este patrón permite manejar operaciones asincrónicas de forma elegante,
 * facilitando la coordinación y composición de múltiples tareas sin bloquear el hilo principal.
 * */

@Slf4j
public class EfficientManagementOfAsynchronousOperations {

    public static void main(String[] args) {
        // Iniciar múltiples operaciones asincrónicas
        CompletableFuture<String> llamadaAPI1 = realizarLlamadaAPI("Endpoint1");
        CompletableFuture<String> llamadaAPI2 = realizarLlamadaAPI("Endpoint2");
        CompletableFuture<String> llamadaAPI3 = realizarLlamadaAPI("Endpoint3");

        // Coordinar todas las operaciones sin bloquear
        CompletableFuture<Void> todasCompletadas = CompletableFuture.allOf(llamadaAPI1, llamadaAPI2, llamadaAPI3);

        // Acción después de que todas las llamadas se completen
        todasCompletadas.thenRun(() -> {
            try {
                String resultado1 = llamadaAPI1.get();
                String resultado2 = llamadaAPI2.get();
                String resultado3 = llamadaAPI3.get();
                log.info("Resultados: {}, {}, {}", resultado1, resultado2, resultado3);
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            }
        });

        // Continuar con otras tareas sin esperar a que las llamadas se completen
        log.info("Operaciones asincrónicas iniciadas.");

        // Opcional: Esperar a que todas las operaciones se completen antes de finalizar la aplicación
        try {
            todasCompletadas.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
    }

    private static CompletableFuture<String> realizarLlamadaAPI(String endpoint) {
        return CompletableFuture.supplyAsync(() -> {
            // Simular una llamada API con retardo
            try {
                Thread.sleep(3000); // Simula el tiempo de respuesta de la API
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            return "Respuesta de " + endpoint;
        });
    }

}
