package com.jorge.lab7;

/*
Global Configuration Management: Design a system that ensures a single, globally accessible configuration object without access conflicts.
Se optó por el patrón de diseño Singleton. Este patrón asegura que solo haya una única instancia de una clase y que sea accesible de forma global.
*/
public class GlobalConfigurationManagement {
    // Instancia única de la clase
    private static volatile GlobalConfigurationManagement instance;

    // Ejemplo de configuración global
    private String configParam;

    // Constructor privado para evitar instanciación externa
    private GlobalConfigurationManagement() {
        // Inicialización de la configuración global
        this.configParam = "valor inicial";
    }

    // Método de acceso global
    public static GlobalConfigurationManagement getInstance() {
        if (instance == null) {
            synchronized (GlobalConfigurationManagement.class) {
                if (instance == null) {
                    instance = new GlobalConfigurationManagement();
                }
            }
        }
        return instance;
    }

    // Métodos para obtener y establecer configuraciones
    public String getConfigParam() {
        return configParam;
    }

    public void setConfigParam(String configParam) {
        this.configParam = configParam;
    }

}
